package com.bm.podroosh.dto;

import com.bm.podroosh.domain.UserStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String email,
        String phone,
        @Nullable
        UserStatus status
) {
}
