package com.fitverse.exception;

// CenterException.java

import lombok.Getter;

@Getter
public class CenterException extends Exception {
    private final String errorCode;

    public CenterException(String message) {
        super(message);
        this.errorCode = "CENTER_ERROR";
    }
}