package com.innowise.task4.dao.impl;

import com.innowise.task4.connection.DBConnectionPool;
import com.innowise.task4.dao.CrudDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.CourierMapper;
import com.innowise.task4.model.Courier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourierDao implements CrudDao<Courier> {

    private static final String FIND_BY_ID = """
            SELECT id, user_id, transport_type, rating, active from couriers
            WHERE id = ?
            """;
    private static final String INSERT_COURIER = """
            INSERT INTO couriers (user_id, transport_type, rating, active)
            VALUES ( ?, ?, ?, ?)
            """;
    private static final String UPDATE_COURIER = """
            UPDATE couriers
            SET user_id = ?, transport_type = ?, rating = ?, active = ?
            WHERE id = ?
            """;
    private static final String DELETE_BY_ID = """
            DELETE FROM couriers WHERE id = ?
            """;

    private static final String GET_ALL_COURIERS = """
            SELECT * FROM couriers
            """;
    private final BaseMapper<Courier> mapper;

    public CourierDao(BaseMapper<Courier> mapper) {
        this.mapper = mapper;
    }
    @Override
    public Optional<Courier> findById(Long id) throws DaoException {
        Optional<Courier> result = Optional.empty();

        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        ) {
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    Courier courier = mapper.map(resultSet);
                    result = Optional.of(courier);
                }

                return result;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int insert(Courier courier) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURIER);
        ){

            preparedStatement.setLong(1, courier.getUserId());
            preparedStatement.setString(2, courier.getTransportType().name());
            preparedStatement.setDouble(3, courier.getRating());
            preparedStatement.setBoolean(4, courier.getActive());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public int update(Courier courier) throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURIER);
        ) {
            preparedStatement.setLong(1, courier.getUserId());
            preparedStatement.setString(2, courier.getTransportType().name());
            preparedStatement.setDouble(3, courier.getRating());
            preparedStatement.setBoolean(4, courier.getActive());
            preparedStatement.setLong(5, courier.getId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(Long id) throws DaoException {
        try (
             Connection connection = DBConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        ) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public List<Courier> getAll() throws DaoException {
        try (
                Connection connection = DBConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_COURIERS);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Courier> couriers = new ArrayList<>();

            while (resultSet.next()) {
                couriers.add(mapper.map(resultSet));
            }

            return couriers;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
