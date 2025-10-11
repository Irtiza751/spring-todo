package com.irtiza.todo.controllers;

import com.irtiza.todo.dtos.UserResponseDto;
import com.irtiza.todo.requests.CreateUserRequest;
import com.irtiza.todo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService.createUser(createUserRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findUserById(id));
    }
}
