package com.example.dominantsoftdevelopment.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public class RestException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    private RestException(String message) {
        super(message);
    }

    private RestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static RestException restThrow(String message) {
        return new RestException(message);
    }

    public static RestException restThrow(String message, HttpStatus status) {
        return new RestException(message, status);
    }
}
