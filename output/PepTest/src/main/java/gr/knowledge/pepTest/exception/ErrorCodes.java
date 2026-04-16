package gr.knowledge.pepTest.exception;

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
