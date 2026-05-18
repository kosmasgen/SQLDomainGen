package com.sqldomaingen.generator;

import com.sqldomaingen.util.GeneratorSupport;
import com.sqldomaingen.util.PackageResolver;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Generates the exception handling layer under the exception package.
 * <p>
 * Generated files:
 * <ul>
 *     <li>{@code ErrorResponse.java}</li>
 *     <li>{@code ErrorCodes.java}</li>
 *     <li>{@code GeneratedRuntimeException.java}</li>
 *     <li>{@code GlobalExceptionHandler.java}</li>
 * </ul>
 */
@Log4j2
public class ExceptionGenerator {

    /**
     * Generates the complete exception handling layer under the exception package.
     *
     * @param outputDir project root output directory
     * @param basePackage base package name
     * @param overwrite whether existing files should be overwritten
     */
    public void generateExceptionHandling(String outputDir, String basePackage, boolean overwrite) {
        Objects.requireNonNull(outputDir, "outputDir must not be null");
        Objects.requireNonNull(basePackage, "basePackage must not be null");

        String trimmedOutputDir = outputDir.trim();
        String trimmedBasePackage = basePackage.trim();

        validateOutputDirectory(trimmedOutputDir);
        validateBasePackage(trimmedBasePackage);

        Path exceptionDirectory = resolveExceptionDirectory(trimmedOutputDir, trimmedBasePackage);
        String exceptionPackage = resolveExceptionPackage(trimmedBasePackage);

        writeErrorResponse(exceptionDirectory, exceptionPackage, overwrite);
        writeErrorCodes(exceptionDirectory, exceptionPackage, overwrite);
        writeErrorMessages(exceptionDirectory, exceptionPackage, overwrite);
        writeGeneratedRuntimeException(exceptionDirectory, exceptionPackage, overwrite);
        writeGlobalExceptionHandler(exceptionDirectory, exceptionPackage, overwrite);

        log.debug(" Exception handling generated under: {}", exceptionDirectory.toAbsolutePath());
    }

    /**
     * Validates the output directory argument.
     *
     * @param outputDir trimmed output directory value
     */
    private void validateOutputDirectory(String outputDir) {
        if (outputDir.isEmpty()) {
            throw new IllegalArgumentException("outputDir must not be blank");
        }
    }

    /**
     * Validates the base package argument.
     *
     * @param basePackage trimmed base package value
     */
    private void validateBasePackage(String basePackage) {
        if (basePackage.isEmpty()) {
            throw new IllegalArgumentException("basePackage must not be blank");
        }
    }

    /**
     * Resolves and creates the exception package directory if needed.
     *
     * @param outputDir trimmed output directory
     * @param basePackage trimmed base package
     * @return resolved exception directory path
     */
    private Path resolveExceptionDirectory(String outputDir, String basePackage) {
        return GeneratorSupport.ensureDirectory(
                PackageResolver.resolvePath(outputDir, basePackage, "exception")
        );
    }

    /**
     * Resolves the exception package name.
     *
     * @param basePackage trimmed base package
     * @return fully qualified exception package name
     */
    private String resolveExceptionPackage(String basePackage) {
        return PackageResolver.resolvePackageName(basePackage, "exception");
    }

