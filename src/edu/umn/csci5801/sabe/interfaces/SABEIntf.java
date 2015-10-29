package edu.umn.csci5801.sabe.interfaces;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import edu.umn.csci5801.sabe.model.Course;
import edu.umn.csci5801.sabe.model.User;

/**
 * Expected interface for SABE. You are expected to create a class
 * edu.umn.csci5801.SABE which implements this interface, and can be 
 * constructed with no arguments (`new SABE();`). 
 * @author jbiatek
 *
 */
public interface SABEIntf {
	
	/**
	 * Use this as the StudentRecord database for future method calls. 
	 * 
	 * @param database The database to be used for getting student information.
	 */
	public void setDatabase(UMNDatabaseIntf database);

	/**
	 * Log in as this user. 
	 *
	 * @param user The user that has successfully logged in
	 * @throws Exception (as defined by implementation)
	 */
	public void loginAs(User user) throws Exception;
	
	/**
	 * Gets all users. The current user must be an administrator. 
	 *
	 * @return a list of all users.
	 * @throws Exception (as defined by implementation)
	 */
	public Set<User> getAllUsers() throws Exception;
	

	/**
	 * Gets the account report for the student.
	 *
	 * @param theStudent The specified student
	 * @return the account report for the student
	 * @throws Exception (as defined by implementation)
	 */
	public AccountReportIntf getAccountReport(User theStudent) throws Exception;
	
	/**
	 * Gets the hypothetical report for the student assuming that the student 
	 * is registered to provided list of courses.
	 *
	 * @param theStudent The specified student
	 * @param courseIds the hypothetical list of enrolled courses
	 * @return the account summary for the student
	 * @throws Exception (as defined by implementation)
	 */
	public AccountReportIntf getHypotheticalReport(User theStudent, List<Course> courseIds) throws Exception;

	
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
	public void addScholarship(User theStudent, BigDecimal amount, String description) throws Exception;
	
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
	public void addWaiver(User theStudent, BigDecimal amount, String description) throws Exception;
	
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
	public void addFee(User theStudent, BigDecimal amount, String description) throws Exception;
	
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
	public void addReceivedPayment(User theStudent, BigDecimal amount, String description) throws Exception;
	
	/**
	 * Add a miscellaneous adjustment to this student's record. The current user 
	 * must be an administrator. 
	 * 
	 * @param studentId 
	 * @param amount
	 * @param description A textual description of the adjustment
	 * @throws Exception (as defined by implementation)
	 */
	public void addOtherAdjustment(User theStudent, BigDecimal amount, String description) throws Exception;
	
	/**
	 * Set the note stored in a student's record.The current user 
	 * must be an administrator. 
	 *
	 * @param studentId the student's login name
	 * @param note the new value for the note field. 
	 */
	public void setStudentNoteField(User theStudent, String note) throws Exception;
}