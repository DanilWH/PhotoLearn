package com.example.PhotoLearn.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ TYPE, ANNOTATION_TYPE })
public @interface PasswordMatches {
    
    String message() default "Passwords don't match.";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};

}
