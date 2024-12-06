package com.academichub.AcademicHub.exceptions;

public class ExistingUsernameException extends RuntimeException {

    public ExistingUsernameException(String message) {
        super(message);
    }
}
