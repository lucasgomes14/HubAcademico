package com.academichub.AcademicHub.exceptions;

public class CommentNullException extends RuntimeException {

    public CommentNullException() {
        super("Comment is null");
    }

    public CommentNullException(String message) {
        super(message);
    }
}