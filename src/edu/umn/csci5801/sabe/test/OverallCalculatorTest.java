/*
 * Raoyin 
 */
package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.OverallCalculator;
import edu.umn.csci5801.sabe.interfaces.ReportItemIntf;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.StudentRecord;

public class OverallCalculatorTest {
	private StudentRecord financialRecord, testY1ResFall, testSoftware, testMs,
			testEE, testPHD, testManagement;
	private final float FINANCIAL_TUITION = (float) 2076.09;
	private final float MED_TUITION = (float) 14037.18;
	private final float SOFTWARE_ENGINEERING_TUITION = (float) 10390.09;
	private final float CS_MASTER_TUITION = (float) 6166.09;
	private final float EE_UNDERGRAD_TUITION = (float) 5907.27;
	private final float PHD_TUITION = (float) 6131.09;
	private final float MANAGEMENT_TECH_TUITION = (float) 17248.18;
	private final String GENERAL_FEES_NAME = "General Fees";
	private List<ReportItemIntf> commonItem;

	@Before
	public void setUp() throws Exception {
		financialRecord = TestUtil.loadDatabaseGet("Financial", "Guy",
				"fngy123", "GradCredit.txt");
		testY1ResFall = TestUtil.loadDatabaseGet("appl", "a", "aaa125",
				"medical.txt");
		testSoftware = TestUtil.loadDatabaseGet("Sofware", "Guy", "sogu1234",
				"students.txt");
		testMs = TestUtil.loadDatabaseGet("Sameera", "Shah", "shahx118",
				"students.txt");
		testEE = TestUtil.loadDatabaseGet("Smith", "Mason", "smith001",
				"students.txt");
		testPHD = TestUtil.loadDatabaseGet("Phd", "Guy", "phdg1234",
				"students.txt");
		testManagement = TestUtil.loadDatabaseGet("Management", "Tech",
				"mate1234", "students.txt");

		commonItem = new LinkedList<ReportItemIntf>();
		ReportItemIntf stadiumFee = new ReportItem("Stadium Fee",
				GENERAL_FEES_NAME, new BigDecimal(12.50));
		commonItem.add(stadiumFee);
		ReportItemIntf healthPlan = new ReportItem(
				"Student Health Benefit Plan", GENERAL_FEES_NAME,
				new BigDecimal(1049.0));
		commonItem.add(healthPlan);
		ReportItemIntf transportationFee = new ReportItem("Transportation Fee",
				GENERAL_FEES_NAME, new BigDecimal(20.0));
		commonItem.add(transportationFee);
		ReportItemIntf capitalFee = new ReportItem("Capital Enhancement Fees",
				GENERAL_FEES_NAME, new BigDecimal(75.0));
		commonItem.add(capitalFee);
	}

	@Test
	public void testFinancialRecord() throws Exception {
		OverallCalculator finCalculator = new OverallCalculator(financialRecord);
		Map<String, ReportItem> entries = finCalculator.calculateEntries();
		BigDecimal finAmount = finCalculator.getAmount();
		// System.out.println("fin Amount " + finAmount.floatValue());
		assertEquals(0,
				Float.compare(FINANCIAL_TUITION, finAmount.floatValue()));
	}

