package ch.jbaumann.fincalc.api;

/**
 * This interface provides the required methods for calculator for financial metrics. 
 * 
 * author Jan Baumann
 * @version 25.02.2021
 *
 */
public interface FinanceCalculator {

	/**
	 * Calculates the Return On Net Operating Assets (RONOA).
	 * 
	 * @param nopat Net Operating Assets
	 * @param ebit Earnings before interest and taxes
	 * @param taxOnEarnings Tax rate on earnings
	 * @return RONOA
	 */
	double ronoa(final double netOperatingAssets, final double ebit, final double taxOnEarnings);

	/**
	 * Calculates the Weighted Average Cost of Capital (WACC).
	 * 
	 * @param costOfDebt Cost of debt rate
	 * @param taxOnEarnings Tax rate on earnings
	 * @param debt Debt according to the balance sheet
	 * @param equityCapital Equity capital according to the balance sheet
	 * @param costOfEquityCapital Cost of equity capital rate
	 * @return WACC
	 */
	double wacc(final double costOfDebt, final double taxOnEarnings, final double debt, final double equityCapital, final double costOfEquityCapital);

	/**
	 * Calculates the Internal Rate of Return (IRR) based on the free cash flows.
	 * 
	 * @param freeCashFlows Freecashflows (entity level)
	 * @return IRR
	 */
	double irr(final double[] freeCashFlows);
}
