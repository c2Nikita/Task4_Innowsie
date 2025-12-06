package com.innowise.task4.validator.impl;

import com.innowise.task4.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {

    private static final String LOGIN_REGEX = "[A-Za-z0-9_]{3,20}";
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
    private static final int MIN_PASSWORD_LENGHT = 6;

    @Override
    public boolean isValidLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }
    @Override
    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }
    @Override
    public boolean isValidPassword(String password) {
        return password != null && password.length() >= MIN_PASSWORD_LENGHT;
    }
}
