package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class ODLFeeCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	private StudentRecord record;
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public ODLFeeCalculator(StudentRecord Record) {
		this.record = Record;
	}
	
	/**
	 * Add entries and balance into the Map with a helper method called calculateFee()
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		calculateFee();
		return entries;
	}

	/**
	 * Add Online Distance Learning Fees into ReportItem with category
	 * "General Fees" and name "ODL Fee". This was then added into Map<FeeName: String,
	 * ReportItem> based on the total registered credit of ODL courses from StudentRecord
	 */
	private void calculateFee() {
		if (CalculatorUtil.calculateODLCredit(record) > 1) {
			amount = new BigDecimal(90);
		} else {
			amount = new BigDecimal(0);
		}
		ReportItem result = new ReportItem("ODL Fee", "General Fees", amount);
		this.entries.put(result.getName(), result);

	}
	
	/** 
	 * Return the total amount of result of calculation.
	 * @return total amount of the calculation result
	 */
	@Override
	public BigDecimal getAmount() {
		return amount;
	}
}
