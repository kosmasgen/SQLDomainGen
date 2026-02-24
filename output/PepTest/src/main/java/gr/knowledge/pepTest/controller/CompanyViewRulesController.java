package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.service.CompanyViewRulesService;

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
 * REST controller for managing CompanyViewRules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyViewRules", description = "CompanyViewRules API")
@RequestMapping("/api/company-view-ruless")
public class CompanyViewRulesController {

    private final CompanyViewRulesService companyViewRulesService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyViewRulesDto
     */
    @Operation(summary = "Get all CompanyViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyViewRulesDto>> getAll() {
        log.info("Fetching all companyviewrules records.");
        return ResponseEntity.ok(companyViewRulesService.getAllCompanyViewRules());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyViewRulesDto
     */
    @Operation(summary = "Get CompanyViewRules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyViewRulesDto> getById(
            @Parameter(description = "CompanyViewRules id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companyviewrules with id: {}", id);
        return ResponseEntity.ok(companyViewRulesService.getCompanyViewRulesById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyViewRulesDto
     */
    @Operation(summary = "Create CompanyViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyViewRulesDto> create(@RequestBody CompanyViewRulesDto dto) {
        log.info("Creating companyviewrules.");
        CompanyViewRulesDto created = companyViewRulesService.createCompanyViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyViewRulesDto
     */
    @Operation(summary = "Update CompanyViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyViewRulesDto> update(
            @Parameter(description = "CompanyViewRules id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyViewRulesDto dto) {
        log.info("Updating companyviewrules with id: {}", id);
        return ResponseEntity.ok(companyViewRulesService.updateCompanyViewRules(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyViewRules id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companyviewrules with id: {}", id);
        companyViewRulesService.deleteCompanyViewRules(id);
        return ResponseEntity.noContent().build();
    }
}
