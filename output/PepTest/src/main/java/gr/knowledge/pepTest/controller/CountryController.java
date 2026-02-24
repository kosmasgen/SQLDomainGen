package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.service.CountryService;

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
 * REST controller for managing Country resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Country", description = "Country API")
@RequestMapping("/api/countrys")
public class CountryController {

    private final CountryService countryService;

    /**
     * Retrieves all records.
     *
     * @return list of CountryDto
     */
    @Operation(summary = "Get all Country")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAll() {
        log.info("Fetching all country records.");
        return ResponseEntity.ok(countryService.getAllCountry());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CountryDto
     */
    @Operation(summary = "Get Country by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getById(
            @Parameter(description = "Country id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching country with id: {}", id);
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CountryDto
     */
    @Operation(summary = "Create Country")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CountryDto> create(@RequestBody CountryDto dto) {
        log.info("Creating country.");
        CountryDto created = countryService.createCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CountryDto
     */
    @Operation(summary = "Update Country")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> update(
            @Parameter(description = "Country id", required = true)
            @PathVariable UUID id,
            @RequestBody CountryDto dto) {
        log.info("Updating country with id: {}", id);
        return ResponseEntity.ok(countryService.updateCountry(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Country")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Country id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting country with id: {}", id);
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }
}
