package ch.jbaumann.trading.platform.orderbook;

import java.time.LocalDateTime;

import ch.jbaumann.trading.platform.util.Price;

public class TradeOrder implements Comparable<TradeOrder> {
	
	private Price limit;
	private int amount;
	private LocalDateTime placementTime;
	
	public TradeOrder(Price price, int amount) {
		this.limit = price;
		this.amount = amount;
		this.placementTime = LocalDateTime.now();	
	}
	
	public TradeOrder(final int amount) {
		this.amount = amount;
	}

	public Price getLimit() {
		return limit;
	}

	public void setLimit(final Price price) {
		this.limit = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}

	public LocalDateTime getPlacementTime() {
		return placementTime;
	}

	@Override
	public int compareTo(final TradeOrder o) {
		return this.limit.getValue().compareTo(o.getLimit().getValue());
	}
}
