package com.example.movieapp.service;

import com.example.movieapp.dto.LoginRequest;
import com.example.movieapp.dto.SignupRequest;
import com.example.movieapp.entity.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest);
    ResponseEntity<?> logoutUser();

    List<User> getUsers();
}
