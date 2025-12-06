package com.innowise.task4.filter;

import com.innowise.task4.util.Endpoints;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final String USER_ATTRIBUTE = "user";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String servletPath = request.getServletPath();
        boolean loggedIn = session != null && session.getAttribute(USER_ATTRIBUTE) != null;
        boolean isPublic = servletPath.equals(Endpoints.TO_LOGIN_PAGE) ||
                servletPath.equals(Endpoints.TO_REGISTRATION_PAGE) ||
                servletPath.equals(Endpoints.TO_START_PAGE) ||
                servletPath.equals(Endpoints.TO_ERROR_PAGE) ||
                servletPath.equals("/login") ||
                servletPath.equals("/register");

        if (loggedIn || isPublic) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + Endpoints.TO_START_PAGE);
        }
    }
}
