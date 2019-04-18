package com.atticusrains.multiblog.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    @Override
    public void initialize(UsernameConstraint username){}

    @Override
    public boolean isValid(String usernameField, ConstraintValidatorContext ctx){
        return usernameField != null && usernameField.matches("^[a-zA-Z0-9]*S");
    }
}
