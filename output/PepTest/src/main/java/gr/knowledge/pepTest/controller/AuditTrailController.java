package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.service.AuditTrailService;
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
 * REST controller for managing Audit Trail resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Audit Trail", description = "Audit Trail API")
@RequestMapping("/api/audit-trail")
public class AuditTrailController {

    private final AuditTrailService auditTrailService;

    /**
     * Retrieves all audit trails.
     * @return list of AuditTrailDto
     */
    @Operation(summary = "Get all audit trails")
    @GetMapping
    public ResponseEntity<List<AuditTrailDto>> getAll() {
        return ResponseEntity.ok(auditTrailService.getAllAuditTrails());
    }

    /**
     * Retrieves the audit trail record by id.
     * @param id audit trail identifier
     * @return AuditTrailDto
     */
    @Operation(summary = "Get Audit Trail by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuditTrailDto> getById(
            @Parameter(description = "audit trail identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(auditTrailService.getAuditTrailById(id));
    }

    /**
     * Creates a new audit trail record.
     * @param dto audit trail payload
     * @return created AuditTrailDto
     */
    @Operation(summary = "Create Audit Trail")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<AuditTrailDto> create(
            @Valid @RequestBody AuditTrailDto dto) {
        AuditTrailDto created = auditTrailService.createAuditTrail(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing audit trail record.
     * Only fields that are not null in the request are updated.
     * @param id audit trail identifier
     * @param dto partial audit trail payload
     * @return updated AuditTrailDto
     */
    @Operation(summary = "Patch Audit Trail")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<AuditTrailDto> patch(
            @Parameter(description = "audit trail identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody AuditTrailDto dto) {
        return ResponseEntity.ok(auditTrailService.updateAuditTrail(id, dto));
    }

    /**
     * Delete an audit trail record by id.
     * @param id audit trail identifier
     * @return no content
     */
    @Operation(summary = "Delete Audit Trail")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "audit trail identifier", required = true)
            @PathVariable UUID id) {
        auditTrailService.deleteAuditTrail(id);
        return ResponseEntity.noContent().build();
    }

}
