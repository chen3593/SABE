package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.database.UMNJsonDatabase;
import edu.umn.csci5801.sabe.interfaces.AccountReportIntf;
import edu.umn.csci5801.sabe.interfaces.ReportItemIntf;
import edu.umn.csci5801.sabe.interfaces.UMNDatabaseIntf;
import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.main.SABE;
import edu.umn.csci5801.sabe.model.Course;
import edu.umn.csci5801.sabe.model.Major;
import edu.umn.csci5801.sabe.model.Role;
import edu.umn.csci5801.sabe.model.User;

/**
 * To test student's functionalities
 *
 */
public class SABEStudentTest {

	private User testUserStudent;
	private File userFile;
	private File studentFile;
	private UMNDatabaseIntf database;
	private SABE sabe;
	private final String TUITION_CATEGORY_NAME = "Tuition";
	private final String TUITION_NAME = "Tuition";
	private final String GENERAL_FEES_NAME = "General Fees";
	private final String COLLEGE_FEE_NAME = "College and Program Fee";
	private final String WAIVER_NAME = "WAIVER";
	private final String OTHER_NAME = "OTHER";

	@Before
	public void setUp() throws Exception {
		testUserStudent = new User("Sameera", "Shah", "shahx118", Role.STUDENT);
		userFile = new File("samples/users.txt");
		studentFile = new File("samples/students.txt");
		database = new UMNJsonDatabase(studentFile, userFile);
		sabe = new SABE();
		sabe.setDatabase(database);
		sabe.loginAs(testUserStudent);
	}

