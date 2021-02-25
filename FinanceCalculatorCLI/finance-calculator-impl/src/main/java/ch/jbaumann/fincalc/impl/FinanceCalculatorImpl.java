package ch.jbaumann.fincalc.impl;

import ch.jbaumann.fincalc.api.FinanceCalculator;

/**
 * Implemenation of the {@link FinanceCalculator} interface.
 * 
 * @author Jan Baumann
 * @version 24.02.2021
 *
 */
public class FinanceCalculatorImpl implements FinanceCalculator {

	@Override
	public double ronoa(final double netOperatingAssets, final double ebit, final double taxOnEarnings) {
		if (netOperatingAssets <= 0) {
			throw new ArithmeticException("The Net Operating Assets have to be greater than 0.");
		}
		double nopat = ebit * (1 - taxOnEarnings); 
		return nopat / netOperatingAssets;
	}

	@Override
	public double wacc(final double costOfDebt, final double taxOnEarnings, final double debt, final double equityCapital, final double costOfEquityCapital) {
		return costOfDebt * (1 - taxOnEarnings) * (debt / (debt + equityCapital)) + costOfEquityCapital * (equityCapital / (debt + equityCapital));
	}

	@Override
	public double irr(final double[] freeCashflows) {
		double maxDelta = 100;
		double firstGuess = 0.1;
		double npv = npv(freeCashflows, firstGuess);
		while (!(npv > - maxDelta && npv < maxDelta)) {
			double nextGuess;
			if (npv > 0) {
				nextGuess = firstGuess + 0.1;
			}
			else {
				nextGuess = firstGuess - 0.1;
			}
			// Interpolation
			double secondNpv = npv(freeCashflows, nextGuess);
			firstGuess = firstGuess - npv / (secondNpv - npv) * (nextGuess - firstGuess);
			npv = npv(freeCashflows, firstGuess);
		}
		return firstGuess;
	}

	private double npv(final double[] freeCashflows, final double guess) {
		double result = 0;
		for (int i = 0; i < freeCashflows.length; i++) {
			double intermediateResult = freeCashflows[i] / Math.pow((1 + guess), i);
			result += intermediateResult;
		}
		return result;
	}
}
