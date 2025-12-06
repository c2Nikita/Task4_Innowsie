package com.innowise.task4.dao.impl;

import com.innowise.task4.connection.DBConnectionPool;
import com.innowise.task4.dao.UserDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.UserMapper;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao<User> {

    private static final String INSERT_USER = """
            INSERT INTO users (login, password, name, email, role)
            VALUES (?, ?, ?, ?, ?);
            """;

    private static final String FIND_BY_ID = """
            SELECT login, password, name, email, role FROM users WHERE id = ?
            """;

    private static final String DELETE_BY_ID = """
            DELETE  FROM users WHERE id = ?
            """;

    private static final String GET_ALL_USERS = """
            SELECT * FROM users
            """;

    private static final String UPDATE_USER = """
            UPDATE USERS
            SET login = ?, password = ?, name = ?, email = ?, role = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_LOGIN = """
            SELECT id, login, password, name, email, role FROM users WHERE login = ?
            """;

    private final BaseMapper<User> mapper;
    public UserDaoImpl(BaseMapper<User> mapper) {
        this.mapper = mapper;
    }
    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> result = Optional.empty();
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)
        ) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    result = Optional.of(mapper.map(resultSet));
                }

                return result;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public int insert(User user) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)
        ) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole().name());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(User user) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)
        ) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setLong(6, user.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(Long id) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try(
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {

                users.add(mapper.map(resultSet));
            }

            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)
        ) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                Optional<User> result = Optional.empty();
                if (resultSet.next()) {

                    result = Optional.of(mapper.map(resultSet));
                }

                return result;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
