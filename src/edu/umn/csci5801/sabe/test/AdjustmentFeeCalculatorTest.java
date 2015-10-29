/*
 * By Yang Yang
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.AdjustmentFeeCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class AdjustmentFeeCalculatorTest {
	StudentRecord testRecord;
	private final String NAME_TEACHING_ASSISTANT = "Teaching Assistant";
	private final String NAME_INSURANCE_WAVIER = "Insurance Waiver";
	private final String CATEGORY_OTHER = "OTHER";
	private final String CATEGORY_WAVIER = "WAIVER";
	private final BigDecimal TA_FELLOWSHIP = new BigDecimal(-7000);
	private final BigDecimal INSURANCE_WAVIER = new BigDecimal(-1049);
	private final BigDecimal ADJUSTMENT_FEE = new BigDecimal(-8049);

	@Before
	public void setUp() throws Exception {

		testRecord = TestUtil.loadDatabaseGet("John", "Weissman", "john3273",
				"students.txt");

	}

	/**
	 * To test the AdjustmentFeeCalculator by testing the student's adjustment
	 * fee sum from StudentRecord and its category and name
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAdjustment() throws Exception {
		AdjustmentFeeCalculator Calculator = new AdjustmentFeeCalculator(
				testRecord);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(ADJUSTMENT_FEE), 0);
		ReportItem TA_ship = entries.get(NAME_TEACHING_ASSISTANT);
		assertEquals(TA_ship.getName(), NAME_TEACHING_ASSISTANT);
		assertEquals(TA_ship.getAmount(), TA_FELLOWSHIP);
		assertEquals(TA_ship.getCategory(), CATEGORY_OTHER);

		ReportItem Insurance = entries.get(NAME_INSURANCE_WAVIER);
		assertEquals(Insurance.getName(), NAME_INSURANCE_WAVIER);
		assertEquals(Insurance.getAmount(), INSURANCE_WAVIER);
		assertEquals(Insurance.getCategory(), CATEGORY_WAVIER);
	}
}