	/**
	 * To test the OverallCalculator by testing the amount, name and category by
	 * passing in a full time year-1 graduate student StudentRecord who is in
	 * Medical School
	 * 
	 * @throws Exception
	 */
	@Test
	public void testYearOneMed() throws Exception {
		OverallCalculator YearOneMedCalc = new OverallCalculator(testY1ResFall);
		Map<String, ReportItem> entries = YearOneMedCalc.calculateEntries();
		BigDecimal totalTuition = YearOneMedCalc.getAmount();
		assertEquals(totalTuition.floatValue(), MED_TUITION, 0.0);

		for (ReportItemIntf item : commonItem) {
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fees");
		assertEquals(collegeFee.getAmount().floatValue(), 250.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf tuition = entries.get("Tuition");
		assertNotNull(tuition);
		assertEquals(tuition.getName(), "Tuition");
		assertEquals(tuition.getCategory(), "Tuition");
		assertEquals(tuition.getAmount().floatValue(), 12624.0, 0.0);
		entries.remove("Tuition");

		ReportItemIntf GradAssembly = entries
				.get("Graduate & Professional Student Assembly");
		assertNotNull(GradAssembly);
		assertEquals(GradAssembly.getName(),
				"Graduate & Professional Student Assembly");
		assertEquals(GradAssembly.getCategory(), "General Fees");
		assertEquals(GradAssembly.getAmount().doubleValue(), 6.68, 0.0);
		entries.remove("Graduate & Professional Student Assembly");

		ReportItemIntf ServiceFee = entries.get("Student Service Fees");
		assertNotNull(ServiceFee);
		assertEquals(ServiceFee.getName(), "Student Service Fees");
		assertEquals(ServiceFee.getCategory(), "General Fees");
		assertEquals(ServiceFee.getAmount().doubleValue(), 0.0, 0.0);
		entries.remove("Student Service Fees");

		// printTable(entries);

		assertEquals(entries.isEmpty(), true);
	}

	/**
	 * To test the OverallCalculator by testing the amount, name and category by
	 * passing in a full time graduate student StudentRecord who is in Science
	 * and Engineering college and major in Computer Science
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMaster() throws Exception {
		OverallCalculator masterCalc = new OverallCalculator(testMs);
		Map<String, ReportItem> entries = masterCalc.calculateEntries();
		BigDecimal totalTuition = masterCalc.getAmount();
		assertEquals(totalTuition.floatValue(), CS_MASTER_TUITION, 0.0);

		for (ReportItemIntf item : commonItem) {
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fee");
		assertEquals(collegeFee.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf tuition = entries.get("Tuition");
		assertNotNull(tuition);
		assertEquals(tuition.getName(), "Tuition");
		assertEquals(tuition.getCategory(), "Tuition");
		assertEquals(tuition.getAmount().floatValue(), 11840.0, 0.0);
		entries.remove("Tuition");

		ReportItemIntf GradAssembly = entries
				.get("Graduate & Professional Student Assembly");
		assertNotNull(GradAssembly);
		assertEquals(GradAssembly.getName(),
				"Graduate & Professional Student Assembly");
		assertEquals(GradAssembly.getCategory(), "General Fees");
		assertEquals(GradAssembly.getAmount().floatValue(), 6.68, 0.001);
		entries.remove("Graduate & Professional Student Assembly");

		ReportItemIntf ServiceFee = entries.get("Student Service Fees");
		assertNotNull(ServiceFee);
		assertEquals(ServiceFee.getName(), "Student Service Fees");
		assertEquals(ServiceFee.getCategory(), "General Fees");
		assertEquals(ServiceFee.getAmount().doubleValue(), 417.91, 0.0);
		entries.remove("Student Service Fees");

		ReportItemIntf InsuranceWaiver = entries.get("Insurance Waiver");
		assertNotNull(InsuranceWaiver);
		assertEquals(InsuranceWaiver.getName(), "Insurance Waiver");
		assertEquals(InsuranceWaiver.getCategory(), "WAIVER");
		assertEquals(InsuranceWaiver.getAmount().doubleValue(), -1049.0, 0.0);
		entries.remove("Insurance Waiver");

		ReportItemIntf UCardReplacement = entries.get("U Card Replacement");
		assertNotNull(UCardReplacement);
		assertEquals(UCardReplacement.getName(), "U Card Replacement");
		assertEquals(UCardReplacement.getCategory(), "WAIVER");
		assertEquals(UCardReplacement.getAmount().doubleValue(), 25.0, 0.0);
		entries.remove("U Card Replacement");

		ReportItemIntf IntlSponsorFee = entries
				.get("International Sponsored Student Fee");
		assertNotNull(IntlSponsorFee);
		assertEquals(IntlSponsorFee.getName(),
				"International Sponsored Student Fee");
		assertEquals(IntlSponsorFee.getCategory(), "General Fees");
		assertEquals(IntlSponsorFee.getAmount().doubleValue(), 300.0, 0.0);
		entries.remove("International Sponsored Student Fee");

		ReportItemIntf IntlAdminFee = entries
				.get("International Student Administrative Fee");
		assertNotNull(IntlAdminFee);
		assertEquals(IntlAdminFee.getName(),
				"International Student Administrative Fee");
		assertEquals(IntlAdminFee.getCategory(), "General Fees");
		assertEquals(IntlAdminFee.getAmount().doubleValue(), 145.0, 0.0);
		entries.remove("International Student Administrative Fee");

		ReportItemIntf TeachingAssistant = entries.get("Teaching Assistant");
		assertNotNull(TeachingAssistant);
		assertEquals(TeachingAssistant.getName(), "Teaching Assistant");
		assertEquals(TeachingAssistant.getCategory(), "OTHER");
		assertEquals(TeachingAssistant.getAmount().doubleValue(), -7000, 0.0);
		entries.remove("Teaching Assistant");

		ReportItemIntf IntlStudentAid = entries
				.get("International Student Aid Fee");
		assertNotNull(IntlStudentAid);
		assertEquals(IntlStudentAid.getName(), "International Student Aid Fee");
		assertEquals(IntlStudentAid.getCategory(), "General Fees");
		assertEquals(IntlStudentAid.getAmount().doubleValue(), 14.0, 0.0);
		entries.remove("International Student Aid Fee");

		ReportItemIntf stopPayment = entries.get("Stop Payment Fee");
		assertNotNull(stopPayment);
		assertEquals(stopPayment.getName(), "Stop Payment Fee");
		assertEquals(stopPayment.getCategory(), "WAIVER");
		assertEquals(stopPayment.getAmount().doubleValue(), 10.0, 0.0);
		entries.remove("Stop Payment Fee");
		//printTable(entries);

		assertEquals(entries.isEmpty(), true);
	}

	/**
	 * To test the OverallCalculator by testing the amount, name and category by
	 * passing in a full time undergraduate student StudentRecord who is in
	 * Science and Engineering college and major in Electrical Engineering
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEEEntries() throws Exception {
		OverallCalculator eeEntriesCalc = new OverallCalculator(testEE);
		Map<String, ReportItem> entries = eeEntriesCalc.calculateEntries();
		BigDecimal totalTuition = eeEntriesCalc.getAmount();
		assertEquals(totalTuition.floatValue(), EE_UNDERGRAD_TUITION, 0.0);

		for (ReportItemIntf item : commonItem) {
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fee");
		assertEquals(collegeFee.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf tuition = entries.get("Tuition");
		assertNotNull(tuition);
		assertEquals(tuition.getName(), "Tuition");
		assertEquals(tuition.getCategory(), "Tuition");
		assertEquals(tuition.getAmount().floatValue(), 6030.0, 0.0);
		entries.remove("Tuition");

		ReportItemIntf MNStudentAsso = entries
				.get("Minnesota Student Association");
		assertNotNull(MNStudentAsso);
		assertEquals(MNStudentAsso.getName(), "Minnesota Student Association");
		assertEquals(MNStudentAsso.getCategory(), "General Fees");
		assertEquals(MNStudentAsso.getAmount().doubleValue(), 2.86, 0.0);
		entries.remove("Minnesota Student Association");

		ReportItemIntf Waiver = entries.get("Waiver");
		assertNotNull(Waiver);
		assertEquals(Waiver.getName(), "Waiver");
		assertEquals(Waiver.getCategory(), "WAIVER");
		assertEquals(Waiver.getAmount().doubleValue(), -2000.0, 0.0);
		entries.remove("Waiver");

		ReportItemIntf ServiceFee = entries.get("Student Service Fees");
		assertNotNull(ServiceFee);
		assertEquals(ServiceFee.getName(), "Student Service Fees");
		assertEquals(ServiceFee.getCategory(), "General Fees");
		assertEquals(ServiceFee.getAmount().doubleValue(), 417.91, 0.0);
		entries.remove("Student Service Fees");

		assertEquals(entries.isEmpty(), true);
	}

	/**
	 * To test the OverallCalculator by testing the balance of a full time
	 * year-1 graduate student StudentRecord who is in Medical School
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMedRecord() throws Exception {
		OverallCalculator medCalculator = new OverallCalculator(testY1ResFall);
		Map<String, ReportItem> entries = medCalculator.calculateEntries();
		BigDecimal medAmount = medCalculator.getAmount();
		assertEquals(0, Float.compare(MED_TUITION, medAmount.floatValue()));
	}

	/**
	 * To test the OverallCalculator by testing the balance of a full time
	 * graduate student StudentRecord who is in Science and Engineering college
	 * and major in Software Engineering
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSftware() throws Exception {
		OverallCalculator seCalculator = new OverallCalculator(testSoftware);
		Map<String, ReportItem> entries = seCalculator.calculateEntries();
		BigDecimal seAmount = seCalculator.getAmount();
		// System.out.println("SE Amount " + seAmount.floatValue());
		assertEquals(
				0,
				Float.compare(SOFTWARE_ENGINEERING_TUITION,
						seAmount.floatValue()));
	}

	/**
	 * To test the OverallCalculator by testing the balance of a full time
	 * graduate student StudentRecord who is in Science and Engineering college
	 * and major in Computer Science
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMs() throws Exception {
		OverallCalculator msCalculator = new OverallCalculator(testMs);
		Map<String, ReportItem> entries = msCalculator.calculateEntries();
		BigDecimal msAmount = msCalculator.getAmount();
		// System.out.println("MS Amount " + msAmount.floatValue());
		assertEquals(0, Float.compare(CS_MASTER_TUITION, msAmount.floatValue()));
	}

	/**
	 * To test the OverallCalculator by testing the balance of a full time
	 * undergraduate student StudentRecord who is in Science and Engineering
	 * college and major in Electrical Engineering
	 * 
	 * @throws Exception
	 */
	@Test
	public void testEE() throws Exception {
		OverallCalculator eeCalculator = new OverallCalculator(testEE);
		Map<String, ReportItem> entries = eeCalculator.calculateEntries();
		BigDecimal eeAmount = eeCalculator.getAmount();
		// System.out.println("EE Amount " + eeAmount.floatValue());
		assertEquals(0,
				Float.compare(EE_UNDERGRAD_TUITION, eeAmount.floatValue()));
	}

	/**
	 * Test if the overall balance for the PHD student is as expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPHD() throws Exception {
		OverallCalculator phdCalculator = new OverallCalculator(testPHD);
		Map<String, ReportItem> entries = phdCalculator.calculateEntries();
		BigDecimal phdAmount = phdCalculator.getAmount();
		// System.out.println("PHD Amount " + phdAmount.floatValue());
		assertEquals(0, Float.compare(PHD_TUITION, phdAmount.floatValue()));
	}

	/**
	 * Test if the overall balance for the Technology Management student is as
	 * expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void testManagement() throws Exception {
		OverallCalculator mngCalculator = new OverallCalculator(testManagement);
		Map<String, ReportItem> entries = mngCalculator.calculateEntries();
		BigDecimal mngAmount = mngCalculator.getAmount();
		// System.out.println("Management Tech Amount " +
		// mngAmount.floatValue());
		assertEquals(0,
				Float.compare(MANAGEMENT_TECH_TUITION, mngAmount.floatValue()));
	}

	/**
	 * To print every reportItems in the entries
	 * 
	 * @param table
	 */
//	public void printTable(Map<String, ReportItem> table) {
//		Set<String> keySet = table.keySet();
//		for (String key : keySet) {
//			System.out.println(table.get(key));
//		}
//	}
}