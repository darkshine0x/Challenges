package ch.jbaumann.trading.platform.util;

import java.math.BigDecimal;
import java.util.Currency;

public class Price {
	private BigDecimal value;
	private Currency currency;
	
	public Price(double value, Currency currency) {
		this.value = BigDecimal.valueOf(value);
		this.currency = currency;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
