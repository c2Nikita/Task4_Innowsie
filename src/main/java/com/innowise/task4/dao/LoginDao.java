package com.innowise.task4.dao;

import com.innowise.task4.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface LoginDao<T> extends BaseDao<T>{
    public Optional<T> getByLoginAndPassword(String login, String password) throws SQLException;
}
