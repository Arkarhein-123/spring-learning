package com.example.todo_backend.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {
    private Long id; // long = 0 / Long = null
    private String title;
    private String description;
    private Boolean isCompleted;
    private LocalDate assignedDate;
    private LocalDate dueDate;
    private int dueDays;
}
