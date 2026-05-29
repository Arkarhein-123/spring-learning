package com.example.todo_backend.dto;

public record ResponseDto(
        Long id,
        String username,
        String email
) {
}
