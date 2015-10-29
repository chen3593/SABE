package edu.umn.csci5801.sabe.calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.Adjustment;
import edu.umn.csci5801.sabe.model.StudentRecord;

/*Created by Raoyin Chen*/

public class AdjustmentFeeCalculator implements CalculatorIntf {
	private BigDecimal amount;
	private Map<String, ReportItem> entries = new HashMap<String, ReportItem>();
	private StudentRecord record;

	/**
	 * Constructor
	 * @param studentRecord student record
	 */
	public AdjustmentFeeCalculator(StudentRecord studentRecord) {
		this.record = studentRecord;
		amount = new BigDecimal(0);
	}

	/**
	 * Update the local variable "amount" by adding up the all adjustment fees,
	 * and each adjustment item's name, category and amount was added into the
	 * Map
	 * @return Map of name and reportItem.
	 */
	@Override
	public Map<String, ReportItem> calculateEntries() {

		for (Adjustment adj : record.getAdjustments()) {
			amount = amount.add(adj.getAmountPerSemester());
			ReportItem result = new ReportItem(adj.getName(), adj.getCategory()
					.name(), adj.getAmountPerSemester());
			entries.put(result.getName(), result);
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
}
