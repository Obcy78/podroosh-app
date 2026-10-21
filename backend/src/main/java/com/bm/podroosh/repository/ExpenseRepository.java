package com.bm.podroosh.repository;

import com.bm.podroosh.domain.Expense;
import com.bm.podroosh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
