package com.ahievran.staj.exception.types;

import com.ahievran.staj.core.result.ErrorDataResult;
import com.fasterxml.jackson.databind.ObjectMapper;;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {


@Override
public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                     AuthenticationException e) throws IOException, ServletException {
    ObjectMapper mapper = new ObjectMapper();
    ErrorDataResult errorDetails = new  ErrorDataResult();
    errorDetails.setCode(401);
    errorDetails.setSuccess(false);
    errorDetails.setMessage("JWT süresi doldu");


    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
    httpServletResponse.setContentType("application/json");
    httpServletResponse.setCharacterEncoding("UTF-8");
    httpServletResponse.getWriter().write(mapper.writeValueAsString(errorDetails));

    }
}
