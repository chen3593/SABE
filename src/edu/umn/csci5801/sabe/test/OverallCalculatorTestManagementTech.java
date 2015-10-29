package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

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

public class OverallCalculatorTestManagementTech {
	private StudentRecord managementTech;
	private final float MANAGEMENT_TECH_TUITION = (float) 17248.18;
	private final String GENERAL_FEES_NAME = "General Fees";
	private List<ReportItemIntf> commonItem;

	@Before
	public void setUp() throws Exception {
		managementTech = TestUtil.loadDatabaseGet("Management", "Tech",
				"mate1234", "students.txt");
		commonItem = new LinkedList<ReportItemIntf>();

		ReportItemIntf interAdmin = new ReportItem(
				"International Student Administrative Fee", GENERAL_FEES_NAME,
				new BigDecimal(145.0));
		commonItem.add(interAdmin);

		ReportItemIntf interSpon = new ReportItem(
				"International Sponsored Student Fee", GENERAL_FEES_NAME,
				new BigDecimal(300.0));
		commonItem.add(interSpon);

		ReportItemIntf collegiateFee = new ReportItem("Collegiate Fee",
				"College and Program Fee", new BigDecimal(150.0));
		commonItem.add(collegiateFee);

		ReportItemIntf healthPlan = new ReportItem(
				"Student Health Benefit Plan", GENERAL_FEES_NAME,
				new BigDecimal(1049.0));
		commonItem.add(healthPlan);

		ReportItemIntf insuranceWaiver = new ReportItem("Insurance Waiver",
				"WAIVER", new BigDecimal(-1049.0));
		commonItem.add(insuranceWaiver);

		ReportItemIntf stadiumFee = new ReportItem("Stadium Fee",
				GENERAL_FEES_NAME, new BigDecimal(12.50));
		commonItem.add(stadiumFee);

		ReportItemIntf capitalFee = new ReportItem("Capital Enhancement Fees",
				GENERAL_FEES_NAME, new BigDecimal(75.0));
		commonItem.add(capitalFee);

		ReportItemIntf interAid = new ReportItem(
				"International Student Aid Fee", GENERAL_FEES_NAME,
				new BigDecimal(14.0));
		commonItem.add(interAid);

		ReportItemIntf gradProStudent = new ReportItem(
				"Graduate & Professional Student Assembly", GENERAL_FEES_NAME,
				new BigDecimal(6.68));
		commonItem.add(gradProStudent);

		ReportItemIntf tuition = new ReportItem("Tuition", "Tuition",
				new BigDecimal(16525.0));
		commonItem.add(tuition);

		ReportItemIntf transportationFee = new ReportItem("Transportation Fee",
				GENERAL_FEES_NAME, new BigDecimal(20.0));
		commonItem.add(transportationFee);

		ReportItemIntf serviceFee = new ReportItem("Student Service Fees",
				GENERAL_FEES_NAME, new BigDecimal(0));
		commonItem.add(serviceFee);
	}

	/**
	 * To test the OverallCalculator by testing the balance by passing in a full
	 * time graduate student StudentRecord who is in Science and Engineering
	 * college and major in Financial Mathematics
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFinancialRecord() throws Exception {
		OverallCalculator finCalculator = new OverallCalculator(managementTech);
		Map<String, ReportItem> entries = finCalculator.calculateEntries();
		BigDecimal totalTuition = finCalculator.getAmount();
		assertEquals(totalTuition.floatValue(), MANAGEMENT_TECH_TUITION, 0.001);

		//printTable(entries);

		for (ReportItemIntf item : commonItem) {
			//System.out.println(item);
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}
		assertEquals(entries.isEmpty(), true);
	}

//	public void printTable(Map<String, ReportItem> table) {
//		Set<String> keySet = table.keySet();
//		for (String key : keySet) {
//			System.out.println(table.get(key));
//		}
//	}
}
