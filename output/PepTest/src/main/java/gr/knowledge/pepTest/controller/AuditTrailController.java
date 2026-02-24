package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.service.AuditTrailService;

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
 * REST controller for managing AuditTrail resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "AuditTrail", description = "AuditTrail API")
@RequestMapping("/api/audit-trails")
public class AuditTrailController {

    private final AuditTrailService auditTrailService;

    /**
     * Retrieves all records.
     *
     * @return list of AuditTrailDto
     */
    @Operation(summary = "Get all AuditTrail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<AuditTrailDto>> getAll() {
        log.info("Fetching all audittrail records.");
        return ResponseEntity.ok(auditTrailService.getAllAuditTrail());
    }

    /**
     * Retrieves a record by id.
     *
     * @return AuditTrailDto
     */
    @Operation(summary = "Get AuditTrail by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuditTrailDto> getById(
            @Parameter(description = "AuditTrail id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching audittrail with id: {}", id);
        return ResponseEntity.ok(auditTrailService.getAuditTrailById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created AuditTrailDto
     */
    @Operation(summary = "Create AuditTrail")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<AuditTrailDto> create(@RequestBody AuditTrailDto dto) {
        log.info("Creating audittrail.");
        AuditTrailDto created = auditTrailService.createAuditTrail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated AuditTrailDto
     */
    @Operation(summary = "Update AuditTrail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuditTrailDto> update(
            @Parameter(description = "AuditTrail id", required = true)
            @PathVariable UUID id,
            @RequestBody AuditTrailDto dto) {
        log.info("Updating audittrail with id: {}", id);
        return ResponseEntity.ok(auditTrailService.updateAuditTrail(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete AuditTrail")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "AuditTrail id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting audittrail with id: {}", id);
        auditTrailService.deleteAuditTrail(id);
        return ResponseEntity.noContent().build();
    }
}
