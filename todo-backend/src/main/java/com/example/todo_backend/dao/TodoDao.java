package com.example.todo_backend.dao;

import com.example.todo_backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoDao extends JpaRepository<Todo, Long> {
    @Query("""
        SELECT t FROM Todo t WHERE t.user.username = :username
    """)
    List<Todo> findAllTodosByUsername(@Param("username") String username);
}
