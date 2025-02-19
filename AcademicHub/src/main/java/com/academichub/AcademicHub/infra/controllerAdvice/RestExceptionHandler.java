package com.academichub.AcademicHub.infra.controllerAdvice;

import com.academichub.AcademicHub.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyEmailException.class)
    private ResponseEntity<String> emptyEmailHandler(EmptyEmailException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Email is required");
    }

    @ExceptionHandler(WrongEmailDomain.class)
    private ResponseEntity<String> wrongEmailHandler(WrongEmailDomain exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Wrong email domain");
    }

    @ExceptionHandler(ExistingEmailException.class)
    private ResponseEntity<String> existingEmailHandler(ExistingEmailException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Email already exists");
    }

    @ExceptionHandler(EmptyNameException.class)
    private ResponseEntity<String> emptyNameHandler(EmptyNameException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Name is empty");
    }

    @ExceptionHandler(EmptyLastNameException.class)
    private ResponseEntity<String> emptyLastNameLHandler(EmptyLastNameException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The last name is empty");
    }

    @ExceptionHandler(SecurityPasswordException.class)
    private ResponseEntity<String> securityPasswordHandler(SecurityPasswordException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Password must have 8 or more digits, upper and lower case letters");
    }

    @ExceptionHandler(ExistingUsernameException.class)
    private ResponseEntity<String> existingUsernameHandler(ExistingUsernameException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username already exists");
    }

    @ExceptionHandler(EmptyUsernameException.class)
    private ResponseEntity<String> emptyUsernameHandler(EmptyUsernameException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Username is empty");
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User not found");
    }

    @ExceptionHandler(ContainsSpaceException.class)
    private ResponseEntity<String> containsSpaceHandler(ContainsSpaceException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The username contains a space");
    }

    @ExceptionHandler(ContainsSpecialCharactersException.class)
    private ResponseEntity<String> containsSpecialCharactersHandler(ContainsSpecialCharactersException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Special characters found in username");
    }
}
