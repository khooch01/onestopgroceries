package com.khooch.onestopgroceries.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordComplexityValidator implements ConstraintValidator<PasswordComplexity, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[!@#$%^&*(),.?\":{}|<>]).{10,20}$");

    @Override
    public void initialize(PasswordComplexity constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
}
