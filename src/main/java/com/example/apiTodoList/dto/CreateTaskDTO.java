package com.example.apiTodoList.dto;

public record CreateTaskDTO(String title, String content, String date, Boolean isDone) {
}
