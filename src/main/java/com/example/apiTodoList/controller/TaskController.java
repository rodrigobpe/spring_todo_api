package com.example.apiTodoList.controller;

import com.example.apiTodoList.dto.CreateTaskDTO;
import com.example.apiTodoList.dto.UpdateTaskDTO;
import com.example.apiTodoList.model.Task;
import com.example.apiTodoList.service.TaskService;
import com.example.apiTodoList.utils.HandleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/users/{user_id}/tasks")
    public ResponseEntity<Map<String, Object>> saveTask(@RequestBody CreateTaskDTO task, @PathVariable("user_id") UUID user_id) {
        Task newTask = taskService.save(task, user_id);
        HandleResponse response = new HandleResponse(newTask, HttpStatus.CREATED);

        return response.run();
    }

    @PatchMapping(value = "/tasks/{id}")
    public ResponseEntity<Map<String, Object>> updateTask(@RequestBody UpdateTaskDTO task, @PathVariable("id") long id) {
        System.out.println(task);
        Task updateTask = taskService.update(task, id);
        HandleResponse response = new HandleResponse(updateTask, HttpStatus.CREATED);

        return response.run();
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable("id") long id) {
        taskService.delete(id);
        HandleResponse response = new HandleResponse("Tarefa com ID " + id + " foi deletada", HttpStatus.CREATED);

        return response.run();
    }

}
