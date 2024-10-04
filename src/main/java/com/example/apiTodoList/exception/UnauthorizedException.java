package com.example.apiTodoList.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("Usuário não autorizado!", HttpStatus.UNAUTHORIZED);
    }
}
