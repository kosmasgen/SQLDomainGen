package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.service.LanguagesService;

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
 * REST controller for managing Languages resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Languages", description = "Languages API")
@RequestMapping("/api/languagess")
public class LanguagesController {

    private final LanguagesService languagesService;

    /**
     * Retrieves all records.
     *
     * @return list of LanguagesDto
     */
    @Operation(summary = "Get all Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<LanguagesDto>> getAll() {
        log.info("Fetching all languages records.");
        return ResponseEntity.ok(languagesService.getAllLanguages());
    }

    /**
     * Retrieves a record by id.
     *
     * @return LanguagesDto
     */
    @Operation(summary = "Get Languages by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LanguagesDto> getById(
            @Parameter(description = "Languages id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching languages with id: {}", id);
        return ResponseEntity.ok(languagesService.getLanguagesById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created LanguagesDto
     */
    @Operation(summary = "Create Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<LanguagesDto> create(@RequestBody LanguagesDto dto) {
        log.info("Creating languages.");
        LanguagesDto created = languagesService.createLanguages(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated LanguagesDto
     */
    @Operation(summary = "Update Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LanguagesDto> update(
            @Parameter(description = "Languages id", required = true)
            @PathVariable UUID id,
            @RequestBody LanguagesDto dto) {
        log.info("Updating languages with id: {}", id);
        return ResponseEntity.ok(languagesService.updateLanguages(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Languages id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting languages with id: {}", id);
        languagesService.deleteLanguages(id);
        return ResponseEntity.noContent().build();
    }
}
