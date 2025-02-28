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
    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public List<Languages> getAllLanguages() {
        return movieService.getAllLanguages();
    }
    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public List<Genres> getAllGenres() {
        return movieService.getAllGenres();
    }

    @RequestMapping(value = "/popular", method = RequestMethod.GET)
    public List<Movie> getPopularMovies() {
        LOG.info("Fetch Popular Movies...");
        List<Movie> list = movieService.getAllMovies();
        LOG.info(": " + list.size());
        List<Movie> ratedMovies = list.parallelStream().filter(obj -> null != obj.getRating())
                .collect(Collectors.toList());
        LOG.debug(": " + ratedMovies.size());
        return ratedMovies;
    }
    @RequestMapping(value = "/{movieId}", method = RequestMethod.GET)
    public List<Movie> getMovieInfo(@PathVariable("movieId") Integer movieId) {
        return movieService.getMovieInfo(movieId);
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public List<Movie> addMovieReview(@RequestBody Review reviews) {
        LOG.info("Add Movie Reviews...");
        movieService.addReview(reviews);

        return movieService.getMovieInfo(reviews.getMovieId());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Movie> getAllMovies() {
        LOG.info("Fetch all the Movies...");
        return movieService.getAllMovies();
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<Movie> addMovie(@RequestBody Movie movie) {
        LOG.info("Add a Movie...");
        movieService.addMovie(movie);
        return movieService.getAllMovies();
    }
}
