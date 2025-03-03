package com.example.movieapp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
public class SignupRequest {
    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Size(max = 50)
    private String email;

    private Set<String> role;
    
    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 40)
    private String password;

}