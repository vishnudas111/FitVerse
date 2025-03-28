package com.fitverse.controller;

// WorkoutController.java

import com.fitverse.entity.WorkoutType;
import com.fitverse.exception.WorkoutException;
import com.fitverse.service.WorkoutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@Slf4j
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public WorkoutType addWorkoutType(@RequestBody WorkoutType workoutType) {
        log.info("Adding new workout type: {}", workoutType.getName());
        return workoutService.addWorkoutType(workoutType);
    }

    @GetMapping("/center/{centerId}")
    public List<WorkoutType> getWorkoutsByCenter(@PathVariable String centerId) {
        log.info("Fetching workouts for center: {}", centerId);
        return workoutService.getWorkoutsByCenter(centerId);
    }
}
