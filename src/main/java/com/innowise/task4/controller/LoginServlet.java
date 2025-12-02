package com.innowise.task4.controller;

import com.innowise.task4.model.User;
import com.innowise.task4.service.UserService;
import com.innowise.task4.util.Endpoints;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String ERROR_ATTRIBUTE_MESSAGE = "Incorrect login or password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);
        User user = userService.authenticate(login, password);

        if(user == null) {
            req.setAttribute(ERROR_ATTRIBUTE, ERROR_ATTRIBUTE_MESSAGE);
            req.getRequestDispatcher(Endpoints.TO_LOGIN_PAGE).forward(req, resp);
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect(Endpoints.TO_MAIN_PAGE);

    }
}
