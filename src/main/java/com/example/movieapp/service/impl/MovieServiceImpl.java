package com.example.movieapp.service.impl;

import com.example.movieapp.entity.*;
import com.example.movieapp.entity.enums.Genres;
import com.example.movieapp.entity.enums.Languages;
import com.example.movieapp.repository.*;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<Movie> movieList = movieRepository.findAllMoviesWithReviews();

        return movieList;
    }

    @Override
    public Movie addMovie(Movie movieRequest) {
        Movie movie = new Movie();
        movie.setName(movieRequest.getName());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setGenre(movieRequest.getGenre());
        movie.setStory(movieRequest.getStory());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setActive(movieRequest.isActive());
        movie.setBase64Img(movieRequest.getBase64Img());
        movie.setCreatedBy(movieRequest.getCreatedBy());
        movie.setCreatedTimestamp(new Date());
        movie.setLastUpdtTimestamp(new Date());

        // Save the movie first to get a valid movieId
        movie = movieRepository.save(movie);

        // Handle Ratings
        if (movieRequest.getRating() != null) {
            Ratings rating = movieRequest.getRating();
            rating.setMovie(movie); // Associate rating with movie
            ratingRepository.save(rating);
            movie.setRating(rating);
        }

        // Handle Reviews
        if (movieRequest.getReviews() != null) {
            List<Review> reviews = new ArrayList<>();
            for (Review review : movieRequest.getReviews()) {
                review.setMovie(movie); // Link review to movie
                reviews.add(review);
            }
            reviewRepository.saveAll(reviews);
            movie.setReviews(reviews);
        }

        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getMovieInfo(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        Movie movie = movieOptional.get();
        // Update Reviews
        List<Review> reviews = reviewRepository.fetchReviewByMovieId(movie.getMovieId());
        movie.setReviews(reviews);

        // Update Genre & Language
        List<Movie> list = Arrays.asList(new Movie[] { movie });
        updateLanguageGenres(list);

        return list;
    }

    @Override
    public void updateLanguageGenres(List<Movie> list) {

    }

    @Override
    public void addReview(Review reviews) {

    }
}
