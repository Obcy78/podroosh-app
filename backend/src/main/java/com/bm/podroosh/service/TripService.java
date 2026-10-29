package com.bm.podroosh.service;

import com.bm.podroosh.domain.Trip;
import com.bm.podroosh.domain.TripStatus;
import com.bm.podroosh.dto.TripRequest;
import com.bm.podroosh.dto.TripResponse;
import com.bm.podroosh.exceptions.TripNotFoundException;
import com.bm.podroosh.repository.TripRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    @Transactional
    public TripResponse createTrip(TripRequest request) {
        Trip tripEntity = mapToEntity(request);
        Trip trip = tripRepository.save(tripEntity);
        return new TripResponse(trip.getId(), trip.getName(), trip.getDescription(),
                trip.getStatus(), trip.getStartDate(), trip.getEndDate());
    }

    private Trip mapToEntity(TripRequest request) {
        return Trip.builder().name(request.name())
                .description(request.description())
                .status(TripStatus.CREATED)
                .startDate(request.startDate())
                .endDate(request.endDate())
                .build();
    }

    public List<TripResponse> getAllTrips() {
        List<Trip> all = tripRepository.findAll();
        return all.stream().map(trip ->
                new TripResponse(trip.getId(), trip.getName(), trip.getDescription(),
                        trip.getStatus(), trip.getStartDate(), trip.getEndDate())
        ).toList();
    }

    public TripResponse getTrip(Long tripId) {
        return toResponse(tripRepository.findById(tripId).orElseThrow(() ->
                new TripNotFoundException(tripId)));
    }

    @Transactional
    public TripResponse updateTrip(Long tripId, TripRequest request) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() ->
                new TripNotFoundException(tripId));
        updateEntity(request, trip);
        return toResponse(trip);
    }

    private void updateEntity(TripRequest request, Trip entity) {
        entity.setName(request.name());
        entity.setDescription(request.description());
        if (request.status() != null) entity.setStatus(request.status());
        if (request.startDate() != null) entity.setStartDate(request.startDate());
        if (request.endDate() != null) entity.setEndDate(request.endDate());
    }

    private TripResponse toResponse(Trip trip) {
        return new TripResponse(
                trip.getId(),
                trip.getName(),
                trip.getDescription(),
                trip.getStatus(),
                trip.getStartDate(),
                trip.getEndDate()
        );
    }
}
