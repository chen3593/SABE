package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.DegreeProgram;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class CommonFeeCalculator implements CalculatorIntf {
	private StudentRecord record;
	private BigDecimal amount;

	/* For Undergraduate student */
	private final BigDecimal CAPITAL_ENHANCEMENT_FEE = new BigDecimal(75);
	private final BigDecimal MINNESOTA_STUDENT_ASSOCIATION_FEE = new BigDecimal(
			2.86);
	private final BigDecimal STADIUM_FEE = new BigDecimal(12.50);
	private final BigDecimal STUDENT_HEALTH_BENEFICIAL_PLAN_FEE = new BigDecimal(
			1049);
	private final BigDecimal TRANSPORTATION_FEE = new BigDecimal(20);
	private final String CAPITAL_ENHANCEMENT_NAME = "Capital Enhancement Fees";
	private final String MINNESOTA_STUDENT_ASSOCIATION_FEE_NAME = "Minnesota Student Association";
	private final String STADIUM_FEE_NAME = "Stadium Fee";
	private final String STUDENT_HEALTH_BENEFICIAL_PLAN_NAME = "Student Health Benefit Plan";
	private final String TRANSPORTATION_FEE_NAME = "Transportation Fee";

	/* For Graduate student Only */
	private final BigDecimal GRADUATE_PROFESSIONAL_FEE = new BigDecimal(6.68);
	private final String GRADUATE_PROFESSIONAL_FEE_NAME = "Graduate & Professional Student Assembly";
	private final String CATEGORY = "General Fees";

	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public CommonFeeCalculator(StudentRecord studentRecord) {
		this.record = studentRecord;
	}

	/**
	 * Update the local variable "amount" by adding up the all common fees, and
	 * each common fee item's name, category and amount was added into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		amount = new BigDecimal(0);
		Map<String, ReportItem> entries = new HashMap<String, ReportItem>();

		// Handle capital enhancement fee item
		ReportItem capitalEnhancementItem = new ReportItem(
				CAPITAL_ENHANCEMENT_NAME, CATEGORY, CAPITAL_ENHANCEMENT_FEE);
		entries.put(CAPITAL_ENHANCEMENT_NAME, capitalEnhancementItem);
		amount = amount.add(CAPITAL_ENHANCEMENT_FEE);

		// Handle stadium fee item
		ReportItem stadiumFeeItem = new ReportItem(STADIUM_FEE_NAME, CATEGORY,
				STADIUM_FEE);
		entries.put(STADIUM_FEE_NAME, stadiumFeeItem);
		amount = amount.add(STADIUM_FEE);

		// Handle student health insurance fee item
		ReportItem studentHealthBeneficialPlanItem = new ReportItem(
				STUDENT_HEALTH_BENEFICIAL_PLAN_NAME, CATEGORY,
				STUDENT_HEALTH_BENEFICIAL_PLAN_FEE);
		entries.put(STUDENT_HEALTH_BENEFICIAL_PLAN_NAME,
				studentHealthBeneficialPlanItem);
		amount = amount.add(STUDENT_HEALTH_BENEFICIAL_PLAN_FEE);

		// Handle transportation fee item
		ReportItem transportationItem = new ReportItem(TRANSPORTATION_FEE_NAME,
				CATEGORY, TRANSPORTATION_FEE);
		entries.put(TRANSPORTATION_FEE_NAME, transportationItem);
		amount = amount.add(TRANSPORTATION_FEE);

		// Handle graduate professional fee item and Minnesota student
		// association fee item
		if (isGraduate()) {
			ReportItem GraduateProfessionalFeeItem = new ReportItem(
					GRADUATE_PROFESSIONAL_FEE_NAME, CATEGORY,
					GRADUATE_PROFESSIONAL_FEE);
			entries.put(GRADUATE_PROFESSIONAL_FEE_NAME,
					GraduateProfessionalFeeItem);
			amount = amount.add(GRADUATE_PROFESSIONAL_FEE);
		} else {
			ReportItem minnesotaStudentAssociationItem = new ReportItem(
					MINNESOTA_STUDENT_ASSOCIATION_FEE_NAME, CATEGORY,
					MINNESOTA_STUDENT_ASSOCIATION_FEE);
			entries.put(MINNESOTA_STUDENT_ASSOCIATION_FEE_NAME,
					minnesotaStudentAssociationItem);
			amount = amount.add(MINNESOTA_STUDENT_ASSOCIATION_FEE);
		}
		return entries;
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
	 * Return if student is graduate student
	 * @return a boolean indicate if a student is a graduate student
	 */
	private boolean isGraduate() {
		return (record.getDegreeEnrolledIn().getProgram()) == DegreeProgram.PHD
				|| (record.getDegreeEnrolledIn().getProgram() == DegreeProgram.MASTER);
	}
}
