package com.innowise.task4.controller;

import com.innowise.task4.dao.OrderDao;
import com.innowise.task4.dao.impl.OrderDaoImpl;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.OrderMapper;
import com.innowise.task4.model.Order;
import com.innowise.task4.service.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/get-user-orders")
public class GetUserOrdersServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() {
        BaseMapper<Order> mapper = new OrderMapper();
        OrderDao orderDao = new OrderDaoImpl(mapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
