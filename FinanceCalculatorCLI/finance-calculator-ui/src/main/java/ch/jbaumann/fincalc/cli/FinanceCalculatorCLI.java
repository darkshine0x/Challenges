package ch.jbaumann.fincalc.cli;

import java.util.Scanner;

import ch.jbaumann.fincalc.api.FinanceCalculator;
import ch.jbaumann.fincalc.factory.CalculatorFactory;

/**
 * This class represents  the console program where a user can calculate financial metrics.
 * 
 * @author Jan Baumann
 * @version 25.02.2021
 *
 */
public class FinanceCalculatorCLI {

	private static boolean isRunning;
	private static FinanceCalculator calculator;
	
	static {
		isRunning = true;
		calculator = CalculatorFactory.createCalculator();
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		printMenu();
		while (isRunning) {
			int metric;
			while (true) {
				try {
					System.out.println();
					System.out.print("Nummer [bspw. 2]: ");
					metric = Integer.parseInt(scanner.nextLine()); 
					break;
				} catch (NumberFormatException n) {
					System.out.println("Bitte ganze Zahl eingeben.");
				}
			}
			
			while(true) {
				try {
					switch (metric) {
					case 1:
						double netOperatingAssets = parse(scanner, "Net Operating Assets [bspw. 300.5]: ");
						double ebit = parse(scanner, "Ebit [bspw. 400.2]: ");
						double taxOnEarningsRonoa = parse(scanner, "Gewinnsteuersatz [bspw. 0.18]: ");
						double resultRonoa = calculator.ronoa(netOperatingAssets, ebit, taxOnEarningsRonoa);
						System.out.println("RONOA = " + resultRonoa);
						break;
						
					case 2:
						double costOfDebtRate = parse(scanner, "Cost of debt rate [bspw. 0.0385]: ");
						double taxOnEarningsWacc = parse(scanner, "Gewinnsteuersatz [bspw. 0.15]: ");
						double debt = parse(scanner, "Verzinsliches Fremdkapital [bspw. 500000.50]: ");
						double equityCapital = parse(scanner, "Eigenkapital [bspw. 5565560.50]: ");
						double costOfEquityCapital = parse(scanner, "Eigenkapitalkostensatz [bspw. 0.1010]: ");
						double resultWacc = calculator.wacc(costOfDebtRate, taxOnEarningsWacc, debt, equityCapital, costOfEquityCapital);
						System.out.println("WACC = " + resultWacc);
						break;
						
					case 3:
						System.out.print("Free Cashflows [bspw. -1000, 100.50, 300, 300.50]: ");
						String freeCashflowsInput = scanner.nextLine();
						freeCashflowsInput = freeCashflowsInput.trim();
						String[] singleCashflowStrings=  freeCashflowsInput.split(",");
						double[] freeCashflows = new double[singleCashflowStrings.length];
						for (int i = 0; i < singleCashflowStrings.length; i++) {
							freeCashflows[i] = Double.parseDouble(singleCashflowStrings[i]);
						}
						double resultIrr = calculator.irr(freeCashflows);
						System.out.println("IRR = " + resultIrr);
						break;
						
					case 4:
						isRunning = false;
						break;
					}
					break;
				} catch (NumberFormatException n) {
					System.out.println("Bitte Zahlen in korrektem Format eingeben.");
				}
			}
		}
		scanner.close();
		System.out.println("Programm wird beendet.");
	}
	
	private static double parse(Scanner scanner, String inputText) {
		System.out.print(inputText);
		double result = Double.parseDouble(scanner.nextLine());
		System.out.println();
		return result;
	}
	
	private static void printMenu() {
		System.out.println("[1] RONOA [2] WACC [3] IRR [4] Quit");
	}
}
