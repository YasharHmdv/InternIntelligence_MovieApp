package com.example.movieapp.service;

import com.example.movieapp.entity.Review;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ResponseEntity<Review> save(Review review);

    ResponseEntity<Review> update(Long reviewId, Review reviewRequest);
}
