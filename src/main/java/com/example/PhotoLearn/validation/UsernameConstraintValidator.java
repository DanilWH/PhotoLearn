package com.example.PhotoLearn.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.PhotoLearn.repositories.UserRepository;

public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {
    
    @Autowired
    private UserRepository userRepository;  
   
    private Pattern pattern;
    private Matcher matches;
    private final String USERNAME_PATTERN = "[^A-Za-z0-9@.+\\-_]";
    
    public void initialize(ValidUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (usernameExists(username)) {
            String messageTemplate = "Аккаунт с именем " + username + " уже существует.";
            context.buildConstraintViolationWithTemplate(messageTemplate)
                   .addConstraintViolation()
                   .disableDefaultConstraintViolation();
            
            return false;
        }
        else return validateUsername(username);
    }
    
    private boolean validateUsername(String username) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        matches= pattern.matcher(username);
        
        return !matches.find();
    }
    
    private boolean usernameExists(String username) {
        return this.userRepository.findByUsername(username).orElse(null) != null;
    }

}
