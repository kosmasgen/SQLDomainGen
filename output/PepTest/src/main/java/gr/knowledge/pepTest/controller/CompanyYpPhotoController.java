package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.service.CompanyYpPhotoService;

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
 * REST controller for managing CompanyYpPhoto resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyYpPhoto", description = "CompanyYpPhoto API")
@RequestMapping("/api/company-yp-photos")
public class CompanyYpPhotoController {

    private final CompanyYpPhotoService companyYpPhotoService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyYpPhotoDto
     */
    @Operation(summary = "Get all CompanyYpPhoto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyYpPhotoDto>> getAll() {
        log.info("Fetching all companyypphoto records.");
        return ResponseEntity.ok(companyYpPhotoService.getAllCompanyYpPhoto());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyYpPhotoDto
     */
    @Operation(summary = "Get CompanyYpPhoto by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpPhotoDto> getById(
            @Parameter(description = "CompanyYpPhoto id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyypphoto with id: {}", id);
        return ResponseEntity.ok(companyYpPhotoService.getCompanyYpPhotoById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyYpPhotoDto
     */
    @Operation(summary = "Create CompanyYpPhoto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpPhotoDto> create(@RequestBody CompanyYpPhotoDto dto) {
        log.info("Creating companyypphoto.");
        CompanyYpPhotoDto created = companyYpPhotoService.createCompanyYpPhoto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyYpPhotoDto
     */
    @Operation(summary = "Update CompanyYpPhoto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyYpPhotoDto> update(
            @Parameter(description = "CompanyYpPhoto id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyYpPhotoDto dto) {
        log.info("Updating companyypphoto with id: {}", id);
        return ResponseEntity.ok(companyYpPhotoService.updateCompanyYpPhoto(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyYpPhoto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyYpPhoto id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyypphoto with id: {}", id);
        companyYpPhotoService.deleteCompanyYpPhoto(id);
        return ResponseEntity.noContent().build();
    }
}
