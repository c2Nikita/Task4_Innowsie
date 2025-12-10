package com.innowise.task4.controller;

import com.google.gson.Gson;
import com.innowise.task4.dao.CourierDao;
import com.innowise.task4.dao.impl.CourierDaoImpl;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.CourierMapper;
import com.innowise.task4.model.Courier;
import com.innowise.task4.service.CourierService;
import com.innowise.task4.service.impl.CourierServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/get-active-couriers")
public class GetActiveCouriersServlet extends HttpServlet {

    private CourierDao courierDao;
    private CourierService courierService;
    private Gson gson;

    @Override
    public void init() {
        BaseMapper<Courier> mapper = new CourierMapper();
        courierDao = new CourierDaoImpl(mapper);
        courierService = new CourierServiceImpl(courierDao);
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            List<Courier> couriers = courierService.getActiveCouriers();
            String json = gson.toJson(couriers);
            resp.getWriter().write(json);

        } catch (ServiceException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("[]");
        }
    }
}
