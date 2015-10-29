package edu.umn.csci5801.sabe.exception;

public class DegreeNotSupportException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public DegreeNotSupportException(String message) {
        super(message);
    }

    public DegreeNotSupportException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
