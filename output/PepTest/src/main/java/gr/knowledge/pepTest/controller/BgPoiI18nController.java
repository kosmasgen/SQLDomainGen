package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.service.BgPoiI18nService;

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
 * REST controller for managing BgPoiI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "BgPoiI18n", description = "BgPoiI18n API")
@RequestMapping("/api/bg-poi-i18ns")
public class BgPoiI18nController {

    private final BgPoiI18nService bgPoiI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of BgPoiI18nDto
     */
    @Operation(summary = "Get all BgPoiI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<BgPoiI18nDto>> getAll() {
        log.info("Fetching all bgpoii18n records.");
        return ResponseEntity.ok(bgPoiI18nService.getAllBgPoiI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return BgPoiI18nDto
     */
    @Operation(summary = "Get BgPoiI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BgPoiI18nDto> getById(
            @Parameter(description = "BgPoiI18n id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching bgpoii18n with id: {}", id);
        return ResponseEntity.ok(bgPoiI18nService.getBgPoiI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created BgPoiI18nDto
     */
    @Operation(summary = "Create BgPoiI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BgPoiI18nDto> create(@RequestBody BgPoiI18nDto dto) {
        log.info("Creating bgpoii18n.");
        BgPoiI18nDto created = bgPoiI18nService.createBgPoiI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated BgPoiI18nDto
     */
    @Operation(summary = "Update BgPoiI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BgPoiI18nDto> update(
            @Parameter(description = "BgPoiI18n id", required = true)
            @PathVariable UUID id,
            @RequestBody BgPoiI18nDto dto) {
        log.info("Updating bgpoii18n with id: {}", id);
        return ResponseEntity.ok(bgPoiI18nService.updateBgPoiI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete BgPoiI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "BgPoiI18n id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting bgpoii18n with id: {}", id);
        bgPoiI18nService.deleteBgPoiI18n(id);
        return ResponseEntity.noContent().build();
    }
}
