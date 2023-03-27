package com.example.movielist.service;

import com.example.movielist.model.Actor;
import com.example.movielist.model.Movie;
import com.example.movielist.repository.ActorRepository;
import com.example.movielist.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service // denotes this class as a Spring service component
@Path("/movies") // sets the base path for JAX-RS resource methods
public class MovieService {

    // injects the necessary repositories via constructor dependency injection
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    // JAX-RS resource method that returns all movies as JSON
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    // JAX-RS resource method that returns all actors as JSON
    @GET
    @Path("/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    // adds a movie to the database via the movie repository
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // adds an actor to the database via the actor repository
    public void addActor(Actor actor) {
        actorRepository.save(actor);
    }

    // links a movie to an actor by adding the actor to the movie's list of actors
    public void linkMovieToActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID"));
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new IllegalArgumentException("Invalid actor ID"));

        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);
        movieRepository.save(movie);
    }

    // retrieves a movie by its title via the movie repository
    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Invalid movie title"));
    }

    // retrieves all movies that have an actor with a given name via the movie repository
    public List<Movie> getMoviesByActorName(String name) {
        return movieRepository.findByActorsName(name);
    }
}
