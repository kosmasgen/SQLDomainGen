package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.service.CompanyYpArticleI18nService;
import gr.knowledge.pepTest.entity.CompanyYpArticleI18nPK;

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
 * REST controller for managing CompanyYpArticleI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyYpArticleI18n", description = "CompanyYpArticleI18n API")
@RequestMapping("/api/company-yp-article-i18ns")
public class CompanyYpArticleI18nController {

    private final CompanyYpArticleI18nService companyYpArticleI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyYpArticleI18nDto
     */
    @Operation(summary = "Get all CompanyYpArticleI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyYpArticleI18nDto>> getAll() {
        log.info("Fetching all companyyparticlei18n records.");
        return ResponseEntity.ok(companyYpArticleI18nService.getAllCompanyYpArticleI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyYpArticleI18nDto
     */
    @Operation(summary = "Get CompanyYpArticleI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CompanyYpArticleI18nDto> getById(
            @Parameter(description = "company_article_id", required = true)
            @RequestParam(name = "company_article_id") UUID companyArticleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyYpArticleI18nPK id = buildCompanyYpArticleI18nId(companyArticleId, languageId);
        log.info("Fetching companyyparticlei18n with composite id: {}", id);
        return ResponseEntity.ok(companyYpArticleI18nService.getCompanyYpArticleI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyYpArticleI18nDto
     */
    @Operation(summary = "Create CompanyYpArticleI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpArticleI18nDto> create(@RequestBody CompanyYpArticleI18nDto dto) {
        log.info("Creating companyyparticlei18n.");
        CompanyYpArticleI18nDto created = companyYpArticleI18nService.createCompanyYpArticleI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyYpArticleI18nDto
     */
    @Operation(summary = "Update CompanyYpArticleI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CompanyYpArticleI18nDto> update(
            @Parameter(description = "company_article_id", required = true)
            @RequestParam(name = "company_article_id") UUID companyArticleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody CompanyYpArticleI18nDto dto) {
        CompanyYpArticleI18nPK id = buildCompanyYpArticleI18nId(companyArticleId, languageId);
        log.info("Updating companyyparticlei18n with composite id: {}", id);
        return ResponseEntity.ok(companyYpArticleI18nService.updateCompanyYpArticleI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyYpArticleI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_article_id", required = true)
            @RequestParam(name = "company_article_id") UUID companyArticleId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CompanyYpArticleI18nPK id = buildCompanyYpArticleI18nId(companyArticleId, languageId);
        log.info("Deleting companyyparticlei18n with composite id: {}", id);
        companyYpArticleI18nService.deleteCompanyYpArticleI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CompanyYpArticleI18n.
     */
    private CompanyYpArticleI18nPK buildCompanyYpArticleI18nId(UUID companyArticleId, UUID languageId) {
        CompanyYpArticleI18nPK id = new CompanyYpArticleI18nPK();
        id.setCompanyArticleId(companyArticleId);
        id.setLanguageId(languageId);
        return id;
    }
}
