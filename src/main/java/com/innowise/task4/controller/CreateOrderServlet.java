package com.innowise.task4.controller;

import com.innowise.task4.dao.OrderDao;
import com.innowise.task4.dao.impl.OrderDaoImpl;
import com.innowise.task4.dto.UserDto;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.OrderMapper;
import com.innowise.task4.model.Order;
import com.innowise.task4.service.OrderService;
import com.innowise.task4.service.impl.OrderServiceImpl;
import com.innowise.task4.util.Endpoints;
import com.innowise.task4.validator.OrderValidator;
import com.innowise.task4.validator.impl.OrderValidatorImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/create-order")
public class CreateOrderServlet extends HttpServlet {

    private static final String USER_ATTRIBUTE = "user";

    private static final String DESCRIPTION_ATTRIBUTE = "description";
    private static final String AMOUNT_ATTRIBUTE = "amount";
    private static final String COURIER_ATTRIBUTE = "courierId";
    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String ERROR_MESSAGE = "Write data more corectly!";
    private OrderService orderService;

    @Override
    public void init() {
        OrderValidator validator = new OrderValidatorImpl();
        BaseMapper<Order> mapper = new OrderMapper();
        OrderDao orderDao = new OrderDaoImpl(mapper);
        orderService = new OrderServiceImpl(orderDao, validator);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = ((UserDto) req.getSession().getAttribute(USER_ATTRIBUTE)).getId();
        String description = req.getParameter(DESCRIPTION_ATTRIBUTE);
        Double amount = Double.valueOf(req.getParameter(AMOUNT_ATTRIBUTE));
        Long courierId = Long.valueOf(req.getParameter(COURIER_ATTRIBUTE));
        try {
            boolean isCreated = orderService.create(userId, courierId, description, amount);

            if(isCreated) {
                req.getRequestDispatcher(Endpoints.TO_MAIN_PAGE).forward(req, resp);
            } else {
                req.setAttribute(ERROR_ATTRIBUTE, ERROR_MESSAGE);
                req.getRequestDispatcher(Endpoints.TO_MAIN_PAGE).forward(req, resp);
            }
        } catch (ServiceException e) {
            req.setAttribute(ERROR_ATTRIBUTE,e);
            req.getRequestDispatcher(Endpoints.TO_ERROR_PAGE).forward(req, resp);
        }


    }
}
