package com.innowise.task4.dao;

import com.innowise.task4.exception.DaoException;
import com.innowise.task4.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface UserDao<T> extends CrudDao<T> {
    Optional<T> findByLogin(String login) throws DaoException;
    Long insertWithReturningId(User user, Connection connection) throws DaoException;
}
