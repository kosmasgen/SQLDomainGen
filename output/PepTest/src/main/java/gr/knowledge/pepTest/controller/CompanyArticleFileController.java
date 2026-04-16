package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.service.CompanyArticleFileService;
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
 * REST controller for managing Company Article File resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Article File", description = "Company Article File API")
@RequestMapping("/api/company-article-file")
public class CompanyArticleFileController {

    private final CompanyArticleFileService companyArticleFileService;

    /**
     * Retrieves all company article files.
     * @return list of CompanyArticleFileDto
     */
    @Operation(summary = "Get all company article files")
    @GetMapping
    public ResponseEntity<List<CompanyArticleFileDto>> getAll() {
        return ResponseEntity.ok(companyArticleFileService.getAllCompanyArticleFiles());
    }

    /**
     * Retrieves the company article file record by id.
     * @param id company article file identifier
     * @return CompanyArticleFileDto
     */
    @Operation(summary = "Get Company Article File by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyArticleFileDto> getById(
            @Parameter(description = "company article file identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyArticleFileService.getCompanyArticleFileById(id));
    }

    /**
     * Creates a new company article file record.
     * @param dto company article file payload
     * @return created CompanyArticleFileDto
     */
    @Operation(summary = "Create Company Article File")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyArticleFileDto> create(
            @Valid @RequestBody CompanyArticleFileDto dto) {
        CompanyArticleFileDto created = companyArticleFileService.createCompanyArticleFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company article file record.
     * Only fields that are not null in the request are updated.
     * @param id company article file identifier
     * @param dto partial company article file payload
     * @return updated CompanyArticleFileDto
     */
    @Operation(summary = "Patch Company Article File")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyArticleFileDto> patch(
            @Parameter(description = "company article file identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyArticleFileDto dto) {
        return ResponseEntity.ok(companyArticleFileService.updateCompanyArticleFile(id, dto));
    }

    /**
     * Delete an company article file record by id.
     * @param id company article file identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Article File")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company article file identifier", required = true)
            @PathVariable UUID id) {
        companyArticleFileService.deleteCompanyArticleFile(id);
        return ResponseEntity.noContent().build();
    }

}
