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

@Service
@Path("/movies")
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GET
    @Path("/actors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void addActor(Actor actor) {
        actorRepository.save(actor);
    }

    public void linkMovieToActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID"));
        Actor actor = actorRepository.findById(actorId).orElseThrow(() -> new IllegalArgumentException("Invalid actor ID"));

        List<Actor> actors = movie.getActors();
        actors.add(actor);
        movie.setActors(actors);
        movieRepository.save(movie);
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new IllegalArgumentException("Invalid movie title"));
    }

    public List<Movie> getMoviesByActorName(String name) {
        return movieRepository.findByActorsName(name);
    }
}
