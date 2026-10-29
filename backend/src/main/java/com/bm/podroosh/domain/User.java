package com.bm.podroosh.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Table(name = "app_user")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant created;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Builder
    public User(String name, String surname, String email, String phone, UserStatus status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }
}
