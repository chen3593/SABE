package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.College;
import edu.umn.csci5801.sabe.model.DegreeProgram;
import edu.umn.csci5801.sabe.model.Major;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class OverallCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private StudentRecord record;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	
	/**
	 * Add entries and balance into the Map with a helper method called calculateTuition() and calculateFee()
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		/* tuition calculation */
		calculateTuition();
		calculateFee();
		return entries;
	}
	
	/**
	 * Helper method that calculate tuition 
	 */
	private void calculateTuition() {
		DegreeProgram degreeProgram = record.getDegreeEnrolledIn().getProgram();
		Major major = record.getDegreeEnrolledIn().getMajor();
		College college = record.getDegreeEnrolledIn().getCollege();
		if (degreeProgram == DegreeProgram.BACHELOR) {
			CalculatorIntf uGradTuitionCal = new UnderGradTuitionCalculator(record);
			entries = CalculatorUtil.updateEntry(uGradTuitionCal, entries);
			amount = amount.add(uGradTuitionCal.getAmount());
		} else if (degreeProgram == DegreeProgram.MASTER || degreeProgram == DegreeProgram.PHD ) {
			if (college == College.MEDICAL) {
				CalculatorIntf GradMedTuitionCal = new GradMedTuitionCalculator(record);
				entries = CalculatorUtil.updateEntry(GradMedTuitionCal, entries);
				amount = amount.add(GradMedTuitionCal.getAmount());
			}
			else {
				if(major == Major.SOFTWARE_ENGINEERING || major == Major.MANAGEMENT_TECHNOLOGY) {
					CalculatorIntf GradCSETermCalc = new GradCSETermCalculator(record);
					entries = CalculatorUtil.updateEntry(GradCSETermCalc, entries);
					amount = amount.add(GradCSETermCalc.getAmount());
				}
				else {
					CalculatorIntf GradCSECreditCalc = new GradCSECreditCalculator(record);
					entries = CalculatorUtil.updateEntry(GradCSECreditCalc, entries);
					amount = amount.add(GradCSECreditCalc.getAmount());
				}
			}
		} 
	}
	
	/**
	 * Helper method that calculate tuition 
	 */
	private void calculateFee() {
		
		DegreeProgram degreeProgram = record.getDegreeEnrolledIn().getProgram();
		College college = record.getDegreeEnrolledIn().getCollege();
		// Adjustment Fee
		CalculatorIntf AdjFeeCalc = new AdjustmentFeeCalculator(record);
		entries = CalculatorUtil.updateEntry(AdjFeeCalc, entries);
		amount = amount.add(AdjFeeCalc.getAmount());
		// Common Fee
		CalculatorIntf CommFeeCalc = new CommonFeeCalculator(record);
		entries = CalculatorUtil.updateEntry(CommFeeCalc, entries);
		amount = amount.add(CommFeeCalc.getAmount());
		// College Fee
		if (college == College.SCIENCE_AND_ENGINEERING) {
			CalculatorIntf CSECollegeFeeCalc = new CSECollegeFeeCalculator(record);
			entries = CalculatorUtil.updateEntry(CSECollegeFeeCalc, entries);
			amount = amount.add(CSECollegeFeeCalc.getAmount());
		}
		else {
			CalculatorIntf MedCollegeFeeCalc = new MedCollegeFeeCalculator(record);
			entries = CalculatorUtil.updateEntry(MedCollegeFeeCalc, entries);
			amount = amount.add(MedCollegeFeeCalc.getAmount());
		}
		// International Fee
		if(record.isInternationalStudent()) {
			CalculatorIntf IntlFeeCalc = new InternationalFeeCalculator(record);
			entries = CalculatorUtil.updateEntry(IntlFeeCalc, entries);
			amount = amount.add(IntlFeeCalc.getAmount());
		}
		// Student Service Fee
		CalculatorIntf srvFeeCalc = new StudentServiceCalculator(record);
		entries = CalculatorUtil.updateEntry(srvFeeCalc, entries);
		amount = amount.add(srvFeeCalc.getAmount());
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
	public OverallCalculator(StudentRecord record) {
		this.record = record;
		amount = BigDecimal.ZERO;
	}
}
