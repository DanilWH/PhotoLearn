package com.example.PhotoLearn.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {
   
    Pattern pattern;
    Matcher matches;
    private final String USERNAME_PATTERN = "[^A-Za-z0-9@.+\\-_]";
    
    public void initialize(ValidUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return validateUsername(username);
    }
    
    private boolean validateUsername(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matches= pattern.matcher(username);
        
        return !matches.find();
    }

}
