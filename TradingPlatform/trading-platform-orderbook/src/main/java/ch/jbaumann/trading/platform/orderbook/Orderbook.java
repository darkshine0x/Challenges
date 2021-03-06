package ch.jbaumann.trading.platform.orderbook;

import java.util.Collections;
import java.util.LinkedList;

import ch.jbaumann.trading.platform.domain.Share;
import ch.jbaumann.trading.platform.util.Price;

public class Orderbook {
	
	private Share share;
	private LinkedList<TradeOrder> bestBuyOrders;
	private LinkedList<TradeOrder> bestSellOrders;
	private LinkedList<TradeOrder> limitedBuyOrders;
	private LinkedList<TradeOrder> limitedSellOrders;
	private LinkedList<Execution> executions;
	
	public Orderbook(Share share) {
		this.share = share;
		this.bestBuyOrders = new LinkedList<>();
		this.bestSellOrders = new LinkedList<>();
		this.limitedBuyOrders = new LinkedList<>();
		this.limitedSellOrders = new LinkedList<>();
		this.executions = new LinkedList<>();
	}
	
	public Share getShare() {
		return share;
	}

	public LinkedList<TradeOrder> getBestBuyOrders() {
		return new LinkedList<>(bestBuyOrders);
	}

	public LinkedList<TradeOrder> getBestSellOrders() {
		return new LinkedList<>(bestSellOrders);
	}

	public LinkedList<TradeOrder> getLimitedBuyOrders() {
		return new LinkedList<>(limitedBuyOrders);
	}

	public LinkedList<TradeOrder> getLimitedSellOrders() {
		return new LinkedList<>(limitedSellOrders);
	}

	public TradeOrder placeTrade(final Price price, final int amount) {
		if (amount == 0) {
			return null;
		}
		
		TradeOrder newOffer = new TradeOrder(price, amount);
		if (price == null) {
			if (amount < 0) bestSellOrders.add(newOffer);
			else bestBuyOrders.add(newOffer);
		} else {
			if (amount < 0) limitedSellOrders.add(newOffer);
			else limitedBuyOrders.add(newOffer);
		}
		
		return newOffer;
	}
	
	public void removeTrade(final TradeOrder tradeToRemove) {
		if (tradeToRemove.getLimit() == null) {
			if (tradeToRemove.getAmount() < 0) bestSellOrders.remove(tradeToRemove);
			else bestBuyOrders.remove(tradeToRemove);
		} else {
			if (tradeToRemove.getAmount() < 0) limitedSellOrders.remove(tradeToRemove);
			else limitedBuyOrders.remove(tradeToRemove);
		}
	}
	
	public void matchOrders() {
		// Match best orders
		while (bestBuyOrders.size() != 0 && bestSellOrders.size() != 0) {
			this.executeTrade(bestBuyOrders.getFirst(), bestSellOrders.getFirst());
		}
		
		// Fill rest of best orders
		Collections.sort(limitedBuyOrders, Collections.reverseOrder());
		Collections.sort(limitedSellOrders);
		
		while (bestBuyOrders.size() != 0 && limitedSellOrders.size() != 0) {
			this.executeTrade(limitedSellOrders.getFirst() , bestBuyOrders.getFirst());
		}
		
		while (bestSellOrders.size() != 0 && limitedBuyOrders.size() != 0) {
			this.executeTrade(bestSellOrders.getFirst(), limitedBuyOrders.getFirst());
		}
		
		// Fill limited orders
		if (limitedBuyOrders.size() == 0 || limitedSellOrders.size() == 0) {
			// No more orders to fill
			return;
		}
		
		while (limitedBuyOrders.size() == 0 || limitedSellOrders.size() == 0) {
			if (limitedBuyOrders.getFirst().compareTo(limitedBuyOrders.getFirst()) == 0) {
				this.executeTrade(limitedSellOrders.getFirst(), limitedBuyOrders.getFirst());
			} else {
				return;
			}
		}
	}
	
	private void executeTrade(final TradeOrder sellOrder, final TradeOrder buyOrder) {
		Price executionPrice = share.getPrice();
		if (sellOrder.getLimit() != null) {
			executionPrice = sellOrder.getLimit();
		} else if (buyOrder.getLimit() != null) {
			executionPrice = buyOrder.getLimit();
		}
		share.setPrice(executionPrice);
		
		// Reduce amount if no full execution
		if (-sellOrder.getAmount() > buyOrder.getAmount()) {
			sellOrder.setAmount(sellOrder.getAmount() + buyOrder.getAmount());
			this.removeTrade(buyOrder);
			Execution execution = new Execution(executionPrice, new TradeOrder(sellOrder.getLimit(), buyOrder.getAmount()), buyOrder);
			executions.add(execution);
			return;
			
		} 
		if (-sellOrder.getAmount() < buyOrder.getAmount()) {
			buyOrder.setAmount(buyOrder.getAmount() + sellOrder.getAmount());
			this.removeTrade(sellOrder);
			Execution execution = new Execution(executionPrice, sellOrder, new TradeOrder(buyOrder.getLimit(), sellOrder.getAmount()));
			executions.add(execution);
			return;
		}
		
		Execution execution = new Execution(executionPrice, sellOrder, buyOrder);
		this.removeTrade(sellOrder);
		this.removeTrade(buyOrder);
		executions.add(execution);
	}
}
