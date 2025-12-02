package com.innowise.task4.dao.impl;

import com.innowise.task4.connection.DBConnectionPool;
import com.innowise.task4.dao.BaseDao;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.UserMapper;
import com.innowise.task4.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements BaseDao<User> {

    private static final String INSERT_USER = """
            INSERT INTO users (login, password, name, email)
            VALUES (?, ?, ?, ?);
            """;

    private static final String FIND_BY_ID = """
            SELECT login, password, name, email FROM users WHERE id = ?
            """;

    private static final String DELETE_BY_ID = """
            DELETE  FROM users WHERE id = ?
            """;

    private static final String GET_ALL = """
            SELECT * FROM users
            """;

    private static final String UPDATE_USER = """
            UPDATE USERS
            SET login = ?, password = ?, name = ?, email = ?
            WHERE id = ?
            """;

    @Override
    public Optional<User> findById(Long id) throws SQLException {
        Connection connection = DBConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setLong(1,id);
        BaseMapper<User> mapper = new UserMapper();
        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {

            User user = mapper.map(resultSet);
            return Optional.of(user);

        }

        return Optional.empty();

    }

    @Override
    public int insert(User user) throws SQLException {
        Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_USER);
        statement.setString(1,user.getLogin());
        statement.setString(2,user.getPassword());
        statement.setString(3,user.getName());
        statement.setString(4,user.getEmail());
        return statement.executeUpdate();
    }

    @Override
    public int update(User user) throws SQLException {
        Connection connection = DBConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setString(1,user.getLogin());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getName());
        preparedStatement.setString(4,user.getEmail());
        preparedStatement.setLong(5,user.getId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Long id) throws SQLException {
        Connection connection = DBConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setLong(1,id);
        return preparedStatement.executeUpdate();
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        BaseMapper<User> mapper = new UserMapper();
        Connection connection = DBConnectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            User user = mapper.map(resultSet);
            users.add(user);
        }

        return users;
    }

}
