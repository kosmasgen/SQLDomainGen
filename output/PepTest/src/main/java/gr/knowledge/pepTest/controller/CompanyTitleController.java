package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.service.CompanyTitleService;

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
 * REST controller for managing CompanyTitle resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyTitle", description = "CompanyTitle API")
@RequestMapping("/api/company-titles")
public class CompanyTitleController {

    private final CompanyTitleService companyTitleService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyTitleDto
     */
    @Operation(summary = "Get all CompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyTitleDto>> getAll() {
        log.info("Fetching all companytitle records.");
        return ResponseEntity.ok(companyTitleService.getAllCompanyTitle());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyTitleDto
     */
    @Operation(summary = "Get CompanyTitle by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyTitleDto> getById(
            @Parameter(description = "CompanyTitle id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companytitle with id: {}", id);
        return ResponseEntity.ok(companyTitleService.getCompanyTitleById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyTitleDto
     */
    @Operation(summary = "Create CompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyTitleDto> create(@RequestBody CompanyTitleDto dto) {
        log.info("Creating companytitle.");
        CompanyTitleDto created = companyTitleService.createCompanyTitle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyTitleDto
     */
    @Operation(summary = "Update CompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyTitleDto> update(
            @Parameter(description = "CompanyTitle id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyTitleDto dto) {
        log.info("Updating companytitle with id: {}", id);
        return ResponseEntity.ok(companyTitleService.updateCompanyTitle(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyTitle")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyTitle id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companytitle with id: {}", id);
        companyTitleService.deleteCompanyTitle(id);
        return ResponseEntity.noContent().build();
    }
}
