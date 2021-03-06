package ch.jbaumann.trading.platform.domain;

import java.math.BigDecimal;

public class Share extends Tradeable {

	private BigDecimal nominalValue;
	
	public Share(String name, String tickerSymbol, String isin, BigDecimal nominalValue) {
		super(name, tickerSymbol, isin);
		this.nominalValue = nominalValue;
	}

	public BigDecimal getNominalValue() {
		return this.nominalValue;
	}
	
	public void setNominalValue(final BigDecimal nominalValue) {
		this.nominalValue = nominalValue;
	}
}
