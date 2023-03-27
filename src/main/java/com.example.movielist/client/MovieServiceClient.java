package com.example.movielist.client;

import com.example.movielist.model.Actor;
import com.example.movielist.model.Movie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MovieActorServiceClient {
    private static final String MOVIE_SERVICE_URL = "http://localhost:8080/movie";
    private static final String ACTOR_SERVICE_URL = "http://localhost:8080/actor";

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        createMovie();
        createActor();
        linkMovieToActor();
        getMovieByTitle();
        getActorByName();
    }

    private static void createMovie() {
        Movie newMovie = new Movie();
        newMovie.setTitle("The Godfather");
        newMovie.setYear(1972);
        newMovie.setDirector("Francis Ford Coppola");
        newMovie.setGenre("Crime, Drama");
        HttpEntity<Movie> request = new HttpEntity<>(newMovie);
        Movie createdMovie = restTemplate.postForObject(MOVIE_SERVICE_URL, request, Movie.class);
        System.out.println("Created movie: " + createdMovie);
    }

    private static void createActor() {
        Actor newActor = new Actor();
        newActor.setName("Marlon Brando");
        newActor.setBirthYear(1924);
        newActor.setGender("Male");
        HttpEntity<Actor> request = new HttpEntity<>(newActor);
        Actor createdActor = restTemplate.postForObject(ACTOR_SERVICE_URL, request, Actor.class);
        System.out.println("Created actor: " + createdActor);
    }

    private static void linkMovieToActor() {
        String movieTitle = "The Godfather";
        String actorName = "Marlon Brando";
        Movie movie = restTemplate.getForObject(MOVIE_SERVICE_URL + "/title/" + movieTitle, Movie.class);
        Actor actor = restTemplate.getForObject(ACTOR_SERVICE_URL + "/name/" + actorName, Actor.class);
        if (movie == null) {
            throw new RuntimeException("Movie with title " + movieTitle + " not found");
        }
        if (actor == null) {
            throw new RuntimeException("Actor with name " + actorName + " not found");
        }
        movie.setActors(Arrays.asList(actor));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Actor>> request = new HttpEntity<>(movie.getActors(), headers);
        restTemplate.exchange(MOVIE_SERVICE_URL + "/" + movie.getId() + "/actors", HttpMethod.PUT, request, Void.class);
        System.out.println("Linked movie " + movieTitle + " to actor " + actorName);
    }

    private static void getMovieByTitle() {
        String title = "The Godfather";
        Movie movie = restTemplate.getForObject(MOVIE_SERVICE_URL + "/title/" + title, Movie.class);
        if (movie == null) {
            throw new ResourceNotFoundException("Movie with title " + title + " not found");
        }
        System.out.println("Movie by title " + title + ": " + movie);
    }

    private static void getActorByName() {
        String name = "Marlon Brando";
        Actor actor = restTemplate.getForObject(ACTOR_SERVICE_URL + "/name/" + name, Actor.class);
        if (actor == null) {
            throw
