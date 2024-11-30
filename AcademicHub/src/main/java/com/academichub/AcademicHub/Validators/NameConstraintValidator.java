package com.academichub.AcademicHub.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<NameValidator, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        boolean isLongEnough = value.length() > 6;

        boolean hasSpecialCharacter = value.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return isLongEnough && hasSpecialCharacter;
    }

}
