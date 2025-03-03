package com.example.movieapp.repository;

import com.example.movieapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m LEFT JOIN FETCH m.reviews")
    List<Movie> findAllMoviesWithReviews();

    @Query(value = "SELECT m.movie_id AS movieId, m.name, m.release_year, r.rating " +
            "FROM movie m " +
            "LEFT JOIN ratings r ON m.movie_id = r.movie_id " +
            "ORDER BY r.rating DESC",
            nativeQuery = true)
    List<Movie> findTop10ByOrderByRating_LikesDesc();

    void deleteById(Long movieId);
}