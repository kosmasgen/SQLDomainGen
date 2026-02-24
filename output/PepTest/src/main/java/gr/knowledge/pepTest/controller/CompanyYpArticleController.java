package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.service.CompanyYpArticleService;

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
 * REST controller for managing CompanyYpArticle resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyYpArticle", description = "CompanyYpArticle API")
@RequestMapping("/api/company-yp-articles")
public class CompanyYpArticleController {

    private final CompanyYpArticleService companyYpArticleService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyYpArticleDto
     */
    @Operation(summary = "Get all CompanyYpArticle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyYpArticleDto>> getAll() {
        log.info("Fetching all companyyparticle records.");
        return ResponseEntity.ok(companyYpArticleService.getAllCompanyYpArticle());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyYpArticleDto
     */
    @Operation(summary = "Get CompanyYpArticle by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpArticleDto> getById(
            @Parameter(description = "CompanyYpArticle id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyyparticle with id: {}", id);
        return ResponseEntity.ok(companyYpArticleService.getCompanyYpArticleById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyYpArticleDto
     */
    @Operation(summary = "Create CompanyYpArticle")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpArticleDto> create(@RequestBody CompanyYpArticleDto dto) {
        log.info("Creating companyyparticle.");
        CompanyYpArticleDto created = companyYpArticleService.createCompanyYpArticle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyYpArticleDto
     */
    @Operation(summary = "Update CompanyYpArticle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyYpArticleDto> update(
            @Parameter(description = "CompanyYpArticle id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyYpArticleDto dto) {
        log.info("Updating companyyparticle with id: {}", id);
        return ResponseEntity.ok(companyYpArticleService.updateCompanyYpArticle(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyYpArticle")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyYpArticle id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyyparticle with id: {}", id);
        companyYpArticleService.deleteCompanyYpArticle(id);
        return ResponseEntity.noContent().build();
    }
}
