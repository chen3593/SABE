package edu.umn.csci5801.sabe.test;

/*vicky*/
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.InternationalFeeCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class InternationalFeeCalculatorTest {

	private StudentRecord testRecordUG;
	private StudentRecord testRecordUGPart;
	private StudentRecord testRecordGrd;
	private BigDecimal INTERNATIONAL_SPON_STUDENT = new BigDecimal(300.00);
	private BigDecimal INTERNATIONAL_STUDENT_AID = new BigDecimal(14.00);
	private BigDecimal INTERNATIONAL_ADMIN = new BigDecimal(145.00);
	private BigDecimal INTER_STUDENT_ACADEMIC_SVR_FEE6Plus = new BigDecimal(
			125.00);
	private BigDecimal INTER_STUDENT_ACADEMIC_SVR_FEE6Minus = new BigDecimal(
			62.50);
	private final String INTERNATIONAL_SPON_STUDENT_NAME = "International Sponsored Student Fee";
	private final String INTERNATIONAL_STUDENT_AID_NAME = "International Student Aid Fee";
	private final String INTERNATIONAL_ADMIN_NAME = "International Student Administrative Fee";
	private final String INTER_STUDENT_ACADEMIC_SVR_NAME = "International Student Academic Services Fee";

	private final String INTERNATIONAL_CATEGORY = "General Fees";
	private BigDecimal studentServceFeeFullTimeUG = INTERNATIONAL_SPON_STUDENT
			.add(INTERNATIONAL_ADMIN).add(INTERNATIONAL_STUDENT_AID)
			.add(INTER_STUDENT_ACADEMIC_SVR_FEE6Plus);
	private BigDecimal studentServceFeePartTimeUG = INTERNATIONAL_SPON_STUDENT
			.add(INTERNATIONAL_ADMIN).add(INTERNATIONAL_STUDENT_AID)
			.add(INTER_STUDENT_ACADEMIC_SVR_FEE6Minus);
	private BigDecimal studentServceFeeGrd = INTERNATIONAL_SPON_STUDENT.add(
			INTERNATIONAL_ADMIN).add(INTERNATIONAL_STUDENT_AID);

	@Before
	public void setUp() throws Exception {
		testRecordGrd = TestUtil.loadDatabaseGet("Sameera", "Shah", "shahx118",
				"students.txt");
		testRecordUG = TestUtil.loadDatabaseGet("Smith", "Mason", "smith001",
				"students.txt");
		testRecordUGPart = TestUtil.loadDatabaseGet("Mile", "Mason",
				"smith003", "students.txt");
	}

	/**
	 * To test the InternationalFeeCalculator by testing the amount, name and
	 * category of "General Fees" by passing in a full time international
	 * undergraduate student StudentRecord
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInternationalFeeUD() throws Exception {
		InternationalFeeCalculator Calculator = new InternationalFeeCalculator(
				testRecordUG);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(studentServceFeeFullTimeUG), 0);
		ReportItem item1 = entries.get(INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getName(), INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getAmount(), INTERNATIONAL_SPON_STUDENT);
		assertEquals(item1.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item2 = entries.get(INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getName(), INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getAmount(), INTERNATIONAL_STUDENT_AID);
		assertEquals(item2.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item3 = entries.get(INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getName(), INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getAmount(), INTERNATIONAL_ADMIN);
		assertEquals(item3.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item4 = entries.get(INTER_STUDENT_ACADEMIC_SVR_NAME);
		assertEquals(item4.getName(), INTER_STUDENT_ACADEMIC_SVR_NAME);
		assertEquals(item4.getAmount(), INTER_STUDENT_ACADEMIC_SVR_FEE6Plus);
		assertEquals(item4.getCategory(), INTERNATIONAL_CATEGORY);
	}

	/**
	 * To test the InternationalFeeCalculator by testing the amount, name and
	 * category of "General Fees" by passing in a full time international
	 * graduate student StudentRecord
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInternationalFeeGrd() throws Exception {
		InternationalFeeCalculator Calculator = new InternationalFeeCalculator(
				testRecordGrd);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(studentServceFeeGrd), 0);
		ReportItem item1 = entries.get(INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getName(), INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getAmount(), INTERNATIONAL_SPON_STUDENT);
		assertEquals(item1.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item2 = entries.get(INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getName(), INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getAmount(), INTERNATIONAL_STUDENT_AID);
		assertEquals(item2.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item3 = entries.get(INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getName(), INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getAmount(), INTERNATIONAL_ADMIN);
		assertEquals(item3.getCategory(), INTERNATIONAL_CATEGORY);

	}

	/**
	 * To test the InternationalFeeCalculator by testing the amount, name and
	 * category of "General Fees" by passing in a part time international
	 * undergraduate student StudentRecord
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInternationalFeeUDPart() throws Exception {
		InternationalFeeCalculator Calculator = new InternationalFeeCalculator(
				testRecordUGPart);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(studentServceFeePartTimeUG), 0);
		ReportItem item1 = entries.get(INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getName(), INTERNATIONAL_SPON_STUDENT_NAME);
		assertEquals(item1.getAmount(), INTERNATIONAL_SPON_STUDENT);
		assertEquals(item1.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item2 = entries.get(INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getName(), INTERNATIONAL_STUDENT_AID_NAME);
		assertEquals(item2.getAmount(), INTERNATIONAL_STUDENT_AID);
		assertEquals(item2.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item3 = entries.get(INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getName(), INTERNATIONAL_ADMIN_NAME);
		assertEquals(item3.getAmount(), INTERNATIONAL_ADMIN);
		assertEquals(item3.getCategory(), INTERNATIONAL_CATEGORY);
		ReportItem item4 = entries.get(INTER_STUDENT_ACADEMIC_SVR_NAME);
		assertEquals(item4.getName(), INTER_STUDENT_ACADEMIC_SVR_NAME);
		assertEquals(item4.getAmount(), INTER_STUDENT_ACADEMIC_SVR_FEE6Minus);
		assertEquals(item4.getCategory(), INTERNATIONAL_CATEGORY);
	}

}
