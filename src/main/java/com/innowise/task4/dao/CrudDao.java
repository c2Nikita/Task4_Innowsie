package com.innowise.task4.dao;

import com.innowise.task4.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {
    Optional<T> findById(Long id) throws DaoException;
    int insert(T t) throws DaoException;
    int update(T t) throws DaoException;
    int delete(Long id) throws DaoException;
    List<T> getAll() throws DaoException;
}
