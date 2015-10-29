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

public class OverallCalculatorTestFinancialGuy {
	private StudentRecord financialRecord;
	private final float FINANCIAL_TUITION = (float) 2076.09;
	private final String GENERAL_FEES_NAME = "General Fees";
	private List<ReportItemIntf> commonItem;

	@Before
	public void setUp() throws Exception {
		financialRecord = TestUtil.loadDatabaseGet("Financial", "Guy",
				"fngy123", "GradCredit.txt");
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

		ReportItemIntf taWaiver = new ReportItem("Teaching Assistant", "OTHER",
				new BigDecimal(-7000));
		commonItem.add(taWaiver);

		ReportItemIntf interAid = new ReportItem(
				"International Student Aid Fee", GENERAL_FEES_NAME,
				new BigDecimal(14.0));
		commonItem.add(interAid);

		ReportItemIntf serviceFee = new ReportItem("Student Service Fees",
				GENERAL_FEES_NAME, new BigDecimal(417.91));
		commonItem.add(serviceFee);
	}

	/**
	 * To test the OverallCalculator by testing the amount, name and category by
	 * passing in a full time graduate student StudentRecord who is in Science
	 * and Engineering college and major in Financial Mathematics
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFinancialRecord() throws Exception {
		OverallCalculator finCalculator = new OverallCalculator(financialRecord);
		Map<String, ReportItem> entries = finCalculator.calculateEntries();
		BigDecimal totalTuition = finCalculator.getAmount();
		assertEquals(totalTuition.floatValue(), FINANCIAL_TUITION, 0.001);

		//printTable(entries);

		for (ReportItemIntf item : commonItem) {
			//System.out.println(item);
			assertEquals(entries.containsValue(item), true);
			entries.remove(item.getName());
		}

		ReportItemIntf collegeFee = entries.get("Collegiate Fee");
		assertNotNull(collegeFee);
		assertEquals(collegeFee.getName(), "Collegiate Fee");
		assertEquals(collegeFee.getCategory(), "College and Program Fee");
		assertEquals(collegeFee.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("Collegiate Fee");

		ReportItemIntf insuranceWaiver = entries.get("Insurance Waiver");
		assertNotNull(insuranceWaiver);
		assertEquals(insuranceWaiver.getName(), "Insurance Waiver");
		assertEquals(insuranceWaiver.getCategory(), "WAIVER");
		assertEquals(insuranceWaiver.getAmount().floatValue(), -1049.0, 0.0);
		entries.remove("Insurance Waiver");

		ReportItemIntf tuition = entries.get("Tuition");
		assertNotNull(tuition);
		assertEquals(tuition.getName(), "Tuition");
		assertEquals(tuition.getCategory(), "Tuition");
		assertEquals(tuition.getAmount().floatValue(), 7785.0, 0.0);
		entries.remove("Tuition");

		ReportItemIntf gradPro = entries
				.get("Graduate & Professional Student Assembly");
		assertNotNull(gradPro);
		assertEquals(gradPro.getName(),
				"Graduate & Professional Student Assembly");
		assertEquals(gradPro.getCategory(), "General Fees");
		assertEquals(gradPro.getAmount().floatValue(), 6.68, 0.001);
		entries.remove("Graduate & Professional Student Assembly");

		ReportItemIntf interSponsored = entries
				.get("International Sponsored Student Fee");
		assertNotNull(interSponsored);
		assertEquals(interSponsored.getName(),
				"International Sponsored Student Fee");
		assertEquals(interSponsored.getCategory(), "General Fees");
		assertEquals(interSponsored.getAmount().floatValue(), 300.0, 0.0);
		entries.remove("International Sponsored Student Fee");

		ReportItemIntf interAdmin = entries
				.get("International Student Administrative Fee");
		assertNotNull(interAdmin);
		assertEquals(interAdmin.getName(),
				"International Student Administrative Fee");
		assertEquals(interAdmin.getCategory(), "General Fees");
		assertEquals(interAdmin.getAmount().floatValue(), 145.0, 0.0);
		entries.remove("International Student Administrative Fee");

		ReportItemIntf teach = entries
				.get("International Student Administrative Fee");
		assertNotNull(interAdmin);
		assertEquals(interAdmin.getName(),
				"International Student Administrative Fee");
		assertEquals(interAdmin.getCategory(), "General Fees");
		assertEquals(interAdmin.getAmount().floatValue(), 145.0, 0.0);
		entries.remove("International Student Administrative Fee");

		//printTable(entries);
		assertEquals(entries.isEmpty(), true);
	}

//	public void printTable(Map<String, ReportItem> table) {
//		Set<String> keySet = table.keySet();
//		for (String key : keySet) {
//			System.out.println(table.get(key));
//		}
//	}
}
