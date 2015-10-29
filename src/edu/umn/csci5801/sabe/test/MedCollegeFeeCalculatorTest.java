package edu.umn.csci5801.sabe.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import edu.umn.csci5801.sabe.calculator.MedCollegeFeeCalculator;
import edu.umn.csci5801.sabe.database.UMNJsonDatabase;
import edu.umn.csci5801.sabe.model.College;
import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.User;

public class MedCollegeFeeCalculatorTest {

	StudentRecord phdRecord, undergradRecord;
	private static final float PHD_MED_FEE = (float) 250.0;
	private static final float BACHELOR_MED_FEE = (float) 300.0;

	@Before
	public void setUp() throws Exception {
		File studentFile = new File("samples/students.txt");
		File userFile = new File("samples/users.txt");
		UMNJsonDatabase database = new UMNJsonDatabase(studentFile, userFile);
		User phdUser = new User("John", "May", "john234");
		User undergradUser = new User("Eric", "Wang", "Wang1234");
		phdRecord = database.getRecordFor(phdUser);
		undergradRecord = database.getRecordFor(undergradUser);
	}

	/**
	 * To test the MedCollegeFeeCalculator by testing the amount of medical
	 * college fee and degree enrolled in by passing in a PHD or undergraduate
	 * StudentRecord who is in medical college
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRecord() throws Exception {
		MedCollegeFeeCalculator phdMedFee = new MedCollegeFeeCalculator(
				phdRecord);
		MedCollegeFeeCalculator undergradeMedFee = new MedCollegeFeeCalculator(
				undergradRecord);
		phdMedFee.calculateEntries();
		undergradeMedFee.calculateEntries();
		assertEquals(0,
				Float.compare(PHD_MED_FEE, phdMedFee.getAmount().floatValue()));
		assertEquals(phdRecord.getDegreeEnrolledIn().getCollege(),
				College.MEDICAL);
		assertEquals(0, Float.compare(BACHELOR_MED_FEE, undergradeMedFee
				.getAmount().floatValue()));
		assertEquals(undergradRecord.getDegreeEnrolledIn().getCollege(),
				College.MEDICAL);
	}

}
