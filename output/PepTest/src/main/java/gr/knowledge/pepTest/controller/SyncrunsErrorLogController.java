package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncrunsErrorLogDto;
import gr.knowledge.pepTest.service.SyncrunsErrorLogService;

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
 * REST controller for managing SyncrunsErrorLog resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "SyncrunsErrorLog", description = "SyncrunsErrorLog API")
@RequestMapping("/api/syncruns-error-logs")
public class SyncrunsErrorLogController {

    private final SyncrunsErrorLogService syncrunsErrorLogService;

    /**
     * Retrieves all records.
     *
     * @return list of SyncrunsErrorLogDto
     */
    @Operation(summary = "Get all SyncrunsErrorLog")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<SyncrunsErrorLogDto>> getAll() {
        log.info("Fetching all syncrunserrorlog records.");
        return ResponseEntity.ok(syncrunsErrorLogService.getAllSyncrunsErrorLog());
    }

    /**
     * Retrieves a record by id.
     *
     * @return SyncrunsErrorLogDto
     */
    @Operation(summary = "Get SyncrunsErrorLog by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SyncrunsErrorLogDto> getById(
            @Parameter(description = "SyncrunsErrorLog id", required = true)
            @PathVariable String id) {
        log.info("Fetching syncrunserrorlog with id: {}", id);
        return ResponseEntity.ok(syncrunsErrorLogService.getSyncrunsErrorLogById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created SyncrunsErrorLogDto
     */
    @Operation(summary = "Create SyncrunsErrorLog")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SyncrunsErrorLogDto> create(@RequestBody SyncrunsErrorLogDto dto) {
        log.info("Creating syncrunserrorlog.");
        SyncrunsErrorLogDto created = syncrunsErrorLogService.createSyncrunsErrorLog(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated SyncrunsErrorLogDto
     */
    @Operation(summary = "Update SyncrunsErrorLog")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SyncrunsErrorLogDto> update(
            @Parameter(description = "SyncrunsErrorLog id", required = true)
            @PathVariable String id,
            @RequestBody SyncrunsErrorLogDto dto) {
        log.info("Updating syncrunserrorlog with id: {}", id);
        return ResponseEntity.ok(syncrunsErrorLogService.updateSyncrunsErrorLog(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete SyncrunsErrorLog")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "SyncrunsErrorLog id", required = true)
            @PathVariable String id) {
        log.info("Deleting syncrunserrorlog with id: {}", id);
        syncrunsErrorLogService.deleteSyncrunsErrorLog(id);
        return ResponseEntity.noContent().build();
    }
}
