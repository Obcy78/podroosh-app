package com.bm.podroosh.exceptions;

public class TripNotFoundExceptionApi extends ApiServiceException {
    public TripNotFoundExceptionApi(Long id) {
        super("Trip with id " + id + " not found.");
    }

}
