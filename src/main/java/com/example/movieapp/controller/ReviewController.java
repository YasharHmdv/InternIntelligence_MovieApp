package com.example.movieapp.controller;

import com.example.movieapp.entity.Review;
import com.example.movieapp.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review reviewRequest) {
        return reviewService.save(reviewRequest);
    }

    @PutMapping("/update/{reviewId}")
    public ResponseEntity<Review> update(@PathVariable Long reviewId, @RequestBody Review reviewRequest){
        return reviewService.update(reviewId,reviewRequest);
    }

}
