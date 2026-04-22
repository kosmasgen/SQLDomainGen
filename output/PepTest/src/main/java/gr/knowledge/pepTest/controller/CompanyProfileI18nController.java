package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.service.CompanyProfileI18nService;
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
 * REST controller for managing Company Profile I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Profile I18n", description = "Company Profile I18n API")
@RequestMapping("/api/company-profile-i18n")
public class CompanyProfileI18nController {

    private final CompanyProfileI18nService companyProfileI18nService;

    /**
     * Retrieves all company profile i18ns.
     * @return list of CompanyProfileI18nDto
     */
    @Operation(summary = "Get all company profile i18ns")
    @GetMapping
    public ResponseEntity<List<CompanyProfileI18nDto>> getAll() {
        return ResponseEntity.ok(companyProfileI18nService.getAllCompanyProfileI18ns());
    }

    /**
     * Retrieves the company profile i18n record by id.
     * @param companyProfileId company profile id identifier
     * @param languageId language id identifier
     * @return CompanyProfileI18nDto
     */
    @Operation(summary = "Get Company Profile I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyProfileId}/{languageId}")
    public ResponseEntity<CompanyProfileI18nDto> getById(
            @Parameter(description = "company profile id identifier", required = true)
            @PathVariable UUID companyProfileId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(companyProfileI18nService.getCompanyProfileI18nById(companyProfileId, languageId));
    }

    /**
     * Creates a new company profile i18n record.
     * @param dto company profile i18n payload
     * @return created CompanyProfileI18nDto
     */
    @Operation(summary = "Create Company Profile I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfileI18nDto> create(
            @Valid @RequestBody CompanyProfileI18nDto dto) {
        CompanyProfileI18nDto created = companyProfileI18nService.createCompanyProfileI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company profile i18n record.
     * Only fields that are not null in the request are updated.
     * @param companyProfileId company profile id identifier
     * @param languageId language id identifier
     * @param dto partial company profile i18n payload
     * @return updated CompanyProfileI18nDto
     */
    @Operation(summary = "Patch Company Profile I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyProfileId}/{languageId}")
    public ResponseEntity<CompanyProfileI18nDto> patch(
            @Parameter(description = "company profile id identifier", required = true)
            @PathVariable UUID companyProfileId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody CompanyProfileI18nDto dto) {
        return ResponseEntity.ok(companyProfileI18nService.updateCompanyProfileI18n(companyProfileId, languageId, dto));
    }

    /**
     * Delete an company profile i18n record by id.
     * @param companyProfileId company profile id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Profile I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyProfileId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company profile id identifier", required = true)
            @PathVariable UUID companyProfileId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        companyProfileI18nService.deleteCompanyProfileI18n(companyProfileId, languageId);
        return ResponseEntity.noContent().build();
    }

}
