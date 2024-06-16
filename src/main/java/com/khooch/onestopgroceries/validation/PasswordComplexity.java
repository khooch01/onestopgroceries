package com.khooch.onestopgroceries.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordComplexityValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordComplexity {
    String message() default "Password must be between 10 and 20 characters long and include at least one special character (@, !, #, $, etc.)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
