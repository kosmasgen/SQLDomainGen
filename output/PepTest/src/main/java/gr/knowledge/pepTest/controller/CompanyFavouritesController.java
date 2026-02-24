package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.service.CompanyFavouritesService;

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
 * REST controller for managing CompanyFavourites resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyFavourites", description = "CompanyFavourites API")
@RequestMapping("/api/company-favouritess")
public class CompanyFavouritesController {

    private final CompanyFavouritesService companyFavouritesService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyFavouritesDto
     */
    @Operation(summary = "Get all CompanyFavourites")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyFavouritesDto>> getAll() {
        log.info("Fetching all companyfavourites records.");
        return ResponseEntity.ok(companyFavouritesService.getAllCompanyFavourites());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyFavouritesDto
     */
    @Operation(summary = "Get CompanyFavourites by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyFavouritesDto> getById(
            @Parameter(description = "CompanyFavourites id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyfavourites with id: {}", id);
        return ResponseEntity.ok(companyFavouritesService.getCompanyFavouritesById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyFavouritesDto
     */
    @Operation(summary = "Create CompanyFavourites")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyFavouritesDto> create(@RequestBody CompanyFavouritesDto dto) {
        log.info("Creating companyfavourites.");
        CompanyFavouritesDto created = companyFavouritesService.createCompanyFavourites(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyFavouritesDto
     */
    @Operation(summary = "Update CompanyFavourites")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyFavouritesDto> update(
            @Parameter(description = "CompanyFavourites id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyFavouritesDto dto) {
        log.info("Updating companyfavourites with id: {}", id);
        return ResponseEntity.ok(companyFavouritesService.updateCompanyFavourites(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyFavourites")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyFavourites id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyfavourites with id: {}", id);
        companyFavouritesService.deleteCompanyFavourites(id);
        return ResponseEntity.noContent().build();
    }
}
