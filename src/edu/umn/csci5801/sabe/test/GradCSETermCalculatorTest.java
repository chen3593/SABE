/*
 * By Yang Yang
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.GradCSETermCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class GradCSETermCalculatorTest {
	private StudentRecord[] testRecord = new StudentRecord[2];
	private final String NAME = "Tuition";
	private final String CATEGORY = "Tuition";
	private final BigDecimal SOFTWARE_ENGINEERING_FEE = new BigDecimal(8050);
	private final BigDecimal MANAGEMENT_TECH_FEE = new BigDecimal(16525);

	@Before
	public void setUp() throws Exception {
		testRecord[0] = TestUtil.loadDatabaseGet("Jason", "Biatek", "biate001",
				"students.txt");
		testRecord[1] = TestUtil.loadDatabaseGet("John", "Weissman",
				"john3273", "students.txt");
	}

	/**
	 * To test the GradCSETermCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time student StudentRecord who
	 * is in Science and Engineering college and major in Software Engineering
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGardCSETermSoftWareEngineering() throws Exception {
		GradCSETermCalculator Calculator = new GradCSETermCalculator(
				testRecord[0]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(SOFTWARE_ENGINEERING_FEE), 0);
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getAmount(), SOFTWARE_ENGINEERING_FEE);
		assertEquals(item.getCategory(), CATEGORY);

	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time resident student
	 * StudentRecord who is in Science and Engineering college and major in
	 * Management Technology
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGardCSETermManagement() throws Exception {
		GradCSETermCalculator Calculator = new GradCSETermCalculator(
				testRecord[1]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(MANAGEMENT_TECH_FEE), 0);
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getAmount(), MANAGEMENT_TECH_FEE);
		assertEquals(item.getCategory(), CATEGORY);

	}
}