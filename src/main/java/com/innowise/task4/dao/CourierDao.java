package com.innowise.task4.dao;

import com.innowise.task4.exception.DaoException;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;

import java.sql.Connection;

public interface CourierDao extends CrudDao<Courier>{
    int insert(Courier courier, Connection connection) throws DaoException;
}
