package com.sqldomaingen.generator;

import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * Generates exception handling layer under:
 * src/main/java/{basePackagePath}/exception
 *
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

        Path exceptionDir = ensureDirectory(PackageResolver.resolvePath(out, pkg, "exception"));
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

        writeFile(file, content, overwrite);
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
                """.formatted(exceptionPackage);

        writeFile(file, content, overwrite);
        log.info("✅ GlobalExceptionHandler generated: {}", file.toAbsolutePath());
    }

    private static Path ensureDirectory(Path dir) {
        try {
            Files.createDirectories(dir);
            return dir;
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create directory: " + dir, e);
        }
    }

    private static void writeFile(Path filePath, String content, boolean overwrite) {
        Objects.requireNonNull(filePath, "filePath must not be null");
        Objects.requireNonNull(content, "content must not be null");

        try {
            if (Files.exists(filePath) && !overwrite) {
                log.info("ℹ️ File exists, skipping (overwrite=false): {}", filePath.toAbsolutePath());
                return;
            }
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(filePath, content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to write file: " + filePath.toAbsolutePath(), e);
        }
    }
}
