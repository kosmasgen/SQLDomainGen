package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.service.CompanyStatusi18nService;
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
 * REST controller for managing Company Statusi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Statusi18n", description = "Company Statusi18n API")
@RequestMapping("/api/company-statusi18n")
public class CompanyStatusi18nController {

    private final CompanyStatusi18nService companyStatusi18nService;

    /**
     * Retrieves all company statusi18ns.
     * @return list of CompanyStatusi18nDto
     */
    @Operation(summary = "Get all company statusi18ns")
    @GetMapping
    public ResponseEntity<List<CompanyStatusi18nDto>> getAll() {
        return ResponseEntity.ok(companyStatusi18nService.getAllCompanyStatusi18ns());
    }

    /**
     * Retrieves the company statusi18n record by id.
     * @param companyStatusId company status id identifier
     * @param languageId language id identifier
     * @return CompanyStatusi18nDto
     */
    @Operation(summary = "Get Company Statusi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyStatusId}/{languageId}")
    public ResponseEntity<CompanyStatusi18nDto> getById(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(companyStatusi18nService.getCompanyStatusi18nById(companyStatusId, languageId));
    }

    /**
     * Creates a new company statusi18n record.
     * @param dto company statusi18n payload
     * @return created CompanyStatusi18nDto
     */
    @Operation(summary = "Create Company Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusi18nDto> create(
            @Valid @RequestBody CompanyStatusi18nDto dto) {
        CompanyStatusi18nDto created = companyStatusi18nService.createCompanyStatusi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company statusi18n record.
     * Only fields that are not null in the request are updated.
     * @param companyStatusId company status id identifier
     * @param languageId language id identifier
     * @param dto partial company statusi18n payload
     * @return updated CompanyStatusi18nDto
     */
    @Operation(summary = "Patch Company Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyStatusId}/{languageId}")
    public ResponseEntity<CompanyStatusi18nDto> patch(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @RequestBody CompanyStatusi18nDto dto) {
        return ResponseEntity.ok(companyStatusi18nService.updateCompanyStatusi18n(companyStatusId, languageId, dto));
    }

    /**
     * Delete an company statusi18n record by id.
     * @param companyStatusId company status id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyStatusId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        companyStatusi18nService.deleteCompanyStatusi18n(companyStatusId, languageId);
        return ResponseEntity.noContent().build();
    }

}
