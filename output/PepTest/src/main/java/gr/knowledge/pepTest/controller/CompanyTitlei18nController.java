package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.service.CompanyTitlei18nService;
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
 * REST controller for managing Company Titlei18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Titlei18n", description = "Company Titlei18n API")
@RequestMapping("/api/company-titlei18n")
public class CompanyTitlei18nController {

    private final CompanyTitlei18nService companyTitlei18nService;

    /**
     * Retrieves all company titlei18ns.
     * @return list of CompanyTitlei18nDto
     */
    @Operation(summary = "Get all company titlei18ns")
    @GetMapping
    public ResponseEntity<List<CompanyTitlei18nDto>> getAll() {
        return ResponseEntity.ok(companyTitlei18nService.getAllCompanyTitlei18ns());
    }

    /**
     * Retrieves the company titlei18n record by id.
     * @param companyTitleId company title id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @return CompanyTitlei18nDto
     */
    @Operation(summary = "Get Company Titlei18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyTitleId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<CompanyTitlei18nDto> getById(
            @Parameter(description = "company title id identifier", required = true)
            @PathVariable UUID companyTitleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId) {
        return ResponseEntity.ok(companyTitlei18nService.getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId));
    }

    /**
     * Creates a new company titlei18n record.
     * @param dto company titlei18n payload
     * @return created CompanyTitlei18nDto
     */
    @Operation(summary = "Create Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyTitlei18nDto> create(
            @Valid @RequestBody CompanyTitlei18nDto dto) {
        CompanyTitlei18nDto created = companyTitlei18nService.createCompanyTitlei18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company titlei18n record.
     * Only fields that are not null in the request are updated.
     * @param companyTitleId company title id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @param dto partial company titlei18n payload
     * @return updated CompanyTitlei18nDto
     */
    @Operation(summary = "Patch Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyTitleId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<CompanyTitlei18nDto> patch(
            @Parameter(description = "company title id identifier", required = true)
            @PathVariable UUID companyTitleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId,
            @RequestBody CompanyTitlei18nDto dto) {
        return ResponseEntity.ok(companyTitlei18nService.updateCompanyTitlei18n(companyTitleId, languageId, chamberI18nId, dto));
    }

    /**
     * Delete an company titlei18n record by id.
     * @param companyTitleId company title id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyTitleId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company title id identifier", required = true)
            @PathVariable UUID companyTitleId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId) {
        companyTitlei18nService.deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId);
        return ResponseEntity.noContent().build();
    }

}
