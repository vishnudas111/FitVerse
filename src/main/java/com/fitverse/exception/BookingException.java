package com.fitverse.exception;

// BookingException.java

import lombok.Getter;

@Getter
public class BookingException extends Exception {
    private final String errorCode;

    public BookingException(String message) {
        super(message);
        this.errorCode = "BOOKING_ERROR";
    }

    public BookingException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
