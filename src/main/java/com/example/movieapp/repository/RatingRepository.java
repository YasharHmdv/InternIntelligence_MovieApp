package com.example.movieapp.repository;

import com.example.movieapp.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RatingRepository extends JpaRepository<Ratings,Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Ratings r WHERE r.movie.movieId = :movieId")
    void deleteByMovieId(@Param("movieId") Long movieId);
}
