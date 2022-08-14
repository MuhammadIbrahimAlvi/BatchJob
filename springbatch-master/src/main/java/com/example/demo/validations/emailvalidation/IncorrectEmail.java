package com.example.demo.validations.emailvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IncorrectEmail {
    String message() default "Invalid Email, must follow the pattern of abc@domai.com.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
