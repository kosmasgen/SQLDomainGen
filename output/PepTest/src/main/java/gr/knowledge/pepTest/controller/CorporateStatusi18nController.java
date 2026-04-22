package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.service.CorporateStatusi18nService;
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
 * REST controller for managing Corporate Statusi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Corporate Statusi18n", description = "Corporate Statusi18n API")
@RequestMapping("/api/corporate-statusi18n")
public class CorporateStatusi18nController {

    private final CorporateStatusi18nService corporateStatusi18nService;

    /**
     * Retrieves all corporate statusi18ns.
     * @return list of CorporateStatusi18nDto
     */
    @Operation(summary = "Get all corporate statusi18ns")
    @GetMapping
    public ResponseEntity<List<CorporateStatusi18nDto>> getAll() {
        return ResponseEntity.ok(corporateStatusi18nService.getAllCorporateStatusi18ns());
    }

    /**
     * Retrieves the corporate statusi18n record by id.
     * @param corporateStatusId corporate status id identifier
     * @param languageId language id identifier
     * @return CorporateStatusi18nDto
     */
    @Operation(summary = "Get Corporate Statusi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{corporateStatusId}/{languageId}")
    public ResponseEntity<CorporateStatusi18nDto> getById(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(corporateStatusi18nService.getCorporateStatusi18nById(corporateStatusId, languageId));
    }

    /**
     * Creates a new corporate statusi18n record.
     * @param dto corporate statusi18n payload
     * @return created CorporateStatusi18nDto
     */
    @Operation(summary = "Create Corporate Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusi18nDto> create(
            @Valid @RequestBody CorporateStatusi18nDto dto) {
        CorporateStatusi18nDto created = corporateStatusi18nService.createCorporateStatusi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing corporate statusi18n record.
     * Only fields that are not null in the request are updated.
     * @param corporateStatusId corporate status id identifier
     * @param languageId language id identifier
     * @param dto partial corporate statusi18n payload
     * @return updated CorporateStatusi18nDto
     */
    @Operation(summary = "Patch Corporate Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{corporateStatusId}/{languageId}")
    public ResponseEntity<CorporateStatusi18nDto> patch(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody CorporateStatusi18nDto dto) {
        return ResponseEntity.ok(corporateStatusi18nService.updateCorporateStatusi18n(corporateStatusId, languageId, dto));
    }

    /**
     * Delete an corporate statusi18n record by id.
     * @param corporateStatusId corporate status id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Corporate Statusi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{corporateStatusId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        corporateStatusi18nService.deleteCorporateStatusi18n(corporateStatusId, languageId);
        return ResponseEntity.noContent().build();
    }

}
