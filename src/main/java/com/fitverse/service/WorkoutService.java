package com.fitverse.service;

// WorkoutService.java

import com.fitverse.dao.CenterDao;
import com.fitverse.dao.WorkoutDao;
import com.fitverse.entity.WorkoutType;
import com.fitverse.exception.WorkoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WorkoutService {
    private final WorkoutDao workoutDao;
    private final CenterDao centerDao;

    public WorkoutService(WorkoutDao workoutDao, CenterDao centerDao) {
        this.workoutDao = workoutDao;
        this.centerDao = centerDao;
    }

    public WorkoutType addWorkoutType(WorkoutType workoutType) throws WorkoutException {
        if (centerDao.getCenter(workoutType.getCenterId()).isEmpty()) {
            throw new WorkoutException("Center not found");
        }

        log.info("Adding workout type: {} for center: {}", workoutType.getName(), workoutType.getCenterId());
        workoutDao.addWorkoutType(workoutType);
        return workoutType;
    }

    public List<WorkoutType> getWorkoutsByCenter(String centerId) {
        log.info("Fetching workouts for center: {}", centerId);
        return workoutDao.getWorkoutsByCenter(centerId);
    }
}