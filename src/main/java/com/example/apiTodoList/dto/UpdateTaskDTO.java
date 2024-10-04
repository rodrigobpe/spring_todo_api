package com.example.apiTodoList.dto;

import java.util.Optional;

public record UpdateTaskDTO(String title, String content, String dueDate, Boolean isDone) {
}
