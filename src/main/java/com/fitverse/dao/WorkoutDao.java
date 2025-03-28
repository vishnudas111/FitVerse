package com.fitverse.dao;

// WorkoutDao.java

import com.fitverse.entity.WorkoutType;

import java.util.List;

public interface WorkoutDao {
    void addWorkoutType(WorkoutType workoutType);
    List<WorkoutType> getWorkoutsByCenter(String centerId);
}
