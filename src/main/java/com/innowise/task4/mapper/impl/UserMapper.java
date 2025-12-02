package com.innowise.task4.mapper.impl;

import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements BaseMapper<User> {
    private static final String EMAIL_COLUMN = "email";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String NAME_COLUMN = "name";
    private static final String ID_COLUMN = "id";
    @Override
    public User map(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId( rs.getLong(ID_COLUMN));
        user.setLogin( rs.getString(LOGIN_COLUMN));
        user.setPassword( rs.getString(PASSWORD_COLUMN));
        user.setName( rs.getString(NAME_COLUMN));
        user.setEmail( rs.getString(EMAIL_COLUMN));
        return user;
    }
}
