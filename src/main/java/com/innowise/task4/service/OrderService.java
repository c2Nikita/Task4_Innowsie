package com.innowise.task4.service;

import com.innowise.task4.exception.ServiceException;

public interface OrderService {
    boolean create(Long userId, Long courierId, String description, double amount) throws ServiceException;
}
