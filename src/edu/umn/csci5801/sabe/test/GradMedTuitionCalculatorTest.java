package edu.umn.csci5801.sabe.test;

/*vicky*/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.GradMedTuitionCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class GradMedTuitionCalculatorTest {
	private StudentRecord testY1ResFall;
	private StudentRecord testY1NonResFall;
	private StudentRecord testOutRange;

	private final String TUITION_NAME = "Tuition";
	private final String CATEGORY = "Tuition";
	private final BigDecimal FIRST_YEAR_TUITION_FEE_NONMN = new BigDecimal(
			16859);
	private final BigDecimal FIRST_YEAR_TUITION_FEE_MN = new BigDecimal(12624.0);

	@Before
	public void setUp() throws Exception {
		testY1ResFall = TestUtil.loadDatabaseGet("apple", "ann", "aaa125",
				"medical.txt");
		testY1NonResFall = TestUtil.loadDatabaseGet("pob", "push", "pp125",
				"medical.txt");
		testOutRange = TestUtil.loadDatabaseGet("fog", "fue", "fff123",
				"medical.txt");
	}

	/**
	 * To test the GradMedTuitionCalculator by testing the amount, name and
	 * category of the tuition of a medical college first year resident student
	 * 
	 * @throws Exception
	 */
	@Test
	public void testY1FallMN() throws Exception {

		GradMedTuitionCalculator Calculator1 = new GradMedTuitionCalculator(
				testY1ResFall);
		Map<String, ReportItem> entries1 = Calculator1.calculateEntries();
		BigDecimal amount1 = Calculator1.getAmount();
		assertEquals(amount1.compareTo(FIRST_YEAR_TUITION_FEE_MN), 0);
		ReportItem item1 = entries1.get(TUITION_NAME);
		assertEquals(item1.getName(), TUITION_NAME);
		assertEquals(item1.getAmount(), FIRST_YEAR_TUITION_FEE_MN);
		assertEquals(item1.getCategory(), CATEGORY);
	}

	/**
	 * To test the GradMedTuitionCalculator by testing the amount, name and
	 * category of the tuition of a medical college first year non-resident
	 * student
	 * 
	 * @throws Exception
	 */
	@Test
	public void testY1FallNonMN() throws Exception {
		GradMedTuitionCalculator Calculator2 = new GradMedTuitionCalculator(
				testY1NonResFall);
		Map<String, ReportItem> entries2 = Calculator2.calculateEntries();
		BigDecimal amount2 = Calculator2.getAmount();
		assertEquals(amount2.compareTo(FIRST_YEAR_TUITION_FEE_NONMN), 0);
		ReportItem item2 = entries2.get(TUITION_NAME);
		assertEquals(item2.getName(), TUITION_NAME);
		assertEquals(item2.getAmount(), FIRST_YEAR_TUITION_FEE_NONMN);
		assertEquals(item2.getCategory(), CATEGORY);
	}

	/**
	 * To test illegal year exception case by passing in a seventh year student
	 * of medical college
	 */
	@Test
	public void testThrowsIndexOutOfBoundsException() {
		GradMedTuitionCalculator Calculator0 = new GradMedTuitionCalculator(
				testOutRange);
		boolean thrown = false;
		try {
			Calculator0.calculateEntries();
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
