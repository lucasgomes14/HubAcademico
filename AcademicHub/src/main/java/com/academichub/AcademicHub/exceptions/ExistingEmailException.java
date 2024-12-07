package com.academichub.AcademicHub.exceptions;

public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException() {
        super("Email already exists");
    }

    public ExistingEmailException(String message) {
        super(message);
    }
}
