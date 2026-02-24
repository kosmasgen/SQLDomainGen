package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.service.CompanyProfileService;

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
 * REST controller for managing CompanyProfile resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyProfile", description = "CompanyProfile API")
@RequestMapping("/api/company-profiles")
public class CompanyProfileController {

    private final CompanyProfileService companyProfileService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyProfileDto
     */
    @Operation(summary = "Get all CompanyProfile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyProfileDto>> getAll() {
        log.info("Fetching all companyprofile records.");
        return ResponseEntity.ok(companyProfileService.getAllCompanyProfile());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyProfileDto
     */
    @Operation(summary = "Get CompanyProfile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfileDto> getById(
            @Parameter(description = "CompanyProfile id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyprofile with id: {}", id);
        return ResponseEntity.ok(companyProfileService.getCompanyProfileById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyProfileDto
     */
    @Operation(summary = "Create CompanyProfile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfileDto> create(@RequestBody CompanyProfileDto dto) {
        log.info("Creating companyprofile.");
        CompanyProfileDto created = companyProfileService.createCompanyProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyProfileDto
     */
    @Operation(summary = "Update CompanyProfile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyProfileDto> update(
            @Parameter(description = "CompanyProfile id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyProfileDto dto) {
        log.info("Updating companyprofile with id: {}", id);
        return ResponseEntity.ok(companyProfileService.updateCompanyProfile(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyProfile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyProfile id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyprofile with id: {}", id);
        companyProfileService.deleteCompanyProfile(id);
        return ResponseEntity.noContent().build();
    }
}
