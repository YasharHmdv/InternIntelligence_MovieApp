package com.example.movieapp.controller;

import com.example.movieapp.entity.enums.Genres;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @GetMapping
    public List<Genres> getAllGenres() {
        return Arrays.asList(Genres.values());
    }
}
