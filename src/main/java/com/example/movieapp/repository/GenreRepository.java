package com.example.movieapp.repository;

import com.example.movieapp.entity.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genres,Long> {
}
