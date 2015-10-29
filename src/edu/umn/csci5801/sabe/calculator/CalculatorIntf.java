package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;

public interface CalculatorIntf {
	/**
	 * With the studentRecord passed in the calculator, calculate and return the
	 * Map<FeeName: String, ReportItem> and total amount for the local
	 * calculator. return the map as result
	 * @return Map of name and reportItem.
	 */
	public Map<String, ReportItem> calculateEntries();

	/**
	 * Return the total amount of result of calculation.
	 * @return total amount of the calculation result
	 */
	public BigDecimal getAmount();
}
