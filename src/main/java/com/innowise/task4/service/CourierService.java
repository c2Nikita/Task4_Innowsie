package com.innowise.task4.service;

import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.Courier;
import com.innowise.task4.model.TransportType;

import java.util.List;

public interface CourierService {
    List<Courier> getActiveCouriers() throws ServiceException;
}
