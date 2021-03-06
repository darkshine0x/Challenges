package ch.jbaumann.trading.platform.domain;

import ch.jbaumann.trading.platform.util.Price;

public abstract class Tradeable {
	
	private String name;
	private String tickerSymbol;
	private String isin;
	private Price price;
	
	public Tradeable(String name, String tickerSymbol, String isin) {
		this.name = name;
		this.tickerSymbol = tickerSymbol;
		this.isin = isin;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTickerSymbol() {
		return this.tickerSymbol;
	}

	public String getIsin() {
		return this.isin;
	}
	
	public Price getPrice() {
		return this.price;
	}
	
	public void setPrice(final Price price) {
		this.price = price;
	}
}
