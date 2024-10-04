package com.example.apiTodoList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "due_date", nullable = false)
    private Instant dueDate;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone;

    @JsonIgnore()
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @Column(name = "user_id")
    private UUID userId;

    public Task(String title, String content, Instant createdAt, Instant dueDate, Boolean isDone, UUID userId) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.isDone = isDone;
        this.userId = userId;
    }
}
