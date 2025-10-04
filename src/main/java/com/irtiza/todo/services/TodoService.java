package com.irtiza.todo.services;

import com.irtiza.todo.daos.TodoRepository;
import com.irtiza.todo.requests.CreateTodoRequest;
import com.irtiza.todo.dtos.TodoResponseDto;
import com.irtiza.todo.entities.Todo;
import com.irtiza.todo.requests.UpdateTodoRequest;
import com.irtiza.todo.utils.DtoPatcher;
//import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
//    private final EntityManager entityManager;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
//        this.entityManager = entityManager;
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
    public TodoResponseDto createTodo(CreateTodoRequest createTodoRequest) {
        Todo todo = new Todo(createTodoRequest.getName(), createTodoRequest.getDescription(), createTodoRequest.isCompleted());
         Todo newTodo = this.todoRepository.save(todo);
         return new TodoResponseDto(newTodo.getId(), newTodo.getName(), newTodo.getDescription(), newTodo.isCompleted());
    }

    public TodoResponseDto findById(String id) {
        Todo todo = this.todoRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("todo with this id does not exist"));
        return new TodoResponseDto(todo.getId(), todo.getName(), todo.getDescription(), todo.isCompleted());
    }

    public TodoResponseDto updateOne(String id, UpdateTodoRequest updateTodoRequest) {
        Todo todo = this.todoRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo id"));

        todo.setName(updateTodoRequest.getName());
        todo.setDescription(updateTodoRequest.getDescription());
        todo.setCompleted(updateTodoRequest.isCompleted());

        Todo updatedTodo = this.todoRepository.save(todo);

        return  new TodoResponseDto(
                updatedTodo.getId(),
                updatedTodo.getName(),
                updatedTodo.getDescription(),
                updatedTodo.isCompleted()
        );
    }

    public void deleteOne(String id) {
        this.todoRepository.deleteById(Integer.valueOf(id));
    }

    public TodoResponseDto patchOne(String id, UpdateTodoRequest updateTodoRequest) {
        Todo todo = this.todoRepository.findById(Integer.valueOf(id))
                .orElseThrow(() -> new IllegalArgumentException("Invalid todo id"));
        DtoPatcher.patchFields(updateTodoRequest, todo);
        Todo updatedTodo = this.todoRepository.save(todo);
        return new TodoResponseDto(
                updatedTodo.getId(),
                updatedTodo.getName(),
                updatedTodo.getDescription(),
                updatedTodo.isCompleted()
        );
    }
}
