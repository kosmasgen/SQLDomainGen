package gr.knowledge.schoolmanagement.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Centralized exception handling for REST APIs.
 */
@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link ResponseStatusException}.
     *
     * @param ex      exception
     * @param request request
     * @return error response
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException ex,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        String message = safeMessage(ex.getReason(), ex.getMessage());
        return build(status, message, ex, request);
    }

    /**
     * Handles request body validation errors.
     *
     * @param ex      exception
     * @param request request
     * @return error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String message = buildValidationMessage(ex);
        return build(HttpStatus.UNPROCESSABLE_ENTITY, message, ex, request);
    }

    /**
     * Handles validation errors for parameters/path variables.
     *
     * @param ex      exception
     * @param request request
     * @return error response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {
        String message = buildConstraintViolationMessage(ex);
        return build(HttpStatus.UNPROCESSABLE_ENTITY, message, ex, request);
    }

    /**
     * Fallback handler for unexpected exceptions.
     *
     * @param ex      exception
     * @param request request
     * @return error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        log.error("Unhandled exception at {} {}", request.getMethod(), request.getRequestURI(), ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex, request);
    }

    private ResponseEntity<ErrorResponse> build(
            HttpStatus status,
            String message,
            Exception ex,
            HttpServletRequest request
    ) {
        ErrorResponse body = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .exception(ex.getClass().getSimpleName())
                .build();

        return ResponseEntity.status(status).body(body);
    }

    private String buildValidationMessage(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        if (errors == null || errors.isEmpty()) {
            return "Validation failed";
        }

        FieldError first = errors.stream()
                .filter(Objects::nonNull)
                .min(Comparator.comparing(FieldError::getField))
                .orElse(errors.get(0));

        String details = errors.stream()
                .filter(Objects::nonNull)
                .map(e -> e.getField() + ": " + safeMessage(e.getDefaultMessage(), "invalid"))
                .distinct()
                .collect(Collectors.joining(", "));

        return safeMessage(first.getField() + ": " + safeMessage(first.getDefaultMessage(), "invalid"), details);
    }

    private String buildConstraintViolationMessage(ConstraintViolationException ex) {
        if (ex.getConstraintViolations() == null || ex.getConstraintViolations().isEmpty()) {
            return "Validation failed";
        }

        return ex.getConstraintViolations().stream()
                .map(v -> v.getPropertyPath() + ": " + safeMessage(v.getMessage(), "invalid"))
                .distinct()
                .collect(Collectors.joining(", "));
    }

    private String safeMessage(String primary, String fallback) {
        String p = primary == null ? "" : primary.trim();
        if (!p.isEmpty()) {
            return p;
        }
        String f = fallback == null ? "" : fallback.trim();
        return f.isEmpty() ? "Error" : f;
    }
}