    /**
     * Generates the {@code ErrorResponse} class.
     *
     * @param exceptionDirectory target exception directory
     * @param exceptionPackage target package name
     * @param overwrite whether existing files should be overwritten
     */
    private void writeErrorResponse(Path exceptionDirectory, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDirectory.resolve("ErrorResponse.java");
        String content = buildErrorResponseContent(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.info("ErrorResponse generated: {}", file.toAbsolutePath());
    }

    /**
     * Builds the {@code GeneratedRuntimeException} handler method.
     *
     * @return generated method source content
     */
    private String buildGeneratedRuntimeExceptionHandlerMethod() {
        return """
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
                String code = exception.getCode();
                HttpStatus status = resolveStatus(code);
                String message = safeMessage(
                        exception.getMessage(),
                        messageResolver.resolve(ErrorMessages.ERROR_UNEXPECTED)
                );

                return build(code, status, message, exception, request);
            }

            """;
    }

    /**
     * Builds the source code of the {@code ErrorResponse} class.
     *
     * @param exceptionPackage target package name
     * @return generated Java source content
     */
    private String buildErrorResponseContent(String exceptionPackage) {
        return """
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

                    @Schema(description = "Stable application error code", example = "VALIDATION_ERROR")
                    private String code;

                    @Schema(description = "Error timestamp (UTC)", example = "2026-02-18T10:15:30Z")
                    private Instant timestamp;

                    @Schema(description = "HTTP status code", example = "404")
                    private int status;

                    @Schema(description = "HTTP status reason phrase", example = "Not Found")
                    private String error;

                    @Schema(description = "Error message", example = "Resource not found with id: 10")
                    private String message;

                    @Schema(description = "Request path", example = "/api/absences/10")
                    private String path;

                    @Schema(description = "Exception type", example = "ResponseStatusException")
                    private String exception;
                }
                """.formatted(exceptionPackage);
    }

    /**
     * Generates the {@code ErrorCodes} class.
     *
     * @param exceptionDirectory target exception directory
     * @param exceptionPackage target package name
     * @param overwrite whether existing files should be overwritten
     */
    private void writeErrorCodes(Path exceptionDirectory, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDirectory.resolve("ErrorCodes.java");
        String content = buildErrorCodesContent(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.debug(" ErrorCodes generated: {}", file.toAbsolutePath());
    }

    /**
     * Builds the source code of the {@code ErrorCodes} class.
     *
     * @param exceptionPackage target package name
     * @return generated Java source content
     */
    private String buildErrorCodesContent(String exceptionPackage) {
        return """
                package %s;

                /**
                 * Centralized application error codes.
                 */
                public final class ErrorCodes {

                    public static final String NOT_FOUND = "NOT_FOUND";
                    public static final String BAD_REQUEST = "BAD_REQUEST";
                    public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
                    public static final String REQUEST_ERROR = "REQUEST_ERROR";
                    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

                    /**
                     * Prevents instantiation.
                     */
                    private ErrorCodes() {
                    }
                }
                """.formatted(exceptionPackage);
    }


    /**
     * Generates the {@code ErrorMessages} class.
     *
     * @param exceptionDirectory target exception directory
     * @param exceptionPackage target package name
     * @param overwrite whether existing files should be overwritten
     */
    private void writeErrorMessages(Path exceptionDirectory, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDirectory.resolve("ErrorMessages.java");
        String content = buildErrorMessagesContent(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.debug(" ErrorMessages generated: {}", file.toAbsolutePath());
    }

    /**
     * Builds the source code of the {@code ErrorMessages} class.
     *
     * @param exceptionPackage target package name
     * @return generated Java source content
     */
    private String buildErrorMessagesContent(String exceptionPackage) {
        return """
            package %s;

            /**
             * Centralized message keys for exception handling.
             */
            public final class ErrorMessages {

                public static final String ERROR_UNEXPECTED = "error.unexpected";
                public static final String ERROR_ENDPOINT_NOT_FOUND = "error.endpointNotFound";
                public static final String ERROR_INVALID_REQUEST_BODY = "error.invalidRequestBody";
                public static final String ERROR_VALIDATION_FAILED = "error.validationFailed";
                public static final String ERROR_INVALID = "error.invalid";

                /**
                 * Prevents instantiation.
                 */
                private ErrorMessages() {
                }
            }
            """.formatted(exceptionPackage);
    }

    /**
     * Generates the {@code GeneratedRuntimeException} class.
     *
     * @param exceptionDirectory target exception directory
     * @param exceptionPackage target package name
     * @param overwrite whether existing files should be overwritten
     */
    private void writeGeneratedRuntimeException(Path exceptionDirectory, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDirectory.resolve("GeneratedRuntimeException.java");
        String content = buildGeneratedRuntimeExceptionContent(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.debug(" GeneratedRuntimeException generated: {}", file.toAbsolutePath());
    }

    /**
     * Builds the source code of the {@code GeneratedRuntimeException} class.
     *
     * @param exceptionPackage target package name
     * @return generated Java source content
     */
    private String buildGeneratedRuntimeExceptionContent(String exceptionPackage) {
        return """
            package %s;

            import lombok.Builder;
            import lombok.Getter;

            /**
             * Generic runtime exception used across generated services.
             * Carries structured error information for consistent API responses.
             */
            @Getter
            public class GeneratedRuntimeException extends RuntimeException {

                private final String code;

                /**
                 * Constructs a new exception instance.
                 *
                 * @param code application error code
                 * @param message error message
                 */
                @Builder
                public GeneratedRuntimeException(String code, String message) {
                    super(message);
                    this.code = code;
                }
            }
            """.formatted(exceptionPackage);
    }

    /**
     * Generates the {@code GlobalExceptionHandler} class.
     *
     * @param exceptionDirectory target exception directory
     * @param exceptionPackage target package name
     * @param overwrite whether existing files should be overwritten
     */
    private void writeGlobalExceptionHandler(Path exceptionDirectory, String exceptionPackage, boolean overwrite) {
        Path file = exceptionDirectory.resolve("GlobalExceptionHandler.java");
        String content = buildGlobalExceptionHandlerContent(exceptionPackage);

        GeneratorSupport.writeFile(file, content, overwrite);
        log.info(" GlobalExceptionHandler generated: {}", file.toAbsolutePath());
    }

    private String buildGlobalExceptionHandlerContent(String exceptionPackage) {
        String basePackage = exceptionPackage.substring(0, exceptionPackage.lastIndexOf(".exception"));

        return """
            package %s;

            %s
            %s
            @Log4j2
            @RestControllerAdvice
            @RequiredArgsConstructor
            @SuppressWarnings("unused")
            public class GlobalExceptionHandler {

                private final MessageResolver messageResolver;

            %s
            }
            """.formatted(
                exceptionPackage,
                buildGlobalExceptionHandlerImports().formatted(basePackage),
                buildGlobalExceptionHandlerClassJavaDoc(),
                indent(buildGlobalExceptionHandlerBody())
        );
    }

    /**
     * Applies indentation padding to generated multiline content.
     *
     * @param content generated content
     * @return indented content
     */
    private String indent(String content) {
        String padding = " ".repeat(4);

        return content.lines()
                .map(line -> line.isBlank() ? line : padding + line)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Builds the import section of the generated {@code GlobalExceptionHandler}.
     *
     * @return generated import source content
     */
    private String buildGlobalExceptionHandlerImports() {
        return """
        import %s.util.MessageResolver;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.validation.ConstraintViolation;
        import jakarta.validation.ConstraintViolationException;
        import lombok.RequiredArgsConstructor;
        import lombok.extern.log4j.Log4j2;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.http.converter.HttpMessageNotReadableException;
        import org.springframework.validation.FieldError;
        import org.springframework.web.bind.MethodArgumentNotValidException;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.RestControllerAdvice;
        import org.springframework.web.server.ResponseStatusException;
        import org.springframework.web.servlet.NoHandlerFoundException;

        import java.time.Instant;
        import java.util.List;
        import java.util.Set;
        import java.util.stream.Collectors;

        import static java.time.temporal.ChronoUnit.MILLIS;
        """;
    }

    /**
     * Builds the JavaDoc section of the generated {@code GlobalExceptionHandler}.
     *
     * @return generated class Javadoc source content
     */
    private String buildGlobalExceptionHandlerClassJavaDoc() {
        return """
            /**
             * Centralized exception handling for REST APIs.
             */""";
    }

    /**
     * Builds the complete body of the generated {@code GlobalExceptionHandler}.
     *
     * @return generated class body source content
     */
    private String buildGlobalExceptionHandlerBody() {
        return buildGeneratedRuntimeExceptionHandlerMethod()
                + buildResponseStatusExceptionHandlerMethod()
                + buildMethodArgumentNotValidHandlerMethod()
                + buildConstraintViolationHandlerMethod()
                + buildNoHandlerFoundExceptionHandlerMethod()
                + buildHttpMessageNotReadableHandlerMethod()
                + buildGenericExceptionHandlerMethod()
                + buildErrorResponseBuilderMethod()
                + buildResolveCodeMethod()
                + buildResolveStatusMethod()
                + buildValidationMessageMethod()
                + buildFormatValidationMessageMethod()
                + buildViolationMessageMethod()
                + buildFormatViolationMethod()
                + buildBadRequestMethod()
                + buildSafeMessageMethod()
                + buildValidationErrorMethod();
    }
    /**
     * Builds the {@code HttpMessageNotReadableException} handler method.
     *
     * @return generated method source content
     */
    private String buildHttpMessageNotReadableHandlerMethod() {
        return """
            /**
             * Handles malformed or unreadable request bodies.
             *
             * @param exception thrown message parsing exception
             * @param request current HTTP request
             * @return standardized bad request error response
             */
            @ExceptionHandler(HttpMessageNotReadableException.class)
            public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
                    HttpMessageNotReadableException exception,
                    HttpServletRequest request
            ) {
                log.warn("Unreadable request body at {} {}", request.getMethod(), request.getRequestURI());

                return badRequest(messageResolver.resolve(ErrorMessages.ERROR_INVALID_REQUEST_BODY), exception, request);
            }

            """;
    }

    /**
     * Builds the safe message helper method.
     *
     * @return generated method source content
     */
    private String buildSafeMessageMethod() {
        return """
            /**
             * Returns the message when it is not blank; otherwise returns the fallback message.
             *
             * @param message preferred message
             * @param fallback fallback message
             * @return resolved message
             */
            private String safeMessage(String message, String fallback) {
                return (message == null || message.isBlank())
                        ? fallback
                        : message;
            }

            """;
    }

    /**
     * Builds the constraint violation message helper method.
     *
     * @return generated method source content
     */
    private String buildViolationMessageMethod() {
        return """
            /**
             * Builds a readable validation message from constraint violations.
             *
             * @param exception constraint violation exception
             * @return resolved validation message
             */
            private String buildViolationMessage(ConstraintViolationException exception) {
                Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();

                if (violations.isEmpty()) {
                    return messageResolver.resolve(ErrorMessages.ERROR_VALIDATION_FAILED);
                }

                return violations.stream()
                        .map(this::formatViolation)
                        .distinct()
                        .collect(Collectors.joining(", "));
            }

            """;
    }

    /**
     * Builds the validation error response helper method.
     *
     * @return generated method source content
     */
    private String buildValidationErrorMethod() {
        return """
            /**
             * Builds a standardized validation error response.
             *
             * @param message response message
             * @param exception original exception
             * @param request current HTTP request
             * @return standardized validation error response
             */
            private ResponseEntity<ErrorResponse> validationError(
                    String message,
                    Exception exception,
                    HttpServletRequest request
            ) {
                return build(ErrorCodes.VALIDATION_ERROR, HttpStatus.UNPROCESSABLE_ENTITY, message, exception, request);
            }

            """;
    }



    /**
     * Builds the constraint violation formatter helper method.
     *
     * @return generated method source content
     */
    private String buildFormatViolationMethod() {
        return """
            /**
             * Formats a constraint violation into a readable validation message.
             *
             * @param violation constraint violation
             * @return formatted validation message
             */
            private String formatViolation(ConstraintViolation<?> violation) {
                return formatValidationMessage(
                        violation.getPropertyPath().toString(),
                        safeMessage(
                                violation.getMessage(),
                                messageResolver.resolve(ErrorMessages.ERROR_INVALID)
                        )
                );
            }

            """;
    }

    /**
     * Builds the bad request response helper method.
     *
     * @return generated method source content
     */
    private String buildBadRequestMethod() {
        return """
            /**
             * Builds a standardized bad request error response.
             *
             * @param message response message
             * @param exception original exception
             * @param request current HTTP request
             * @return standardized bad request response
             */
            private ResponseEntity<ErrorResponse> badRequest(
                    String message,
                    Exception exception,
                    HttpServletRequest request
            ) {
                return build(ErrorCodes.BAD_REQUEST, HttpStatus.BAD_REQUEST, message, exception, request);
            }

            """;
    }

    /**
     * Builds the validation message helper method.
     *
     * @return generated method source content
     */
    private String buildValidationMessageMethod() {
        return """
            /**
             * Builds a readable validation message from field errors.
             *
             * @param exception method argument validation exception
             * @return resolved validation message
             */
            private String buildValidationMessage(MethodArgumentNotValidException exception) {
                List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

                if (fieldErrors.isEmpty()) {
                    return messageResolver.resolve(ErrorMessages.ERROR_VALIDATION_FAILED);
                }

                FieldError fieldError = fieldErrors.getFirst();

                return formatValidationMessage(
                        fieldError.getField(),
                        safeMessage(
                                fieldError.getDefaultMessage(),
                                messageResolver.resolve(ErrorMessages.ERROR_INVALID)
                        )
                );
            }

            """;
    }

    /**
     * Builds the validation message formatter helper method.
     *
     * @return generated method source content
     */
    private String buildFormatValidationMessageMethod() {
        return """
            /**
             * Formats a validation message using the field name and resolved message.
             *
             * @param field field name
             * @param message validation message
             * @return formatted validation message
             */
            private String formatValidationMessage(String field, String message) {
                return field + ": " + message;
            }

            """;
    }

    /**
     * Builds the method that resolves HTTP status from application error code.
     *
     * @return generated method source content
     */
    private String buildResolveStatusMethod() {
        return """
            /**
             * Resolves the HTTP status from a provided application error code.
             *
             * @param errorCode application error code
             * @return resolved HTTP status
             */
            private HttpStatus resolveStatus(String errorCode) {
                if (errorCode == null || errorCode.isBlank()) {
                    return HttpStatus.INTERNAL_SERVER_ERROR;
                }

                return switch (errorCode) {
                    case ErrorCodes.NOT_FOUND -> HttpStatus.NOT_FOUND;
                    case ErrorCodes.BAD_REQUEST, ErrorCodes.REQUEST_ERROR -> HttpStatus.BAD_REQUEST;
                    case ErrorCodes.VALIDATION_ERROR -> HttpStatus.UNPROCESSABLE_ENTITY;
                    default -> HttpStatus.INTERNAL_SERVER_ERROR;
                };
            }

            """;
    }

    /**
     * Builds the method that resolves application error codes from HTTP status.
     *
     * @return generated method source content
     */
    private String buildResolveCodeMethod() {
        return """
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
                    case UNAUTHORIZED, FORBIDDEN, CONFLICT -> ErrorCodes.REQUEST_ERROR;
                    default -> status.is4xxClientError()
                            ? ErrorCodes.REQUEST_ERROR
                            : ErrorCodes.INTERNAL_ERROR;
                };
            }

            """;
    }

    /**
     * Builds the {@code ErrorResponse} builder helper method.
     *
     * @return generated method source content
     */
    private String buildErrorResponseBuilderMethod() {
        return """
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
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .code(code)
                        .timestamp(Instant.now().truncatedTo(MILLIS))
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .message(message)
                        .path(request.getRequestURI())
                        .exception(exception.getClass().getSimpleName())
                        .build();

                return ResponseEntity.status(status).body(errorResponse);
            }

            """;
    }

    /**
     * Builds the generic {@code Exception} handler method.
     *
     * @return generated method source content
     */
    private String buildGenericExceptionHandlerMethod() {
        return """
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
                log.error("Unhandled exception at {} {}: {}", request.getMethod(), request.getRequestURI(), exception.getMessage());

                return build(
                        ErrorCodes.INTERNAL_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        messageResolver.resolve(ErrorMessages.ERROR_UNEXPECTED),
                        exception,
                        request
                );
            }

            """;
    }

    /**
     * Builds the {@code NoHandlerFoundException} handler method.
     *
     * @return generated method source content
     */
    private String buildNoHandlerFoundExceptionHandlerMethod() {
        return """
            /**
             * Handles requests that do not match any controller endpoint.
             *
             * @param exception thrown no-handler exception
             * @param request current HTTP request
             * @return standardized not found error response
             */
            @ExceptionHandler(NoHandlerFoundException.class)
            public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(
                    NoHandlerFoundException exception,
                    HttpServletRequest request
            ) {
                return build(
                        ErrorCodes.NOT_FOUND,
                        HttpStatus.NOT_FOUND,
                        messageResolver.resolve(ErrorMessages.ERROR_ENDPOINT_NOT_FOUND, request.getRequestURI()),
                        exception,
                        request
                );
            }

            """;
    }

    /**
     * Builds the {@code ConstraintViolationException} handler method.
     *
     * @return generated method source content
     */
    private String buildConstraintViolationHandlerMethod() {
        return """
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
                String message = buildViolationMessage(exception);

                return validationError(message, exception, request);
            }

            """;
    }

    /**
     * Builds the {@code MethodArgumentNotValidException} handler method.
     *
     * @return generated method source content
     */
    private String buildMethodArgumentNotValidHandlerMethod() {
        return """
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

                return validationError(message, exception, request);
            }

            """;
    }

    /**
     * Builds the {@code ResponseStatusException} handler method.
     *
     * @return generated method source content
     */
    private String buildResponseStatusExceptionHandlerMethod() {
        return """
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
                String message = safeMessage(
                        exception.getReason(),
                        exception.getMessage()
                );

                return build(resolveCode(status), status, message, exception, request);
            }

            """;
    }
}