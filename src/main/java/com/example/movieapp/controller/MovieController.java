package com.example.movieapp.controller;

import com.example.movieapp.entity.Genres;
import com.example.movieapp.entity.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @GetMapping("/languages")
    public List<Languages> getAllLanguages() {
        return movieService.getAllLanguages();
    }

    @GetMapping("/genres")
    public List<Genres> getAllGenres() {
        return movieService.getAllGenres();
    }

    @GetMapping( "/popular")
    public List<Movie> getPopularMovies() {
        LOG.info("Fetch Popular Movies...");
        List<Movie> list = movieService.getAllMovies();
        LOG.info(": " + list.size());
        List<Movie> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
                .collect(Collectors.toList());
        LOG.debug(": " + ratedMovies.size());
        return ratedMovies;
    }

    @GetMapping("/{movieId}")
    public List<Movie> getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieInfo(movieId);
    }

    @PostMapping("/review")
    public List<Movie> addMovieReview(@RequestBody Review reviews) {
        LOG.info("Add Movie Reviews...");
        movieService.addReview(reviews);

        return movieService.getMovieInfo(reviews.getReviewId());
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        LOG.info("Fetch all the Movies...");
        return movieService.getAllMovies();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        LOG.info("Add a Movie...");
        return movieService.addMovie(movie);

    }
}
