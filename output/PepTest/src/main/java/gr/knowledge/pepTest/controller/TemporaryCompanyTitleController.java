package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitleService;

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
 * REST controller for managing TemporaryCompanyTitle resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "TemporaryCompanyTitle", description = "TemporaryCompanyTitle API")
@RequestMapping("/api/temporary-company-titles")
public class TemporaryCompanyTitleController {

    private final TemporaryCompanyTitleService temporaryCompanyTitleService;

    /**
     * Retrieves all records.
     *
     * @return list of TemporaryCompanyTitleDto
     */
    @Operation(summary = "Get all TemporaryCompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyTitleDto>> getAll() {
        log.info("Fetching all temporarycompanytitle records.");
        return ResponseEntity.ok(temporaryCompanyTitleService.getAllTemporaryCompanyTitle());
    }

    /**
     * Retrieves a record by id.
     *
     * @return TemporaryCompanyTitleDto
     */
    @Operation(summary = "Get TemporaryCompanyTitle by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitleDto> getById(
            @Parameter(description = "TemporaryCompanyTitle id", required = true)
            @PathVariable Long id) {
        log.info("Fetching temporarycompanytitle with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyTitleService.getTemporaryCompanyTitleById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TemporaryCompanyTitleDto
     */
    @Operation(summary = "Create TemporaryCompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyTitleDto> create(@RequestBody TemporaryCompanyTitleDto dto) {
        log.info("Creating temporarycompanytitle.");
        TemporaryCompanyTitleDto created = temporaryCompanyTitleService.createTemporaryCompanyTitle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated TemporaryCompanyTitleDto
     */
    @Operation(summary = "Update TemporaryCompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitleDto> update(
            @Parameter(description = "TemporaryCompanyTitle id", required = true)
            @PathVariable Long id,
            @RequestBody TemporaryCompanyTitleDto dto) {
        log.info("Updating temporarycompanytitle with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyTitleService.updateTemporaryCompanyTitle(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete TemporaryCompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "TemporaryCompanyTitle id", required = true)
            @PathVariable Long id) {
        log.info("Deleting temporarycompanytitle with id: {}", id);
        temporaryCompanyTitleService.deleteTemporaryCompanyTitle(id);
        return ResponseEntity.noContent().build();
    }
}
