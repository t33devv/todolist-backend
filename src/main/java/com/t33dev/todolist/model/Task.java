package com.t33dev.todolist.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(
        name = "tasks"
)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "text"
    )
    private String title;

    @Column(
            nullable = true,
            columnDefinition = "text"
    )
    private String description;

    @Column(
            nullable = true,
            columnDefinition = "DATE",
            name = "due_date"
    )
    private LocalDate dueDate;

    private Instant createdAt;

    public Task() {}

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    public Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
}
