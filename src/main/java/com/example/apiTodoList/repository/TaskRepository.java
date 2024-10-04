package com.example.apiTodoList.repository;

import com.example.apiTodoList.model.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Transactional
    default Task update(Task task){
        return save(task);
    }
}
