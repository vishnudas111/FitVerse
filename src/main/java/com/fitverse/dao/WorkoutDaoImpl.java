package com.fitverse.dao;

// WorkoutDaoImpl.java

import com.fitverse.entity.WorkoutType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class WorkoutDaoImpl implements WorkoutDao {
    private final Map<String, List<WorkoutType>> centerWorkouts = new HashMap<>();

    @Override
    public void addWorkoutType(WorkoutType workoutType) {
        log.debug("Adding workout type: {}", workoutType.getName());
        centerWorkouts.computeIfAbsent(workoutType.getCenterId(), k -> new ArrayList<>())
                .add(workoutType);
    }

    @Override
    public List<WorkoutType> getWorkoutsByCenter(String centerId) {
        log.debug("Fetching workouts for center: {}", centerId);
        return centerWorkouts.getOrDefault(centerId, new ArrayList<>());
    }
}
