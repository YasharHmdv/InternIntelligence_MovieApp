package com.example.movieapp.service;

import com.example.movieapp.entity.Genres;
import com.example.movieapp.entity.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<Languages> getAllLanguages();
    List<Genres> getAllGenres();
    List<Movie> getAllMovies();
    void addMovie(Movie movie);
    List<Movie> getMovieInfo(Integer movieId);
    void updateLanguageGenres(List<Movie> list);
    void addReview(Review reviews);

}
