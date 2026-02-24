package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;

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
 * REST controller for managing TemporaryCompanyProfession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "TemporaryCompanyProfession", description = "TemporaryCompanyProfession API")
@RequestMapping("/api/temporary-company-professions")
public class TemporaryCompanyProfessionController {

    private final TemporaryCompanyProfessionService temporaryCompanyProfessionService;

    /**
     * Retrieves all records.
     *
     * @return list of TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Get all TemporaryCompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyProfessionDto>> getAll() {
        log.info("Fetching all temporarycompanyprofession records.");
        return ResponseEntity.ok(temporaryCompanyProfessionService.getAllTemporaryCompanyProfession());
    }

    /**
     * Retrieves a record by id.
     *
     * @return TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Get TemporaryCompanyProfession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyProfessionDto> getById(
            @Parameter(description = "TemporaryCompanyProfession id", required = true)
            @PathVariable Long id) {
        log.info("Fetching temporarycompanyprofession with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Create TemporaryCompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyProfessionDto> create(@RequestBody TemporaryCompanyProfessionDto dto) {
        log.info("Creating temporarycompanyprofession.");
        TemporaryCompanyProfessionDto created = temporaryCompanyProfessionService.createTemporaryCompanyProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Update TemporaryCompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryCompanyProfessionDto> update(
            @Parameter(description = "TemporaryCompanyProfession id", required = true)
            @PathVariable Long id,
            @RequestBody TemporaryCompanyProfessionDto dto) {
        log.info("Updating temporarycompanyprofession with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyProfessionService.updateTemporaryCompanyProfession(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete TemporaryCompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "TemporaryCompanyProfession id", required = true)
            @PathVariable Long id) {
        log.info("Deleting temporarycompanyprofession with id: {}", id);
        temporaryCompanyProfessionService.deleteTemporaryCompanyProfession(id);
        return ResponseEntity.noContent().build();
    }
}
