package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.service.StatusHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Status History resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Status History", description = "Status History API")
@RequestMapping("/api/status-history")
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    /**
     * Retrieves all status histories.
     * @return list of StatusHistoryDto
     */
    @Operation(summary = "Get all status histories")
    @GetMapping
    public ResponseEntity<List<StatusHistoryDto>> getAll() {
        return ResponseEntity.ok(statusHistoryService.getAllStatusHistories());
    }

    /**
     * Retrieves the status history record by id.
     * @param id status history identifier
     * @return StatusHistoryDto
     */
    @Operation(summary = "Get Status History by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StatusHistoryDto> getById(
            @Parameter(description = "status history identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(statusHistoryService.getStatusHistoryById(id));
    }

    /**
     * Creates a new status history record.
     * @param dto status history payload
     * @return created StatusHistoryDto
     */
    @Operation(summary = "Create Status History")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<StatusHistoryDto> create(
            @Valid @RequestBody StatusHistoryDto dto) {
        StatusHistoryDto created = statusHistoryService.createStatusHistory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing status history record.
     * Only fields that are not null in the request are updated.
     * @param id status history identifier
     * @param dto partial status history payload
     * @return updated StatusHistoryDto
     */
    @Operation(summary = "Patch Status History")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<StatusHistoryDto> patch(
            @Parameter(description = "status history identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody StatusHistoryDto dto) {
        return ResponseEntity.ok(statusHistoryService.updateStatusHistory(id, dto));
    }

    /**
     * Delete an status history record by id.
     * @param id status history identifier
     * @return no content
     */
    @Operation(summary = "Delete Status History")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "status history identifier", required = true)
            @PathVariable UUID id) {
        statusHistoryService.deleteStatusHistory(id);
        return ResponseEntity.noContent().build();
    }

}
