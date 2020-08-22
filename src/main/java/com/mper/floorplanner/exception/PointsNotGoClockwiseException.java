package com.mper.floorplanner.exception;

public class PointsNotGoClockwiseException extends RuntimeException {
    public PointsNotGoClockwiseException(String message) {
        super(message);
    }

    public PointsNotGoClockwiseException(String message, Throwable cause) {
        super(message, cause);
    }
}
