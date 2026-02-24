package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.service.MunicipalityI18nService;
import gr.knowledge.pepTest.entity.MunicipalityI18nPK;

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
 * REST controller for managing MunicipalityI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "MunicipalityI18n", description = "MunicipalityI18n API")
@RequestMapping("/api/municipality-i18ns")
public class MunicipalityI18nController {

    private final MunicipalityI18nService municipalityI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of MunicipalityI18nDto
     */
    @Operation(summary = "Get all MunicipalityI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<MunicipalityI18nDto>> getAll() {
        log.info("Fetching all municipalityi18n records.");
        return ResponseEntity.ok(municipalityI18nService.getAllMunicipalityI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return MunicipalityI18nDto
     */
    @Operation(summary = "Get MunicipalityI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<MunicipalityI18nDto> getById(
            @Parameter(description = "municipality_id", required = true)
            @RequestParam(name = "municipality_id") UUID municipalityId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        MunicipalityI18nPK id = buildMunicipalityI18nId(municipalityId, languageId);
        log.info("Fetching municipalityi18n with composite id: {}", id);
        return ResponseEntity.ok(municipalityI18nService.getMunicipalityI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created MunicipalityI18nDto
     */
    @Operation(summary = "Create MunicipalityI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<MunicipalityI18nDto> create(@RequestBody MunicipalityI18nDto dto) {
        log.info("Creating municipalityi18n.");
        MunicipalityI18nDto created = municipalityI18nService.createMunicipalityI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated MunicipalityI18nDto
     */
    @Operation(summary = "Update MunicipalityI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<MunicipalityI18nDto> update(
            @Parameter(description = "municipality_id", required = true)
            @RequestParam(name = "municipality_id") UUID municipalityId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody MunicipalityI18nDto dto) {
        MunicipalityI18nPK id = buildMunicipalityI18nId(municipalityId, languageId);
        log.info("Updating municipalityi18n with composite id: {}", id);
        return ResponseEntity.ok(municipalityI18nService.updateMunicipalityI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete MunicipalityI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "municipality_id", required = true)
            @RequestParam(name = "municipality_id") UUID municipalityId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        MunicipalityI18nPK id = buildMunicipalityI18nId(municipalityId, languageId);
        log.info("Deleting municipalityi18n with composite id: {}", id);
        municipalityI18nService.deleteMunicipalityI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for MunicipalityI18n.
     */
    private MunicipalityI18nPK buildMunicipalityI18nId(UUID municipalityId, UUID languageId) {
        MunicipalityI18nPK id = new MunicipalityI18nPK();
        id.setMunicipalityId(municipalityId);
        id.setLanguageId(languageId);
        return id;
    }
}
