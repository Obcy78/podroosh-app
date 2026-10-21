package com.bm.podroosh.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TripStatus status;
    private LocalDate startDate;
    private LocalDate endDate;

    @Builder
    public Trip(String name, String description, TripStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
