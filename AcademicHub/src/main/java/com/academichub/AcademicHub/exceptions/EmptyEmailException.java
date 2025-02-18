package com.academichub.AcademicHub.exceptions;

public class EmptyEmailException extends RuntimeException {
    public EmptyEmailException() {
        super("Email is empty");
    }
    public EmptyEmailException(String message) {
        super(message);
    }
}
