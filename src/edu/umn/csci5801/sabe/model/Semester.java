package edu.umn.csci5801.sabe.model;

public class Semester {
	private Term term;
	private int year;
	
	
	public Semester() {
	}
	
	public Semester(Term term, int year) {
		this.term = term;
		this.year = year;
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + year;
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
		Semester other = (Semester) obj;
		if (term != other.term)
			return false;
		if (year != other.year)
			return false;
		return true;
	}	
}
