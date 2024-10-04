package com.example.apiTodoList.controller;

import com.example.apiTodoList.exception.BaseException;

import com.example.apiTodoList.utils.HandleResponseError;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception) throws Exception{
        HandleResponseError response = new HandleResponseError(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        return response.run();
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(BaseException exception){
        HandleResponseError response = new HandleResponseError(exception.getMessage(), exception.getHttpStatus());
        return response.run();
    }
}
