package com.innowise.task4.filter;

import com.innowise.task4.util.Endpoints;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class GlobalErrorFilter implements Filter {

    private static final String ERROR_ATTRIBUTE = "errorMessage";
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            filterChain.doFilter(request, response);
        } catch (Exception t) {
            request.setAttribute(ERROR_ATTRIBUTE, t);
            request.getRequestDispatcher(Endpoints.TO_ERROR_PAGE).forward(request, response);
        }
    }
}
