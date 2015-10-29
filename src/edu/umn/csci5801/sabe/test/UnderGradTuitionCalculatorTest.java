package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.UnderGradTuitionCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class UnderGradTuitionCalculatorTest {

	private StudentRecord MNUndergadPartTimeStudent, MNUndergadFullTimeStudent,
			IntlUndergradPartTimeStudent, IntlUndergradFullTimeStudent;
	private final String NAME = "Tuition";
	private final String CATEGORY = "Tuition";
	private final float MN_PARTTIME = (float) 3710.8;
	private final float MN_FULLTIME = (float) 6030.0;
	private final float INTL_PARTTIME = (float) 5941.52;
	private final float INTL_FULLTIME = (float) 9655.0;

	@Before
	public void setUp() throws Exception {
		MNUndergadPartTimeStudent = TestUtil.loadDatabaseGet("Mn", "Part",
				"mnpa1234", "students.txt");
		MNUndergadFullTimeStudent = TestUtil.loadDatabaseGet("Mn", "Full",
				"mnfu1234", "students.txt");
		IntlUndergradPartTimeStudent = TestUtil.loadDatabaseGet("Intl", "Part",
				"inpa1234", "students.txt");
		IntlUndergradFullTimeStudent = TestUtil.loadDatabaseGet("Intl", "Full",
				"infu1234", "students.txt");
	}

	/**
	 * To test the UnderGradTuitionCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a parttime MN StudentRecord who
	 * registered less than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMNPartTimeStudent() throws Exception {
		UnderGradTuitionCalculator MNPartTimeCalc = new UnderGradTuitionCalculator(
				MNUndergadPartTimeStudent);
		Map<String, ReportItem> entries = MNPartTimeCalc.calculateEntries();
		BigDecimal MNPartTimeAmount = MNPartTimeCalc.getAmount();
		// System.out.println("MN_PARTTIME " + MNPartTimeAmount.floatValue());
		assertEquals(0,
				Float.compare(MN_PARTTIME, MNPartTimeAmount.floatValue()));
		ReportItem item_MN_PARTTIME = entries.get("Tuition");
		assertEquals(item_MN_PARTTIME.getName(), "Tuition");
		assertEquals(item_MN_PARTTIME.getCategory(), "Tuition");
	}

	/**
	 * To test the UnderGradTuitionCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full-time MN StudentRecord who
	 * registered more than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMNFullTimeStudent() throws Exception {
		UnderGradTuitionCalculator MNFullTimeCalc = new UnderGradTuitionCalculator(
				MNUndergadFullTimeStudent);
		Map<String, ReportItem> entries = MNFullTimeCalc.calculateEntries();
		BigDecimal MNFullTimeAmount = MNFullTimeCalc.getAmount();
		// System.out.println("FULLTIME " + MNFullTimeAmount.floatValue());
		assertEquals(0,
				Float.compare(MN_FULLTIME, MNFullTimeAmount.floatValue()));
	}

	/**
	 * To test the UnderGradTuitionCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a parttime International
	 * StudentRecord who registered less than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIntlPartTimeStudent() throws Exception {
		UnderGradTuitionCalculator IntlPartTimeCalc = new UnderGradTuitionCalculator(
				IntlUndergradPartTimeStudent);
		Map<String, ReportItem> entries = IntlPartTimeCalc.calculateEntries();
		BigDecimal IntlPartTimeAmount = IntlPartTimeCalc.getAmount();
		// System.out.println("INTL_PARTTIME " +
		// IntlPartTimeAmount.floatValue());
		assertEquals(0,
				Float.compare(INTL_PARTTIME, IntlPartTimeAmount.floatValue()));

	}

	/**
	 * To test the UnderGradTuitionCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full-time International
	 * StudentRecord who registered less than 6 credits
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIntlFullTimeStudent() throws Exception {
		UnderGradTuitionCalculator IntlFullTimeCalc = new UnderGradTuitionCalculator(
				IntlUndergradFullTimeStudent);
		Map<String, ReportItem> entries = IntlFullTimeCalc.calculateEntries();
		BigDecimal IntlFullTimeAmount = IntlFullTimeCalc.getAmount();
		// System.out.println("INTL_FULLTIME " +
		// IntlFullTimeAmount.floatValue());
		assertEquals(0,
				Float.compare(INTL_FULLTIME, IntlFullTimeAmount.floatValue()));

	}

}
