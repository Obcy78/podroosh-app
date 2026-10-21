package com.bm.podroosh.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_member_id", nullable = false)
    private TripMember tripMember;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}
