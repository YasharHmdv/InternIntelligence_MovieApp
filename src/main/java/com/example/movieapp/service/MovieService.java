package com.example.movieapp.service;

import com.example.movieapp.entity.enums.Genres;
import com.example.movieapp.entity.enums.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Movie> getAllMovies();
    Movie addMovie(Movie movie);
    List<Movie> getMovieInfo(Long movieId);

    List<Movie> getPopularMovies();

    void deleteById(Long movieId);

    ResponseEntity<Movie> update(Long movieId, Movie movie);
}
