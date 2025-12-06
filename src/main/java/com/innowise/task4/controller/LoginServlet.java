package com.innowise.task4.controller;

import com.innowise.task4.dao.UserDao;
import com.innowise.task4.dao.impl.UserDaoImpl;
import com.innowise.task4.exception.ServiceException;
import com.innowise.task4.mapper.BaseMapper;
import com.innowise.task4.mapper.impl.UserMapper;
import com.innowise.task4.model.User;
import com.innowise.task4.service.UserService;
import com.innowise.task4.service.impl.UserServiceImpl;
import com.innowise.task4.util.Endpoints;
import com.innowise.task4.validator.UserValidator;
import com.innowise.task4.validator.impl.UserValidatorImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";

    private static final String ERROR_ATTRIBUTE = "errorMessage";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ERROR_ATTRIBUTE_MESSAGE = "Incorrect login or password";

    private UserService userService;

    @Override
    public void init() {
        BaseMapper mapper = new UserMapper();
        UserDao userDao = new UserDaoImpl(mapper);
        UserValidator validator = new UserValidatorImpl();
        this.userService =  new UserServiceImpl(userDao, validator);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        try {
            Optional<User> userOptional = userService.authenticate(login, password);

            if (userOptional.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute(USER_ATTRIBUTE, userOptional.get());
                resp.sendRedirect(Endpoints.TO_MAIN_PAGE);
            } else {
                req.setAttribute(ERROR_ATTRIBUTE, ERROR_ATTRIBUTE_MESSAGE);
                req.getRequestDispatcher(Endpoints.TO_LOGIN_PAGE).forward(req, resp);
            }

        } catch (ServiceException e) {
            req.getRequestDispatcher(Endpoints.TO_ERROR_PAGE).forward(req, resp);
        }
    }

}
