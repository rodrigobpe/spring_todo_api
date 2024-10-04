package com.example.apiTodoList.service;

import com.example.apiTodoList.dto.CreateTaskDTO;
import com.example.apiTodoList.dto.UpdateTaskDTO;
import com.example.apiTodoList.exception.NotFoundException;
import com.example.apiTodoList.exception.UserNotFoundException;
import com.example.apiTodoList.model.Task;
import com.example.apiTodoList.model.User;
import com.example.apiTodoList.repository.TaskRepository;
import com.example.apiTodoList.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task save(CreateTaskDTO createTaskDTO, UUID user_id) {
        Optional<User> userExists = userRepository.findById(user_id);
        if (userExists.isEmpty()) throw new UserNotFoundException();
//
        Task newTask = new Task(
                createTaskDTO.title(),
                createTaskDTO.content(),
                Instant.now(),
                Instant.parse(createTaskDTO.date()),
                createTaskDTO.isDone(),
                user_id
        );

        System.out.println(newTask.getDueDate());

        return taskRepository.save(newTask);
    }

    public Task update(UpdateTaskDTO updateTaskDTO, long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
        task.setIsDone(updateTaskDTO.isDone());
        task.setContent(updateTaskDTO.content());
        task.setTitle(updateTaskDTO.title());
        task.setDueDate(Instant.parse(updateTaskDTO.dueDate()));

        return this.taskRepository.save(task);
    }

    public void delete(long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));

        this.taskRepository.delete(task);
    }
}
