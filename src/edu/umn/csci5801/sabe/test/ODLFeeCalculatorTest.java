/*
 * By Yang Yang
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.ODLFeeCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class ODLFeeCalculatorTest {
	private StudentRecord[] testRecord = new StudentRecord[2];
	private final String ODL_NAME = "ODL Fee";
	private final String CATEGORY = "General Fees";
	private final BigDecimal ODL = new BigDecimal(90);
	private final BigDecimal NO_ODL = new BigDecimal(0);

	@Before
	public void setUp() throws Exception {
		testRecord[0] = TestUtil.loadDatabaseGet("Sameera", "Shah", "shahx118",
				"students.txt");
		testRecord[1] = TestUtil.loadDatabaseGet("John", "Weissman",
				"john3273", "students.txt");
	}

	/**
	 * To test the ODLFeeCalculator by testing the amount, name and category of
	 * "General Fees" by passing in a StudentRecord who registered less than 2
	 * credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testODLLessThanTwoCredits() throws Exception {
		ODLFeeCalculator Calculator = new ODLFeeCalculator(testRecord[0]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(NO_ODL), 0);
		ReportItem item = entries.get(ODL_NAME);
		assertEquals(item.getName(), ODL_NAME);
		assertEquals(item.getAmount(), NO_ODL);
		assertEquals(item.getCategory(), CATEGORY);
	}

	/**
	 * To test the ODLFeeCalculator by testing the amount, name and category of
	 * "General Fees" by passing in a StudentRecord who registered more than 2
	 * credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testODLMoreThanTwoCredits() throws Exception {
		ODLFeeCalculator Calculator = new ODLFeeCalculator(testRecord[1]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(ODL), 0);
		ReportItem item = entries.get(ODL_NAME);
		assertEquals(item.getName(), ODL_NAME);
		assertEquals(item.getAmount(), ODL);
		assertEquals(item.getCategory(), CATEGORY);
	}
}
