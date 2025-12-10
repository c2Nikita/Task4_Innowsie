package com.innowise.task4.validator.impl;

import com.innowise.task4.validator.OrderValidator;

public class OrderValidatorImpl implements OrderValidator {

    private static final int MIN_AMOUNT = 0;
    @Override
    public boolean validate(String description, double amount) {
        boolean valid = description != null && !description.trim().isEmpty() && amount > MIN_AMOUNT;
        return valid;
    }
}
