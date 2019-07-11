package com.healthme.validatior;

import com.healthme.model.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordCheckValidator implements ConstraintValidator<PasswordCheck, Object> {

    @Override
    public void initialize(PasswordCheck passwordCheck) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserDto user = (UserDto) object;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
