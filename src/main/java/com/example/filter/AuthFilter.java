package com.example.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/user/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
        Object attr = servletRequest.getAttribute("user");
        if (attr == null) {
            ((HttpServletResponse) httpServletResponse).sendRedirect("/login.jsp");
        }
    }
}