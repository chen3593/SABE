package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.Term;

/*Created by Raoyin Chen*/

public class GradCSECreditCalculator implements CalculatorIntf {
	//Calculate CSE graduate student's tuition by price per credit
	
	private BigDecimal amount;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	private StudentRecord record;
	private final BigDecimal RESIDENT_FINANCIAL_MATHEMATICS_PER_CREDIT = new BigDecimal(
			865.00);
	private final BigDecimal NON_RESIDENT_FINANCIAL_MATHEMATICS_PER_CREDIT = new BigDecimal(
			865.00);
	private final BigDecimal NON_RESIDENT_LATE_FINANCIAL_MATHEMATICS_PER_CREDIT = new BigDecimal(
			1065.00);
	private final BigDecimal MANAGEMENT_TECHNOLOGY_PER_CREDIT = new BigDecimal(
			1836.00);
	private final BigDecimal SECURITY_TECHNOLOGY_PER_CREDIT = new BigDecimal(
			1060.00);
	private final BigDecimal MEDICAL_DEVICE_INNOVATION_PER_CREDIT = new BigDecimal(
			1103.00);
	private final BigDecimal OTHER_PARTTIME_MN = new BigDecimal(1288.16);
	private final BigDecimal OTHER_FULLTIME_MN_6_TO_14 = new BigDecimal(7729.00);
	private final BigDecimal OTHER_FULLTIME_MN_EACH_OVER14 = new BigDecimal(
			1288.16);
	private final BigDecimal OTHER_PARTTIME_NONMN = new BigDecimal(1973.33);
	private final BigDecimal OTHER_FULLTIME_NONMN_6_TO_14 = new BigDecimal(
			11840.00);
	private final BigDecimal OTHER_FULLTIME_NONMN_EACH_OVER14 = new BigDecimal(
			1973.33);
	
	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public GradCSECreditCalculator(StudentRecord studentRecord) {
		this.record = studentRecord;
		amount = BigDecimal.ZERO;
	}

	/**
	 * Calculate the entries and balance for a students majors, stored in a
	 * HashMap
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		int totalCredit = CalculatorUtil.calculateTotalCredit(record);
		switch (record.getDegreeEnrolledIn().getMajor()) {
		case FINANCIAL_MATHEMATICS:
			if (record.isMinnesotaResident()) {
				amount = amount.add(RESIDENT_FINANCIAL_MATHEMATICS_PER_CREDIT
						.multiply(new BigDecimal(totalCredit)));
			} else if (!record.isMinnesotaResident()
					&& isAdmittedAfter2012Fall()) {
				amount = amount
						.add(NON_RESIDENT_LATE_FINANCIAL_MATHEMATICS_PER_CREDIT
								.multiply(new BigDecimal(totalCredit)));
			} else {
				amount = amount
						.add(NON_RESIDENT_FINANCIAL_MATHEMATICS_PER_CREDIT
								.multiply(new BigDecimal(totalCredit)));
			}
			break;
		case MANAGEMENT_TECHNOLOGY:
			amount = amount.add(MANAGEMENT_TECHNOLOGY_PER_CREDIT
					.multiply(new BigDecimal(totalCredit)));
			break;
		case SECURITY_TECHNOLOGY:
			amount = amount.add(SECURITY_TECHNOLOGY_PER_CREDIT
					.multiply(new BigDecimal(totalCredit)));
			break;
		case MEDICAL_DEVICE_INNOVATION:
			amount = amount.add(MEDICAL_DEVICE_INNOVATION_PER_CREDIT
					.multiply(new BigDecimal(totalCredit)));
			break;
		default:
			amount = calculateOtherTuition();
		}
		ReportItem result = new ReportItem("Tuition", "Tuition", amount);
		entries.put(result.getName(), result);
		return entries;
	}
	
	/**
	 * Return if student is admitted after 2013 Fall
	 * @return a boolean indicate if a student is admitted after 2013 Fall
	 */
	private boolean isAdmittedAfter2012Fall() {
		boolean enroll2012Fall = record.getSemesterAdmitted().getYear() == 2012
				&& record.getSemesterAdmitted().getTerm() == Term.FALL;
		boolean enrollAfter = record.getSemesterAdmitted().getYear() > 2012;

		return enroll2012Fall || enrollAfter;
	}

	/**
	 * Helper method, to calculate the entries and balance for students with
	 * other general majors
	 * @return tuition amount for regular majors
	 */
	private BigDecimal calculateOtherTuition() {
		int credit = CalculatorUtil.calculateTotalCredit(record);
		BigDecimal result = null;
		if (isPartTime() && isMNResident()) {
			result = OTHER_PARTTIME_MN.multiply(new BigDecimal(credit));
		} else if (isPartTime() && !isMNResident()) {
			result = OTHER_PARTTIME_NONMN.multiply(new BigDecimal(credit));
		} else if (!isPartTime()) {
			BigDecimal tuition6To14 = null;
			BigDecimal tuitionPerCreditMoreThan14 = null;
			if (isMNResident()) {
				tuition6To14 = OTHER_FULLTIME_MN_6_TO_14;
				tuitionPerCreditMoreThan14 = OTHER_FULLTIME_MN_EACH_OVER14;
			} else {
				tuition6To14 = OTHER_FULLTIME_NONMN_6_TO_14;
				tuitionPerCreditMoreThan14 = OTHER_FULLTIME_NONMN_EACH_OVER14;
			}
			BigDecimal totalTuitionOver14 = BigDecimal.ZERO;
			result = tuition6To14;
			if (credit > 14) {
				totalTuitionOver14.add(tuitionPerCreditMoreThan14
						.multiply(new BigDecimal(credit - 14)));
			}

			result.add(totalTuitionOver14);
		}
		return result;
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
	 * Return if student is part-time student
	 * @return a boolean indicate if a student is a part time student
	 */
	private boolean isPartTime() {
		return CalculatorUtil.calculateTotalCredit(record) < 6;
	}
	
	/**
	 * Return if student is a student is Minnesota resident
	 * @return a boolean indicate if a student is Minnesota resident
	 */
	private boolean isMNResident() {
		return record.isMinnesotaResident();
	}

}
