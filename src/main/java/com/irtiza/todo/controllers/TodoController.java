package com.irtiza.todo.controllers;

import com.irtiza.todo.dtos.CreateTodoDto;
import com.irtiza.todo.dtos.TodoResponseDto;
import com.irtiza.todo.services.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Todo Controller", description = "Handle todos operations")
@RestController
@RequestMapping("todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping
    public ResponseEntity<TodoResponseDto> createNewTodo(@RequestBody CreateTodoDto createTodoDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.todoService.createTodo(createTodoDto));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getTodos() {
        List<TodoResponseDto> todos = this.todoService.getTodos();
        return ResponseEntity.ok().body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> findTodoById(@PathVariable String id) {
        return ResponseEntity.ok().body(this.todoService.findById(id));
    }
}
