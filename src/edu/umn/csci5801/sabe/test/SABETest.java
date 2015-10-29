package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.database.UMNJsonDatabase;
import edu.umn.csci5801.sabe.interfaces.AccountReportIntf;
import edu.umn.csci5801.sabe.interfaces.ReportItemIntf;
import edu.umn.csci5801.sabe.interfaces.UMNDatabaseIntf;
import edu.umn.csci5801.sabe.main.SABE;
import edu.umn.csci5801.sabe.model.Adjustment;
import edu.umn.csci5801.sabe.model.AdjustmentKind;
import edu.umn.csci5801.sabe.model.Role;
import edu.umn.csci5801.sabe.model.User;

/**
 * To test administrator's functionalities
 *
 */
public class SABETest {

	private User testUserStudent, testStudentSameera, testUserAdmin;
	private UMNDatabaseIntf database;
	private SABE sabe;
	private final String TUITION_CATEGORY_NAME = "Tuition";
	private final String TUITION_NAME = "Tuition";

	/**
	 * To initialize the private variables used in unit tests
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testUserStudent = new User("John", "Weissman", "john3273", Role.STUDENT);
		testStudentSameera = new User("Sameera", "Shah", "shahx118",
				Role.STUDENT);
		testUserAdmin = new User("Gregory", "Gay", "gayx0067",
				Role.ADMINISTRATOR);
		File userFile = new File("samples/users.txt");
		File studentFile = new File("samples/students.txt");
		UMNDatabaseIntf Database = new UMNJsonDatabase(studentFile, userFile);
		this.database = Database;
		sabe = new SABE();
		sabe.setDatabase(database);
		sabe.loginAs(testUserAdmin);
	}

	/**
	 * To test the get student account report requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAccountReport() throws Exception {

		BigDecimal expectedAmount = new BigDecimal(6166.09);
		AccountReportIntf report = sabe.getAccountReport(testStudentSameera);
		BigDecimal realAmount = report.getAccountBalance();
		assertEquals(realAmount.floatValue(), expectedAmount.floatValue(), 0.0);

		Map<String, Set<ReportItemIntf>> reportItems = report
				.getItemsByCategory();
		Set<ReportItemIntf> tuitionSet = reportItems.get(TUITION_CATEGORY_NAME);

		assertEquals(tuitionSet.size(), 1);

		for (ReportItemIntf item : tuitionSet) {
			assertEquals(item.getCategory(), TUITION_CATEGORY_NAME);
			assertEquals(item.getName(), TUITION_NAME);
			float expectedValue = (float) 11840.0;
			float realValue = item.getAmount().floatValue();
			assertEquals(expectedValue, realValue, 0.0);
		}

	}

	/**
	 * To test the add note requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddNote() throws Exception {

		String TEST_NOTE = "This is a test note";
		sabe.setStudentNoteField(testUserStudent, TEST_NOTE);
		List<String> noteList = database.getRecordFor(testUserStudent)
				.getNotes();
		assertEquals(noteList.get(noteList.size() - 1), TEST_NOTE);
	}

	/**
	 * To test the add scholarship requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddScholarship() throws Exception {

		String SCHOLARSHIP_NAME = "scholarship 5000";
		sabe.addScholarship(testUserStudent, new BigDecimal(-5000.00),
				SCHOLARSHIP_NAME);
		List<Adjustment> adj = database.getRecordFor(testUserStudent)
				.getAdjustments();
		assertEquals(AdjustmentKind.SCHOLARSHIP, adj.get(adj.size() - 1)
				.getCategory());
		assertEquals(SCHOLARSHIP_NAME, adj.get(adj.size() - 1).getName());
		assertEquals(new BigDecimal(-5000), adj.get(adj.size() - 1)
				.getAmountPerSemester());

	}

	/**
	 * To test the add usage fee requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddUsageFee() throws Exception {

		String FEE_NAME = "usage fee 20";
		sabe.addFee(testUserStudent, new BigDecimal(20.00), FEE_NAME);
		List<Adjustment> adj = database.getRecordFor(testUserStudent)
				.getAdjustments();
		assertEquals(AdjustmentKind.FEE, adj.get(adj.size() - 1).getCategory());
		assertEquals(FEE_NAME, adj.get(adj.size() - 1).getName());
		assertEquals(new BigDecimal(20.00), adj.get(adj.size() - 1)
				.getAmountPerSemester());

	}

	/**
	 * To test the add exemptions requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddWaiver() throws Exception {

		String WAVIER_NAME = "exemption 2000";
		sabe.addWaiver(testUserStudent, new BigDecimal(2000.00), WAVIER_NAME);
		List<Adjustment> adj = database.getRecordFor(testUserStudent)
				.getAdjustments();
		assertEquals(AdjustmentKind.WAIVER, adj.get(adj.size() - 1)
				.getCategory());
		assertEquals(WAVIER_NAME, adj.get(adj.size() - 1).getName());
		assertEquals(new BigDecimal(2000.00), adj.get(adj.size() - 1)
				.getAmountPerSemester());

	}

	/**
	 * To test the add payment requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddPayment() throws Exception {

		String PAYMENT_NAME = "received payment 10000";
		sabe.addReceivedPayment(testUserStudent, new BigDecimal(-10000.00),
				PAYMENT_NAME);
		List<Adjustment> adj = database.getRecordFor(testUserStudent)
				.getAdjustments();
		assertEquals(AdjustmentKind.PAYMENT, adj.get(adj.size() - 1)
				.getCategory());
		assertEquals(PAYMENT_NAME, adj.get(adj.size() - 1).getName());
		assertEquals(new BigDecimal(-10000.00), adj.get(adj.size() - 1)
				.getAmountPerSemester());

	}

	/**
	 * To test the add other waiver requirement for administrators
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddOther() throws Exception {

		String OTHER_NAME = "other adjustment 1000";
		sabe.addOtherAdjustment(testUserStudent, new BigDecimal(1000.00),
				OTHER_NAME);
		List<Adjustment> adj = database.getRecordFor(testUserStudent)
				.getAdjustments();
		assertEquals(AdjustmentKind.OTHER, adj.get(adj.size() - 1)
				.getCategory());
		assertEquals(OTHER_NAME, adj.get(adj.size() - 1).getName());
		assertEquals(new BigDecimal(1000.00), adj.get(adj.size() - 1)
				.getAmountPerSemester());

	}
}
