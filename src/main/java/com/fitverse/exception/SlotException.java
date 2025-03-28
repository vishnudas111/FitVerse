package com.fitverse.exception;

// SlotException.java

import lombok.Getter;

@Getter
public class SlotException extends Exception {
    private final String errorCode;

    public SlotException(String message) {
        super(message);
        this.errorCode = "SLOT_ERROR";
    }
}
