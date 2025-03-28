package com.fitverse.exception;

// UserException.java

import lombok.Getter;

@Getter
public class UserException extends Exception {
    private final String errorCode;

    public UserException(String message) {
        super(message);
        this.errorCode = "USER_ERROR";
    }
}