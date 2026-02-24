package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
import gr.knowledge.pepTest.entity.CorporateStatusViewRulesPK;

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
 * REST controller for managing CorporateStatusViewRules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CorporateStatusViewRules", description = "CorporateStatusViewRules API")
@RequestMapping("/api/corporate-status-view-ruless")
public class CorporateStatusViewRulesController {

    private final CorporateStatusViewRulesService corporateStatusViewRulesService;

    /**
     * Retrieves all records.
     *
     * @return list of CorporateStatusViewRulesDto
     */
    @Operation(summary = "Get all CorporateStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CorporateStatusViewRulesDto>> getAll() {
        log.info("Fetching all corporatestatusviewrules records.");
        return ResponseEntity.ok(corporateStatusViewRulesService.getAllCorporateStatusViewRules());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CorporateStatusViewRulesDto
     */
    @Operation(summary = "Get CorporateStatusViewRules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<CorporateStatusViewRulesDto> getById(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId) {
        CorporateStatusViewRulesPK id = buildCorporateStatusViewRulesId(corporateStatusId, companyViewRulesId);
        log.info("Fetching corporatestatusviewrules with composite id: {}", id);
        return ResponseEntity.ok(corporateStatusViewRulesService.getCorporateStatusViewRulesById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CorporateStatusViewRulesDto
     */
    @Operation(summary = "Create CorporateStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusViewRulesDto> create(@RequestBody CorporateStatusViewRulesDto dto) {
        log.info("Creating corporatestatusviewrules.");
        CorporateStatusViewRulesDto created = corporateStatusViewRulesService.createCorporateStatusViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CorporateStatusViewRulesDto
     */
    @Operation(summary = "Update CorporateStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<CorporateStatusViewRulesDto> update(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId,
            @RequestBody CorporateStatusViewRulesDto dto) {
        CorporateStatusViewRulesPK id = buildCorporateStatusViewRulesId(corporateStatusId, companyViewRulesId);
        log.info("Updating corporatestatusviewrules with composite id: {}", id);
        return ResponseEntity.ok(corporateStatusViewRulesService.updateCorporateStatusViewRules(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CorporateStatusViewRules")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "corporate_status_id", required = true)
            @RequestParam(name = "corporate_status_id") UUID corporateStatusId,
            @Parameter(description = "company_view_rules_id", required = true)
            @RequestParam(name = "company_view_rules_id") UUID companyViewRulesId) {
        CorporateStatusViewRulesPK id = buildCorporateStatusViewRulesId(corporateStatusId, companyViewRulesId);
        log.info("Deleting corporatestatusviewrules with composite id: {}", id);
        corporateStatusViewRulesService.deleteCorporateStatusViewRules(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for CorporateStatusViewRules.
     */
    private CorporateStatusViewRulesPK buildCorporateStatusViewRulesId(UUID corporateStatusId, UUID companyViewRulesId) {
        CorporateStatusViewRulesPK id = new CorporateStatusViewRulesPK();
        id.setCorporateStatusId(corporateStatusId);
        id.setCompanyViewRulesId(companyViewRulesId);
        return id;
    }
}
