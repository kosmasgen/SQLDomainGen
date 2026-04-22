package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.service.CompanyYpArticleI18nService;
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
 * REST controller for managing Company Yp Article I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Yp Article I18n", description = "Company Yp Article I18n API")
@RequestMapping("/api/company-yp-article-i18n")
public class CompanyYpArticleI18nController {

    private final CompanyYpArticleI18nService companyYpArticleI18nService;

    /**
     * Retrieves all company yp article i18ns.
     * @return list of CompanyYpArticleI18nDto
     */
    @Operation(summary = "Get all company yp article i18ns")
    @GetMapping
    public ResponseEntity<List<CompanyYpArticleI18nDto>> getAll() {
        return ResponseEntity.ok(companyYpArticleI18nService.getAllCompanyYpArticleI18ns());
    }

    /**
     * Retrieves the company yp article i18n record by id.
     * @param companyArticleId company article id identifier
     * @param languageId language id identifier
     * @return CompanyYpArticleI18nDto
     */
    @Operation(summary = "Get Company Yp Article I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyArticleId}/{languageId}")
    public ResponseEntity<CompanyYpArticleI18nDto> getById(
            @Parameter(description = "company article id identifier", required = true)
            @PathVariable UUID companyArticleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(companyYpArticleI18nService.getCompanyYpArticleI18nById(companyArticleId, languageId));
    }

    /**
     * Creates a new company yp article i18n record.
     * @param dto company yp article i18n payload
     * @return created CompanyYpArticleI18nDto
     */
    @Operation(summary = "Create Company Yp Article I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpArticleI18nDto> create(
            @Valid @RequestBody CompanyYpArticleI18nDto dto) {
        CompanyYpArticleI18nDto created = companyYpArticleI18nService.createCompanyYpArticleI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company yp article i18n record.
     * Only fields that are not null in the request are updated.
     * @param companyArticleId company article id identifier
     * @param languageId language id identifier
     * @param dto partial company yp article i18n payload
     * @return updated CompanyYpArticleI18nDto
     */
    @Operation(summary = "Patch Company Yp Article I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyArticleId}/{languageId}")
    public ResponseEntity<CompanyYpArticleI18nDto> patch(
            @Parameter(description = "company article id identifier", required = true)
            @PathVariable UUID companyArticleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody CompanyYpArticleI18nDto dto) {
        return ResponseEntity.ok(companyYpArticleI18nService.updateCompanyYpArticleI18n(companyArticleId, languageId, dto));
    }

    /**
     * Delete an company yp article i18n record by id.
     * @param companyArticleId company article id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Yp Article I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyArticleId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company article id identifier", required = true)
            @PathVariable UUID companyArticleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        companyYpArticleI18nService.deleteCompanyYpArticleI18n(companyArticleId, languageId);
        return ResponseEntity.noContent().build();
    }

}
