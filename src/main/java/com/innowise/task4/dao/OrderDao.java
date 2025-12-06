package com.innowise.task4.dao;

import com.innowise.task4.exception.DaoException;
import com.innowise.task4.model.Order;

import java.util.List;

public interface OrderDao extends CrudDao<Order>{
    List<Order> findByUserId(Long id) throws DaoException;

}
