package edu.umn.csci5801.sabe.model;

import java.util.List;

public class StudentRecord {

	private User student;
	
	private Semester semesterAdmitted;
	
	private boolean isMinnesotaResident;
	
	private boolean isInternationalStudent;
	
	private boolean isInsuredOutsideOfUMN;

	private Degree degreeEnrolledIn;
	
	private List<Course> registeredCourses;
	
	private List<Adjustment> adjustments;
	
	private List<String> notes;

	public StudentRecord() {
		
	}


	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Semester getSemesterAdmitted() {
		return semesterAdmitted;
	}

	public void setSemesterAdmitted(Semester semesterAdmitted) {
		this.semesterAdmitted = semesterAdmitted;
	}

	public boolean isMinnesotaResident() {
		return isMinnesotaResident;
	}

	public void setMinnesotaResident(boolean isMinnesotaResident) {
		this.isMinnesotaResident = isMinnesotaResident;
	}

	public boolean isInternationalStudent() {
		return isInternationalStudent;
	}

	public void setInternationalStudent(boolean isInternationalStudent) {
		this.isInternationalStudent = isInternationalStudent;
	}

	public Degree getDegreeEnrolledIn() {
		return degreeEnrolledIn;
	}

	public void setDegreeEnrolledIn(Degree degreeEnrolledIn) {
		this.degreeEnrolledIn = degreeEnrolledIn;
	}

	public List<Course> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<Course> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public List<Adjustment> getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(List<Adjustment> adjustments) {
		this.adjustments = adjustments;
	}

	public List<String> getNotes() {
		return notes;
	}

	public void setNotes(List<String> notes) {
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((adjustments == null) ? 0 : adjustments.hashCode());
		result = prime
				* result
				+ ((degreeEnrolledIn == null) ? 0 : degreeEnrolledIn.hashCode());
		result = prime * result + (isInternationalStudent ? 1231 : 1237);
		result = prime * result + (isMinnesotaResident ? 1231 : 1237);
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime
				* result
				+ ((registeredCourses == null) ? 0 : registeredCourses
						.hashCode());
		result = prime
				* result
				+ ((semesterAdmitted == null) ? 0 : semesterAdmitted.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentRecord other = (StudentRecord) obj;
		if (adjustments == null) {
			if (other.adjustments != null)
				return false;
		} else if (!adjustments.equals(other.adjustments))
			return false;
		if (degreeEnrolledIn == null) {
			if (other.degreeEnrolledIn != null)
				return false;
		} else if (!degreeEnrolledIn.equals(other.degreeEnrolledIn))
			return false;
		if (isInternationalStudent != other.isInternationalStudent)
			return false;
		if (isMinnesotaResident != other.isMinnesotaResident)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (registeredCourses == null) {
			if (other.registeredCourses != null)
				return false;
		} else if (!registeredCourses.equals(other.registeredCourses))
			return false;
		if (semesterAdmitted == null) {
			if (other.semesterAdmitted != null)
				return false;
		} else if (!semesterAdmitted.equals(other.semesterAdmitted))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}


	public boolean isInsuredOutsideOfUMN() {
		return isInsuredOutsideOfUMN;
	}


	public void setInsuredOutsideOfUMN(boolean isInsuredOutsideOfUMN) {
		this.isInsuredOutsideOfUMN = isInsuredOutsideOfUMN;
	}
}
