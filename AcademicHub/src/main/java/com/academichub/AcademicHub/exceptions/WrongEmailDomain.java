package com.academichub.AcademicHub.exceptions;

public class WrongEmailDomain extends RuntimeException {
    public WrongEmailDomain() {
        super("Wrong Email Domain");
    }

    public WrongEmailDomain(String message) {
        super(message);
    }
}
