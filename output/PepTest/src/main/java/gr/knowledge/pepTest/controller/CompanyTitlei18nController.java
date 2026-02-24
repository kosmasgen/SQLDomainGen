package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.service.CompanyTitlei18nService;
import gr.knowledge.pepTest.entity.CompanyTitlei18nPK;

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
 * REST controller for managing CompanyTitlei18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyTitlei18n", description = "CompanyTitlei18n API")
@RequestMapping("/api/company-titlei18ns")
public class CompanyTitlei18nController {

    private final CompanyTitlei18nService companyTitlei18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyTitlei18nDto
     */
    @Operation(summary = "Get all CompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyTitlei18nDto>> getAll() {
        log.info("Fetching all companytitlei18n records.");
        return ResponseEntity.ok(companyTitlei18nService.getAllCompanyTitlei18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyTitlei18nDto
     */
    @Operation(summary = "Get CompanyTitlei18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CompanyTitlei18nDto> getById(
            @Parameter(description = "company_title_id", required = true)
            @RequestParam(name = "company_title_id") UUID companyTitleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId) {
        CompanyTitlei18nPK id = buildCompanyTitlei18nId(companyTitleId, languageId, chamberI18nId);
        log.info("Fetching companytitlei18n with composite id: {}", id);
        return ResponseEntity.ok(companyTitlei18nService.getCompanyTitlei18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyTitlei18nDto
     */
    @Operation(summary = "Create CompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyTitlei18nDto> create(@RequestBody CompanyTitlei18nDto dto) {
        log.info("Creating companytitlei18n.");
        CompanyTitlei18nDto created = companyTitlei18nService.createCompanyTitlei18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyTitlei18nDto
     */
    @Operation(summary = "Update CompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CompanyTitlei18nDto> update(
            @Parameter(description = "company_title_id", required = true)
            @RequestParam(name = "company_title_id") UUID companyTitleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId,
            @RequestBody CompanyTitlei18nDto dto) {
        CompanyTitlei18nPK id = buildCompanyTitlei18nId(companyTitleId, languageId, chamberI18nId);
        log.info("Updating companytitlei18n with composite id: {}", id);
        return ResponseEntity.ok(companyTitlei18nService.updateCompanyTitlei18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_title_id", required = true)
            @RequestParam(name = "company_title_id") UUID companyTitleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "chamber_i18n_id", required = true)
            @RequestParam(name = "chamber_i18n_id") Integer chamberI18nId) {
        CompanyTitlei18nPK id = buildCompanyTitlei18nId(companyTitleId, languageId, chamberI18nId);
        log.info("Deleting companytitlei18n with composite id: {}", id);
        companyTitlei18nService.deleteCompanyTitlei18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CompanyTitlei18n.
     */
    private CompanyTitlei18nPK buildCompanyTitlei18nId(UUID companyTitleId, UUID languageId, Integer chamberI18nId) {
        CompanyTitlei18nPK id = new CompanyTitlei18nPK();
        id.setCompanyTitleId(companyTitleId);
        id.setLanguageId(languageId);
        id.setChamberI18nId(chamberI18nId);
        return id;
    }
}
