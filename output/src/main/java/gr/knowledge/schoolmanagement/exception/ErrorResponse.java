package gr.knowledge.schoolmanagement.exception;

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
