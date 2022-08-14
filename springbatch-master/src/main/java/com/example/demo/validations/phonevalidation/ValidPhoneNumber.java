package com.example.demo.validations.phonevalidation;

import javax.validation.Payload;

public @interface ValidPhoneNumber {
    String message() default "Invalid PhoneNumber, must contain the digits only.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
