package com.innowise.task4.mapper.impl;

import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements BaseMapper<Order> {

    private static final String ID_COLUMN = "id";
    private static final String USER_ID_COLUMN = "userId";
    private static final String COURIER_ID_COLUMN = "courierId";
    private static final String DESCRIPTION_COLUMN = "description";
    private static final String COMPLETED_COLUMN = "completed";

    @Override
    public Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(ID_COLUMN));
        order.setUserId(resultSet.getLong(USER_ID_COLUMN));
        order.setCourierId(resultSet.getLong(COURIER_ID_COLUMN));
        order.setDescription(resultSet.getString(DESCRIPTION_COLUMN));
        order.setCompleted(resultSet.getBoolean(COMPLETED_COLUMN));

        return order;
    }
}
