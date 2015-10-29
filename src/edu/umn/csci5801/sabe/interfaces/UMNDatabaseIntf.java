package edu.umn.csci5801.sabe.interfaces;

import java.util.Set;

import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.User;

public interface UMNDatabaseIntf {

	/**
	 * @return A set of all Users in this database. This set is immutable, 
	 * if you try to modify it an exception will be thrown.
	 */
	public Set<User> getAllUsers();
	
	/**
	 * @param student The student whose record is to be retreived.
	 * @return The student's record, or null if that student does not exist in
	 * the database. 
	 */
	public StudentRecord getRecordFor(User student);
	
	/**
	 * @param student The student whose record is being updated
 	 * @param record The new record, which will replace the old one.
	 */
	public void replaceStudentRecord(User student, StudentRecord record);
}
