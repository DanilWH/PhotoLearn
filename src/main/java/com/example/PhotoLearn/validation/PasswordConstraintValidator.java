package com.example.PhotoLearn.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.UsernameRule;
import org.passay.WhitespaceRule;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    
    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // at least 8 characters.
                new LengthRule(8, 30),
                
                // at least one lower-case character.
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                
                // at least one digit character.
                new CharacterRule(EnglishCharacterData.Digit, 1),
                
                // the password can't contain the user name.
                new UsernameRule(),
                
                // no whitespace.
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid())
            return true;
        List<String> messages = validator.getMessages(result);
        
        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
               .addConstraintViolation()
               .disableDefaultConstraintViolation();
        
        return false;
    }
    
    

}
