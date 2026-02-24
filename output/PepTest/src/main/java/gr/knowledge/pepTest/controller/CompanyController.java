package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.service.CompanyService;

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
 * REST controller for managing Company resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company", description = "Company API")
@RequestMapping("/api/companys")
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyDto
     */
    @Operation(summary = "Get all Company")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll() {
        log.info("Fetching all company records.");
        return ResponseEntity.ok(companyService.getAllCompany());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyDto
     */
    @Operation(summary = "Get Company by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(
            @Parameter(description = "Company id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching company with id: {}", id);
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyDto
     */
    @Operation(summary = "Create Company")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyDto> create(@RequestBody CompanyDto dto) {
        log.info("Creating company.");
        CompanyDto created = companyService.createCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyDto
     */
    @Operation(summary = "Update Company")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(
            @Parameter(description = "Company id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyDto dto) {
        log.info("Updating company with id: {}", id);
        return ResponseEntity.ok(companyService.updateCompany(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Company")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Company id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting company with id: {}", id);
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
