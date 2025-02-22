package com.academichub.AcademicHub.exceptions;

public class ContainsSpaceException extends RuntimeException {

    public ContainsSpaceException() {
        super("The username contains a space");
    }

    public ContainsSpaceException(String message) {
        super(message);
    }
}