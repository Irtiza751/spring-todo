package com.irtiza.todo.services;

import com.irtiza.todo.auth.JwtService;
import com.irtiza.todo.auth.UserRoles;
import com.irtiza.todo.dtos.AuthResponseDto;
import com.irtiza.todo.entities.User;
import com.irtiza.todo.repository.UserRepository;
import com.irtiza.todo.requests.CreateUserRequest;
import com.irtiza.todo.requests.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto signupUser(CreateUserRequest signupUser) {
        String encodedPassword = passwordEncoder.encode(signupUser.getPassword());
        User newUser = User.builder()
                .username(signupUser.getUsername())
                .email(signupUser.getEmail())
                .password(encodedPassword)
                .role(UserRoles.USER)
                .build();
        User savedUser = userRepository.save(newUser);
        Map<String, String> claims = new HashMap<>();
        claims.put("email", savedUser.getEmail());
        String token = jwtService.generateToken(claims, savedUser);
        return AuthResponseDto.builder().token(token).build();
    }

    public AuthResponseDto siginUser(SigninRequest signinRequest) {
        String username = signinRequest.getUsername();
        String password = signinRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        Map<String, String> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        String token = jwtService.generateToken(claims, user);
        return AuthResponseDto.builder().token(token).build();
    }
}
