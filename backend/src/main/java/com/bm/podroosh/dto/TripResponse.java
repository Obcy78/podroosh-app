package com.bm.podroosh.dto;

import com.bm.podroosh.domain.TripStatus;

import java.time.LocalDate;

public record TripResponse(
        Long id,
        String name,
        String description,
        TripStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}
