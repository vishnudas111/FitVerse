package com.fitverse.repository;

// CenterRepository.java

import com.fitverse.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, String> {
    @Query("SELECT c FROM Center c WHERE c.city = :city")
    List<Center> findByCity(String city);

    @Query("SELECT c FROM Center c JOIN c.slots s WHERE s.workoutType = :workoutType")
    List<Center> findByWorkoutType(String workoutType);
}