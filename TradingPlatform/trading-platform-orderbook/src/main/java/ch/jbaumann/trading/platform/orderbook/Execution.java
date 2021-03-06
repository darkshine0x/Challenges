package ch.jbaumann.trading.platform.orderbook;

import ch.jbaumann.trading.platform.util.Price;

public class Execution {
	private Price price;
	private TradeOrder sellOrder;
	private TradeOrder buyOrder;
	
	public Execution(Price price, TradeOrder sellOrder, TradeOrder buyOrder) {
		this.price = price;
		this.sellOrder = sellOrder;
		this.buyOrder = buyOrder;
	}

	public Price getPrice() {
		return price;
	}

	public TradeOrder getSellOrder() {
		return sellOrder;
	}

	public TradeOrder getBuyOrder() {
		return buyOrder;
	}
}
