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
    // URL endpoints for the movie and actor services
    private static final String MOVIE_SERVICE_URL = "http://localhost:8080/movie";
    private static final String ACTOR_SERVICE_URL = "http://localhost:8080/actor";

    // Create a new RestTemplate instance to send HTTP requests
    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        // Call the methods to demonstrate the functionality of the movie and actor services
        createMovie();
        createActor();
        linkMovieToActor();
        getMovieByTitle();
        getActorByName();
    }

    private static void createMovie() {
        // Create a new Movie instance and set its properties
        Movie newMovie = new Movie();
        newMovie.setTitle("The Godfather");
        newMovie.setYear(1972);
        newMovie.setDirector("Francis Ford Coppola");
        newMovie.setGenre("Crime, Drama");

        // Create an HTTP request entity containing the new Movie object
        HttpEntity<Movie> request = new HttpEntity<>(newMovie);

        // Send an HTTP POST request to the movie service to create a new movie resource
        // The response entity will contain the newly created movie resource
        Movie createdMovie = restTemplate.postForObject(MOVIE_SERVICE_URL, request, Movie.class);

        // Print out the details of the created movie resource
        System.out.println("Created movie: " + createdMovie);
    }

    private static void createActor() {
        // Create a new Actor instance and set its properties
        Actor newActor = new Actor();
        newActor.setName("Marlon Brando");
        newActor.setBirthYear(1924);
        newActor.setGender("Male");

        // Create an HTTP request entity containing the new Actor object
        HttpEntity<Actor> request = new HttpEntity<>(newActor);

        // Send an HTTP POST request to the actor service to create a new actor resource
        // The response entity will contain the newly created actor resource
        Actor createdActor = restTemplate.postForObject(ACTOR_SERVICE_URL, request, Actor.class);

        // Print out the details of the created actor resource
        System.out.println("Created actor: " + createdActor);
    }

    private static void linkMovieToActor() {
        // Set the title of the movie and name of the actor to link
        String movieTitle = "The Godfather";
        String actorName = "Marlon Brando";

        // Send an HTTP GET request to the movie service to retrieve the movie resource with the given title
        Movie movie = restTemplate.getForObject(MOVIE_SERVICE_URL + "/title/" + movieTitle, Movie.class);

        // Send an HTTP GET request to the actor service to retrieve the actor resource with the given name
        Actor actor = restTemplate.getForObject(ACTOR_SERVICE_URL + "/name/" + actorName, Actor.class);

        // Check if either the movie or actor resource was not found
        if (movie == null) {
            throw new RuntimeException("Movie with title " + movieTitle + " not found");
        }
        if (actor == null) {
            throw new RuntimeException("Actor with name " + actorName + " not found");
        }

        // Set the movie's actors property to a list containing only the linked actor
       