	/**
	 * To test the get account report function by passing in several student
	 * records and check if all the entries of the report are as expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAccountReport() throws Exception {
		BigDecimal expectedAmount = new BigDecimal(6166.09);
		AccountReportIntf report = sabe.getAccountReport(testUserStudent);
		BigDecimal realAmount = report.getAccountBalance();
		assertEquals(realAmount.floatValue(), expectedAmount.floatValue(), 0.0);

		Map<String, Set<ReportItemIntf>> reportItems = report
				.getItemsByCategory();
		// printReport(reportItems);

		Set<ReportItemIntf> tuitionSet = reportItems.get(TUITION_CATEGORY_NAME);
		assertEquals(tuitionSet.size(), 1);
		for (ReportItemIntf item : tuitionSet) {
			assertEquals(item.getCategory(), TUITION_CATEGORY_NAME);
			assertEquals(item.getName(), TUITION_NAME);
			float expectedValue = (float) 11840.0;
			float realValue = item.getAmount().floatValue();
			assertEquals(expectedValue, realValue, 0.0);
		}

		Set<ReportItemIntf> generalFeeSet = reportItems.get(GENERAL_FEES_NAME);
		Set<ReportItemIntf> expectedGeneralFeeSet = new HashSet<ReportItemIntf>();
		ReportItemIntf stadiumFee = new ReportItem("Stadium Fee",
				GENERAL_FEES_NAME, new BigDecimal(12.50));
		expectedGeneralFeeSet.add(stadiumFee);

		ReportItemIntf healthPlan = new ReportItem(
				"Student Health Benefit Plan", GENERAL_FEES_NAME,
				new BigDecimal(1049.0));
		expectedGeneralFeeSet.add(healthPlan);

		ReportItemIntf gradProAssembly = new ReportItem(
				"Graduate & Professional Student Assembly", GENERAL_FEES_NAME,
				new BigDecimal(6.68));
		expectedGeneralFeeSet.add(gradProAssembly);

		ReportItemIntf serviceFee = new ReportItem("Student Service Fees",
				GENERAL_FEES_NAME, new BigDecimal(417.91));
		expectedGeneralFeeSet.add(serviceFee);

		ReportItemIntf transportationFee = new ReportItem("Transportation Fee",
				GENERAL_FEES_NAME, new BigDecimal(20.0));
		expectedGeneralFeeSet.add(transportationFee);

		ReportItemIntf interSpon = new ReportItem(
				"International Sponsored Student Fee", GENERAL_FEES_NAME,
				new BigDecimal(300.0));
		expectedGeneralFeeSet.add(interSpon);

		ReportItemIntf interAdmin = new ReportItem(
				"International Student Administrative Fee", GENERAL_FEES_NAME,
				new BigDecimal(145.0));
		expectedGeneralFeeSet.add(interAdmin);

		ReportItemIntf interAidFee = new ReportItem(
				"International Student Aid Fee", GENERAL_FEES_NAME,
				new BigDecimal(14.0));
		expectedGeneralFeeSet.add(interAidFee);

		ReportItemIntf capitalFee = new ReportItem("Capital Enhancement Fees",
				GENERAL_FEES_NAME, new BigDecimal(75.0));
		expectedGeneralFeeSet.add(capitalFee);

		assertTrue(expectedGeneralFeeSet.equals(generalFeeSet));

		Set<ReportItemIntf> collegeFeeSet = reportItems.get(COLLEGE_FEE_NAME);
		Set<ReportItemIntf> expectedCollegeFeeSet = new HashSet<ReportItemIntf>();
		ReportItemIntf collegeFee = new ReportItem("Collegiate Fee",
				COLLEGE_FEE_NAME, new BigDecimal(300.0));
		expectedCollegeFeeSet.add(collegeFee);

		assertTrue(expectedCollegeFeeSet.equals(collegeFeeSet));

		Set<ReportItemIntf> waiverSet = reportItems.get(WAIVER_NAME);
		Set<ReportItemIntf> expectedWaiverSet = new HashSet<ReportItemIntf>();
		ReportItemIntf UCard = new ReportItem("U Card Replacement",
				WAIVER_NAME, new BigDecimal(25.0));
		expectedWaiverSet.add(UCard);

		ReportItemIntf insuranceWaiver = new ReportItem("Insurance Waiver",
				WAIVER_NAME, new BigDecimal(-1049.0));
		expectedWaiverSet.add(insuranceWaiver);

		ReportItemIntf stopPayment = new ReportItem("Stop Payment Fee",
				WAIVER_NAME, new BigDecimal(10.0));
		expectedWaiverSet.add(stopPayment);

		assertEquals(expectedWaiverSet, waiverSet);

		Set<ReportItemIntf> otherSet = reportItems.get(OTHER_NAME);
		Set<ReportItemIntf> expectedOtherSet = new HashSet<ReportItemIntf>();
		ReportItemIntf TA = new ReportItem("Teaching Assistant", OTHER_NAME,
				new BigDecimal(-7000.0));
		expectedOtherSet.add(TA);

		assertEquals(expectedOtherSet, otherSet);
	}

	/**
	 * To test the get hypothetical report function by passing in several student
	 * records and check if all the entries of the report are as expected
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetHypotheticalReport() throws Exception {
		Course testCourse = new Course();
		testCourse.setCredits(3);
		testCourse.setLevel(5);
		testCourse.setMajor(Major.SOFTWARE_ENGINEERING);
		testCourse.setName("Distributed System");
		testCourse.setODLCourse(false);
		List<Course> courseLst = new ArrayList<Course>();
		courseLst.add(testCourse);
		AccountReportIntf report = sabe.getHypotheticalReport(testUserStudent,
				courseLst);
		
		BigDecimal expectedAmount = new BigDecimal(-321.83);
		BigDecimal realAmount = report.getAccountBalance();
		assertEquals(realAmount.floatValue(), expectedAmount.floatValue(), 0.0);
		
		printReport(report.getItemsByCategory());
		
		Map<String, Set<ReportItemIntf>> items = report.getItemsByCategory();
		Set<ReportItemIntf> expectedTuitionSet = new HashSet<ReportItemIntf>();
		
		ReportItemIntf tuition = new ReportItem(TUITION_NAME,
				TUITION_CATEGORY_NAME, new BigDecimal(5919.99));
		expectedTuitionSet.add(tuition);
		assertEquals(expectedTuitionSet, items.get(TUITION_CATEGORY_NAME));
		
		Set<ReportItemIntf> generalFeeSet = items.get(GENERAL_FEES_NAME);
		boolean hasServiceFee = false;
		for(ReportItemIntf item : generalFeeSet) {
			if (item.getName().equals("Student Service Fees")) {
				assertEquals(item.getAmount().floatValue(), 0.0, 0.0);
				hasServiceFee = true;
			}
		}
		assertEquals(hasServiceFee, true);
		
		Set<ReportItemIntf> collegeFeeSet = items.get(COLLEGE_FEE_NAME);
		boolean hasCollegeFee = false;
		for(ReportItemIntf item : collegeFeeSet) {
			if (item.getName().equals("Collegiate Fee")) {
				assertEquals(item.getAmount().floatValue(), 150.0, 0.0);
				hasCollegeFee = true;
			}
		}
		assertEquals(hasCollegeFee, true);
	}

	private void printReport(Map<String, Set<ReportItemIntf>> reportItems) {
		Set<String> keyset = reportItems.keySet();
		for (String i : keyset) {
			System.out.println(i + ":");
			for (ReportItemIntf item : reportItems.get(i)) {
				System.out.println(item);
			}
		}
	}
}
