package com.example.apiTodoList.controller;

import com.example.apiTodoList.model.User;
import com.example.apiTodoList.service.UserService;
import com.example.apiTodoList.utils.HandleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        HandleResponse response = new HandleResponse(userService.getAll(), HttpStatus.OK);
        return response.run();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable UUID id) {
        User user = userService.getById(id);
        HandleResponse response = new HandleResponse(user, HttpStatus.OK);
        return response.run();
    }

}
