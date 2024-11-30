package com.academichub.AcademicHub.exceptions;

public class ExistingEmailException extends RuntimeException {

    public ExistingEmailException(String message) {
        super(message);
    }
}
