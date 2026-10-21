package com.bm.podroosh.repository;

import com.bm.podroosh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
