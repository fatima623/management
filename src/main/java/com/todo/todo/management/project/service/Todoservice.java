package com.todo.todo.management.project.service;

import com.todo.todo.management.project.dto.TodoDto;

import java.util.List;

public interface Todoservice {
    TodoDto addTodo(TodoDto todoDto);

    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodo();

    TodoDto updateTodo(TodoDto todoDto, Long id);

    void deleteTodo(Long id);
    TodoDto todoCompleted(Long id);
}
