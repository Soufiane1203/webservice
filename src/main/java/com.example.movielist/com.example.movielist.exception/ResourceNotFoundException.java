// Import necessary libraries
package com.example.movielist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Set the response status to NOT_FOUND
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    // Constructor with message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
