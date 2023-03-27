package com.example.movielist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbApiClient {

    // Get the OMDB API key from application.properties
    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String omdbApiUrl = "http://www.omdbapi.com/";

    public String getMovieRanking(String movieTitle) {

        // Build the URL to the OMDB API with the movie title and API key
        String url = omdbApiUrl + "?apikey=" + apiKey + "&t=" + movieTitle;

        // Send a GET request to the OMDB API and get the response
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        // Get the response body as a string
        String responseBody = response.getBody();

        // Return the movie ranking as a string
        return null;
    }
}
