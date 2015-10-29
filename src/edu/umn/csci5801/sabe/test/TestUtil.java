package edu.umn.csci5801.sabe.test;

import java.io.File;
import java.io.FileNotFoundException;

import com.google.gson.JsonParseException;

import edu.umn.csci5801.sabe.database.UMNJsonDatabase;
import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.User;

public class TestUtil {
	/**
	 * passing in the information of student and return the student record get
	 * from database.
	 * 
	 * @param firstName
	 *            student's first name
	 * @param lastName
	 *            student's last name
	 * @param studentID
	 *            student's ID
	 * @param studentFileName
	 *            filename of student's database
	 * @return the student record
	 * @throws FileNotFoundException
	 *             student database file is not found
	 * @throws JsonParseException
	 *             JSON parse error
	 */
	public static StudentRecord loadDatabaseGet(String firstName,
			String lastName, String studentID, String studentFileName)
			throws JsonParseException, FileNotFoundException {
		File studentFile = new File("samples/" + studentFileName);
		File userFile = new File("samples/users.txt");
		UMNJsonDatabase database = new UMNJsonDatabase(studentFile, userFile);
		User testUser = new User(firstName, lastName, studentID);
		StudentRecord record = database.getRecordFor(testUser);
		return record;
	}
}
