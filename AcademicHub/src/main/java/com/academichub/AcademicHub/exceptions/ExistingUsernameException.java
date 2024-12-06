package com.academichub.AcademicHub.exceptions;

public class ExistingUsernameException extends RuntimeException {

    public ExistingUsernameException() {
        super("Username already exists");
    }

    public ExistingUsernameException(String message) {
        super(message);
    }
}
