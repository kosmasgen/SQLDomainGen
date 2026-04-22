package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.service.CountryService;
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
 * REST controller for managing Country resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Country", description = "Country API")
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    /**
     * Retrieves all countries.
     * @return list of CountryDto
     */
    @Operation(summary = "Get all countries")
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAll() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    /**
     * Retrieves the country record by id.
     * @param id country identifier
     * @return CountryDto
     */
    @Operation(summary = "Get Country by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getById(
            @Parameter(description = "country identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    /**
     * Creates a new country record.
     * @param dto country payload
     * @return created CountryDto
     */
    @Operation(summary = "Create Country")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CountryDto> create(
            @Valid @RequestBody CountryDto dto) {
        CountryDto created = countryService.createCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing country record.
     * Only fields that are not null in the request are updated.
     * @param id country identifier
     * @param dto partial country payload
     * @return updated CountryDto
     */
    @Operation(summary = "Patch Country")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CountryDto> patch(
            @Parameter(description = "country identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CountryDto dto) {
        return ResponseEntity.ok(countryService.updateCountry(id, dto));
    }

    /**
     * Delete an country record by id.
     * @param id country identifier
     * @return no content
     */
    @Operation(summary = "Delete Country")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "country identifier", required = true)
            @PathVariable UUID id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

}
