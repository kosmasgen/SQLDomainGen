package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.service.CorporateStatusi18nService;
import gr.knowledge.pepTest.entity.CorporateStatusi18nPK;

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
 * REST controller for managing CorporateStatusi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CorporateStatusi18n", description = "CorporateStatusi18n API")
@RequestMapping("/api/corporate-statusi18ns")
public class CorporateStatusi18nController {

    private final CorporateStatusi18nService corporateStatusi18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CorporateStatusi18nDto
     */
    @Operation(summary = "Get all CorporateStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CorporateStatusi18nDto>> getAll() {
        log.info("Fetching all corporatestatusi18n records.");
        return ResponseEntity.ok(corporateStatusi18nService.getAllCorporateStatusi18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CorporateStatusi18nDto
     */
    @Operation(summary = "Get CorporateStatusi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CorporateStatusi18nDto> getById(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CorporateStatusi18nPK id = buildCorporateStatusi18nId(corporateStatusId, languageId);
        log.info("Fetching corporatestatusi18n with composite id: {}", id);
        return ResponseEntity.ok(corporateStatusi18nService.getCorporateStatusi18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CorporateStatusi18nDto
     */
    @Operation(summary = "Create CorporateStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusi18nDto> create(@RequestBody CorporateStatusi18nDto dto) {
        log.info("Creating corporatestatusi18n.");
        CorporateStatusi18nDto created = corporateStatusi18nService.createCorporateStatusi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CorporateStatusi18nDto
     */
    @Operation(summary = "Update CorporateStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CorporateStatusi18nDto> update(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody CorporateStatusi18nDto dto) {
        CorporateStatusi18nPK id = buildCorporateStatusi18nId(corporateStatusId, languageId);
        log.info("Updating corporatestatusi18n with composite id: {}", id);
        return ResponseEntity.ok(corporateStatusi18nService.updateCorporateStatusi18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CorporateStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CorporateStatusi18nPK id = buildCorporateStatusi18nId(corporateStatusId, languageId);
        log.info("Deleting corporatestatusi18n with composite id: {}", id);
        corporateStatusi18nService.deleteCorporateStatusi18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CorporateStatusi18n.
     */
    private CorporateStatusi18nPK buildCorporateStatusi18nId(UUID corporateStatusId, UUID languageId) {
        CorporateStatusi18nPK id = new CorporateStatusi18nPK();
        id.setCorporateStatusId(corporateStatusId);
        id.setLanguageId(languageId);
        return id;
    }
}
