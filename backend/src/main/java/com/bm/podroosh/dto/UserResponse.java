package com.bm.podroosh.dto;

import com.bm.podroosh.domain.UserStatus;
import lombok.Builder;

import java.time.Instant;

@Builder
public record UserResponse(
        Long id,
        String name,
        String surname,
        String email,
        String phone,
        Instant created,
        UserStatus status
) {
}
