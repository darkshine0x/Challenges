package ch.jbaumann.trading.platform.orderbook;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.jbaumann.trading.platform.domain.Share;
import ch.jbaumann.trading.platform.util.Price;

public class OrderbookTest {

	@Test
	void testMatchOrders() {
		// Arrange
		Share novartis = new Share("Novartis AG", "NOVN", "CH0012005267", BigDecimal.valueOf(0.5));
		
		Orderbook orderbook = new Orderbook(novartis);
		orderbook.placeTrade(new Price(100, Currency.getInstance("CHF")), 10);
		orderbook.placeTrade(new Price(101, Currency.getInstance("CHF")), 20);
		orderbook.placeTrade(null, -30);
		orderbook.placeTrade(new Price(103, Currency.getInstance("CHF")), -5);
		orderbook.placeTrade(null, -10);
		
		// Act
		orderbook.matchOrders();
		
		// Assert
		Assertions.assertEquals(1, orderbook.getBestSellOrders().size());
		Assertions.assertEquals(1, orderbook.getLimitedSellOrders().size());
		Assertions.assertEquals(-10, orderbook.getBestSellOrders().getFirst().getAmount());
		Assertions.assertEquals(-5, orderbook.getLimitedSellOrders().getFirst().getAmount());
	}

}
