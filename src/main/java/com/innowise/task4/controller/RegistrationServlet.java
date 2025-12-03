package com.innowise.task4.controller;

import com.innowise.task4.service.UserService;
import com.innowise.task4.util.Endpoints;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String NAME_PARAMETER = "name";
    private static final String EMAIL_PARAMETER = "email";
    private static final String ROLE_PARAMETER = "role";
    private static final String OK_ATTRIBUTE = "okMessage";
    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String OK_ATTRIBUTE_MESSAGE = "Your data is ok. Now login!";
    private static final String ERROR_ATTRIBUTE_MESSAGE = "Your data is wrong!";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserService userService = new UserService();
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String email = request.getParameter(EMAIL_PARAMETER);
        String role = request.getParameter(ROLE_PARAMETER);
        boolean isCreated = false;
        try {
            isCreated = userService.create(login, password, name, email, role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (isCreated) {
            request.setAttribute(OK_ATTRIBUTE, OK_ATTRIBUTE_MESSAGE);
            request.getRequestDispatcher(Endpoints.TO_LOGIN_PAGE).forward(request,response);
        } else {
            request.setAttribute(ERROR_ATTRIBUTE, ERROR_ATTRIBUTE_MESSAGE);
            request.getRequestDispatcher(Endpoints.TO_REGISTRATION_PAGE).forward(request, response);
        }
    }
}
