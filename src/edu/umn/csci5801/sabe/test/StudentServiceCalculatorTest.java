/*
 * By Yang Yang
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.StudentServiceCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class StudentServiceCalculatorTest {
	private StudentRecord[] testRecord = new StudentRecord[2];
	private final String STUDENT_SERVICE_FEE_NAME = "Student Service Fees";
	private final String CATEGORY = "General Fees";
	private final BigDecimal SERVICE_FEE = new BigDecimal(417.91);
	private final BigDecimal NO_SERVICE_FEE = new BigDecimal(0);

	@Before
	public void setUp() throws Exception {
		testRecord[0] = TestUtil.loadDatabaseGet("Sameera", "Shah", "shahx118",
				"students.txt");
		testRecord[1] = TestUtil.loadDatabaseGet("John", "Weissman",
				"john3273", "students.txt");
	}

	/**
	 * To test the StudentServiceCalculator by testing the amount, name and
	 * category of "General Fees" by passing in a StudentRecord who registered
	 * less than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStudentServiceMoreThanSixCredits() throws Exception {
		StudentServiceCalculator Calculator = new StudentServiceCalculator(
				testRecord[0]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(SERVICE_FEE), 0);
		ReportItem item = entries.get(STUDENT_SERVICE_FEE_NAME);
		assertEquals(item.getName(), STUDENT_SERVICE_FEE_NAME);
		assertEquals(item.getAmount(), SERVICE_FEE);
		assertEquals(item.getCategory(), CATEGORY);
	}

	/**
	 * To test the StudentServiceCalculator by testing the amount, name and
	 * category of "General Fees" by passing in a StudentRecord who registered
	 * more than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStudentServiceLessThanSixCredits() throws Exception {
		StudentServiceCalculator Calculator = new StudentServiceCalculator(
				testRecord[1]);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(NO_SERVICE_FEE), 0);
		ReportItem item = entries.get(STUDENT_SERVICE_FEE_NAME);
		assertEquals(item.getName(), STUDENT_SERVICE_FEE_NAME);
		assertEquals(item.getAmount(), NO_SERVICE_FEE);
		assertEquals(item.getCategory(), CATEGORY);
	}
}
