package io.hexlet.hexletproject4.controller.validator;

import io.hexlet.hexletproject4.domain.UserRegistration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserRegistration.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserRegistration userRegistration = (UserRegistration) o;

        if (!userRegistration.getPasswordConfirm().equals(userRegistration.getPassword())) {
            errors.rejectValue("passwordConfirm", "error.validation.password.confirmation");
        }
    }
}
