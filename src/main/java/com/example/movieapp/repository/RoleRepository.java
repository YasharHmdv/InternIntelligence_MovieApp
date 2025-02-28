package com.example.movieapp.repository;

import java.util.Optional;

import com.example.movieapp.entity.ERole;
import com.example.movieapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}