package com.irtiza.todo.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    @NotBlank
    @Size(max = 100)
    private String username;

    @NotBlank
    private String password;
}

