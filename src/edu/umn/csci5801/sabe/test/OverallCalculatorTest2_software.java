/*
 * Vicky 
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

public class OverallCalculatorTest2_software {

	private StudentRecord testSoftware, testPHD;

	private final float SOFTWARE_ENGINEERING_TUITION = (float) 10390.09;
	private final float PHD_TUITION = (float) 6131.09;
	private final String GENERAL_FEES_NAME = "General Fees";
	private List<ReportItemIntf> commonItem;

	@Before
	public void setUp() throws Exception {

		testSoftware = TestUtil.loadDatabaseGet("Sofware", "Guy", "sogu1234",
				"students.txt");
		testPHD = TestUtil.loadDatabaseGet("Phd", "Guy", "phdg1234",
				"students.txt");

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

	/**
	 * @throws Exception
	 *             To test the OverallCalculator by testing the amount, name and
	 *             category by passing in a full time undergraduate student
	 *             StudentRecord who is in Science and Engineering college and
	 *             major in Software Engineering
	 */
	@Test
	public void testSoftwareRecord() throws Exception {
		OverallCalculator softCalculator = new OverallCalculator(testSoftware);
		Map<String, ReportItem> entries = softCalculator.calculateEntries();
		BigDecimal totalTuition = softCalculator.getAmount();
		assertEquals(totalTuition.floatValue(), SOFTWARE_ENGINEERING_TUITION,
				0.0);

		for (ReportItemIntf item : commonItem) {
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		/* Only for test */
		// printTable(entries);

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fee");
		assertEquals(collegeFee.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf generalFee = entries.get("Student Service Fees");
		assertNotNull(generalFee);
		assertEquals(generalFee.getName(), "Student Service Fees");
		assertEquals(generalFee.getCategory(), "General Fees");
		assertEquals(generalFee.getAmount().floatValue(), 417.91, 0.001);
		entries.remove("Student Service Fees");

		ReportItemIntf tuitionFee = entries.get("Tuition");
		assertNotNull(tuitionFee);
		assertEquals(tuitionFee.getName(), "Tuition");
		assertEquals(tuitionFee.getCategory(), "Tuition");
		assertEquals(tuitionFee.getAmount().floatValue(), 8050.0, 0.001);
		entries.remove("Tuition");

		ReportItemIntf graduateFee = entries
				.get("Graduate & Professional Student Assembly");
		assertNotNull(graduateFee);
		assertEquals(graduateFee.getName(),
				"Graduate & Professional Student Assembly");
		assertEquals(graduateFee.getCategory(), "General Fees");
		assertEquals(graduateFee.getAmount().floatValue(), 6.68, 0.001);
		entries.remove("Graduate & Professional Student Assembly");

		ReportItemIntf InternationalAidFee = entries
				.get("International Student Aid Fee");
		assertNotNull(InternationalAidFee);
		assertEquals(InternationalAidFee.getName(),
				"International Student Aid Fee");
		assertEquals(InternationalAidFee.getCategory(), "General Fees");
		assertEquals(InternationalAidFee.getAmount().floatValue(), 14, 0.001);
		entries.remove("International Student Aid Fee");

		ReportItemIntf InternationalSponFee = entries
				.get("International Sponsored Student Fee");
		assertNotNull(InternationalSponFee);
		assertEquals(InternationalSponFee.getName(),
				"International Sponsored Student Fee");
		assertEquals(InternationalSponFee.getCategory(), "General Fees");
		assertEquals(InternationalSponFee.getAmount().floatValue(), 300, 0.001);
		entries.remove("International Sponsored Student Fee");

		ReportItemIntf InternationalAminFee = entries
				.get("International Student Administrative Fee");
		assertNotNull(InternationalAminFee);
		assertEquals(InternationalAminFee.getName(),
				"International Student Administrative Fee");
		assertEquals(InternationalAminFee.getCategory(), "General Fees");
		assertEquals(InternationalAminFee.getAmount().floatValue(), 145, 0.001);
		entries.remove("International Student Administrative Fee");

		// printTable(entries);
		assertEquals(entries.isEmpty(), true);
	}

	/**
	 * @throws Exception
	 *             To test the OverallCalculator by testing the amount, name and
	 *             category by passing in a full time phd student StudentRecord
	 *             who is in Science and Engineering college and major in
	 *             Computer Science
	 */
	@Test
	public void testPhdRecord() throws Exception {
		OverallCalculator PhdCalculator = new OverallCalculator(testPHD);
		Map<String, ReportItem> entries = PhdCalculator.calculateEntries();
		BigDecimal totalTuition = PhdCalculator.getAmount();
		assertEquals(totalTuition.floatValue(), PHD_TUITION, 0.0);

		for (ReportItemIntf item : commonItem) {
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		/* Only for test */
		// printTable(entries);

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fee");
		assertEquals(collegeFee.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf generalFee = entries.get("Student Service Fees");
		assertNotNull(generalFee);
		assertEquals(generalFee.getName(), "Student Service Fees");
		assertEquals(generalFee.getCategory(), "General Fees");
		assertEquals(generalFee.getAmount().floatValue(), 417.91, 0.001);
		entries.remove("Student Service Fees");

		ReportItemIntf tuitionFee = entries.get("Tuition");
		assertNotNull(tuitionFee);
		assertEquals(tuitionFee.getName(), "Tuition");
		assertEquals(tuitionFee.getCategory(), "Tuition");
		assertEquals(tuitionFee.getAmount().floatValue(), 11840.0, 0.001);
		entries.remove("Tuition");

		ReportItemIntf graduateFee = entries
				.get("Graduate & Professional Student Assembly");
		assertNotNull(graduateFee);
		assertEquals(graduateFee.getName(),
				"Graduate & Professional Student Assembly");
		assertEquals(graduateFee.getCategory(), "General Fees");
		assertEquals(graduateFee.getAmount().floatValue(), 6.68, 0.001);
		entries.remove("Graduate & Professional Student Assembly");

		ReportItemIntf InternationalAidFee = entries
				.get("International Student Aid Fee");
		assertNotNull(InternationalAidFee);
		assertEquals(InternationalAidFee.getName(),
				"International Student Aid Fee");
		assertEquals(InternationalAidFee.getCategory(), "General Fees");
		assertEquals(InternationalAidFee.getAmount().floatValue(), 14, 0.001);
		entries.remove("International Student Aid Fee");

		ReportItemIntf InternationalSponFee = entries
				.get("International Sponsored Student Fee");
		assertNotNull(InternationalSponFee);
		assertEquals(InternationalSponFee.getName(),
				"International Sponsored Student Fee");
		assertEquals(InternationalSponFee.getCategory(), "General Fees");
		assertEquals(InternationalSponFee.getAmount().floatValue(), 300, 0.001);
		entries.remove("International Sponsored Student Fee");

		ReportItemIntf InternationalAminFee = entries
				.get("International Student Administrative Fee");
		assertNotNull(InternationalAminFee);
		assertEquals(InternationalAminFee.getName(),
				"International Student Administrative Fee");
		assertEquals(InternationalAminFee.getCategory(), "General Fees");
		assertEquals(InternationalAminFee.getAmount().floatValue(), 145, 0.001);
		entries.remove("International Student Administrative Fee");

		ReportItemIntf InsuranceWaivFee = entries.get("Insurance Waiver");
		assertNotNull(InsuranceWaivFee);
		assertEquals(InsuranceWaivFee.getName(), "Insurance Waiver");
		assertEquals(InsuranceWaivFee.getCategory(), "WAIVER");
		assertEquals(InsuranceWaivFee.getAmount().floatValue(), -1049.0, 0.001);
		entries.remove("Insurance Waiver");

		ReportItemIntf TAWaivFee = entries.get("Teaching Assistant");
		assertNotNull(TAWaivFee);
		assertEquals(TAWaivFee.getName(), "Teaching Assistant");
		assertEquals(TAWaivFee.getCategory(), "OTHER");
		assertEquals(TAWaivFee.getAmount().floatValue(), -7000.0, 0.001);
		entries.remove("Teaching Assistant");
		// printTable(entries);
		assertEquals(entries.isEmpty(), true);
	}

//	public void printTable(Map<String, ReportItem> table) {
//		Set<String> keySet = table.keySet();
//		for (String key : keySet) {
//			System.out.println(table.get(key));
//		}
//	}

}
