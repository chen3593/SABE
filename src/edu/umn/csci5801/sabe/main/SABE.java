package edu.umn.csci5801.sabe.main;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umn.csci5801.sabe.calculator.OverallCalculator;
import edu.umn.csci5801.sabe.exception.InvalidRequestException;
import edu.umn.csci5801.sabe.exception.UserIsNotFoundException;
import edu.umn.csci5801.sabe.interfaces.AccountReportIntf;
import edu.umn.csci5801.sabe.interfaces.SABEIntf;
import edu.umn.csci5801.sabe.interfaces.UMNDatabaseIntf;
import edu.umn.csci5801.sabe.model.Adjustment;
import edu.umn.csci5801.sabe.model.AdjustmentKind;
import edu.umn.csci5801.sabe.model.Course;
import edu.umn.csci5801.sabe.model.Role;
import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.User;

public class SABE implements SABEIntf {
	private UMNDatabaseIntf database;
	private User user;
	
	/**
	 * Use this as the StudentRecord database for future method calls. 
	 * 
	 * @param database The database to be used for getting student information.
	 *
	 */
	@Override
	public void setDatabase(UMNDatabaseIntf database) {
		this.database = database;
	}
	
	/**
	 * Log in as this user. 
	 *
	 * @param user The user that has successfully logged in
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void loginAs(User user) throws Exception {
		Set<User> allUser = database.getAllUsers();
		if (allUser.contains(user)) {
			this.user = user;
		} else {
			throw new UserIsNotFoundException();
		}
	}

	/**
	 * Gets all users. The current user must be an administrator. 
	 *
	 * @return a list of all users.
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public Set<User> getAllUsers() throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Set<User> allUser = database.getAllUsers();
		return allUser;
	}


	/**
	 * Gets the account report for the student.
	 *
	 * @param theStudent The specified student
	 * @return the account report for the student
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public AccountReportIntf getAccountReport(User theStudent) throws Exception {
		if (user.getRole() == Role.STUDENT
				&& !theStudent.getUserId().equals(user.getUserId())) {
			throw new InvalidRequestException();
		}
		StudentRecord theRecord = database.getRecordFor(theStudent);
		AccountReport result = getAccountReportByRecord(theRecord);
		return result;
	}

	/**
	 * Gets the hypothetical report for the student assuming that the student 
	 * is registered to provided list of courses.
	 *
	 * @param theStudent The specified student
	 * @param courseIds the hypothetical list of enrolled courses
	 * @return the account summary for the student
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public AccountReportIntf getHypotheticalReport(User theStudent,
			List<Course> courseIds) throws Exception {
		if (user.getRole() == Role.STUDENT
				&& !theStudent.getUserId().equals(user.getUserId())) {
			throw new InvalidRequestException();
		}
		StudentRecord theRecord = database.getRecordFor(theStudent);
		theRecord.setRegisteredCourses(courseIds);
		AccountReport result = getAccountReportByRecord(theRecord);
		return result;
	}

	/**
	 * Add a scholarship to this student's record. A value less than or equal 
	 * to zero should result in an exception of some sort. The current user 
	 * must be an administrator. 
	 * 
	 * @param theStudent 
	 * @param amount
	 * @param description A textual description of the scholarship
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void addScholarship(User theStudent, BigDecimal amount,
			String description) throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Adjustment adjustment = new Adjustment(description, amount,
				AdjustmentKind.SCHOLARSHIP);
		StudentRecord record = database.getRecordFor(theStudent);
		List<Adjustment> oldAdjustments = record.getAdjustments();
		oldAdjustments.add(adjustment);
		record.setAdjustments(oldAdjustments);
		database.replaceStudentRecord(theStudent, record);
	}

	/**
	 * Add a fee waiver to this student's record. A value less than or equal 
	 * to zero should result in an exception of some sort. The current user 
	 * must be an administrator. 
	 * 
	 * @param studentId 
	 * @param amount
	 * @param description A textual description of the waiver
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void addWaiver(User theStudent, BigDecimal amount, String description)
			throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Adjustment adjustment = new Adjustment(description, amount,
				AdjustmentKind.WAIVER);
		StudentRecord record = database.getRecordFor(theStudent);
		List<Adjustment> oldAdjustments = record.getAdjustments();
		oldAdjustments.add(adjustment);
		record.setAdjustments(oldAdjustments);
		database.replaceStudentRecord(theStudent, record);
	}
	
	/**
	 * Add a fee to this student's record. A value greater than or equal 
	 * to zero should result in an exception of some sort. The current user 
	 * must be an administrator. 
	 * 
	 * @param studentId 
	 * @param amount
	 * @param description A textual description of the fee
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void addFee(User theStudent, BigDecimal amount, String description)
			throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Adjustment adjustment = new Adjustment(description, amount,
				AdjustmentKind.FEE);
		StudentRecord record = database.getRecordFor(theStudent);
		List<Adjustment> oldAdjustments = record.getAdjustments();
		oldAdjustments.add(adjustment);
		record.setAdjustments(oldAdjustments);
		database.replaceStudentRecord(theStudent, record);
	}
	
	/**
	 * Add a bill payment to this student's record. A value less than or equal 
	 * to zero should result in an exception of some sort. The current user 
	 * must be an administrator. 
	 * 
	 * @param studentId 
	 * @param amount
	 * @param description A textual description of the scholarship
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void addReceivedPayment(User theStudent, BigDecimal amount,
			String description) throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Adjustment adjustment = new Adjustment(description, amount,
				AdjustmentKind.PAYMENT);
		StudentRecord record = database.getRecordFor(theStudent);
		List<Adjustment> oldAdjustments = record.getAdjustments();
		oldAdjustments.add(adjustment);
		record.setAdjustments(oldAdjustments);
		database.replaceStudentRecord(theStudent, record);
	}
	
	/**
	 * Add a miscellaneous adjustment to this student's record. The current user 
	 * must be an administrator. 
	 * 
	 * @param studentId 
	 * @param amount
	 * @param description A textual description of the adjustment
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void addOtherAdjustment(User theStudent, BigDecimal amount,
			String description) throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		Adjustment adjustment = new Adjustment(description, amount,
				AdjustmentKind.OTHER);
		StudentRecord record = database.getRecordFor(theStudent);
		List<Adjustment> oldAdjustments = record.getAdjustments();
		oldAdjustments.add(adjustment);
		record.setAdjustments(oldAdjustments);
		database.replaceStudentRecord(theStudent, record);
	}
	
	/**
	 * Set student note
	 * 
	 * @param student record 
	 * @param description A textual description of the note
	 * @throws Exception (as defined by implementation)
	 */
	@Override
	public void setStudentNoteField(User theStudent, String note)
			throws Exception {
		if (user.getRole() == Role.STUDENT) {
			throw new InvalidRequestException();
		}
		StudentRecord record = database.getRecordFor(theStudent);
		List<String> studentNotes = record.getNotes();
		studentNotes.add(note);
		record.setNotes(studentNotes);
		database.replaceStudentRecord(theStudent, record);
	}
	
	/**
	 * Initialize overall calculator and generate a accountReport for the student.
	 * @param record studentRecord of requested student
	 * @return the account report of certain student.
	 */
	private AccountReport getAccountReportByRecord(StudentRecord record) {
		OverallCalculator calculator = new OverallCalculator(record);
		Map<String, ReportItem> resultEntry = calculator.calculateEntries();
		BigDecimal resultAmount = calculator.getAmount();
		AccountReport accountReport = new AccountReport(resultEntry,
				resultAmount);
		return accountReport;
	}
}
