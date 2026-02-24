package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.service.CorporateStatusService;

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
 * REST controller for managing CorporateStatus resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CorporateStatus", description = "CorporateStatus API")
@RequestMapping("/api/corporate-statuss")
public class CorporateStatusController {

    private final CorporateStatusService corporateStatusService;

    /**
     * Retrieves all records.
     *
     * @return list of CorporateStatusDto
     */
    @Operation(summary = "Get all CorporateStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CorporateStatusDto>> getAll() {
        log.info("Fetching all corporatestatus records.");
        return ResponseEntity.ok(corporateStatusService.getAllCorporateStatus());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CorporateStatusDto
     */
    @Operation(summary = "Get CorporateStatus by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CorporateStatusDto> getById(
            @Parameter(description = "CorporateStatus id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching corporatestatus with id: {}", id);
        return ResponseEntity.ok(corporateStatusService.getCorporateStatusById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CorporateStatusDto
     */
    @Operation(summary = "Create CorporateStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusDto> create(@RequestBody CorporateStatusDto dto) {
        log.info("Creating corporatestatus.");
        CorporateStatusDto created = corporateStatusService.createCorporateStatus(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CorporateStatusDto
     */
    @Operation(summary = "Update CorporateStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CorporateStatusDto> update(
            @Parameter(description = "CorporateStatus id", required = true)
            @PathVariable UUID id,
            @RequestBody CorporateStatusDto dto) {
        log.info("Updating corporatestatus with id: {}", id);
        return ResponseEntity.ok(corporateStatusService.updateCorporateStatus(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CorporateStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CorporateStatus id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting corporatestatus with id: {}", id);
        corporateStatusService.deleteCorporateStatus(id);
        return ResponseEntity.noContent().build();
    }
}
