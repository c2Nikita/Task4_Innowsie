package com.innowise.task4.mapper.impl;

import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.User;
import com.innowise.task4.model.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements BaseMapper<User> {
    private static final String EMAIL_COLUMN = "email";
    private static final String LOGIN_COLUMN = "login";
    private static final String PASSWORD_COLUMN = "password";
    private static final String NAME_COLUMN = "name";
    private static final String ID_COLUMN = "id";
    private static final String ROLE_COLUMN = "role";
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(ID_COLUMN));
        user.setLogin(resultSet.getString(LOGIN_COLUMN));
        user.setPassword(resultSet.getString(PASSWORD_COLUMN));
        user.setName(resultSet.getString(NAME_COLUMN));
        user.setEmail(resultSet.getString(EMAIL_COLUMN));
        user.setRole(UserRole.valueOf(resultSet.getString(ROLE_COLUMN)));
        return user;
    }
}
