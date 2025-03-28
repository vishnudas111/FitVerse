package com.fitverse.repository;

// WorkoutRepository.java

import com.fitverse.entity.WorkoutType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutType, String> {
    List<WorkoutType> findByCenterId(String centerId);
}