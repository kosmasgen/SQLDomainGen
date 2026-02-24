package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.service.SyncrunsService;

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
 * REST controller for managing Syncruns resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Syncruns", description = "Syncruns API")
@RequestMapping("/api/syncrunss")
public class SyncrunsController {

    private final SyncrunsService syncrunsService;

    /**
     * Retrieves all records.
     *
     * @return list of SyncrunsDto
     */
    @Operation(summary = "Get all Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<SyncrunsDto>> getAll() {
        log.info("Fetching all syncruns records.");
        return ResponseEntity.ok(syncrunsService.getAllSyncruns());
    }

    /**
     * Retrieves a record by id.
     *
     * @return SyncrunsDto
     */
    @Operation(summary = "Get Syncruns by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SyncrunsDto> getById(
            @Parameter(description = "Syncruns id", required = true)
            @PathVariable Long id) {
        log.info("Fetching syncruns with id: {}", id);
        return ResponseEntity.ok(syncrunsService.getSyncrunsById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created SyncrunsDto
     */
    @Operation(summary = "Create Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SyncrunsDto> create(@RequestBody SyncrunsDto dto) {
        log.info("Creating syncruns.");
        SyncrunsDto created = syncrunsService.createSyncruns(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated SyncrunsDto
     */
    @Operation(summary = "Update Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SyncrunsDto> update(
            @Parameter(description = "Syncruns id", required = true)
            @PathVariable Long id,
            @RequestBody SyncrunsDto dto) {
        log.info("Updating syncruns with id: {}", id);
        return ResponseEntity.ok(syncrunsService.updateSyncruns(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Syncruns id", required = true)
            @PathVariable Long id) {
        log.info("Deleting syncruns with id: {}", id);
        syncrunsService.deleteSyncruns(id);
        return ResponseEntity.noContent().build();
    }
}
