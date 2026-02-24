package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.service.CompanyProfileI18nService;
import gr.knowledge.pepTest.entity.CompanyProfileI18nPK;

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
 * REST controller for managing CompanyProfileI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyProfileI18n", description = "CompanyProfileI18n API")
@RequestMapping("/api/company-profile-i18ns")
public class CompanyProfileI18nController {

    private final CompanyProfileI18nService companyProfileI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyProfileI18nDto
     */
    @Operation(summary = "Get all CompanyProfileI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyProfileI18nDto>> getAll() {
        log.info("Fetching all companyprofilei18n records.");
        return ResponseEntity.ok(companyProfileI18nService.getAllCompanyProfileI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyProfileI18nDto
     */
    @Operation(summary = "Get CompanyProfileI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CompanyProfileI18nDto> getById(
            @Parameter(description = "company_profile_id", required = true)
            @RequestParam(name = "company_profile_id") UUID companyProfileId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyProfileI18nPK id = buildCompanyProfileI18nId(companyProfileId, languageId);
        log.info("Fetching companyprofilei18n with composite id: {}", id);
        return ResponseEntity.ok(companyProfileI18nService.getCompanyProfileI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyProfileI18nDto
     */
    @Operation(summary = "Create CompanyProfileI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfileI18nDto> create(@RequestBody CompanyProfileI18nDto dto) {
        log.info("Creating companyprofilei18n.");
        CompanyProfileI18nDto created = companyProfileI18nService.createCompanyProfileI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyProfileI18nDto
     */
    @Operation(summary = "Update CompanyProfileI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CompanyProfileI18nDto> update(
            @Parameter(description = "company_profile_id", required = true)
            @RequestParam(name = "company_profile_id") UUID companyProfileId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody CompanyProfileI18nDto dto) {
        CompanyProfileI18nPK id = buildCompanyProfileI18nId(companyProfileId, languageId);
        log.info("Updating companyprofilei18n with composite id: {}", id);
        return ResponseEntity.ok(companyProfileI18nService.updateCompanyProfileI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyProfileI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_profile_id", required = true)
            @RequestParam(name = "company_profile_id") UUID companyProfileId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyProfileI18nPK id = buildCompanyProfileI18nId(companyProfileId, languageId);
        log.info("Deleting companyprofilei18n with composite id: {}", id);
        companyProfileI18nService.deleteCompanyProfileI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CompanyProfileI18n.
     */
    private CompanyProfileI18nPK buildCompanyProfileI18nId(UUID companyProfileId, UUID languageId) {
        CompanyProfileI18nPK id = new CompanyProfileI18nPK();
        id.setCompanyProfileId(companyProfileId);
        id.setLanguageId(languageId);
        return id;
    }
}
