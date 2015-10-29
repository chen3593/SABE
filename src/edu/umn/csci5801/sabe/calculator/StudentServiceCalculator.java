/*
 * Created by Raoyin Chen 
 */
package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class StudentServiceCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	private StudentRecord record;

	/**
	 * Constructor 
	 * @param studentRecord
	 *            student record
	 */
	public StudentServiceCalculator(StudentRecord Record) {
		this.record = Record;
	}

	/**
	 * Add entries and balance into the Map with a helper method called
	 * calculateFee()
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		calculateFee();
		return entries;
	}

	/**
	 * Helper method that update the local variable "amount" and add the student
	 * service entries and balance into the Map
	 */
	private void calculateFee() {
		if (CalculatorUtil.calculateTotalCredit(record) > 6) {
			amount = new BigDecimal(417.91);
		} else {
			amount = new BigDecimal(0);
		}
		ReportItem result = new ReportItem("Student Service Fees",
				"General Fees", amount);
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
