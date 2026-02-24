package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.service.Companyi18nService;
import gr.knowledge.pepTest.entity.Companyi18nPK;

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
 * REST controller for managing Companyi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Companyi18n", description = "Companyi18n API")
@RequestMapping("/api/companyi18ns")
public class Companyi18nController {

    private final Companyi18nService companyi18nService;

    /**
     * Retrieves all records.
     *
     * @return list of Companyi18nDto
     */
    @Operation(summary = "Get all Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<Companyi18nDto>> getAll() {
        log.info("Fetching all companyi18n records.");
        return ResponseEntity.ok(companyi18nService.getAllCompanyi18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return Companyi18nDto
     */
    @Operation(summary = "Get Companyi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<Companyi18nDto> getById(
            @Parameter(description = "company_id", required = true)
            @RequestParam(name = "company_id") UUID companyId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId) {
        Companyi18nPK id = buildCompanyi18nId(companyId, languageId, chamberI18nId);
        log.info("Fetching companyi18n with composite id: {}", id);
        return ResponseEntity.ok(companyi18nService.getCompanyi18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created Companyi18nDto
     */
    @Operation(summary = "Create Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Companyi18nDto> create(@RequestBody Companyi18nDto dto) {
        log.info("Creating companyi18n.");
        Companyi18nDto created = companyi18nService.createCompanyi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated Companyi18nDto
     */
    @Operation(summary = "Update Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<Companyi18nDto> update(
            @Parameter(description = "company_id", required = true)
            @RequestParam(name = "company_id") UUID companyId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId,
            @RequestBody Companyi18nDto dto) {
        Companyi18nPK id = buildCompanyi18nId(companyId, languageId, chamberI18nId);
        log.info("Updating companyi18n with composite id: {}", id);
        return ResponseEntity.ok(companyi18nService.updateCompanyi18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_id", required = true)
            @RequestParam(name = "company_id") UUID companyId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId) {
        Companyi18nPK id = buildCompanyi18nId(companyId, languageId, chamberI18nId);
        log.info("Deleting companyi18n with composite id: {}", id);
        companyi18nService.deleteCompanyi18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for Companyi18n.
     */
    private Companyi18nPK buildCompanyi18nId(UUID companyId, UUID languageId, Integer chamberI18nId) {
        Companyi18nPK id = new Companyi18nPK();
        id.setCompanyId(companyId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);
        return id;
    }
}
