package edu.umn.csci5801.sabe.exception;

@SuppressWarnings("serial")
public class UserIsNotFoundException extends RuntimeException{
    public UserIsNotFoundException() {
    	super("Couldn't find user in database");
    }
}
