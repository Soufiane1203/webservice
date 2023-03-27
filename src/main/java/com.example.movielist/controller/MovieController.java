package com.example.movielist.controller;

import com.example.movielist.model.Actor;
import com.example.movielist.model.Movie;
import com.example.movielist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @PostMapping("/actors")
    public ResponseEntity<Void> addActor(@RequestBody Actor actor) {
        movieService.addActor(actor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{movieId}/actors/{actorId}")
    public ResponseEntity<Void> linkMovieToActor(@PathVariable Long movieId, @PathVariable Long actorId) {
        movieService.linkMovieToActor(movieId, actorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        Movie movie = movieService.getMovieByTitle(title);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/actors/{name}")
    public ResponseEntity<List<Movie>> getMoviesByActorName(@PathVariable String name) {
        List<Movie> movies = movieService.getMoviesByActorName(name);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
