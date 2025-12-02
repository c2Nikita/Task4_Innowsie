package com.innowise.task4.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BaseDao<T> {
    Optional<T> findById(Long id) throws SQLException;
    int insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(Long id) throws SQLException;
    List<T> getAll() throws SQLException;
}
