package com.academichub.AcademicHub.exceptions;

public class EmptyUsernameException extends RuntimeException {

    public EmptyUsernameException() {
        super("Username is empty");
    }

    public EmptyUsernameException(String message) {
        super(message);
    }
}
