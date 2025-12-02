package com.innowise.task4.validator;

public class UserValidator {

    private static final String LOGIN_REGEX = "[A-Za-z0-9_]{3,20}";
    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";

    public boolean isValidLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    public boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}
