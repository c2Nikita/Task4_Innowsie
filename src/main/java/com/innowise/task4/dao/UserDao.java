package com.innowise.task4.dao;

import com.innowise.task4.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao<T> extends CrudDao<T> {
    public Optional<T> findByLogin(String login) throws DaoException;
}
