package com.innowise.task4.service;

import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.User;

import java.util.Optional;

public interface UserService {
       Optional<User> authenticate(String login, String password) throws ServiceException;
}
