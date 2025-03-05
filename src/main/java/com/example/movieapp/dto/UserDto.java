package com.example.movieapp.dto;

import com.example.movieapp.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String username;
    String email;
    String password;
    Set<Role> roles = new HashSet<>();
}
