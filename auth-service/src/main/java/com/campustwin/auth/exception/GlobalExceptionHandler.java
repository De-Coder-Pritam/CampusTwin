package com.campustwin.auth.exception;

import com.campustwin.auth.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── RuntimeException (email duplicate, user not found, etc.) ─────────────

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, ex.getMessage()));
    }

    // ── Database unique constraint violation ──────────────────────────────────
    // Phone, email, universityId already exists in DB

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(
            DataIntegrityViolationException ex) {

        String message = "Duplicate value detected";
        String cause = ex.getMostSpecificCause().getMessage().toLowerCase();

        if (cause.contains("phone")) {
            message = "Phone number already registered";
        } else if (cause.contains("email")) {
            message = "Email already registered";
        } else if (cause.contains("university_id")) {
            message = "University ID already registered";
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(409, message));
    }

    // ── Wrong HTTP method (GET instead of POST, etc.) ─────────────────────────

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleWrongMethod(
            HttpRequestMethodNotSupportedException ex) {
        String message = "Method '" + ex.getMethod() + "' not allowed. Use: "
                + ex.getSupportedHttpMethods();
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(405, message));
    }

    // ── Wrong URL / endpoint not found ────────────────────────────────────────

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NoResourceFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(404, "Endpoint not found: " + ex.getResourcePath()));
    }

    // ── BadCredentialsException (wrong password) ──────────────────────────────

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(401, "Invalid credentials"));
    }

    // ── Validation errors (@NotBlank, @Email, etc.) ───────────────────────────

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    // ── Koi bhi unexpected exception ──────────────────────────────────────────

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, "Something went wrong"));
    }
}