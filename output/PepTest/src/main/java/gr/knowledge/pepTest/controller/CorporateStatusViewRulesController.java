package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
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
 * REST controller for managing Corporate Status View Rules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Corporate Status View Rules", description = "Corporate Status View Rules API")
@RequestMapping("/api/corporate-status-view-rules")
public class CorporateStatusViewRulesController {

    private final CorporateStatusViewRulesService corporateStatusViewRulesService;

    /**
     * Retrieves all corporate status view ruleses.
     * @return list of CorporateStatusViewRulesDto
     */
    @Operation(summary = "Get all corporate status view ruleses")
    @GetMapping
    public ResponseEntity<List<CorporateStatusViewRulesDto>> getAll() {
        return ResponseEntity.ok(corporateStatusViewRulesService.getAllCorporateStatusViewRuleses());
    }

    /**
     * Retrieves the corporate status view rules record by id.
     * @param corporateStatusId corporate status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @return CorporateStatusViewRulesDto
     */
    @Operation(summary = "Get Corporate Status View Rules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{corporateStatusId}/{companyViewRulesId}")
    public ResponseEntity<CorporateStatusViewRulesDto> getById(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId) {
        return ResponseEntity.ok(corporateStatusViewRulesService.getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId));
    }

    /**
     * Creates a new corporate status view rules record.
     * @param dto corporate status view rules payload
     * @return created CorporateStatusViewRulesDto
     */
    @Operation(summary = "Create Corporate Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusViewRulesDto> create(
            @Valid @RequestBody CorporateStatusViewRulesDto dto) {
        CorporateStatusViewRulesDto created = corporateStatusViewRulesService.createCorporateStatusViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing corporate status view rules record.
     * Only fields that are not null in the request are updated.
     * @param corporateStatusId corporate status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @param dto partial corporate status view rules payload
     * @return updated CorporateStatusViewRulesDto
     */
    @Operation(summary = "Patch Corporate Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{corporateStatusId}/{companyViewRulesId}")
    public ResponseEntity<CorporateStatusViewRulesDto> patch(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId,
            @RequestBody CorporateStatusViewRulesDto dto) {
        return ResponseEntity.ok(corporateStatusViewRulesService.updateCorporateStatusViewRules(corporateStatusId, companyViewRulesId, dto));
    }

    /**
     * Delete an corporate status view rules record by id.
     * @param corporateStatusId corporate status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @return no content
     */
    @Operation(summary = "Delete Corporate Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{corporateStatusId}/{companyViewRulesId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "corporate status id identifier", required = true)
            @PathVariable UUID corporateStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId) {
        corporateStatusViewRulesService.deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId);
        return ResponseEntity.noContent().build();
    }

}
