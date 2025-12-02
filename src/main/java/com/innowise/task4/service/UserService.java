package com.innowise.task4.service;

import com.innowise.task4.dao.BaseDao;
import com.innowise.task4.dao.impl.UserDao;
import com.innowise.task4.model.User;
import com.innowise.task4.util.EncoderSHA256;
import com.innowise.task4.validator.UserValidator;

import java.sql.SQLException;
import java.util.Optional;


public class UserService {

    public boolean create(String login, String password, String name, String email) throws RuntimeException, SQLException {
        BaseDao<User> baseDao = new UserDao();
        UserValidator validator = new UserValidator();
        User user = new User();

        if ( validator.isValidEmail(email) && validator.isValidLogin(login) &&
                validator.isValidPassword(password)) {

            user.setLogin(login);
            user.setPassword(EncoderSHA256.encode(password));
            user.setEmail(email);
            user.setName(name);

            try {
                return baseDao.insert(user) > 0;
            } catch (SQLException e) {
                return false;
            }
        } else {
            return false;
        }


    }

    public User authenticate(String login, String password) {
        UserDao userDao = new UserDao();
        String ecncodedPassword = EncoderSHA256.encode(password);
        try {
            Optional<User> userOptional = userDao.getByLoginAndPassword(login, ecncodedPassword);
            return userOptional.orElse(null);
        } catch (SQLException e) {
            return null;
        }

    }
}
