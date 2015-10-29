package edu.umn.csci5801.sabe.exception;

@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
    	super("The request is invalid, this students is asking for other's report");
    }
}
