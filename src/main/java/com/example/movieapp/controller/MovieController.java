package com.example.movieapp.controller;

import com.example.movieapp.entity.enums.Genres;
import com.example.movieapp.entity.enums.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.entity.Review;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {
    private final MovieService movieService;
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @GetMapping("/languages")
    public List<Languages> getAllLanguages() {
        return Arrays.asList(Languages.values());
    }

    @GetMapping( "/popular")
    public ResponseEntity<List<Movie>> getPopularMovies() {
        LOG.info("Fetch Popular Movies...");
      /*  List<Movie> list = movieService.getAllMovies();
        LOG.info(": " + list.size());
        List<Movie> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
                .collect(Collectors.toList());
        LOG.debug(": " + ratedMovies.size());
        return ratedMovies;*/
        List<Movie> movieList = movieService.getPopularMovies();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping("/{movieId}")
    public List<Movie> getMovieInfo(@PathVariable("movieId") Long movieId) {
        return movieService.getMovieInfo(movieId);
    }


    @GetMapping
    public List<Movie> getAllMovies() {
        LOG.info("Fetch all the Movies...");
        return movieService.getAllMovies();
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long movieId,@RequestBody Movie movie) {
        LOG.info("Updating Movie...");
        movieService.update(movieId,movie);
        return ResponseEntity.ok(movie);
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        LOG.info("Add a Movie...");
        return movieService.addMovie(movie);

    }
    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable Long movieId) {
        movieService.deleteById(movieId);
    }
}
