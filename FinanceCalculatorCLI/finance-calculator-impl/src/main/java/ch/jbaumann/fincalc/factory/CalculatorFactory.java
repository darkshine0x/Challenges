package ch.jbaumann.fincalc.factory;

import ch.jbaumann.fincalc.api.FinanceCalculator;
import ch.jbaumann.fincalc.impl.FinanceCalculatorImpl;

/**
 * This factory is responsible for providing new instances of finance calculators.
 * @author Jan Baumann
 * @version 24.02.2021
 */
public class CalculatorFactory {

	/**
	 * No instances of this class allowed.
	 */
	private CalculatorFactory() {

	}

	/**
	 * Creates a new instance of a finance calculator.
	 * @return {@link FinanceCalculator}
	 */
	public static FinanceCalculator createCalculator() {
		return new FinanceCalculatorImpl();
	}
}
