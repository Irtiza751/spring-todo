package com.irtiza.todo.controllers;

import com.irtiza.todo.dtos.AuthResponseDto;
import com.irtiza.todo.requests.CreateUserRequest;
import com.irtiza.todo.requests.SigninRequest;
import com.irtiza.todo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signupUser(@RequestBody CreateUserRequest signupRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signupUser(signupRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDto> signinUser(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.siginUser(signinRequest));
    }
}
