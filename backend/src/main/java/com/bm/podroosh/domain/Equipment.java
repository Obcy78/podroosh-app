package com.bm.podroosh.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provided_by_id")
    private TripMember providedBy;
    private String description;
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;

}
