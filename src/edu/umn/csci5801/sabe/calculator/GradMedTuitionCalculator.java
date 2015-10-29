package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class GradMedTuitionCalculator implements CalculatorIntf {

	private BigDecimal amount;
	private StudentRecord record;
	private final int CURRENT_YEAR = 2015;
	private final String TUITION_NAME = "Tuition";

	private final List<BigDecimal> RESIDENT_TUITION_LIST = Arrays.asList(
			new BigDecimal(12624.0), new BigDecimal(12624.0), new BigDecimal(
					12376.0), new BigDecimal(11900.0), new BigDecimal(11366.0),
			new BigDecimal(10859.0));
	private final List<BigDecimal> NONRESIDENT_TUITION_LIST = Arrays.asList(
			new BigDecimal(16859), new BigDecimal(16859), new BigDecimal(
					16263.0), new BigDecimal(15489.0), new BigDecimal(14456.0),
			new BigDecimal(13495.0));
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public GradMedTuitionCalculator(StudentRecord record) {
		this.record = record;
	}
	
	/**
	 * Update the local variable "amount" by calculating student tuition, and
	 * add the entries and balance into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		int year = calculateYear();
		BigDecimal tuition = null;
		if (record.isMinnesotaResident()) {
			tuition = RESIDENT_TUITION_LIST.get(year);
		} else {
			tuition = NONRESIDENT_TUITION_LIST.get(year);
		}
		amount = tuition;
		ReportItem reportItem = new ReportItem("Tuition", "Tuition", amount);
		Map<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		itemMap.put(TUITION_NAME, reportItem);
		return itemMap;
	}
	
	/**
	 * Return the total amount of result of calculation.
	 * @return total amount of the calculation result
	 */
	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * calculate which year the student is, only for med graduate.
	 * @return the years the student in.
	 */
	private int calculateYear() {
		int admitedYear = record.getSemesterAdmitted().getYear();
		int year = CURRENT_YEAR - admitedYear;
		if (year < 0 || year > 5) {
			throw new IndexOutOfBoundsException();
		}
		return year;
	}
}
