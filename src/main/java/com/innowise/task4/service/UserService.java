package com.innowise.task4.service;

import com.innowise.task4.dao.BaseDao;
import com.innowise.task4.dao.impl.UserDao;
import com.innowise.task4.model.User;
import com.innowise.task4.util.EncoderSHA256;
import com.innowise.task4.validator.UserValidator;

import java.sql.SQLException;


public class UserService {

    public boolean create(String login, String password, String name, String email) throws RuntimeException, SQLException {
        BaseDao<User> baseDao = new UserDao();

        User user2 = new User();
        user2.setName("vlada");
        user2.setEmail("vlada@mail.ru");
        user2.setPassword("antilopa32");
        user2.setLogin("BABY HIPPO");
        user2.setId(5L);
        baseDao.update(user2);


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
}
