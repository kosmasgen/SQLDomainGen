package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncWatermarksDto;
import gr.knowledge.pepTest.service.SyncWatermarksService;
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

/**
 * REST controller for managing Sync Watermarks resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Sync Watermarks", description = "Sync Watermarks API")
@RequestMapping("/api/sync-watermarks")
public class SyncWatermarksController {

    private final SyncWatermarksService syncWatermarksService;

    /**
     * Retrieves all sync watermarkses.
     * @return list of SyncWatermarksDto
     */
    @Operation(summary = "Get all sync watermarkses")
    @GetMapping
    public ResponseEntity<List<SyncWatermarksDto>> getAll() {
        return ResponseEntity.ok(syncWatermarksService.getAllSyncWatermarkses());
    }

    /**
     * Retrieves the sync watermarks record by id.
     * @param id sync watermarks identifier
     * @return SyncWatermarksDto
     */
    @Operation(summary = "Get Sync Watermarks by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SyncWatermarksDto> getById(
            @Parameter(description = "sync watermarks identifier", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(syncWatermarksService.getSyncWatermarksById(id));
    }

    /**
     * Creates a new sync watermarks record.
     * @param dto sync watermarks payload
     * @return created SyncWatermarksDto
     */
    @Operation(summary = "Create Sync Watermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SyncWatermarksDto> create(
            @Valid @RequestBody SyncWatermarksDto dto) {
        SyncWatermarksDto created = syncWatermarksService.createSyncWatermarks(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing sync watermarks record.
     * Only fields that are not null in the request are updated.
     * @param id sync watermarks identifier
     * @param dto partial sync watermarks payload
     * @return updated SyncWatermarksDto
     */
    @Operation(summary = "Patch Sync Watermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SyncWatermarksDto> patch(
            @Parameter(description = "sync watermarks identifier", required = true)
            @PathVariable Long id,
            @Valid @RequestBody SyncWatermarksDto dto) {
        return ResponseEntity.ok(syncWatermarksService.updateSyncWatermarks(id, dto));
    }

    /**
     * Delete an sync watermarks record by id.
     * @param id sync watermarks identifier
     * @return no content
     */
    @Operation(summary = "Delete Sync Watermarks")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "sync watermarks identifier", required = true)
            @PathVariable Long id) {
        syncWatermarksService.deleteSyncWatermarks(id);
        return ResponseEntity.noContent().build();
    }

}
