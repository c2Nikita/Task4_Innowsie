package com.innowise.task4.dao.impl;

import com.innowise.task4.dao.BaseDao;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.Courier;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourierDao implements BaseDao<Courier> {

    @Override
    public Optional<Courier> findById(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public int insert(Courier courier) throws SQLException {
        return 0;
    }

    @Override
    public int update(Courier courier) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<Courier> getAll() throws SQLException {
        return null;
    }
}
