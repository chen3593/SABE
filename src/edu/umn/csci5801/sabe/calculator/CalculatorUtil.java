package edu.umn.csci5801.sabe.calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.umn.csci5801.sabe.main.ReportItem;
import edu.umn.csci5801.sabe.model.Course;
import edu.umn.csci5801.sabe.model.StudentRecord;

/* Created by Yao */

public class CalculatorUtil {
	/**
	 * This function combines two Maps into one
	 * @param srcMap source map
	 * @param dstMap destination map
	 * @return the combination of two maps
	 */
	public static Map<String, ReportItem> combineTwoItemLists(
			Map<String, ReportItem> srcMap, Map<String, ReportItem> dstMap) {
		Map<String, ReportItem> item = new HashMap<String, ReportItem>(srcMap);
		item.putAll(dstMap);
		return item;
	}
	
	/**
	 * This function calculate the entries using the calculator and old entries passed in and return it
	 * @param calculator the calculator used in this process
	 * @param entries old <name, reportItem> table
	 * @return new <name, reportItem> table
	 */
	public static Map<String, ReportItem> updateEntry(CalculatorIntf calculator,
			Map<String, ReportItem> entries) {
		Map<String, ReportItem> calculatedEntries = calculator.calculateEntries();
		Map<String, ReportItem> newEntries = combineTwoItemLists(calculatedEntries,
				entries);
		return newEntries;
	}
	
	/**
	 * Passing in a studentRecord and calculate the total credit of registered courses
	 * @param record record of student
	 * @return the total credit of all courses
	 */
	public static int calculateTotalCredit(StudentRecord record) {
		List<Course> courseList = record.getRegisteredCourses();
		int totalCredit = 0;
		for (Course course : courseList) {
			totalCredit += course.getCredits();
		}
		return totalCredit;
	}
	
	/**
	 * Passing in a studentRecord and calculate the total credit of ODL courses
	 * @param record record of student
	 * @return total credit of ODL courses
	 */
	public static int calculateODLCredit(StudentRecord record) {
		List<Course> courseList = record.getRegisteredCourses();
		int ODLCredit = 0;
		for (Course course : courseList) {
			if(course.isODLCourse()) {
				ODLCredit+=course.getCredits();
			}
		}
		return ODLCredit;
	}
}
