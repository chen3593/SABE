/*
 * Created by Raoyin Chen 
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.GradCSECreditCalculator;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class GradCSECreditCalculatorTest {

	private StudentRecord financialRecord, managementRecord, deviceRecord,
			securityRecord, financialEarlyRecord, financialLateRecord;
	private final BigDecimal FINANCIAL_TUITION = new BigDecimal(7785.0); 
	private final BigDecimal MANAGEMENT_TUITION = new BigDecimal(16524.0); 
	private final BigDecimal DEVICE_TUITION = new BigDecimal(16545.0); 
	private final BigDecimal SECURITY_TUITION = new BigDecimal(9540.0); 
	private final BigDecimal FINANCIAL_EARLY_TUITION = new BigDecimal(7785.0); 
	private final BigDecimal FINANCIAL_LATE_TUITION = new BigDecimal(9585.0); 
	private final String NAME = "Tuition";
	private final String CATEGORY = "Tuition";

	@Before
	public void setUp() throws Exception {

		financialRecord = TestUtil.loadDatabaseGet("Financial", "Guy",
				"fngy123", "GradCredit.txt");
		managementRecord = TestUtil.loadDatabaseGet("Management", "Guy",
				"magu1234", "GradCredit.txt");
		deviceRecord = TestUtil.loadDatabaseGet("Device", "Mason", "dema1234",
				"GradCredit.txt");
		securityRecord = TestUtil.loadDatabaseGet("Security", "Guy", "scry123",
				"GradCredit.txt");
		financialEarlyRecord = TestUtil.loadDatabaseGet("FinEarly", "Guy",
				"fner1234", "GradCredit.txt");
		financialLateRecord = TestUtil.loadDatabaseGet("FinLate", "Guy",
				"fnla1234", "GradCredit.txt");

	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time resident student
	 * StudentRecord who is in Science and Engineering college and major in
	 * Financial Mathematics
	 * 
	 * @throws Exception
	 */
	@Test
	public void testfinancialRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator finCalculator = new GradCSECreditCalculator(
				financialRecord);
		Map<String, ReportItem> entries = finCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), FINANCIAL_TUITION);
		// test getAmount()
		BigDecimal finAmount = finCalculator.getAmount();
		assertEquals(finAmount.compareTo(FINANCIAL_TUITION), 0);
	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time student StudentRecord who
	 * is in Science and Engineering college and major in Management of
	 * Technology
	 * 
	 * @throws Exception
	 */
	@Test
	public void testManagementRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator mngCalculator = new GradCSECreditCalculator(
				managementRecord);
		Map<String, ReportItem> entries = mngCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), MANAGEMENT_TUITION);
		// test getAmount()
		BigDecimal mngAmount = mngCalculator.getAmount();
		assertEquals(mngAmount.compareTo(MANAGEMENT_TUITION), 0);
	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time student StudentRecord who
	 * is in Science and Engineering college and major in Medical Device
	 * Innovation
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDeviceRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator dvcCalculator = new GradCSECreditCalculator(
				deviceRecord);
		Map<String, ReportItem> entries = dvcCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), DEVICE_TUITION);
		// test getAmount()
		BigDecimal dvcAmount = dvcCalculator.getAmount();
		assertEquals(dvcAmount.compareTo(DEVICE_TUITION), 0);
	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time student StudentRecord who
	 * is in Science and Engineering college and major in Security Technology
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSecurityRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator secCalculator = new GradCSECreditCalculator(
				securityRecord);
		Map<String, ReportItem> entries = secCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), SECURITY_TUITION);
		// test getAmount()
		BigDecimal secAmount = secCalculator.getAmount();
		assertEquals(secAmount.compareTo(SECURITY_TUITION), 0);
	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time non-resident student
	 * StudentRecord who is in Science and Engineering college and major in
	 * Financial Mathematics enrolled earlier than 2012 fall
	 * 
	 * @throws Exception
	 */
	@Test
	/**
	 */
	public void testFinancialEarlyRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator finElyCalculator = new GradCSECreditCalculator(
				financialEarlyRecord);
		Map<String, ReportItem> entries = finElyCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), FINANCIAL_EARLY_TUITION);
		// test getAmount()
		BigDecimal finAmount = finElyCalculator.getAmount();
		assertEquals(finAmount.compareTo(FINANCIAL_EARLY_TUITION), 0);
	}

	/**
	 * To test the GradCSECreditCalculator by testing the amount, name and
	 * category of "Tuition" by passing in a full time non-resident student
	 * StudentRecord who is in Science and Engineering college and major in
	 * Financial Mathematics enrolled later than 2012 fall
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFinancialLateRecord() throws Exception {
		// test name, category, amount
		GradCSECreditCalculator finLteCalculator = new GradCSECreditCalculator(
				financialLateRecord);
		Map<String, ReportItem> entries = finLteCalculator.calculateEntries();
		ReportItem item = entries.get(NAME);
		assertEquals(item.getName(), NAME);
		assertEquals(item.getCategory(), CATEGORY);
		assertEquals(item.getAmount(), FINANCIAL_LATE_TUITION);
		// test getAmount()
		BigDecimal finAmount = finLteCalculator.getAmount();
		assertEquals(finAmount.compareTo(FINANCIAL_LATE_TUITION), 0);
	}
}
