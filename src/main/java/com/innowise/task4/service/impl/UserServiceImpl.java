package com.innowise.task4.service.impl;

import com.innowise.task4.dao.UserDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.User;
import com.innowise.task4.model.UserRole;
import com.innowise.task4.service.UserService;
import com.innowise.task4.util.EncoderSHA256;
import com.innowise.task4.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class UserServiceImpl implements UserService {

    private UserDao<User> userDao;

    private UserValidator validator;

    public UserServiceImpl(UserDao<User> userDao, UserValidator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ServiceException {
        try {
            Optional<User> userOptional = userDao.findByLogin(login);
            String encodedPassword = EncoderSHA256.encode(password);

            return userOptional.filter(user -> encodedPassword.equals(user.getPassword()));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


}
