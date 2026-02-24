package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.service.CompanyProfessionService;

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
 * REST controller for managing CompanyProfession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyProfession", description = "CompanyProfession API")
@RequestMapping("/api/company-professions")
public class CompanyProfessionController {

    private final CompanyProfessionService companyProfessionService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyProfessionDto
     */
    @Operation(summary = "Get all CompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyProfessionDto>> getAll() {
        log.info("Fetching all companyprofession records.");
        return ResponseEntity.ok(companyProfessionService.getAllCompanyProfession());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyProfessionDto
     */
    @Operation(summary = "Get CompanyProfession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfessionDto> getById(
            @Parameter(description = "CompanyProfession id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyprofession with id: {}", id);
        return ResponseEntity.ok(companyProfessionService.getCompanyProfessionById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyProfessionDto
     */
    @Operation(summary = "Create CompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfessionDto> create(@RequestBody CompanyProfessionDto dto) {
        log.info("Creating companyprofession.");
        CompanyProfessionDto created = companyProfessionService.createCompanyProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyProfessionDto
     */
    @Operation(summary = "Update CompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyProfessionDto> update(
            @Parameter(description = "CompanyProfession id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyProfessionDto dto) {
        log.info("Updating companyprofession with id: {}", id);
        return ResponseEntity.ok(companyProfessionService.updateCompanyProfession(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyProfession")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyProfession id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyprofession with id: {}", id);
        companyProfessionService.deleteCompanyProfession(id);
        return ResponseEntity.noContent().build();
    }
}
