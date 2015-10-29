package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class CSECollegeFeeCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private StudentRecord record;
	private BigDecimal COLLEGIATE_FEE_FULLTIME = new BigDecimal(300);
	private BigDecimal COLLEGIATE_FEE_PARTTIME = new BigDecimal(150);
	private final String NAME = "Collegiate Fee";
	private final String CATEGORY = "College and Program Fee";
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public CSECollegeFeeCalculator(StudentRecord record) {
		this.record = record;
	}
	
	/**
	 * Update the local variable "amount" by checking if the student is full time or not, and
	 * CSE college fee's name, category and amount was added into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		int credit = CalculatorUtil.calculateTotalCredit(record);
		if (isFullTime(credit)) {
			amount = COLLEGIATE_FEE_FULLTIME;
		} else {
			amount = COLLEGIATE_FEE_PARTTIME;
		}
		ReportItem collegiateFeeItem = new ReportItem(NAME, CATEGORY, amount);
		Map<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		itemMap.put(NAME, collegiateFeeItem);
		return itemMap;
	}

	/**
	 * Return if student is full-time student
	 * @param credit the student registered
	 * @return a boolean indicate if a student is a full time student
	 */
	public boolean isFullTime(int credit) {
		return credit >= 6;
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
