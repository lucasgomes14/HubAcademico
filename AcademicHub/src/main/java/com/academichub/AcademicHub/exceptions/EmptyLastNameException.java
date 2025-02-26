package com.academichub.AcademicHub.exceptions;

public class EmptyLastNameException extends RuntimeException {

    public EmptyLastNameException() {
        super("The last name is empty");
    }

    public EmptyLastNameException(String message) {
        super(message);
    }
}
