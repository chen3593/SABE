package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.DegreeProgram;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class MedCollegeFeeCalculator implements CalculatorIntf {
	private final BigDecimal MED_COLLEGIATE = new BigDecimal(250);
	private final BigDecimal MED_MORTUARY = new BigDecimal(50);
	private final String MED_COLLEGIATE_NAME = "Collegiate Fee";
	private final String MED_MORTUARY_NAME = "Mortuary Science program fee";
	private final String CATEGORY = "College and Program Fees";
	private StudentRecord record;
	private BigDecimal amount;

	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public MedCollegeFeeCalculator(StudentRecord Record) {
		this.record = Record;
	}
	
	/**
	 * Update the local variable "amount" by adding up all the medical
	 * college fees, and add the entries and balance into the Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {
		amount = BigDecimal.ZERO;
		Map<String, ReportItem> itemMap = new HashMap<String, ReportItem>();
		ReportItem collegiateItem = new ReportItem(MED_COLLEGIATE_NAME, CATEGORY, MED_COLLEGIATE);
		itemMap.put(MED_COLLEGIATE_NAME, collegiateItem);
		amount = amount.add(MED_COLLEGIATE);
		if (!isGraduate()) {
			ReportItem mortuaryItem = new ReportItem(MED_MORTUARY_NAME, CATEGORY, MED_MORTUARY);
			itemMap.put(MED_MORTUARY_NAME, mortuaryItem);
			amount = amount.add(MED_MORTUARY);
		}
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
	 * Return if student is graduate student
	 * @return a boolean indicate if a student is graduate student
	 */
	private boolean isGraduate() {
		return (record.getDegreeEnrolledIn().getProgram()) == DegreeProgram.PHD
				|| (record.getDegreeEnrolledIn().getProgram() == DegreeProgram.MASTER);
	}
}