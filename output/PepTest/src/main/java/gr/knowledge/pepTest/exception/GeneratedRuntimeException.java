package gr.knowledge.pepTest.exception;

import lombok.Builder;
import lombok.Getter;

/**
 * Generic runtime exception used across generated services.
 * Carries structured error information for consistent API responses.
 */
@Getter
public class GeneratedRuntimeException extends RuntimeException {

    private final String code;
    private final String entity;

    /**
     * Constructs a new exception instance.
     *
     * @param code application error code
     * @param entity entity name
     * @param message error message
     */
    @Builder
    public GeneratedRuntimeException(String code, String entity, String message) {
        super(message);
        this.code = code;
        this.entity = entity;
    }
}
