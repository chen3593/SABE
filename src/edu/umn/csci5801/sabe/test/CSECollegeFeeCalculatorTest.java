package edu.umn.csci5801.sabe.test;

/*vicky*/
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.CSECollegeFeeCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class CSECollegeFeeCalculatorTest {
	private BigDecimal COLLEGIATE_FEE_FULLTIME = new BigDecimal(300);
	private BigDecimal COLLEGIATE_FEE_PARTTIME = new BigDecimal(150);
	private final String NAME = "Collegiate Fee";
	private final String CATEGORY = "College and Program Fee";
	private StudentRecord testRecordFull;
	private StudentRecord testRecordPart;

	@Before
	public void setUp() throws Exception {
		testRecordFull = TestUtil.loadDatabaseGet("Sameera", "Shah",
				"shahx118", "students.txt");
		testRecordPart = TestUtil.loadDatabaseGet("John", "Weissman",
				"john3273", "students.txt");
	}

	/**
	 * To test the CSECollegeFeeCalculator by testing the amount, name and
	 * category of "College and Program Fee" by passing in a full time student
	 * StudentRecord who is in Science and Engineering college
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFullCSECollegeFee() throws Exception {
		CSECollegeFeeCalculator Calculator = new CSECollegeFeeCalculator(
				testRecordFull);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(COLLEGIATE_FEE_FULLTIME), 0);
		ReportItem item1 = entries.get(NAME);
		assertEquals(item1.getName(), NAME);
		assertEquals(item1.getAmount(), COLLEGIATE_FEE_FULLTIME);
		assertEquals(item1.getCategory(), CATEGORY);
	}

	/**
	 * To test the CSECollegeFeeCalculator by testing the amount, name and
	 * category of "College and Program Fee" by passing in a part time student
	 * StudentRecord who is in Science and Engineering college
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPartCSECollegeFee() throws Exception {
		CSECollegeFeeCalculator Calculator = new CSECollegeFeeCalculator(
				testRecordPart);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(COLLEGIATE_FEE_PARTTIME), 0);
		ReportItem item1 = entries.get(NAME);
		assertEquals(item1.getName(), NAME);
		assertEquals(item1.getAmount(), COLLEGIATE_FEE_PARTTIME);
		assertEquals(item1.getCategory(), CATEGORY);
	}

}
