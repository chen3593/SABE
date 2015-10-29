package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.DegreeProgram;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class InternationalFeeCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private StudentRecord record;
	private BigDecimal INTERNATIONAL_SPON_STUDENT = new BigDecimal(300.00);
	private BigDecimal INTERNATIONAL_STUDENT_AID = new BigDecimal(14.00);
	private BigDecimal INTERNATIONAL_ADMIN = new BigDecimal(145.00);
	private BigDecimal INTER_STUDENT_ACADEMIC_SVR_FEE6Plus = new BigDecimal(
			125.00);
	private BigDecimal INTER_STUDENT_ACADEMIC_SVR_FEE6Minus = new BigDecimal(
			62.50);
	private final String INTERNATIONAL_SPON_STUDENT_NAME = "International Sponsored Student Fee";
	private final String INTERNATIONAL_STUDENT_AID_NAME = "International Student Aid Fee";
	private final String INTERNATIONAL_ADMIN_NAME = "International Student Administrative Fee";
	private final String INTER_STUDENT_ACADEMIC_SVR_NAME = "International Student Academic Services Fee";
	private final String INTERNATIONAL_CATEGORY = "General Fees";

	/**
	 * Update the local variable "amount" by adding up all the international
	 * student fees, and add the entries and balance into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		amount = INTERNATIONAL_SPON_STUDENT.add(INTERNATIONAL_STUDENT_AID);
		amount = amount.add(INTERNATIONAL_ADMIN);

		ReportItem sponsoredStudentFeeItem = new ReportItem(
				INTERNATIONAL_SPON_STUDENT_NAME, INTERNATIONAL_CATEGORY,
				INTERNATIONAL_SPON_STUDENT);
		ReportItem studentAidItem = new ReportItem(
				INTERNATIONAL_STUDENT_AID_NAME, INTERNATIONAL_CATEGORY,
				INTERNATIONAL_STUDENT_AID);
		ReportItem adminFeeItem = new ReportItem(INTERNATIONAL_ADMIN_NAME,
				INTERNATIONAL_CATEGORY, INTERNATIONAL_ADMIN);

		Map<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		itemMap.put(INTERNATIONAL_SPON_STUDENT_NAME, sponsoredStudentFeeItem);
		itemMap.put(INTERNATIONAL_STUDENT_AID_NAME, studentAidItem);
		itemMap.put(INTERNATIONAL_ADMIN_NAME, adminFeeItem);
		if (isUndergrade()) {
			int credit = CalculatorUtil.calculateTotalCredit(record);
			BigDecimal studentAcademicAmount = null;
			if (credit < 6) {
				studentAcademicAmount = INTER_STUDENT_ACADEMIC_SVR_FEE6Minus;
			} else {
				studentAcademicAmount = INTER_STUDENT_ACADEMIC_SVR_FEE6Plus;
			}
			amount = amount.add(studentAcademicAmount);
			ReportItem studentAcademicItem = new ReportItem(
					INTER_STUDENT_ACADEMIC_SVR_NAME, INTERNATIONAL_CATEGORY,
					studentAcademicAmount);
			itemMap.put(INTER_STUDENT_ACADEMIC_SVR_NAME, studentAcademicItem);

		}
		return itemMap;
	}
	
	/**
	 * Return if student is undergraduate
	 * @return a boolean indicate if a student is undergraduate
	 */
	private boolean isUndergrade() {
		return record.getDegreeEnrolledIn().getProgram() == DegreeProgram.BACHELOR;
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
	 * Constructor
	 * @param studentRecord student record
	 */
	public InternationalFeeCalculator(StudentRecord record) {
		this.record = record;
	}
}
