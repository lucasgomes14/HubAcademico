package com.academichub.AcademicHub.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidator, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        boolean isLongEnough = value.length() > 7;

        boolean hasNumber = value.matches(".*\\d.*");

        boolean hasSpecialCharacter = value.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return isLongEnough && hasNumber && hasSpecialCharacter;

    }
}
