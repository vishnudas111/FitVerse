package com.fitverse.exception;

// WorkoutException.java

import lombok.Getter;

@Getter
public class WorkoutException extends Exception {
    private final String errorCode;

    public WorkoutException(String message) {
        super(message);
        this.errorCode = "WORKOUT_ERROR";
    }
}