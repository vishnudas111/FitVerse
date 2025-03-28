package com.fitverse.repository;

// SlotRepository.java

import com.fitverse.entity.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, String> {
}