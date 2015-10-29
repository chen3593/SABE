package edu.umn.csci5801.sabe.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import edu.umn.csci5801.sabe.interfaces.UMNDatabaseIntf;
import edu.umn.csci5801.sabe.model.Adjustment;
import edu.umn.csci5801.sabe.model.Course;
import edu.umn.csci5801.sabe.model.Semester;
import edu.umn.csci5801.sabe.model.StudentRecord;
import edu.umn.csci5801.sabe.model.User;

public class UMNJsonDatabase implements UMNDatabaseIntf {
	
	private Set<User> allUsers = new HashSet<User>();
	private Map<User, StudentRecord> studentRecords = new HashMap<User, StudentRecord>();

	public UMNJsonDatabase(File studentFile, File userFile) throws JsonParseException, FileNotFoundException {
		// Load data from JSON files
		Set<StudentRecord> studentSet = new Gson().fromJson( 
				new FileReader( studentFile), 
				new TypeToken<HashSet<StudentRecord>>(){}.getType());
		
		allUsers = new Gson().fromJson( 
				new FileReader( userFile), 
				new TypeToken<HashSet<User>>(){}.getType());
		
		// Pull the user info out of the database
		for (StudentRecord record : studentSet) {
			studentRecords.put(record.getStudent(), record);
		}
	}
	
	@Override
	public Set<User> getAllUsers() {
		return Collections.unmodifiableSet(allUsers);
	}

	@Override
	public StudentRecord getRecordFor(User student) {
		for (User user : studentRecords.keySet()) {
			if (user.getUserId().equals(student.getUserId())) {
				return cloneRecord(studentRecords.get(user));
			}
		}
		return null;
	}

	@Override
	public void replaceStudentRecord(User student, StudentRecord record) {
		studentRecords.put(student, record);
	}
	
	private static StudentRecord cloneRecord(StudentRecord record) {
		StudentRecord clone = new StudentRecord();
		
		clone.setAdjustments(cloneAdjustments(record.getAdjustments()));
		clone.setDegreeEnrolledIn(record.getDegreeEnrolledIn());
		clone.setInsuredOutsideOfUMN(record.isInsuredOutsideOfUMN());
		clone.setInternationalStudent(record.isInternationalStudent());
		clone.setMinnesotaResident(record.isMinnesotaResident());
		clone.setNotes(new ArrayList<String>(record.getNotes()));
		clone.setRegisteredCourses(cloneCourses(record.getRegisteredCourses()));
		clone.setSemesterAdmitted(new Semester(record.getSemesterAdmitted().getTerm(), 
				record.getSemesterAdmitted().getYear()));
		clone.setStudent(new User(record.getStudent().getFirstName(), 
				record.getStudent().getLastName(), 
				record.getStudent().getUserId(),
				record.getStudent().getRole()));
		
		return clone;
	}
	
	private static List<Course> cloneCourses(List<Course> originalList) {
		List<Course> newList = new ArrayList<Course>();
		for (Course c : originalList) {
			Course newCourse = new Course();
			newCourse.setCredits(c.getCredits());
			newCourse.setLevel(c.getLevel());
			newCourse.setMajor(c.getMajor());
			newCourse.setName(c.getName());
			newCourse.setODLCourse(c.isODLCourse());
			newList.add(newCourse);
		}
		return newList;
	}

	private static List<Adjustment> cloneAdjustments(List<Adjustment> originalList) {
		List<Adjustment> newList = new ArrayList<Adjustment>();
		for (Adjustment a : originalList) {
			newList.add(new Adjustment(a.getName(), 
					a.getAmountPerSemester(), a.getCategory()));
		}
		return newList;
	}

}
