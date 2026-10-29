package com.bm.podroosh.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TripNotFoundException extends ApiServiceException {
    public TripNotFoundException(Long id) {
        super("Trip with id " + id + " not found.");
    }

}
