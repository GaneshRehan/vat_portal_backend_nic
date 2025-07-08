package com.telusko.SpringSecEx;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Global exception handler for all REST controllers.
 * Uses @RestControllerAdvice to intercept exceptions across the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle cases where a JPA entity is not found in the database.
     * Returns HTTP 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle illegal arguments passed to methods.
     * Returns HTTP 400 (Bad Request).
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle illegal state exceptions, indicating conflicts with the current application state.
     * Returns HTTP 409 (Conflict).
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handle validation errors triggered by @Valid or @Validated annotations.
     * Collects all field errors and returns HTTP 400 (Bad Request).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        String messages = ex.getBindingResult()
                             .getAllErrors()
                             .stream()
                             .map(error -> error.getDefaultMessage())
                             .collect(Collectors.joining(", "));
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Validation failed: " + messages,
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle missing request parameters or missing path variables.
     * Returns HTTP 400 (Bad Request).
     */
    @ExceptionHandler({ MissingServletRequestParameterException.class, MissingPathVariableException.class })
    public ResponseEntity<ErrorResponse> handleMissingParams(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle cases where the request body cannot be read (e.g., malformed JSON).
     * Returns HTTP 400 (Bad Request).
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Malformed JSON request: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle access-denied exceptions from Spring Security.
     * Returns HTTP 403 (Forbidden).
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN,
                "Access denied: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    /**
     * Fallback handler for any unhandled exceptions.
     * Returns HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Internal Server Error: " + ex.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Standard error response format returned to clients.
     */
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String path;

        // Constructor initializes timestamp, status code, message, and request path
        public ErrorResponse(HttpStatus status, String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.status = status.value();
            this.error = message;
            this.path = path;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
