package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.service.SyncWatermarksService;

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

import java.util.List;

/**
 * REST controller for managing SyncWatermarks resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "SyncWatermarks", description = "SyncWatermarks API")
@RequestMapping("/api/sync-watermarkss")
public class SyncWatermarksController {

    private final SyncWatermarksService syncWatermarksService;

    /**
     * Retrieves all records.
     *
     * @return list of SyncWatermarksDto
     */
    @Operation(summary = "Get all SyncWatermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<SyncWatermarksDto>> getAll() {
        log.info("Fetching all syncwatermarks records.");
        return ResponseEntity.ok(syncWatermarksService.getAllSyncWatermarks());
    }

    /**
     * Retrieves a record by id.
     *
     * @return SyncWatermarksDto
     */
    @Operation(summary = "Get SyncWatermarks by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SyncWatermarksDto> getById(
            @Parameter(description = "SyncWatermarks id", required = true)
            @PathVariable Long id) {
        log.info("Fetching syncwatermarks with id: {}", id);
        return ResponseEntity.ok(syncWatermarksService.getSyncWatermarksById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created SyncWatermarksDto
     */
    @Operation(summary = "Create SyncWatermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SyncWatermarksDto> create(@RequestBody SyncWatermarksDto dto) {
        log.info("Creating syncwatermarks.");
        SyncWatermarksDto created = syncWatermarksService.createSyncWatermarks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated SyncWatermarksDto
     */
    @Operation(summary = "Update SyncWatermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SyncWatermarksDto> update(
            @Parameter(description = "SyncWatermarks id", required = true)
            @PathVariable Long id,
            @RequestBody SyncWatermarksDto dto) {
        log.info("Updating syncwatermarks with id: {}", id);
        return ResponseEntity.ok(syncWatermarksService.updateSyncWatermarks(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete SyncWatermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "SyncWatermarks id", required = true)
            @PathVariable Long id) {
        log.info("Deleting syncwatermarks with id: {}", id);
        syncWatermarksService.deleteSyncWatermarks(id);
        return ResponseEntity.noContent().build();
    }
}
