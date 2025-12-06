package com.innowise.task4.service;

import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.TransportType;

public interface RegistrationService {
    boolean create(String login, String password, String name, String email, String role, TransportType transportType) throws ServiceException;

}
