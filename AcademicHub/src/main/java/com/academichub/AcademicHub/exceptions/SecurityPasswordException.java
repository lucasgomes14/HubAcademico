package com.academichub.AcademicHub.exceptions;

public class SecurityPasswordException extends RuntimeException {

    public SecurityPasswordException() {
        super("Password must have 8 or more digits, upper and lower case letters\n");
    }

    public SecurityPasswordException(String message) {
        super(message);
    }
}
