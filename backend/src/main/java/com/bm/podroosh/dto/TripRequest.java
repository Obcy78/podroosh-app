package com.bm.podroosh.dto;

import com.bm.podroosh.domain.TripStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TripRequest(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @Nullable
        TripStatus status,
        @Nullable
        LocalDate startDate,
        @Nullable
        LocalDate endDate
) {
}
