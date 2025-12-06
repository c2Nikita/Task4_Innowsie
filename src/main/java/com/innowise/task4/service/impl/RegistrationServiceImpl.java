package com.innowise.task4.service.impl;

import com.innowise.task4.connection.DBConnectionPool;
import com.innowise.task4.dao.CourierDao;
import com.innowise.task4.dao.UserDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;
import com.innowise.task4.model.User;
import com.innowise.task4.model.UserRole;
import com.innowise.task4.service.RegistrationService;
import com.innowise.task4.util.EncoderSHA256;
import com.innowise.task4.validator.UserValidator;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationServiceImpl implements RegistrationService {
    private CourierDao courierDao;
    private UserDao userDao;
    private UserValidator validator;

    public RegistrationServiceImpl(CourierDao courierDao, UserDao userDao, UserValidator validator) {
        this.courierDao = courierDao;
        this.userDao = userDao;
        this.validator = validator;
    }
    @Override
    public boolean create(String login, String password, String name, String email, String role, TransportType transportType) throws ServiceException {
        boolean result = false;

        if ( validator.isValidEmail(email)
                && validator.isValidLogin(login)
                && validator.isValidPassword(password)) {

            User user = new User();
            user.setLogin(login);
            user.setPassword(EncoderSHA256.encode(password));
            user.setEmail(email);
            user.setName(name);
            user.setRole(UserRole.valueOf(role));

            Courier courier = new Courier();
            courier.setActive(false);
            courier.setTransportType(transportType);
            courier.setRating(0);

            try (Connection connection = DBConnectionPool.getConnection()) {
                connection.setAutoCommit(false);
                try {
                    Long userId = userDao.insertWithReturningId(user, connection);
                    courier.setUserId(userId);
                    courierDao.insert(courier, connection);
                    connection.commit();
                    result = true;
                } catch (SQLException | DaoException e) {
                    connection.rollback();
                    throw e;
                }
            } catch (SQLException | DaoException e) {
                throw new ServiceException(e);
            }
        }

        return result;
    }
}
