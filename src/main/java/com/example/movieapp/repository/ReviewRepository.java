package com.example.movieapp.repository;

import com.example.movieapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("Select reviews from Review reviews where reviews.movie.movieId= :movieId")
    List<Review> fetchReviewByMovieId(@Param("movieId") Long movieId);
}