package com.todo.todo.management.project.controller;

import com.todo.todo.management.project.dto.TodoDto;
import com.todo.todo.management.project.service.Todoservice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@AllArgsConstructor
@RestController
@RequestMapping("/api/todo")
public class TodoController {
    private Todoservice todoservice;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto)
    {
        return new ResponseEntity<>(todoservice.addTodo(todoDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto>getTodo(@PathVariable("id")Long id)
    {
        return ResponseEntity.ok(todoservice.getTodo(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>>getAllTodos()
    {
        List<TodoDto>todos=todoservice.getAllTodo();
        return ResponseEntity.ok(todos);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto>updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id")Long id)
    {
        return ResponseEntity.ok(todoservice.updateTodo(todoDto, id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteTodo(@PathVariable("id")Long id)
    {
        todoservice.deleteTodo(id);
        return ResponseEntity.ok("Deleted SAuccessfully");
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto>todoCompleted(@PathVariable Long id)
    {
        return ResponseEntity.ok(todoservice.todoCompleted(id));
    }
}
