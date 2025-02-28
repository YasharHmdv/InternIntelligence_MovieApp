package com.example.movieapp.controller;

import com.example.movieapp.entity.Genres;
import com.example.movieapp.entity.Languages;
import com.example.movieapp.entity.Movie;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.boot.Banner.Mode.LOG;

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
}
