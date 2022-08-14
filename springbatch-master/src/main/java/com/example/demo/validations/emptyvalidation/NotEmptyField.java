package com.example.demo.validations.emptyvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmptyValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyField {
    String message() default "Invalid, Field Can't be empty";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
