package com.bm.podroosh.service;

import com.bm.podroosh.domain.Trip;
import com.bm.podroosh.domain.TripStatus;
import com.bm.podroosh.dto.TripRequest;
import com.bm.podroosh.dto.TripResponse;
import com.bm.podroosh.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Audited;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {
    @Audited
    private final TripRepository tripRepository;

    public TripResponse createTrip(TripRequest request) {
        Trip tripEntity = Trip.builder().name(request.name())
                .description(request.description())
                .status(TripStatus.CREATED).build();
        Trip trip = tripRepository.saveAndFlush(tripEntity);
        return new TripResponse(trip.getName(), trip.getDescription(),
                trip.getStatus(), trip.getStartDate(), trip.getEndDate());
    }

    public List<TripResponse> getAllTrips() {
        List<Trip> all = tripRepository.findAll();
        return all.stream().map(trip ->
                new TripResponse(trip.getName(), trip.getDescription(),
                        trip.getStatus(), trip.getStartDate(), trip.getEndDate())
        ).toList();
    }
}
