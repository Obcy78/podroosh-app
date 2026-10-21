package com.bm.podroosh.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "trip_member",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_trip_user",
                columnNames = {"trip_id", "user_id"}
        ))
public class TripMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private Instant joinedAt;


}
