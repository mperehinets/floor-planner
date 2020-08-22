package com.mper.floorplanner.exception;

public class RoomHasNoRightAnglesException extends RuntimeException {
    public RoomHasNoRightAnglesException(String message) {
        super(message);
    }

    public RoomHasNoRightAnglesException(String message, Throwable cause) {
        super(message, cause);
    }
}
