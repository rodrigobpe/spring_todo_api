package com.example.apiTodoList.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("Usuário não encontrado!", HttpStatus.NOT_FOUND);
    }
}
