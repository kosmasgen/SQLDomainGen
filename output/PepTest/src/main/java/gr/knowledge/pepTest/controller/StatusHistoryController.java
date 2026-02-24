package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.service.StatusHistoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

/**
 * REST controller for managing StatusHistory resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "StatusHistory", description = "StatusHistory API")
@RequestMapping("/api/status-historys")
public class StatusHistoryController {

    private final StatusHistoryService statusHistoryService;

    /**
     * Retrieves all records.
     *
     * @return list of StatusHistoryDto
     */
    @Operation(summary = "Get all StatusHistory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<StatusHistoryDto>> getAll() {
        log.info("Fetching all statushistory records.");
        return ResponseEntity.ok(statusHistoryService.getAllStatusHistory());
    }

    /**
     * Retrieves a record by id.
     *
     * @return StatusHistoryDto
     */
    @Operation(summary = "Get StatusHistory by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StatusHistoryDto> getById(
            @Parameter(description = "StatusHistory id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching statushistory with id: {}", id);
        return ResponseEntity.ok(statusHistoryService.getStatusHistoryById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created StatusHistoryDto
     */
    @Operation(summary = "Create StatusHistory")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<StatusHistoryDto> create(@RequestBody StatusHistoryDto dto) {
        log.info("Creating statushistory.");
        StatusHistoryDto created = statusHistoryService.createStatusHistory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated StatusHistoryDto
     */
    @Operation(summary = "Update StatusHistory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StatusHistoryDto> update(
            @Parameter(description = "StatusHistory id", required = true)
            @PathVariable UUID id,
            @RequestBody StatusHistoryDto dto) {
        log.info("Updating statushistory with id: {}", id);
        return ResponseEntity.ok(statusHistoryService.updateStatusHistory(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete StatusHistory")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "StatusHistory id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting statushistory with id: {}", id);
        statusHistoryService.deleteStatusHistory(id);
        return ResponseEntity.noContent().build();
    }
}
