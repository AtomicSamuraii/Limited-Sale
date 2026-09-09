package com.AtomicSamurai.LimitedSale.Security;

import com.AtomicSamurai.LimitedSale.dto.ExceptionResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.time.LocalDateTime;


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ExceptionResponse error = new ExceptionResponse(401,"Authorization Required", LocalDateTime.now());
        response.setStatus(401);
        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), error);
    }
}
