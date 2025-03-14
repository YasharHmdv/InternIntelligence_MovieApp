package com.example.movieapp.service.impl;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.repository.MovieRepository;
import com.example.movieapp.repository.ReviewRepository;
import com.example.movieapp.service.MovieService;
import com.example.movieapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Override
    public ResponseEntity<Review> save(Review reviewRequest) {
        Review review = new Review();
        review.setComments(reviewRequest.getComments());
        review.setRating(reviewRequest.getRating());
        review.setCreatedUserId(reviewRequest.getCreatedUserId());
        review.setCreateTimestamp(reviewRequest.getCreateTimestamp());
        review.setCreatedUserName(reviewRequest.getCreatedUserName());
        if (reviewRequest.getMovie() != null && reviewRequest.getMovie().getMovieId() != null) {
            Optional<Movie> existingMovie = movieRepository.findById(reviewRequest.getMovie().getMovieId());
            if (existingMovie.isPresent()) {
                review.setMovie(existingMovie.get());
            } else {
                throw new RuntimeException("Movie Not Found with Id: " + reviewRequest.getMovie().getMovieId());
            }
        } else {
            throw new RuntimeException("Movie ID must not be null for review association. ");
        }
        Review save = reviewRepository.save(review);
        return ResponseEntity.ok(save);
    }

    @Override
    public ResponseEntity<Review> update(Long reviewId, Review reviewRequest) {
        Optional<Review> existingReviewOpt = reviewRepository.findById(reviewId);
        if (!existingReviewOpt.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Review existingReview = existingReviewOpt.get();
        existingReview.setCreatedUserId(reviewRequest.getCreatedUserId());
        existingReview.setCreatedUserName(reviewRequest.getCreatedUserName());
        existingReview.setComments(reviewRequest.getComments());
        existingReview.setRating(reviewRequest.getRating());
        existingReview.setCreateTimestamp(reviewRequest.getCreateTimestamp());

        if (reviewRequest.getMovie() != null){
            existingReview.setMovie(reviewRequest.getMovie());
        }
        Review updatedReview = reviewRepository.save(existingReview);
        return ResponseEntity.status(HttpStatus.OK).body(updatedReview);

    }

    @Override
    public ResponseEntity<Review> getReviewById(Long reviewId) {
        if (reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).get();
            return ResponseEntity.status(HttpStatus.OK).body(review);
        }else {
            throw new RuntimeException("NOT FOUND REVIEW WITH id:"+ reviewId);
        }
    }
}
