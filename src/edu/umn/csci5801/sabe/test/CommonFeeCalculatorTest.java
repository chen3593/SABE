package edu.umn.csci5801.sabe.test;

/*vicky*/
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.CommonFeeCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class CommonFeeCalculatorTest {
	StudentRecord testNonUMNInsrGra;
	StudentRecord testUMNInsrGra;
	StudentRecord testUMNInsrUG;
	StudentRecord testNonUMNInsrGraPhd;
	/* For Undergrad */
	private final BigDecimal CAPITAL_ENHANCEMENT = new BigDecimal(75);
	private final BigDecimal MINNESOTA_STUDENT_ASSOCIATION_FEE = new BigDecimal(
			2.86);
	private final BigDecimal STADIUM_FEE = new BigDecimal(12.50);
	private final BigDecimal STUDENT_HEALTH_BENEFICIAL_PLAN = new BigDecimal(
			1049);
	private final BigDecimal TRANSPORTATION_FEE = new BigDecimal(20);

	private final String CAPITAL_ENHANCEMENT_NAME = "Capital Enhancement Fees";
	private final String MINNESOTA_STUDENT_ASSOCIATION_FEE_NAME = "Minnesota Student Association";
	private final String STADIUM_FEE_NAME = "Stadium Fee";
	private final String STUDENT_HEALTH_BENEFICIAL_PLAN_NAME = "Student Health Benefit Plan";
	private final String TRANSPORTATION_FEE_NAME = "Transportation Fee";
	private BigDecimal fee_InsUG = CAPITAL_ENHANCEMENT
			.add(MINNESOTA_STUDENT_ASSOCIATION_FEE).add(STADIUM_FEE)
			.add(STUDENT_HEALTH_BENEFICIAL_PLAN).add(TRANSPORTATION_FEE);
	private BigDecimal fee_NonInsUG = CAPITAL_ENHANCEMENT
			.add(STUDENT_HEALTH_BENEFICIAL_PLAN)
			.add(MINNESOTA_STUDENT_ASSOCIATION_FEE).add(STADIUM_FEE)
			.add(TRANSPORTATION_FEE);
	/* For Grad Only */
	private final BigDecimal GRADUATE_PROFESSIONAL_FEE = new BigDecimal(6.68);

	private final String GRADUATE_PROFESSIONAL_FEE_NAME = "Graduate & Professional Student Assembly";

	private final String CATEGORY = "General Fees";
	private BigDecimal fee_InsGra = CAPITAL_ENHANCEMENT
			.add(MINNESOTA_STUDENT_ASSOCIATION_FEE).add(STADIUM_FEE)
			.add(STUDENT_HEALTH_BENEFICIAL_PLAN).add(TRANSPORTATION_FEE)
			.add(GRADUATE_PROFESSIONAL_FEE)
			.subtract(MINNESOTA_STUDENT_ASSOCIATION_FEE);
	private BigDecimal fee_NonInsGra = CAPITAL_ENHANCEMENT
			.add(STUDENT_HEALTH_BENEFICIAL_PLAN)
			.add(MINNESOTA_STUDENT_ASSOCIATION_FEE).add(STADIUM_FEE)
			.add(TRANSPORTATION_FEE).add(GRADUATE_PROFESSIONAL_FEE)
			.subtract(MINNESOTA_STUDENT_ASSOCIATION_FEE);

	@Before
	public void setUp() throws Exception {
		testNonUMNInsrGra = TestUtil.loadDatabaseGet("Sameera", "Shah",
				"shahx118", "students.txt");
		testUMNInsrGra = TestUtil.loadDatabaseGet("John", "May", "john234",
				"students.txt");
		testNonUMNInsrGraPhd = TestUtil.loadDatabaseGet("Phd", "Guy",
				"phdg1234", "students.txt");
		testUMNInsrUG = TestUtil.loadDatabaseGet("Smith", "Mason", "smith001",
				"students.txt");
	}

	/**
	 * To test the CommonFeeCalculator by testing the amount, name and category
	 * of each item in "General Fees" by passing a master's graduate student
	 * StudentRecord who is insured outside of UMN
	 * 
	 * @throws Exception
	 */
	@Test
	public void testNonUMNInsrGra() throws Exception {
		CommonFeeCalculator Calculator = new CommonFeeCalculator(
				testNonUMNInsrGra);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(fee_NonInsGra), 0);
		ReportItem item_CAPITAL_ENHANCEMENT = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getAmount(), CAPITAL_ENHANCEMENT);
		assertEquals(item_CAPITAL_ENHANCEMENT.getCategory(), CATEGORY);
		ReportItem item_MINNESOTA_STUDENT_ASSOCIATION = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getAmount(),
				CAPITAL_ENHANCEMENT);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getCategory(), CATEGORY);
		ReportItem item_STADIUM_FEE = entries.get(STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getName(), STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getAmount(), STADIUM_FEE);
		assertEquals(item_STADIUM_FEE.getCategory(), CATEGORY);
		ReportItem item_TRANSPORTATION_FEE = entries
				.get(TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getName(), TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getAmount(), TRANSPORTATION_FEE);
		assertEquals(item_TRANSPORTATION_FEE.getCategory(), CATEGORY);
		ReportItem item_GRADUATE_PROFESSIONAL_FEE = entries
				.get(GRADUATE_PROFESSIONAL_FEE_NAME);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getName(),
				GRADUATE_PROFESSIONAL_FEE_NAME);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getAmount(),
				GRADUATE_PROFESSIONAL_FEE);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getCategory(), CATEGORY);
	}

	/**
	 * To test the CommonFeeCalculator by testing the amount, name and category
	 * of each item in "General Fees" by passing a master's graduate student
	 * StudentRecord who is insured in UMN
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUMNInsrGra() throws Exception {
		CommonFeeCalculator Calculator = new CommonFeeCalculator(testUMNInsrGra);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(fee_InsGra), 0);
		ReportItem item_CAPITAL_ENHANCEMENT = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getAmount(), CAPITAL_ENHANCEMENT);
		assertEquals(item_CAPITAL_ENHANCEMENT.getCategory(), CATEGORY);
		ReportItem item_MINNESOTA_STUDENT_ASSOCIATION = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getAmount(),
				CAPITAL_ENHANCEMENT);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getCategory(), CATEGORY);
		ReportItem item_STADIUM_FEE = entries.get(STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getName(), STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getAmount(), STADIUM_FEE);
		assertEquals(item_STADIUM_FEE.getCategory(), CATEGORY);
		ReportItem item_TRANSPORTATION_FEE = entries
				.get(TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getName(), TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getAmount(), TRANSPORTATION_FEE);
		assertEquals(item_TRANSPORTATION_FEE.getCategory(), CATEGORY);
		ReportItem item_GRADUATE_PROFESSIONAL_FEE = entries
				.get(GRADUATE_PROFESSIONAL_FEE_NAME);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getName(),
				GRADUATE_PROFESSIONAL_FEE_NAME);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getAmount(),
				GRADUATE_PROFESSIONAL_FEE);
		assertEquals(item_GRADUATE_PROFESSIONAL_FEE.getCategory(), CATEGORY);
		ReportItem item_STUDENT_HEALTH_BENEFICIAL_PLAN = entries
				.get(STUDENT_HEALTH_BENEFICIAL_PLAN_NAME);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getName(),
				STUDENT_HEALTH_BENEFICIAL_PLAN_NAME);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getAmount(),
				STUDENT_HEALTH_BENEFICIAL_PLAN);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getCategory(),
				CATEGORY);
	}

	/**
	 * To test the CommonFeeCalculator by testing the amount, name and category
	 * of each item in "General Fees" by passing an undergraduate student
	 * StudentRecord who is insured in UMN
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUMNInsrUG() throws Exception {
		CommonFeeCalculator Calculator = new CommonFeeCalculator(testUMNInsrUG);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(fee_InsUG), 0);
		ReportItem item_CAPITAL_ENHANCEMENT = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getAmount(), CAPITAL_ENHANCEMENT);
		assertEquals(item_CAPITAL_ENHANCEMENT.getCategory(), CATEGORY);
		ReportItem item_MINNESOTA_STUDENT_ASSOCIATION = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getAmount(),
				CAPITAL_ENHANCEMENT);
		assertEquals(item_MINNESOTA_STUDENT_ASSOCIATION.getCategory(), CATEGORY);
		ReportItem item_STADIUM_FEE = entries.get(STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getName(), STADIUM_FEE_NAME);
		assertEquals(item_STADIUM_FEE.getAmount(), STADIUM_FEE);
		assertEquals(item_STADIUM_FEE.getCategory(), CATEGORY);
		ReportItem item_TRANSPORTATION_FEE = entries
				.get(TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getName(), TRANSPORTATION_FEE_NAME);
		assertEquals(item_TRANSPORTATION_FEE.getAmount(), TRANSPORTATION_FEE);
		assertEquals(item_TRANSPORTATION_FEE.getCategory(), CATEGORY);
		ReportItem item_STUDENT_HEALTH_BENEFICIAL_PLAN = entries
				.get(STUDENT_HEALTH_BENEFICIAL_PLAN_NAME);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getName(),
				STUDENT_HEALTH_BENEFICIAL_PLAN_NAME);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getAmount(),
				STUDENT_HEALTH_BENEFICIAL_PLAN);
		assertEquals(item_STUDENT_HEALTH_BENEFICIAL_PLAN.getCategory(),
				CATEGORY);
	}

	@Test
	/**
	 * To test the CommonFeeCalculator by testing the amount, name and category of each 
	 * item in "General Fees" by passing a PHD student StudentRecord who is insured outside of UMN  
	 */
	public void testNonUMNInsrGraPhd() throws Exception {
		CommonFeeCalculator Calculator = new CommonFeeCalculator(
				testNonUMNInsrGraPhd);
		Map<String, ReportItem> entries = Calculator.calculateEntries();
		BigDecimal amount = Calculator.getAmount();
		assertEquals(amount.compareTo(fee_NonInsGra), 0);
		ReportItem item_CAPITAL_ENHANCEMENT = entries
				.get(CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getName(),
				CAPITAL_ENHANCEMENT_NAME);
		assertEquals(item_CAPITAL_ENHANCEMENT.getAmount(), CAPITAL_ENHANCEMENT);
		assertEquals(item_CAPITAL_ENHANCEMENT.getCategory(), CATEGORY);
	}

}
