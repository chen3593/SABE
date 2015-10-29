package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.Major;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class GradCSETermCalculator implements CalculatorIntf {
	//Calculate CSE graduate student's tuition by per semester
	
	private BigDecimal amount;
	private StudentRecord record;
	private final BigDecimal SOFTWARE_ENGINEERING = new BigDecimal(8050);
	private final BigDecimal MANAGEMENT_TECH = new BigDecimal(16525);
	private final BigDecimal INVALID = new BigDecimal(1);
	private final String NAME = "Tuition";
	private final String CATEGORY = "Tuition";
	
	/**
	 * Update the local variable "amount" by checking if the student's major is software engineering or management technology, and
	 * add the entries and balance into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		if (isSoftwareEngineering()) {
			amount = SOFTWARE_ENGINEERING;
		} else if (isMangamentTechnology()) {
			amount = MANAGEMENT_TECH;
		} 
		ReportItem tuitionItem = new ReportItem(NAME, CATEGORY, amount);
		Map<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		itemMap.put(NAME, tuitionItem);
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
	 * Return if student is software engineering student
	 * @return a boolean indicate if a student is a software engineering student
	 */
	private boolean isSoftwareEngineering() {
		return record.getDegreeEnrolledIn().getMajor() == Major.SOFTWARE_ENGINEERING;
	}
	
	/**
	 * Return if student is management of technology student
	 * @return a boolean indicate if a student is a management of technology student
	 */
	private boolean isMangamentTechnology() {
		return record.getDegreeEnrolledIn().getMajor() == Major.MANAGEMENT_TECHNOLOGY;
	}
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public GradCSETermCalculator(StudentRecord record) {
		this.record = record;
	}
}
