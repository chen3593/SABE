package edu.umn.csci5801.sabe.exception;

@SuppressWarnings("serial")
public class IllegalYearException extends RuntimeException{
    public IllegalYearException() {
    	super("Year is illegal");
    }
}
