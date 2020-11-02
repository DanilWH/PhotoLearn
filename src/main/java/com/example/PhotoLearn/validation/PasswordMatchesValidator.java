package com.example.PhotoLearn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.PhotoLearn.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {
    
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getConfirmPassword());
    }
}
