package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;

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
 * REST controller for managing TemporaryCompanyTitlei18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "TemporaryCompanyTitlei18n", description = "TemporaryCompanyTitlei18n API")
@RequestMapping("/api/temporary-company-titlei18ns")
public class TemporaryCompanyTitlei18nController {

    private final TemporaryCompanyTitlei18nService temporaryCompanyTitlei18nService;

    /**
     * Retrieves all records.
     *
     * @return list of TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Get all TemporaryCompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyTitlei18nDto>> getAll() {
        log.info("Fetching all temporarycompanytitlei18n records.");
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Get TemporaryCompanyTitlei18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitlei18nDto> getById(
            @Parameter(description = "TemporaryCompanyTitlei18n id", required = true)
            @PathVariable Long id) {
        log.info("Fetching temporarycompanytitlei18n with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Create TemporaryCompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyTitlei18nDto> create(@RequestBody TemporaryCompanyTitlei18nDto dto) {
        log.info("Creating temporarycompanytitlei18n.");
        TemporaryCompanyTitlei18nDto created = temporaryCompanyTitlei18nService.createTemporaryCompanyTitlei18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Update TemporaryCompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitlei18nDto> update(
            @Parameter(description = "TemporaryCompanyTitlei18n id", required = true)
            @PathVariable Long id,
            @RequestBody TemporaryCompanyTitlei18nDto dto) {
        log.info("Updating temporarycompanytitlei18n with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete TemporaryCompanyTitlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "TemporaryCompanyTitlei18n id", required = true)
            @PathVariable Long id) {
        log.info("Deleting temporarycompanytitlei18n with id: {}", id);
        temporaryCompanyTitlei18nService.deleteTemporaryCompanyTitlei18n(id);
        return ResponseEntity.noContent().build();
    }
}
