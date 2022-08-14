package com.example.demo.validations.emptyvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmptyValidation implements ConstraintValidator<NotEmptyField,String> {
    @Override
    public void initialize(NotEmptyField constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.isEmpty();
    }
}
