package com.bm.podroosh.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;
    private String description;
    @Enumerated(EnumType.STRING)
    private ExpenseType type;
    @NotNull
    @Digits(integer = 6, fraction = 2)
    @Column(name = "amount", nullable = false, precision = 6, scale = 2)
    private BigDecimal amount;
}
