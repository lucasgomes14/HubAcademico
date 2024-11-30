package com.academichub.AcademicHub.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailValidator, String> {

    private String type;

    @Override
    public void initialize(EmailValidator constraintAnnotation) {
        this.type = constraintAnnotation.message();
    } 

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false; 
        }

        if (value.contains("@ifpb.edu.br")) { //professor
            return true;
        } else if (value.contains("@academico.ifpb.edu.br")) { //aluno
            return true;
        } else {
            return false;
        }
    } 
}

