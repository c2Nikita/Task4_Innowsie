package com.innowise.task4.mapper.impl;

import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourierMapper implements BaseMapper<Courier> {

    private static final String ID_COLUMN = "id";
    private static final String ACTIVE_COLUMN = "active";
    private static final String USER_ID_COLUMN = "user_id";
    private static final String RATING_COLUMN = "rating";
    private static final String TRANSPORT_TYPE_COLUMN = "transport_type";
    @Override
    public Courier map(ResultSet rs) throws SQLException {
        Courier courier = new Courier();
        courier.setId(rs.getLong(ID_COLUMN));
        courier.setActive(rs.getBoolean(ACTIVE_COLUMN));
        courier.setRating(rs.getLong( RATING_COLUMN));
        courier.setTransportType(TransportType.valueOf(rs.getString(TRANSPORT_TYPE_COLUMN)));
        courier.setUserId(rs.getLong(USER_ID_COLUMN));
        return null;
    }
}
