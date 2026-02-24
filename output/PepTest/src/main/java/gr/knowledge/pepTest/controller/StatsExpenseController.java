package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.service.StatsExpenseService;

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
 * REST controller for managing StatsExpense resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "StatsExpense", description = "StatsExpense API")
@RequestMapping("/api/stats-expenses")
public class StatsExpenseController {

    private final StatsExpenseService statsExpenseService;

    /**
     * Retrieves all records.
     *
     * @return list of StatsExpenseDto
     */
    @Operation(summary = "Get all StatsExpense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<StatsExpenseDto>> getAll() {
        log.info("Fetching all statsexpense records.");
        return ResponseEntity.ok(statsExpenseService.getAllStatsExpense());
    }

    /**
     * Retrieves a record by id.
     *
     * @return StatsExpenseDto
     */
    @Operation(summary = "Get StatsExpense by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StatsExpenseDto> getById(
            @Parameter(description = "StatsExpense id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching statsexpense with id: {}", id);
        return ResponseEntity.ok(statsExpenseService.getStatsExpenseById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created StatsExpenseDto
     */
    @Operation(summary = "Create StatsExpense")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<StatsExpenseDto> create(@RequestBody StatsExpenseDto dto) {
        log.info("Creating statsexpense.");
        StatsExpenseDto created = statsExpenseService.createStatsExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated StatsExpenseDto
     */
    @Operation(summary = "Update StatsExpense")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StatsExpenseDto> update(
            @Parameter(description = "StatsExpense id", required = true)
            @PathVariable UUID id,
            @RequestBody StatsExpenseDto dto) {
        log.info("Updating statsexpense with id: {}", id);
        return ResponseEntity.ok(statsExpenseService.updateStatsExpense(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete StatsExpense")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "StatsExpense id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting statsexpense with id: {}", id);
        statsExpenseService.deleteStatsExpense(id);
        return ResponseEntity.noContent().build();
    }
}
