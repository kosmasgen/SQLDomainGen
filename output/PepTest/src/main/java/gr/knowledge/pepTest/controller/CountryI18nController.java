package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.service.CountryI18nService;
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
 * REST controller for managing Country I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Country I18n", description = "Country I18n API")
@RequestMapping("/api/country-i18n")
public class CountryI18nController {

    private final CountryI18nService countryI18nService;

    /**
     * Retrieves all country i18ns.
     * @return list of CountryI18nDto
     */
    @Operation(summary = "Get all country i18ns")
    @GetMapping
    public ResponseEntity<List<CountryI18nDto>> getAll() {
        return ResponseEntity.ok(countryI18nService.getAllCountryI18ns());
    }

    /**
     * Retrieves the country i18n record by id.
     * @param countryId country id identifier
     * @param languageId language id identifier
     * @return CountryI18nDto
     */
    @Operation(summary = "Get Country I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{countryId}/{languageId}")
    public ResponseEntity<CountryI18nDto> getById(
            @Parameter(description = "country id identifier", required = true)
            @PathVariable UUID countryId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(countryI18nService.getCountryI18nById(countryId, languageId));
    }

    /**
     * Creates a new country i18n record.
     * @param dto country i18n payload
     * @return created CountryI18nDto
     */
    @Operation(summary = "Create Country I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CountryI18nDto> create(
            @Valid @RequestBody CountryI18nDto dto) {
        CountryI18nDto created = countryI18nService.createCountryI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing country i18n record.
     * Only fields that are not null in the request are updated.
     * @param countryId country id identifier
     * @param languageId language id identifier
     * @param dto partial country i18n payload
     * @return updated CountryI18nDto
     */
    @Operation(summary = "Patch Country I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{countryId}/{languageId}")
    public ResponseEntity<CountryI18nDto> patch(
            @Parameter(description = "country id identifier", required = true)
            @PathVariable UUID countryId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody CountryI18nDto dto) {
        return ResponseEntity.ok(countryI18nService.updateCountryI18n(countryId, languageId, dto));
    }

    /**
     * Delete an country i18n record by id.
     * @param countryId country id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Country I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{countryId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "country id identifier", required = true)
            @PathVariable UUID countryId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        countryI18nService.deleteCountryI18n(countryId, languageId);
        return ResponseEntity.noContent().build();
    }

}
