package com.example.todo_backend.service;

import com.example.todo_backend.dao.TodoDao;
import com.example.todo_backend.dao.UserDao;
import com.example.todo_backend.dto.TodoDto;
import com.example.todo_backend.entity.Todo;
import com.example.todo_backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final UserDao userDao;
    private final TodoDao todoDao;

    @Transactional
    public TodoDto saveTodo(TodoDto todoDto, String username){
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Convert once and reuse the same entity
        Todo todoEntity = todoEntity(todoDto);
        todoEntity.setUser(user); // Assuming bi-directional relationship setup

        Todo savedTodo = todoDao.save(todoEntity);

        user.addTodo(savedTodo);
        userDao.save(user); // saveAndFlush is usually unnecessary unless required by explicit logic

        return todoDto(savedTodo);
    }

    public TodoDto todoDto(Todo todo){
        TodoDto dto = new TodoDto();
        BeanUtils.copyProperties(todo, dto);
        return dto;
    }

    public Todo todoEntity(TodoDto dto){
        Todo todo = new Todo();
        BeanUtils.copyProperties(dto, todo);

        // Handle null values cleanly without crashing
        LocalDate assignedDate = dto.getAssignedDate() == null ? LocalDate.now() : dto.getAssignedDate();
        todo.setAssignedDate(assignedDate);
        todo.setDueDate(assignedDate.plusDays(dto.getDueDays()));

        return todo;
    }

    public List<TodoDto> findAllTodos(String username){
        return todoDao.findAllTodosByUsername(username)
                .stream()
                .map(this::todoDto)
                .toList();
    }

    @Transactional
    public TodoDto changeToCompleted(long id){
        Todo todo = getTodoById(id);
        todo.setIsCompleted(true);
        return todoDto(todo);
    }

    @Transactional
    public TodoDto changeToUncompleted(long id){
        Todo todo = getTodoById(id);
        todo.setIsCompleted(false);
        return todoDto(todo);
    }

    @Transactional
    public void deleteTodoById(long id){
        Todo todo = getTodoById(id);
        if(todo.getUser() != null){
            todo.getUser().getTodos().remove(todo);
        }
        todoDao.delete(todo);
    }

    private Todo getTodoById(long id){
        return todoDao.findById(id).orElseThrow();
    }
}