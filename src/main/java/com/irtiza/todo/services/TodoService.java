package com.irtiza.todo.services;

import com.irtiza.todo.daos.TodoRepository;
import com.irtiza.todo.dtos.CreateTodoDto;
import com.irtiza.todo.dtos.TodoResponseDto;
import com.irtiza.todo.entities.Todo;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<TodoResponseDto> getTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        return todos.stream()
                .map(todo -> new TodoResponseDto(
                        todo.getId(),
                        todo.getName(),
                        todo.getDescription(),
                        todo.isCompleted()))
                .toList();
    }

    @Transient
    public TodoResponseDto createTodo(CreateTodoDto createTodoDto) {
        Todo todo = new Todo(createTodoDto.getName(), createTodoDto.getDescription(), createTodoDto.isCompleted());
         Todo newTodo = this.todoRepository.save(todo);
         return new TodoResponseDto(newTodo.getId(), newTodo.getName(), newTodo.getDescription(), newTodo.isCompleted());
    }

    public TodoResponseDto findById(String id) {
        Todo todo = this.todoRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new IllegalArgumentException("todo with this id does not exist"));
        return new TodoResponseDto(todo.getId(), todo.getName(), todo.getDescription(), todo.isCompleted());
    }
}
