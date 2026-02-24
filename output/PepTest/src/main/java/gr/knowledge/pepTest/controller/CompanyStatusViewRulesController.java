package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
import gr.knowledge.pepTest.entity.CompanyStatusViewRulesPK;

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
 * REST controller for managing CompanyStatusViewRules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyStatusViewRules", description = "CompanyStatusViewRules API")
@RequestMapping("/api/company-status-view-ruless")
public class CompanyStatusViewRulesController {

    private final CompanyStatusViewRulesService companyStatusViewRulesService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyStatusViewRulesDto
     */
    @Operation(summary = "Get all CompanyStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyStatusViewRulesDto>> getAll() {
        log.info("Fetching all companystatusviewrules records.");
        return ResponseEntity.ok(companyStatusViewRulesService.getAllCompanyStatusViewRules());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyStatusViewRulesDto
     */
    @Operation(summary = "Get CompanyStatusViewRules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CompanyStatusViewRulesDto> getById(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId) {
        CompanyStatusViewRulesPK id = buildCompanyStatusViewRulesId(companyStatusId, companyViewRulesId);
        log.info("Fetching companystatusviewrules with composite id: {}", id);
        return ResponseEntity.ok(companyStatusViewRulesService.getCompanyStatusViewRulesById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyStatusViewRulesDto
     */
    @Operation(summary = "Create CompanyStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusViewRulesDto> create(@RequestBody CompanyStatusViewRulesDto dto) {
        log.info("Creating companystatusviewrules.");
        CompanyStatusViewRulesDto created = companyStatusViewRulesService.createCompanyStatusViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyStatusViewRulesDto
     */
    @Operation(summary = "Update CompanyStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CompanyStatusViewRulesDto> update(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId,
            @RequestBody CompanyStatusViewRulesDto dto) {
        CompanyStatusViewRulesPK id = buildCompanyStatusViewRulesId(companyStatusId, companyViewRulesId);
        log.info("Updating companystatusviewrules with composite id: {}", id);
        return ResponseEntity.ok(companyStatusViewRulesService.updateCompanyStatusViewRules(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company_status_id", required = true)
            @RequestParam(name = "company_status_id") UUID companyStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId) {
        CompanyStatusViewRulesPK id = buildCompanyStatusViewRulesId(companyStatusId, companyViewRulesId);
        log.info("Deleting companystatusviewrules with composite id: {}", id);
        companyStatusViewRulesService.deleteCompanyStatusViewRules(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CompanyStatusViewRules.
     */
    private CompanyStatusViewRulesPK buildCompanyStatusViewRulesId(UUID companyStatusId, UUID companyViewRulesId) {
        CompanyStatusViewRulesPK id = new CompanyStatusViewRulesPK();
        id.setCompanyStatusId(companyStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);
        return id;
    }
}
