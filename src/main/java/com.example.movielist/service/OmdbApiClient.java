package com.example.movielist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OmdbApiClient {

    @Value("${omdb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final String omdbApiUrl = "http://www.omdbapi.com/";

    public String getMovieRanking(String movieTitle) {
        String url = omdbApiUrl + "?apikey=" + apiKey + "&t=" + movieTitle;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String responseBody = response.getBody();
        // TODO: Parse the response body to extract the movie ranking
        return null;
    }
}
