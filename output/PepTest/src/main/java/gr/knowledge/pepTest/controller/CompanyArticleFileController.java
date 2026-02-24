package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.service.CompanyArticleFileService;

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
 * REST controller for managing CompanyArticleFile resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyArticleFile", description = "CompanyArticleFile API")
@RequestMapping("/api/company-article-files")
public class CompanyArticleFileController {

    private final CompanyArticleFileService companyArticleFileService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyArticleFileDto
     */
    @Operation(summary = "Get all CompanyArticleFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyArticleFileDto>> getAll() {
        log.info("Fetching all companyarticlefile records.");
        return ResponseEntity.ok(companyArticleFileService.getAllCompanyArticleFile());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyArticleFileDto
     */
    @Operation(summary = "Get CompanyArticleFile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyArticleFileDto> getById(
            @Parameter(description = "CompanyArticleFile id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyarticlefile with id: {}", id);
        return ResponseEntity.ok(companyArticleFileService.getCompanyArticleFileById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyArticleFileDto
     */
    @Operation(summary = "Create CompanyArticleFile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyArticleFileDto> create(@RequestBody CompanyArticleFileDto dto) {
        log.info("Creating companyarticlefile.");
        CompanyArticleFileDto created = companyArticleFileService.createCompanyArticleFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyArticleFileDto
     */
    @Operation(summary = "Update CompanyArticleFile")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyArticleFileDto> update(
            @Parameter(description = "CompanyArticleFile id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyArticleFileDto dto) {
        log.info("Updating companyarticlefile with id: {}", id);
        return ResponseEntity.ok(companyArticleFileService.updateCompanyArticleFile(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyArticleFile")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyArticleFile id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyarticlefile with id: {}", id);
        companyArticleFileService.deleteCompanyArticleFile(id);
        return ResponseEntity.noContent().build();
    }
}
