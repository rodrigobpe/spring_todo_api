package com.example.apiTodoList.dto;

import java.util.UUID;

public record ResponseLoginDTO(UUID id, String email, String name,  String token) {
}
