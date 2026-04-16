package gr.knowledge.pepTest.exception;

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
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    /**
     * Handles {@link GeneratedRuntimeException}.
     *
     * @param exception thrown generated runtime exception
     * @param request current HTTP request
     * @return standardized error response
     */
    @ExceptionHandler(GeneratedRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGeneratedRuntimeException(
            GeneratedRuntimeException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = resolveHttpStatusFromErrorCode(exception.getCode());
        String message = safeMessage(exception.getMessage(), "Unexpected error");

        return build(
                exception.getCode(),
                status,
                message,
                exception,
                request
        );
    }

    /**
     * Handles {@link ResponseStatusException}.
     *
     * @param exception thrown response status exception
     * @param request current HTTP request
     * @return standardized error response
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(
            ResponseStatusException exception,
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
        String message = safeMessage(exception.getReason(), exception.getMessage());

        return build(
                resolveCode(status),
                status,
                message,
                exception,
                request
        );
    }

    /**
     * Handles request body validation errors.
     *
     * @param exception thrown validation exception
     * @param request current HTTP request
     * @return standardized validation error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        String message = buildValidationMessage(exception);

        return build(
                ErrorCodes.VALIDATION_ERROR,
                HttpStatus.UNPROCESSABLE_ENTITY,
                message,
                exception,
                request
        );
    }

    /**
     * Handles validation errors raised for request parameters and path variables.
     *
     * @param exception thrown constraint violation exception
     * @param request current HTTP request
     * @return standardized validation error response
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request
    ) {
        String message = buildConstraintViolationMessage(exception);

        return build(
                ErrorCodes.VALIDATION_ERROR,
                HttpStatus.UNPROCESSABLE_ENTITY,
                message,
                exception,
                request
        );
    }

    /**
     * Handles all unexpected exceptions.
     *
     * @param exception thrown exception
     * @param request current HTTP request
     * @return standardized internal server error response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception exception,
            HttpServletRequest request
    ) {
        log.error("Unhandled exception at {} {}", request.getMethod(), request.getRequestURI(), exception);

        return build(
                ErrorCodes.INTERNAL_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected error",
                exception,
                request
        );
    }

    /**
     * Builds a standardized {@link ErrorResponse}.
     *
     * @param code stable application error code
     * @param status HTTP status
     * @param message response message
     * @param exception original exception
     * @param request current HTTP request
     * @return response entity with standardized error body
     */
    private ResponseEntity<ErrorResponse> build(
            String code,
            HttpStatus status,
            String message,
            Exception exception,
            HttpServletRequest request
    ) {
        ErrorResponse response = ErrorResponse.builder()
                .code(code)
                .timestamp(Instant.now())
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .path(request.getRequestURI())
                .exception(exception.getClass().getSimpleName())
                .build();

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Resolves a stable application error code from an HTTP status.
     *
     * @param status HTTP status
     * @return stable application error code
     */
    private String resolveCode(HttpStatus status) {
        if (status == null) {
            return ErrorCodes.REQUEST_ERROR;
        }

        return switch (status) {
            case NOT_FOUND -> ErrorCodes.NOT_FOUND;
            case BAD_REQUEST -> ErrorCodes.BAD_REQUEST;
            case UNPROCESSABLE_ENTITY -> ErrorCodes.VALIDATION_ERROR;
            case UNAUTHORIZED -> ErrorCodes.REQUEST_ERROR;
            case FORBIDDEN -> ErrorCodes.REQUEST_ERROR;
            case CONFLICT -> ErrorCodes.REQUEST_ERROR;
            default -> status.is4xxClientError()
                    ? ErrorCodes.REQUEST_ERROR
                    : ErrorCodes.INTERNAL_ERROR;
        };
    }

    /**
     * Resolves the HTTP status from the provided application error code.
     *
     * @param errorCode application error code
     * @return resolved HTTP status
     */
    private HttpStatus resolveHttpStatusFromErrorCode(String errorCode) {
        if (errorCode == null || errorCode.isBlank()) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return switch (errorCode) {
            case ErrorCodes.NOT_FOUND -> HttpStatus.NOT_FOUND;
            case ErrorCodes.BAD_REQUEST -> HttpStatus.BAD_REQUEST;
            case ErrorCodes.VALIDATION_ERROR -> HttpStatus.UNPROCESSABLE_ENTITY;
            case ErrorCodes.REQUEST_ERROR -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }

    /**
     * Builds a readable validation message from field errors.
     *
     * @param exception method argument validation exception
     * @return resolved validation message
     */
    private String buildValidationMessage(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        if (fieldErrors.isEmpty()) {
            return "Validation failed";
        }

        FieldError firstFieldError = fieldErrors.stream()
                .filter(Objects::nonNull)
                .min(Comparator.comparing(FieldError::getField))
                .orElse(fieldErrors.getFirst());

        String detailedMessage = fieldErrors.stream()
                .filter(Objects::nonNull)
                .map(fieldError -> fieldError.getField() + ": "
                        + safeMessage(fieldError.getDefaultMessage(), "invalid"))
                .distinct()
                .collect(Collectors.joining(", "));

        String primaryMessage = firstFieldError.getField() + ": "
                + safeMessage(firstFieldError.getDefaultMessage(), "invalid");

        return safeMessage(primaryMessage, detailedMessage);
    }

    /**
     * Builds a readable validation message from constraint violations.
     *
     * @param exception constraint violation exception
     * @return resolved validation message
     */
    private String buildConstraintViolationMessage(ConstraintViolationException exception) {
        if (exception.getConstraintViolations() == null || exception.getConstraintViolations().isEmpty()) {
            return "Validation failed";
        }

        return exception.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": "
                        + safeMessage(violation.getMessage(), "invalid"))
                .distinct()
                .collect(Collectors.joining(", "));
    }

    /**
     * Returns the primary message when it is not blank; otherwise returns the fallback message.
     *
     * @param primary preferred message
     * @param fallback fallback message
     * @return safe non-blank message
     */
    private String safeMessage(String primary, String fallback) {
        String trimmedPrimary = primary == null ? "" : primary.trim();
        if (!trimmedPrimary.isEmpty()) {
            return trimmedPrimary;
        }

        String trimmedFallback = fallback == null ? "" : fallback.trim();
        return trimmedFallback.isEmpty() ? "Error" : trimmedFallback;
    }
}
