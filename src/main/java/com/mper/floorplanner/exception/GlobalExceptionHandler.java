package com.mper.floorplanner.exception;

import com.mper.floorplanner.service.impl.RoomServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);

    @ExceptionHandler({RoomNotFoundException.class})
    public ResponseEntity<ApiError> handlerRoomNotFoundException(RoomNotFoundException ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Collections.singletonList(ex.getMessage()));
        LOGGER.error("Room not found, thrown:", ex);
        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler({PointsNotGoClockwiseException.class})
    public ResponseEntity<ApiError> handlerPointsNotGoClockwiseException(PointsNotGoClockwiseException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Collections.singletonList(ex.getMessage()));
        LOGGER.error("Input points don't go clockwise, thrown:", ex);
        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler({RoomHasNoRightAnglesException.class})
    public ResponseEntity<ApiError> handlerRoomHasNoRightAngles(RoomHasNoRightAnglesException ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                Collections.singletonList(ex.getMessage()));
        LOGGER.error("Room has no right angles, thrown:", ex);
        return ResponseEntity
                .status(apiError.getStatus())
                .body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Invalid arguments", errors);
        LOGGER.error("Bad request. Errors: {}", errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    public static class ApiError {
        private HttpStatus status;
        private String message;
        private Collection<?> errors;

        public ApiError(HttpStatus status, String message, Collection<?> errors) {
            this.status = status;
            this.message = message;
            this.errors = errors;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public void setStatus(HttpStatus status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Collection<?> getErrors() {
            return errors;
        }

        public void setErrors(Collection<?> errors) {
            this.errors = errors;
        }
    }
}
