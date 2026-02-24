package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.service.CompanyStatusi18nService;
import gr.knowledge.pepTest.entity.CompanyStatusi18nPK;

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
 * REST controller for managing CompanyStatusi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyStatusi18n", description = "CompanyStatusi18n API")
@RequestMapping("/api/company-statusi18ns")
public class CompanyStatusi18nController {

    private final CompanyStatusi18nService companyStatusi18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyStatusi18nDto
     */
    @Operation(summary = "Get all CompanyStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyStatusi18nDto>> getAll() {
        log.info("Fetching all companystatusi18n records.");
        return ResponseEntity.ok(companyStatusi18nService.getAllCompanyStatusi18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyStatusi18nDto
     */
    @Operation(summary = "Get CompanyStatusi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CompanyStatusi18nDto> getById(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyStatusi18nPK id = buildCompanyStatusi18nId(companyStatusId, languageId);
        log.info("Fetching companystatusi18n with composite id: {}", id);
        return ResponseEntity.ok(companyStatusi18nService.getCompanyStatusi18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyStatusi18nDto
     */
    @Operation(summary = "Create CompanyStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusi18nDto> create(@RequestBody CompanyStatusi18nDto dto) {
        log.info("Creating companystatusi18n.");
        CompanyStatusi18nDto created = companyStatusi18nService.createCompanyStatusi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyStatusi18nDto
     */
    @Operation(summary = "Update CompanyStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CompanyStatusi18nDto> update(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody CompanyStatusi18nDto dto) {
        CompanyStatusi18nPK id = buildCompanyStatusi18nId(companyStatusId, languageId);
        log.info("Updating companystatusi18n with composite id: {}", id);
        return ResponseEntity.ok(companyStatusi18nService.updateCompanyStatusi18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyStatusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyStatusi18nPK id = buildCompanyStatusi18nId(companyStatusId, languageId);
        log.info("Deleting companystatusi18n with composite id: {}", id);
        companyStatusi18nService.deleteCompanyStatusi18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CompanyStatusi18n.
     */
    private CompanyStatusi18nPK buildCompanyStatusi18nId(UUID companyStatusId, UUID languageId) {
        CompanyStatusi18nPK id = new CompanyStatusi18nPK();
        id.setCompanyStatusId(companyStatusId);
        id.setLanguageId(languageId);
        return id;
    }
}
