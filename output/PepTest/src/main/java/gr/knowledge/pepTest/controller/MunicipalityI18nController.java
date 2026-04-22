package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.service.MunicipalityI18nService;
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
 * REST controller for managing Municipality I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Municipality I18n", description = "Municipality I18n API")
@RequestMapping("/api/municipality-i18n")
public class MunicipalityI18nController {

    private final MunicipalityI18nService municipalityI18nService;

    /**
     * Retrieves all municipality i18ns.
     * @return list of MunicipalityI18nDto
     */
    @Operation(summary = "Get all municipality i18ns")
    @GetMapping
    public ResponseEntity<List<MunicipalityI18nDto>> getAll() {
        return ResponseEntity.ok(municipalityI18nService.getAllMunicipalityI18ns());
    }

    /**
     * Retrieves the municipality i18n record by id.
     * @param municipalityId municipality id identifier
     * @param languageId language id identifier
     * @return MunicipalityI18nDto
     */
    @Operation(summary = "Get Municipality I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{municipalityId}/{languageId}")
    public ResponseEntity<MunicipalityI18nDto> getById(
            @Parameter(description = "municipality id identifier", required = true)
            @PathVariable UUID municipalityId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(municipalityI18nService.getMunicipalityI18nById(municipalityId, languageId));
    }

    /**
     * Creates a new municipality i18n record.
     * @param dto municipality i18n payload
     * @return created MunicipalityI18nDto
     */
    @Operation(summary = "Create Municipality I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<MunicipalityI18nDto> create(
            @Valid @RequestBody MunicipalityI18nDto dto) {
        MunicipalityI18nDto created = municipalityI18nService.createMunicipalityI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing municipality i18n record.
     * Only fields that are not null in the request are updated.
     * @param municipalityId municipality id identifier
     * @param languageId language id identifier
     * @param dto partial municipality i18n payload
     * @return updated MunicipalityI18nDto
     */
    @Operation(summary = "Patch Municipality I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{municipalityId}/{languageId}")
    public ResponseEntity<MunicipalityI18nDto> patch(
            @Parameter(description = "municipality id identifier", required = true)
            @PathVariable UUID municipalityId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody MunicipalityI18nDto dto) {
        return ResponseEntity.ok(municipalityI18nService.updateMunicipalityI18n(municipalityId, languageId, dto));
    }

    /**
     * Delete an municipality i18n record by id.
     * @param municipalityId municipality id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Municipality I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{municipalityId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "municipality id identifier", required = true)
            @PathVariable UUID municipalityId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        municipalityI18nService.deleteMunicipalityI18n(municipalityId, languageId);
        return ResponseEntity.noContent().build();
    }

}
