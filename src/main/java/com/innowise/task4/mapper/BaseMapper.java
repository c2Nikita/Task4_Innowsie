package com.innowise.task4.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseMapper<T> {
    T map(ResultSet resultSet) throws SQLException;
}
