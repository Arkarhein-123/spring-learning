package com.example.demo.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // offer HttpServlet Request and respond which is different with doFilter() of Filter
        var requestId = request.getHeader("Request-Id");
        logger.info("Successfully Authenticated request Id with id"+requestId);
        filterChain.doFilter(request,response);
    }
}
