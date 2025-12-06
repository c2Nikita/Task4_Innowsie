package com.innowise.task4.validator;

public interface UserValidator {
    boolean isValidLogin(String login);

    boolean isValidEmail(String email);

    boolean isValidPassword(String password);
}
