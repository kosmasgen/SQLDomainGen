package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.service.CountryI18nService;
import gr.knowledge.pepTest.entity.CountryI18nPK;

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
 * REST controller for managing CountryI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CountryI18n", description = "CountryI18n API")
@RequestMapping("/api/country-i18ns")
public class CountryI18nController {

    private final CountryI18nService countryI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CountryI18nDto
     */
    @Operation(summary = "Get all CountryI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CountryI18nDto>> getAll() {
        log.info("Fetching all countryi18n records.");
        return ResponseEntity.ok(countryI18nService.getAllCountryI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CountryI18nDto
     */
    @Operation(summary = "Get CountryI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CountryI18nDto> getById(
            @Parameter(description = "country_id", required = true)
            @RequestParam(name = "country_id") UUID countryId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CountryI18nPK id = buildCountryI18nId(countryId, languageId);
        log.info("Fetching countryi18n with composite id: {}", id);
        return ResponseEntity.ok(countryI18nService.getCountryI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CountryI18nDto
     */
    @Operation(summary = "Create CountryI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CountryI18nDto> create(@RequestBody CountryI18nDto dto) {
        log.info("Creating countryi18n.");
        CountryI18nDto created = countryI18nService.createCountryI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CountryI18nDto
     */
    @Operation(summary = "Update CountryI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CountryI18nDto> update(
            @Parameter(description = "country_id", required = true)
            @RequestParam(name = "country_id") UUID countryId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody CountryI18nDto dto) {
        CountryI18nPK id = buildCountryI18nId(countryId, languageId);
        log.info("Updating countryi18n with composite id: {}", id);
        return ResponseEntity.ok(countryI18nService.updateCountryI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CountryI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "country_id", required = true)
            @RequestParam(name = "country_id") UUID countryId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        CountryI18nPK id = buildCountryI18nId(countryId, languageId);
        log.info("Deleting countryi18n with composite id: {}", id);
        countryI18nService.deleteCountryI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CountryI18n.
     */
    private CountryI18nPK buildCountryI18nId(UUID countryId, UUID languageId) {
        CountryI18nPK id = new CountryI18nPK();
        id.setCountryId(countryId);
        id.setLanguageId(languageId);
        return id;
    }
}
