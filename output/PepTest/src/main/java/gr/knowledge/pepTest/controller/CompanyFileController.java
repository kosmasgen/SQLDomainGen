package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.service.CompanyFileService;

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
 * REST controller for managing CompanyFile resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyFile", description = "CompanyFile API")
@RequestMapping("/api/company-files")
public class CompanyFileController {

    private final CompanyFileService companyFileService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyFileDto
     */
    @Operation(summary = "Get all CompanyFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyFileDto>> getAll() {
        log.info("Fetching all companyfile records.");
        return ResponseEntity.ok(companyFileService.getAllCompanyFile());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyFileDto
     */
    @Operation(summary = "Get CompanyFile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyFileDto> getById(
            @Parameter(description = "CompanyFile id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyfile with id: {}", id);
        return ResponseEntity.ok(companyFileService.getCompanyFileById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyFileDto
     */
    @Operation(summary = "Create CompanyFile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyFileDto> create(@RequestBody CompanyFileDto dto) {
        log.info("Creating companyfile.");
        CompanyFileDto created = companyFileService.createCompanyFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyFileDto
     */
    @Operation(summary = "Update CompanyFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyFileDto> update(
            @Parameter(description = "CompanyFile id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyFileDto dto) {
        log.info("Updating companyfile with id: {}", id);
        return ResponseEntity.ok(companyFileService.updateCompanyFile(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyFile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyFile id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyfile with id: {}", id);
        companyFileService.deleteCompanyFile(id);
        return ResponseEntity.noContent().build();
    }
}
