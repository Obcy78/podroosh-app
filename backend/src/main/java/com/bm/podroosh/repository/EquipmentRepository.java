package com.bm.podroosh.repository;

import com.bm.podroosh.domain.Equipment;
import com.bm.podroosh.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
