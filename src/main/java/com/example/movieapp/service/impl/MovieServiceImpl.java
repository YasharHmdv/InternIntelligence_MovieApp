package com.example.movieapp.service.impl;

import com.example.movieapp.entity.Genres;
import com.example.movieapp.entity.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.RatingRepository;
import com.example.movieapp.repository.ReviewRepository;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;
    @Override
    public List<Languages> getAllLanguages() {
        return null;
    }

    @Override
    public List<Genres> getAllGenres() {
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public void addMovie(Movie movie) {

    }

    @Override
    public List<Movie> getMovieInfo(Integer movieId) {
        return null;
    }

    @Override
    public void updateLanguageGenres(List<Movie> list) {

    }

    @Override
    public void addReview(Review reviews) {

    }
}
