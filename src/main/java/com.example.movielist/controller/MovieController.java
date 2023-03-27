// Import necessary classes
package com.example.movielist.controller;

import com.example.movielist.model.Actor;
import com.example.movielist.model.Movie;
import com.example.movielist.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define the REST controller and base URI path
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    // Create a private final instance of the MovieService
    private final MovieService movieService;

    // Inject the MovieService via constructor
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // Define the POST endpoint for adding a movie
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        // Call the MovieService method to add the movie and get the saved movie
        Movie savedMovie = movieService.addMovie(movie);
        // Return the saved movie along with the CREATED HTTP status
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    // Define the POST endpoint for adding an actor to the movie
    @PostMapping("/actors")
    public ResponseEntity<Void> addActor(@RequestBody Actor actor) {
        // Call the MovieService method to add the actor to the movie
        movieService.addActor(actor);
        // Return the CREATED HTTP status with no body
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Define the PUT endpoint for linking a movie to an actor
    @PutMapping("/{movieId}/actors/{actorId}")
    public ResponseEntity<Void> linkMovieToActor(@PathVariable Long movieId, @PathVariable Long actorId) {
        // Call the MovieService method to link the movie to the actor
        movieService.linkMovieToActor(movieId, actorId);
        // Return the OK HTTP status with no body
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Define the GET endpoint for getting a movie by title
    @GetMapping("/{title}")
    public ResponseEntity<Movie> getMovieByTitle(@PathVariable String title) {
        // Call the MovieService method to get the movie by title
        Movie movie = movieService.getMovieByTitle(title);
        // Return the movie along with the OK HTTP status
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    // Define the GET endpoint for getting movies by actor name
    @GetMapping("/actors/{name}")
    public ResponseEntity<List<Movie>> getMoviesByActorName(@PathVariable String name) {
        // Call the MovieService method to get movies by actor name
        List<Movie> movies = movieService.getMoviesByActorName(name);
        // Return the list of movies along with the OK HTTP status
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
