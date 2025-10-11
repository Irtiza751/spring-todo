package com.irtiza.todo.services;

import com.irtiza.todo.dtos.GeneralResponseDto;
import com.irtiza.todo.dtos.UserResponseDto;
import com.irtiza.todo.entities.User;
import com.irtiza.todo.repository.UserRepository;
import com.irtiza.todo.requests.CreateUserRequest;
import com.irtiza.todo.requests.UpdateUserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public UserResponseDto createUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .build();
        User savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserResponseDto.class);
    }

    public List<UserResponseDto> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> this.modelMapper.map(user, UserResponseDto.class))
                .toList();
    }

    public UserResponseDto findUserById(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow();
        return this.modelMapper.map(user, UserResponseDto.class);
    }

    public UserResponseDto updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = this.userRepository.findById(id).orElseThrow();
        user.setUsername(updateUserRequest.getUsername());
        user.setEmail(updateUserRequest.getEmail());
        user.setPassword(updateUserRequest.getPassword());

        User updatedUser = this.userRepository.save(user);
        return this.modelMapper.map(updatedUser, UserResponseDto.class);
    }

    public GeneralResponseDto deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return new GeneralResponseDto("User deleted successfully");
    }
}
