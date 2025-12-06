package com.innowise.task4.dao.impl;

import com.innowise.task4.connection.DBConnectionPool;
import com.innowise.task4.dao.OrderDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private BaseMapper<Order> mapper;
    public OrderDaoImpl(BaseMapper<Order> mapper) {
        this.mapper = mapper;
    }
    public static final String INSERT_ORDER = """
            INSERT INTO orders (user_id,courier_id,description,completed)
            VALUES (?,?,?,?)
            """;
    public static final String FIND_BY_ID = """
            SELECT id, user_id, courier_id, description, completed FROM orders
            WHERE id = ?
            """;
    public static final String GET_ALL_ORDERS = """
            SELECT * FROM orders
            """;

    public static final String DELETE_ORDER = """
            DELETE FROM orders
            WHERE id = ?
            """;

    private static final String UPDATE_ORDER = """
            UPDATE orders
            SET user_id = ?, courier_id = ?, description = ?, completed = ?
            WHERE id = ?
            """;

    private static final String FIND_BY_USER_ID = """
            SELECT * FROM orders
            WHERE user_id = ?
            """;
    @Override
    public Optional<Order> findById(Long id) throws DaoException {
        Optional<Order> result = Optional.empty();
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = mapper.map(resultSet);
                    result = Optional.of(order);
                }
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return result;
    }

    @Override
    public int insert(Order order) throws DaoException {
        try(
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
        ) {
            preparedStatement.setLong(1,order.getUserId());
            preparedStatement.setLong(2,order.getCourierId());
            preparedStatement.setString(3,order.getDescription());
            preparedStatement.setBoolean(4,order.getCompleted());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int update(Order order) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER)
        ) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getCourierId());
            preparedStatement.setString(3, order.getDescription());
            preparedStatement.setBoolean(4, order.getCompleted());
            preparedStatement.setLong(5, order.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(Long id) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER)
        ){
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getAll() throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ){
            List<Order> orders = new ArrayList<>();

            while(resultSet.next()) {
                orders.add(mapper.map(resultSet));
            }

            return orders;

        } catch (SQLException e) {
            throw new DaoException(e);
        }


    }

    @Override
    public List<Order> findByUserId(Long id) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID)
        ) {
            List<Order> orders = new ArrayList<>();
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                while(resultSet.next()) {
                    orders.add(mapper.map(resultSet));
                }

                return orders;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
