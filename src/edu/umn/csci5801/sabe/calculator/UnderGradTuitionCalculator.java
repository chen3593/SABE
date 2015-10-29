package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class UnderGradTuitionCalculator implements CalculatorIntf {

	private BigDecimal amount;
	private StudentRecord record;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	private final BigDecimal RESIDENT_TUITION_PER_CREDIT = new BigDecimal(
			463.85);
	private final BigDecimal NONRESIDENT_TUITION_PER_CREDIT = new BigDecimal(
			742.69);
	private final BigDecimal RESIDENT_TUITION_FULLTIME = new BigDecimal(6030.00);
	private final BigDecimal NONRESIDENT_TUITION_FULLTIME = new BigDecimal(
			9655.00);
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public UnderGradTuitionCalculator(StudentRecord Record) {
		this.record = Record;
	}
	
	/**
	 * Add entries and balance into the Map with a helper method called calculateTuition()
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		calculateTuition();
		return entries;
	}

	/**
	 * Calculate the tuition amount for undergraduate students and add it into
	 * ReportItem with category "Tuition" and name "Tuition"
	 */
	private void calculateTuition() {
		boolean isFulltime = true;
		boolean isResident = true;
		int totalCredit = CalculatorUtil.calculateTotalCredit(record);
		if (totalCredit >= 13) {
			isFulltime = true;
		} else {
			isFulltime = false;
		}
		isResident = record.isMinnesotaResident();
		if (isFulltime && isResident) {
			this.amount = RESIDENT_TUITION_FULLTIME;
		} else if (isFulltime && !isResident) {
			this.amount = NONRESIDENT_TUITION_FULLTIME;
		} else if (!isFulltime && isResident) {
			this.amount = RESIDENT_TUITION_PER_CREDIT.multiply(new BigDecimal(
					totalCredit));
		} else {
			this.amount = NONRESIDENT_TUITION_PER_CREDIT
					.multiply(new BigDecimal(totalCredit));
		}
		ReportItem result = new ReportItem("Tuition", "Tuition", amount);
		entries.put(result.getName(), result);
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
