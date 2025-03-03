package com.example.movieapp.service.impl;

import com.example.movieapp.entity.*;
import com.example.movieapp.entity.enums.Genres;
import com.example.movieapp.entity.enums.Languages;
import com.example.movieapp.exception.GlobalExceptionHandler;
import com.example.movieapp.exception.MovieNotFoundException;
import com.example.movieapp.repository.*;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;
    private final GlobalExceptionHandler globalExceptionHandler;

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

        return list;
    }

    @Override
    public List<Movie> getPopularMovies() {
        List<Movie> popularMovies = movieRepository.findTop10ByOrderByRating_LikesDesc();
        return popularMovies;
    }

    @Override
    public ResponseEntity<Movie> update(Long movieId,Movie movieRequest) {
        Optional<Movie> existingMovie = movieRepository.findById(movieId);
        if (existingMovie.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Movie existingMovieRequest = existingMovie.get();
        existingMovieRequest.setName(movieRequest.getName());
        existingMovieRequest.setReleaseYear(movieRequest.getReleaseYear());
        existingMovieRequest.setStory(movieRequest.getStory());
        existingMovieRequest.setLanguage(movieRequest.getLanguage());
        existingMovieRequest.setActive(movieRequest.isActive());
        existingMovieRequest.setBase64Img(movieRequest.getBase64Img());
        existingMovieRequest.setCreatedBy(movieRequest.getCreatedBy());
        existingMovieRequest.setCreatedTimestamp(new Date());
        existingMovieRequest.setLastUpdtTimestamp(new Date());
        if (movieRequest.getGenre() != null){
            existingMovieRequest.setGenre(movieRequest.getGenre());
        }
        if (movieRequest.getRating() != null){
            Ratings rating = existingMovieRequest.getRating();
            if (rating == null){
                rating = new Ratings();
                rating.setMovie(existingMovieRequest);
            }
            rating.setLikes(movieRequest.getRating().getLikes());
            rating.setDislike(movieRequest.getRating().getDislike());
            rating.setRating(movieRequest.getRating().getRating());
            rating.setTotalRatings(movieRequest.getRating().getTotalRatings());
            rating.setCreateTimestamp(new Date());
            existingMovieRequest.setRating(rating);
        }
        if (movieRequest.getReviews() != null && !movieRequest.getReviews().isEmpty()){
            for (Review review : movieRequest.getReviews()) {
                review.setMovie(existingMovieRequest);
            }
            reviewRepository.saveAll(movieRequest.getReviews());
            existingMovieRequest.setReviews(movieRequest.getReviews());
        }
        Movie updatedMovie = movieRepository.save(existingMovieRequest);
        return ResponseEntity.ok(updatedMovie);
    }

    @Override
    @Transactional
    public void deleteById(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()){
            if (ratingRepository.existsById(movieId)){
                ratingRepository.deleteByMovieId(movieId);
            }
            movieRepository.deleteById(movieId);
        }else {
            throw new MovieNotFoundException("Movie not found with :D"+movieId);
        }
    }
}
