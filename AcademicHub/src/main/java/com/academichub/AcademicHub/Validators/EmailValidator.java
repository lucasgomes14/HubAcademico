package com.academichub.AcademicHub.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {EmailConstraintValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailValidator {
    
    String message() default "nome inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
