package ch.jbaumann.fincalc.impl;

import ch.jbaumann.fincalc.api.FinanceCalculator;
import ch.jbaumann.fincalc.factory.CalculatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FinanceCalculatorImplTest {

	private static FinanceCalculator calculator;
	
	@BeforeAll
	private static void setup() {
		calculator = CalculatorFactory.createCalculator();
	}
	
	@Test
	public void ronoaTest() {
		// Arrange
		double netOperatingAssets = 66568301;
		double ebit = 3483160;
		double taxOnEarnings = 0.18;
		
		// Act
		double result = calculator.ronoa(netOperatingAssets, ebit, taxOnEarnings);
		
		// Assert
		Assertions.assertEquals(0.0429, result, 0.001);
	}
	
	@Test
	public void waccTest() {
		// Arrange
		double costOfDebt = 0.0385; 
		double taxOnEarnings = 0.15; 
		double debt = 105;
		double equityCapital = 350; 
		double costOfEquityCapital = 0.101;
		
		// Act
		double result = calculator.wacc(costOfDebt, taxOnEarnings, debt, equityCapital, costOfEquityCapital);
		
		// Assert
		Assertions.assertEquals(0.0852, result, 0.001);
	}
	
	@Test
	public void irrTest() {
		// Arrange
		double[] freeCashflows = {
			-121052000,
			7488000,
			10059500,
			11918250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			13018250,
			18002250
		};

		// Act
		double result = calculator.irr(freeCashflows);

		// Assert
		Assertions.assertEquals(0.084, result, 0.001);
	}
}