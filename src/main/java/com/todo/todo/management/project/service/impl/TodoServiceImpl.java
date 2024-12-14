package com.todo.todo.management.project.service.impl;

import com.todo.todo.management.project.dto.TodoDto;
import com.todo.todo.management.project.entity.Todo;
import com.todo.todo.management.project.exception.ResourceNotFoundException;
import com.todo.todo.management.project.repository.TodoRepo;
import com.todo.todo.management.project.service.Todoservice;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements Todoservice {
    private final TodoRepo todoRepo;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // Converting TodoDto into Todo entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
        Todo todo=modelMapper.map(todoDto,Todo.class);

        // Save Todo entity to the repository
        Todo savedTodo = todoRepo.save(todo);

        // Converting Todo entity back into TodoDto
//        TodoDto savedTodoDto = new TodoDto();
//        savedTodoDto.setId(savedTodo.getId());
//        savedTodoDto.setTitle(savedTodo.getTitle());
//        savedTodoDto.setDescription(savedTodo.getDescription());
//        savedTodoDto.setCompleted(savedTodo.isCompleted());
        TodoDto savedTodoDto=modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
       Todo todo =todoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found at id"+id));
       return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo>todos =todoRepo.findAll();

        return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto,Long id) {
       Todo todo= todoRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Todo with id"+id+"not found"));
       todo.setTitle(todoDto.getTitle());
       todo.setDescription(todoDto.getDescription());
       todo.setCompleted(todoDto.isCompleted());
       Todo updatedTodo=todoRepo.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo=todoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("not found"));
        todoRepo.deleteById(id);
    }

    @Override
    public TodoDto todoCompleted(Long id) {
       Todo todo= todoRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found"));
       todo.setCompleted(Boolean.TRUE);
      Todo updated= todoRepo.save(todo);
        return modelMapper.map(updated, TodoDto.class);
    }
}
