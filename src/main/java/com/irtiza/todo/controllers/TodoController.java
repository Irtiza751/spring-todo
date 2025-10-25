package com.irtiza.todo.controllers;

import com.irtiza.todo.dtos.NotFoundErrorResponse;
import com.irtiza.todo.exceptions.NotFoundException;
import com.irtiza.todo.requests.CreateTodoRequest;
import com.irtiza.todo.dtos.TodoResponseDto;
import com.irtiza.todo.requests.UpdateTodoRequest;
import com.irtiza.todo.services.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.StubNotFoundException;
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
    public ResponseEntity<TodoResponseDto> createNewTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.todoService.createTodo(createTodoRequest));
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

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable String id,
                                                      @RequestBody UpdateTodoRequest updateTodoRequest) {
        return ResponseEntity.ok().body(this.todoService.updateOne(id, updateTodoRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable String id) {
        this.todoService.deleteOne(id);
        return  ResponseEntity.accepted().body("Todo deleted successfully");
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponseDto> patchTodo(@PathVariable String id,
                                                     @RequestBody UpdateTodoRequest updateTodo) {
        return ResponseEntity.ok().body(this.todoService.patchOne(id, updateTodo));
    }


}
