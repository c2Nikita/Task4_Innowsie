package com.innowise.task4.service.impl;

import com.innowise.task4.dao.OrderDao;
import com.innowise.task4.exception.DaoException;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.model.Order;
import com.innowise.task4.service.OrderService;
import com.innowise.task4.validator.OrderValidator;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private OrderValidator validator;

    public  OrderServiceImpl(OrderDao orderDao, OrderValidator validator) {
        this.orderDao = orderDao;
        this.validator = validator;
    }

    @Override
    public boolean create(Long userId, Long courierId, String description, double amount) throws ServiceException {
        boolean result = false;

        if (validator.validate(description, amount)) {
            Order order = new Order();
            order.setAmount(amount);
            order.setDescription(description);
            order.setUserId(userId);
            order.setCourierId(courierId);
            order.setCompleted(false);

            try {
                result = orderDao.insert(order) > 0;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

        return result;
    }

}
