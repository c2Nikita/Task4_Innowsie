package com.innowise.task4.service.impl;

import com.innowise.task4.dao.CourierDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;
import com.innowise.task4.service.CourierService;

public class CourierServiceImpl implements CourierService {
    private CourierDao courierDao;

    public CourierServiceImpl(CourierDao courierDao) {
        this.courierDao = courierDao;
    }


}
