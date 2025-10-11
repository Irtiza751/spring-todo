package com.irtiza.todo.controllers;

import com.irtiza.todo.dtos.GeneralResponseDto;
import com.irtiza.todo.dtos.UserResponseDto;
import com.irtiza.todo.requests.CreateUserRequest;
import com.irtiza.todo.requests.UpdateUserRequest;
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

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(this.userService.updateUser(id, updateUserRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponseDto> deleteUser(@PathVariable Long id) {
        return ResponseEntity.accepted().body(this.userService.deleteUser(id));
    }
}
