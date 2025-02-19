package com.academichub.AcademicHub.exceptions;

public class ContainsSpecialCharactersException extends  RuntimeException {

    public ContainsSpecialCharactersException() {
        super("Special characters found in username");
    }

    public ContainsSpecialCharactersException(String message) {
        super(message);
    }
}