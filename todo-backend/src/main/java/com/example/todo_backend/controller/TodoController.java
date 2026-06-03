package com.example.todo_backend.controller;

import com.example.todo_backend.dto.TodoDto;
import com.example.todo_backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService todoService;

    // Kept the record for clean DTO binding from JSON
    public record TodoRequest(
            String title,
            String description,
            int dueDays
    ){}

    // http://localhost:8080/api/todos
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TodoDto createTodo(@RequestBody TodoRequest dto, Authentication auth){
        // Convert Request Record to TodoDto before passing to Service
        TodoDto todoDto = new TodoDto();
        todoDto.setIsCompleted(false);
        todoDto.setTitle(dto.title());
        todoDto.setDescription(dto.description());
        todoDto.setDueDays(dto.dueDays());

        return todoService.saveTodo(todoDto, auth.getName());
    }

    @GetMapping
    public List<TodoDto> findAllTodos(Authentication auth){
        return todoService.findAllTodos(auth.getName());
    }

    @PutMapping("/completed/{id}")
    public TodoDto changeCompleted(@PathVariable("id") long id){
        return todoService.changeToCompleted(id);
    }

    @PutMapping("/uncompleted/{id}")
    public TodoDto changeUncompleted(@PathVariable("id")long id){
        return todoService.changeToUncompleted(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id")long id){
        todoService.deleteTodoById(id);
    }

}