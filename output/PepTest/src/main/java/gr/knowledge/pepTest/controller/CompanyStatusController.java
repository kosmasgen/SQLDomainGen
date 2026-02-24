package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.service.CompanyStatusService;

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
 * REST controller for managing CompanyStatus resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyStatus", description = "CompanyStatus API")
@RequestMapping("/api/company-statuss")
public class CompanyStatusController {

    private final CompanyStatusService companyStatusService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyStatusDto
     */
    @Operation(summary = "Get all CompanyStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyStatusDto>> getAll() {
        log.info("Fetching all companystatus records.");
        return ResponseEntity.ok(companyStatusService.getAllCompanyStatus());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyStatusDto
     */
    @Operation(summary = "Get CompanyStatus by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyStatusDto> getById(
            @Parameter(description = "CompanyStatus id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companystatus with id: {}", id);
        return ResponseEntity.ok(companyStatusService.getCompanyStatusById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyStatusDto
     */
    @Operation(summary = "Create CompanyStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusDto> create(@RequestBody CompanyStatusDto dto) {
        log.info("Creating companystatus.");
        CompanyStatusDto created = companyStatusService.createCompanyStatus(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyStatusDto
     */
    @Operation(summary = "Update CompanyStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyStatusDto> update(
            @Parameter(description = "CompanyStatus id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyStatusDto dto) {
        log.info("Updating companystatus with id: {}", id);
        return ResponseEntity.ok(companyStatusService.updateCompanyStatus(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyStatus")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyStatus id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companystatus with id: {}", id);
        companyStatusService.deleteCompanyStatus(id);
        return ResponseEntity.noContent().build();
    }
}
