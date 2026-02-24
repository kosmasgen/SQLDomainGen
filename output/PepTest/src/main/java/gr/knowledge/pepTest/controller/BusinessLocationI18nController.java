package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BusinessLocationI18nDto;
import gr.knowledge.pepTest.service.BusinessLocationI18nService;
import gr.knowledge.pepTest.entity.BusinessLocationI18nPK;

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
 * REST controller for managing BusinessLocationI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "BusinessLocationI18n", description = "BusinessLocationI18n API")
@RequestMapping("/api/business-location-i18ns")
public class BusinessLocationI18nController {

    private final BusinessLocationI18nService businessLocationI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of BusinessLocationI18nDto
     */
    @Operation(summary = "Get all BusinessLocationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<BusinessLocationI18nDto>> getAll() {
        log.info("Fetching all businesslocationi18n records.");
        return ResponseEntity.ok(businessLocationI18nService.getAllBusinessLocationI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return BusinessLocationI18nDto
     */
    @Operation(summary = "Get BusinessLocationI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<BusinessLocationI18nDto> getById(
            @Parameter(description = "business_location_id", required = true)
            @RequestParam(name = "business_location_id") UUID businessLocationId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        BusinessLocationI18nPK id = buildBusinessLocationI18nId(businessLocationId, languageId);
        log.info("Fetching businesslocationi18n with composite id: {}", id);
        return ResponseEntity.ok(businessLocationI18nService.getBusinessLocationI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created BusinessLocationI18nDto
     */
    @Operation(summary = "Create BusinessLocationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BusinessLocationI18nDto> create(@RequestBody BusinessLocationI18nDto dto) {
        log.info("Creating businesslocationi18n.");
        BusinessLocationI18nDto created = businessLocationI18nService.createBusinessLocationI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated BusinessLocationI18nDto
     */
    @Operation(summary = "Update BusinessLocationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<BusinessLocationI18nDto> update(
            @Parameter(description = "business_location_id", required = true)
            @RequestParam(name = "business_location_id") UUID businessLocationId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody BusinessLocationI18nDto dto) {
        BusinessLocationI18nPK id = buildBusinessLocationI18nId(businessLocationId, languageId);
        log.info("Updating businesslocationi18n with composite id: {}", id);
        return ResponseEntity.ok(businessLocationI18nService.updateBusinessLocationI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete BusinessLocationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "business_location_id", required = true)
            @RequestParam(name = "business_location_id") UUID businessLocationId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        BusinessLocationI18nPK id = buildBusinessLocationI18nId(businessLocationId, languageId);
        log.info("Deleting businesslocationi18n with composite id: {}", id);
        businessLocationI18nService.deleteBusinessLocationI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for BusinessLocationI18n.
     */
    private BusinessLocationI18nPK buildBusinessLocationI18nId(UUID businessLocationId, UUID languageId) {
        BusinessLocationI18nPK id = new BusinessLocationI18nPK();
        id.setBusinessLocationId(businessLocationId);
        id.setLanguageId(languageId);
        return id;
    }
}
