package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyi18nService;

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

import java.util.List;

/**
 * REST controller for managing TemporaryCompanyi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "TemporaryCompanyi18n", description = "TemporaryCompanyi18n API")
@RequestMapping("/api/temporary-companyi18ns")
public class TemporaryCompanyi18nController {

    private final TemporaryCompanyi18nService temporaryCompanyi18nService;

    /**
     * Retrieves all records.
     *
     * @return list of TemporaryCompanyi18nDto
     */
    @Operation(summary = "Get all TemporaryCompanyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyi18nDto>> getAll() {
        log.info("Fetching all temporarycompanyi18n records.");
        return ResponseEntity.ok(temporaryCompanyi18nService.getAllTemporaryCompanyi18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return TemporaryCompanyi18nDto
     */
    @Operation(summary = "Get TemporaryCompanyi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyi18nDto> getById(
            @Parameter(description = "TemporaryCompanyi18n id", required = true)
            @PathVariable Long id) {
        log.info("Fetching temporarycompanyi18n with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyi18nService.getTemporaryCompanyi18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TemporaryCompanyi18nDto
     */
    @Operation(summary = "Create TemporaryCompanyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyi18nDto> create(@RequestBody TemporaryCompanyi18nDto dto) {
        log.info("Creating temporarycompanyi18n.");
        TemporaryCompanyi18nDto created = temporaryCompanyi18nService.createTemporaryCompanyi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated TemporaryCompanyi18nDto
     */
    @Operation(summary = "Update TemporaryCompanyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryCompanyi18nDto> update(
            @Parameter(description = "TemporaryCompanyi18n id", required = true)
            @PathVariable Long id,
            @RequestBody TemporaryCompanyi18nDto dto) {
        log.info("Updating temporarycompanyi18n with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyi18nService.updateTemporaryCompanyi18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete TemporaryCompanyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "TemporaryCompanyi18n id", required = true)
            @PathVariable Long id) {
        log.info("Deleting temporarycompanyi18n with id: {}", id);
        temporaryCompanyi18nService.deleteTemporaryCompanyi18n(id);
        return ResponseEntity.noContent().build();
    }
}
