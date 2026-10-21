package com.bm.podroosh.repository;

import com.bm.podroosh.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
