package com.example.movieapp.repository;

import java.util.List;
import java.util.Optional;

import com.example.movieapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Query(value = "SELECT users.* FROM users WHERE  user_id > 1  ORDER BY user_id ASC", nativeQuery = true)
  List<User> findAllUser();
}