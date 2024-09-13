package com.authentication.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        // Set 403 status
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        // Return custom error message in JSON format
        response.setContentType("application/json");
        response.getWriter().write("{ \"error\": \"You are not authorized to access this resource.\" }");
    }

}

