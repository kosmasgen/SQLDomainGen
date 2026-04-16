package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.service.BusinessLocationI18nService;
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
 * REST controller for managing Business Location I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Business Location I18n", description = "Business Location I18n API")
@RequestMapping("/api/business-location-i18n")
public class BusinessLocationI18nController {

    private final BusinessLocationI18nService businessLocationI18nService;

    /**
     * Retrieves all business location i18ns.
     * @return list of BusinessLocationI18nDto
     */
    @Operation(summary = "Get all business location i18ns")
    @GetMapping
    public ResponseEntity<List<BusinessLocationI18nDto>> getAll() {
        return ResponseEntity.ok(businessLocationI18nService.getAllBusinessLocationI18ns());
    }

    /**
     * Retrieves the business location i18n record by id.
     * @param businessLocationId business location id identifier
     * @param languageId language id identifier
     * @return BusinessLocationI18nDto
     */
    @Operation(summary = "Get Business Location I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{businessLocationId}/{languageId}")
    public ResponseEntity<BusinessLocationI18nDto> getById(
            @Parameter(description = "business location id identifier", required = true)
            @PathVariable UUID businessLocationId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(businessLocationI18nService.getBusinessLocationI18nById(businessLocationId, languageId));
    }

    /**
     * Creates a new business location i18n record.
     * @param dto business location i18n payload
     * @return created BusinessLocationI18nDto
     */
    @Operation(summary = "Create Business Location I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BusinessLocationI18nDto> create(
            @Valid @RequestBody BusinessLocationI18nDto dto) {
        BusinessLocationI18nDto created = businessLocationI18nService.createBusinessLocationI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing business location i18n record.
     * Only fields that are not null in the request are updated.
     * @param businessLocationId business location id identifier
     * @param languageId language id identifier
     * @param dto partial business location i18n payload
     * @return updated BusinessLocationI18nDto
     */
    @Operation(summary = "Patch Business Location I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{businessLocationId}/{languageId}")
    public ResponseEntity<BusinessLocationI18nDto> patch(
            @Parameter(description = "business location id identifier", required = true)
            @PathVariable UUID businessLocationId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @RequestBody BusinessLocationI18nDto dto) {
        return ResponseEntity.ok(businessLocationI18nService.updateBusinessLocationI18n(businessLocationId, languageId, dto));
    }

    /**
     * Delete an business location i18n record by id.
     * @param businessLocationId business location id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Business Location I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{businessLocationId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "business location id identifier", required = true)
            @PathVariable UUID businessLocationId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        businessLocationI18nService.deleteBusinessLocationI18n(businessLocationId, languageId);
        return ResponseEntity.noContent().build();
    }

}
