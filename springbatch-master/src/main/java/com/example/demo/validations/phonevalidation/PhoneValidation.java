package com.example.demo.validations.phonevalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidation implements ConstraintValidator<ValidPhoneNumber,String> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "\\d+";
        final Pattern PHONE_NUMBER = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return PHONE_NUMBER.matcher(s).matches();
    }
}
