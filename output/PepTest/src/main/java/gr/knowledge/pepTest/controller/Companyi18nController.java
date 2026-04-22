package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.service.Companyi18nService;
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
 * REST controller for managing Companyi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Companyi18n", description = "Companyi18n API")
@RequestMapping("/api/companyi18n")
public class Companyi18nController {

    private final Companyi18nService companyi18nService;

    /**
     * Retrieves all companyi18ns.
     * @return list of Companyi18nDto
     */
    @Operation(summary = "Get all companyi18ns")
    @GetMapping
    public ResponseEntity<List<Companyi18nDto>> getAll() {
        return ResponseEntity.ok(companyi18nService.getAllCompanyi18ns());
    }

    /**
     * Retrieves the companyi18n record by id.
     * @param companyId company id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @return Companyi18nDto
     */
    @Operation(summary = "Get Companyi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<Companyi18nDto> getById(
            @Parameter(description = "company id identifier", required = true)
            @PathVariable UUID companyId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId) {
        return ResponseEntity.ok(companyi18nService.getCompanyi18nById(companyId, languageId, chamberI18nId));
    }

    /**
     * Creates a new companyi18n record.
     * @param dto companyi18n payload
     * @return created Companyi18nDto
     */
    @Operation(summary = "Create Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Companyi18nDto> create(
            @Valid @RequestBody Companyi18nDto dto) {
        Companyi18nDto created = companyi18nService.createCompanyi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing companyi18n record.
     * Only fields that are not null in the request are updated.
     * @param companyId company id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @param dto partial companyi18n payload
     * @return updated Companyi18nDto
     */
    @Operation(summary = "Patch Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<Companyi18nDto> patch(
            @Parameter(description = "company id identifier", required = true)
            @PathVariable UUID companyId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId,
            @Valid @RequestBody Companyi18nDto dto) {
        return ResponseEntity.ok(companyi18nService.updateCompanyi18n(companyId, languageId, chamberI18nId, dto));
    }

    /**
     * Delete an companyi18n record by id.
     * @param companyId company id identifier
     * @param languageId language id identifier
     * @param chamberI18nId chamber i18n id identifier
     * @return no content
     */
    @Operation(summary = "Delete Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyId}/{languageId}/{chamberI18nId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company id identifier", required = true)
            @PathVariable UUID companyId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "chamber i18n id identifier", required = true)
            @PathVariable Integer chamberI18nId) {
        companyi18nService.deleteCompanyi18n(companyId, languageId, chamberI18nId);
        return ResponseEntity.noContent().build();
    }

}
