package com.innowise.task4.service.impl;

import com.innowise.task4.dao.CourierDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;
import com.innowise.task4.service.CourierService;

import java.util.ArrayList;
import java.util.List;

public class CourierServiceImpl implements CourierService {
    private CourierDao courierDao;

    public CourierServiceImpl(CourierDao courierDao) {
        this.courierDao = courierDao;
    }


    @Override
    public List<Courier> getActiveCouriers() throws ServiceException {
        List<Courier> activateCouriers;

        try {
            activateCouriers = courierDao.getActivateCouriers();

            return activateCouriers;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
