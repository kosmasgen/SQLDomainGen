package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.service.CompanyYpFileService;

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
 * REST controller for managing CompanyYpFile resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyYpFile", description = "CompanyYpFile API")
@RequestMapping("/api/company-yp-files")
public class CompanyYpFileController {

    private final CompanyYpFileService companyYpFileService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyYpFileDto
     */
    @Operation(summary = "Get all CompanyYpFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyYpFileDto>> getAll() {
        log.info("Fetching all companyypfile records.");
        return ResponseEntity.ok(companyYpFileService.getAllCompanyYpFile());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyYpFileDto
     */
    @Operation(summary = "Get CompanyYpFile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpFileDto> getById(
            @Parameter(description = "CompanyYpFile id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyypfile with id: {}", id);
        return ResponseEntity.ok(companyYpFileService.getCompanyYpFileById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyYpFileDto
     */
    @Operation(summary = "Create CompanyYpFile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpFileDto> create(@RequestBody CompanyYpFileDto dto) {
        log.info("Creating companyypfile.");
        CompanyYpFileDto created = companyYpFileService.createCompanyYpFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyYpFileDto
     */
    @Operation(summary = "Update CompanyYpFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyYpFileDto> update(
            @Parameter(description = "CompanyYpFile id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyYpFileDto dto) {
        log.info("Updating companyypfile with id: {}", id);
        return ResponseEntity.ok(companyYpFileService.updateCompanyYpFile(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyYpFile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyYpFile id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyypfile with id: {}", id);
        companyYpFileService.deleteCompanyYpFile(id);
        return ResponseEntity.noContent().build();
    }
}
