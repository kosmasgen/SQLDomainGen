package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.service.CompanyTitleService;
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
 * REST controller for managing Company Title resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Title", description = "Company Title API")
@RequestMapping("/api/company-title")
public class CompanyTitleController {

    private final CompanyTitleService companyTitleService;

    /**
     * Retrieves all company titles.
     * @return list of CompanyTitleDto
     */
    @Operation(summary = "Get all company titles")
    @GetMapping
    public ResponseEntity<List<CompanyTitleDto>> getAll() {
        return ResponseEntity.ok(companyTitleService.getAllCompanyTitles());
    }

    /**
     * Retrieves the company title record by id.
     * @param id company title identifier
     * @return CompanyTitleDto
     */
    @Operation(summary = "Get Company Title by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyTitleDto> getById(
            @Parameter(description = "company title identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyTitleService.getCompanyTitleById(id));
    }

    /**
     * Creates a new company title record.
     * @param dto company title payload
     * @return created CompanyTitleDto
     */
    @Operation(summary = "Create Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyTitleDto> create(
            @Valid @RequestBody CompanyTitleDto dto) {
        CompanyTitleDto created = companyTitleService.createCompanyTitle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company title record.
     * Only fields that are not null in the request are updated.
     * @param id company title identifier
     * @param dto partial company title payload
     * @return updated CompanyTitleDto
     */
    @Operation(summary = "Patch Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyTitleDto> patch(
            @Parameter(description = "company title identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CompanyTitleDto dto) {
        return ResponseEntity.ok(companyTitleService.updateCompanyTitle(id, dto));
    }

    /**
     * Delete an company title record by id.
     * @param id company title identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company title identifier", required = true)
            @PathVariable UUID id) {
        companyTitleService.deleteCompanyTitle(id);
        return ResponseEntity.noContent().build();
    }

}
