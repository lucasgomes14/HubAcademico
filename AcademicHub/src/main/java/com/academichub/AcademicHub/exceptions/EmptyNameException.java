package com.academichub.AcademicHub.exceptions;

public class EmptyNameException extends RuntimeException {

    public EmptyNameException() {
        super("Name is empty");
    }

    public EmptyNameException(String message) {
        super(message);
    }
}
