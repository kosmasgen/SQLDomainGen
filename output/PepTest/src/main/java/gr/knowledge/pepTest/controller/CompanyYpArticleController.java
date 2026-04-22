package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.service.CompanyYpArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Company Yp Article resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Yp Article", description = "Company Yp Article API")
@RequestMapping("/api/company-yp-article")
public class CompanyYpArticleController {

    private final CompanyYpArticleService companyYpArticleService;

    /**
     * Retrieves all company yp articles.
     * @return list of CompanyYpArticleDto
     */
    @Operation(summary = "Get all company yp articles")
    @GetMapping
    public ResponseEntity<List<CompanyYpArticleDto>> getAll() {
        return ResponseEntity.ok(companyYpArticleService.getAllCompanyYpArticles());
    }

    /**
     * Retrieves the company yp article record by id.
     * @param id company yp article identifier
     * @return CompanyYpArticleDto
     */
    @Operation(summary = "Get Company Yp Article by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpArticleDto> getById(
            @Parameter(description = "company yp article identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyYpArticleService.getCompanyYpArticleById(id));
    }

    /**
     * Creates a new company yp article record.
     * @param dto company yp article payload
     * @return created CompanyYpArticleDto
     */
    @Operation(summary = "Create Company Yp Article")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpArticleDto> create(
            @Valid @RequestBody CompanyYpArticleDto dto) {
        CompanyYpArticleDto created = companyYpArticleService.createCompanyYpArticle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company yp article record.
     * Only fields that are not null in the request are updated.
     * @param id company yp article identifier
     * @param dto partial company yp article payload
     * @return updated CompanyYpArticleDto
     */
    @Operation(summary = "Patch Company Yp Article")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyYpArticleDto> patch(
            @Parameter(description = "company yp article identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CompanyYpArticleDto dto) {
        return ResponseEntity.ok(companyYpArticleService.updateCompanyYpArticle(id, dto));
    }

    /**
     * Delete an company yp article record by id.
     * @param id company yp article identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Yp Article")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company yp article identifier", required = true)
            @PathVariable UUID id) {
        companyYpArticleService.deleteCompanyYpArticle(id);
        return ResponseEntity.noContent().build();
    }

}
