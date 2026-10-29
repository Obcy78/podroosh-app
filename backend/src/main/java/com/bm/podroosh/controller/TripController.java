package com.bm.podroosh.controller;

import com.bm.podroosh.dto.TripRequest;
import com.bm.podroosh.dto.TripResponse;
import com.bm.podroosh.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trips", description = "Operations on Trips")
@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @Operation(summary = "Get all trips")
    @GetMapping
    public List<TripResponse> getAllTrips() {
        return tripService.getAllTrips();
    }

    @Operation(summary = "Get trip by trip id")
    @GetMapping("/{tripId}")
    public TripResponse getTrip(@PathVariable Long tripId) {
        return tripService.getTrip(tripId);
    }

    @Operation(summary = "Create trip")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TripResponse createTrip(@Valid @RequestBody TripRequest request) {
        return tripService.createTrip(request);
    }

    @Operation(summary = "Update trip")
    @PutMapping("/{tripId}")
    @ResponseStatus(HttpStatus.OK)
    public TripResponse updateTrip(@PathVariable Long tripId, @Valid @RequestBody TripRequest request) {
        return tripService.updateTrip(tripId, request);
    }

}
