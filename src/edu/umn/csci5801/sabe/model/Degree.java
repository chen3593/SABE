package edu.umn.csci5801.sabe.model;

public class Degree {
	
	private DegreeProgram program;
	private Major major;
	private College college;
	
	public Degree() {}
	
	public Degree(DegreeProgram program, Major major, College college) {
		super();
		this.program = program;
		this.major = major;
		this.college = college;
	}

	public DegreeProgram getProgram() {
		return program;
	}

	public void setProgram(DegreeProgram program) {
		this.program = program;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((college == null) ? 0 : college.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
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
		Degree other = (Degree) obj;
		if (college != other.college)
			return false;
		if (major != other.major)
			return false;
		if (program != other.program)
			return false;
		return true;
	}
	
}
