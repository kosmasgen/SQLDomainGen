package com.sqldomaingen.generator;

import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;
import com.sqldomaingen.util.GeneratorSupport;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Generates exception handling layer under:
 * src/main/java/{basePackagePath}/exception
 * Files:
 * - ErrorResponse.java
 * - GlobalExceptionHandler.java
 */
@Log4j2
public class ExceptionGenerator {

    /**
     * Generates exception package and core exception handling classes.
     *
     * @param outputDir   project root output directory
     * @param basePackage base package (e.g., gr.knowledge.schoolmanagement)
     * @param overwrite   whether to overwrite existing files
     */
    public void generateExceptionHandling(String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String out = outputDir.trim();
        String pkg = basePackage.trim();

        if (out.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
        if (pkg.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }

        Path exceptionDir = GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(out, pkg, "exception")
        );
        String exceptionPackage = PackageResolver.resolvePackageName(pkg, "exception");

        writeErrorResponse(exceptionDir, exceptionPackage, overwrite);
        writeGlobalExceptionHandler(exceptionDir, exceptionPackage, overwrite);

        log.info("✅ Exception handling generated under: {}", exceptionDir.toAbsolutePath());
    }

    private void writeErrorResponse(Path exceptionDir, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDir.resolve("ErrorResponse.java");

        String content = """
                package %s;

                import io.swagger.v3.oas.annotations.media.Schema;
                import lombok.AllArgsConstructor;
                import lombok.Builder;
                import lombok.Data;
                import lombok.NoArgsConstructor;

                import java.time.Instant;

                /**
                 * Standard API error response payload.
                 */
                @Schema(description = "Standard API error response payload")
                @Data
                @Builder
                @NoArgsConstructor
                @AllArgsConstructor
                public class ErrorResponse {

                    @Schema(description = "Error timestamp (UTC)", example = "2026-02-18T10:15:30Z")
                    private Instant timestamp;

                    @Schema(description = "HTTP status code", example = "404")
                    private int status;

                    @Schema(description = "HTTP status reason phrase", example = "Not Found")
                    private String error;

                    @Schema(description = "Human-readable error message", example = "Resource not found with id: 10")
                    private String message;

                    @Schema(description = "Request path", example = "/api/absences/10")
                    private String path;

                    @Schema(description = "Exception type", example = "ResponseStatusException")
                    private String exception;
                }
                """.formatted(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.info("✅ ErrorResponse generated: {}", file.toAbsolutePath());
    }

    private void writeGlobalExceptionHandler(Path exceptionDir, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDir.resolve("GlobalExceptionHandler.java");

        String content = """
            package %s;

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
                 * Handles {@link ResponseStatusException}.
                 *
                 * @param ex the thrown response status exception
                 * @param request the current HTTP request
                 * @return a response entity containing the standardized error body
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
                 * @param ex the thrown validation exception
                 * @param request the current HTTP request
                 * @return a response entity containing the standardized validation error body
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
                 * Handles validation errors raised for request parameters and path variables.
                 *
                 * @param ex the thrown constraint violation exception
                 * @param request the current HTTP request
                 * @return a response entity containing the standardized validation error body
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
                 * Handles unexpected exceptions that are not matched by more specific handlers.
                 *
                 * @param ex the thrown exception
                 * @param request the current HTTP request
                 * @return a response entity containing a generic internal server error body
                 */
                @ExceptionHandler(Exception.class)
                public ResponseEntity<ErrorResponse> handleGeneric(
                        Exception ex,
                        HttpServletRequest request
                ) {
                    log.error("Unhandled exception at {} {}", request.getMethod(), request.getRequestURI(), ex);
                    return build(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex, request);
                }

                /**
                 * Builds a standardized {@link ErrorResponse} and wraps it in a {@link ResponseEntity}.
                 *
                 * @param status the HTTP status to return
                 * @param message the error message to expose in the response
                 * @param ex the original exception
                 * @param request the current HTTP request
                 * @return a response entity containing the standardized error body
                 */
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

                /**
                 * Builds a readable validation message from field-level binding errors.
                 *
                 * @param ex the method argument validation exception
                 * @return the resolved validation message
                 */
                private String buildValidationMessage(MethodArgumentNotValidException ex) {
                    List<FieldError> errors = ex.getBindingResult().getFieldErrors();
                    if (errors.isEmpty()) {
                        return "Validation failed";
                    }

                    FieldError first = errors.stream()
                            .filter(Objects::nonNull)
                            .min(Comparator.comparing(FieldError::getField))
                            .orElse(errors.getFirst());

                    String details = errors.stream()
                            .filter(Objects::nonNull)
                            .map(error -> error.getField() + ": " + safeMessage(error.getDefaultMessage(), "invalid"))
                            .distinct()
                            .collect(Collectors.joining(", "));

                    return safeMessage(first.getField() + ": " + safeMessage(first.getDefaultMessage(), "invalid"), details);
                }

                /**
                 * Builds a readable validation message from constraint violations.
                 *
                 * @param ex the constraint violation exception
                 * @return the resolved validation message
                 */
                private String buildConstraintViolationMessage(ConstraintViolationException ex) {
                    if (ex.getConstraintViolations() == null || ex.getConstraintViolations().isEmpty()) {
                        return "Validation failed";
                    }

                    return ex.getConstraintViolations().stream()
                            .map(violation -> violation.getPropertyPath() + ": " + safeMessage(violation.getMessage(), "invalid"))
                            .distinct()
                            .collect(Collectors.joining(", "));
                }

                /**
                 * Returns the primary message when present; otherwise falls back to the secondary one.
                 *
                 * @param primary the preferred message
                 * @param fallback the fallback message
                 * @return a safe non-blank message
                 */
                private String safeMessage(String primary, String fallback) {
                    String p = primary == null ? "" : primary.trim();
                    if (!p.isEmpty()) {
                        return p;
                    }

                    String f = fallback == null ? "" : fallback.trim();
                    return f.isEmpty() ? "Error" : f;
                }
            }
            """.formatted(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.info("✅ GlobalExceptionHandler generated: {}", file.toAbsolutePath());
    }
}
