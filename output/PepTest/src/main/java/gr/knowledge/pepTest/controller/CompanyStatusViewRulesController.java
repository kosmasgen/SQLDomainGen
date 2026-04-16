package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
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
 * REST controller for managing Company Status View Rules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Status View Rules", description = "Company Status View Rules API")
@RequestMapping("/api/company-status-view-rules")
public class CompanyStatusViewRulesController {

    private final CompanyStatusViewRulesService companyStatusViewRulesService;

    /**
     * Retrieves all company status view ruleses.
     * @return list of CompanyStatusViewRulesDto
     */
    @Operation(summary = "Get all company status view ruleses")
    @GetMapping
    public ResponseEntity<List<CompanyStatusViewRulesDto>> getAll() {
        return ResponseEntity.ok(companyStatusViewRulesService.getAllCompanyStatusViewRuleses());
    }

    /**
     * Retrieves the company status view rules record by id.
     * @param companyStatusId company status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @return CompanyStatusViewRulesDto
     */
    @Operation(summary = "Get Company Status View Rules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{companyStatusId}/{companyViewRulesId}")
    public ResponseEntity<CompanyStatusViewRulesDto> getById(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId) {
        return ResponseEntity.ok(companyStatusViewRulesService.getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId));
    }

    /**
     * Creates a new company status view rules record.
     * @param dto company status view rules payload
     * @return created CompanyStatusViewRulesDto
     */
    @Operation(summary = "Create Company Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusViewRulesDto> create(
            @Valid @RequestBody CompanyStatusViewRulesDto dto) {
        CompanyStatusViewRulesDto created = companyStatusViewRulesService.createCompanyStatusViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company status view rules record.
     * Only fields that are not null in the request are updated.
     * @param companyStatusId company status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @param dto partial company status view rules payload
     * @return updated CompanyStatusViewRulesDto
     */
    @Operation(summary = "Patch Company Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{companyStatusId}/{companyViewRulesId}")
    public ResponseEntity<CompanyStatusViewRulesDto> patch(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId,
            @RequestBody CompanyStatusViewRulesDto dto) {
        return ResponseEntity.ok(companyStatusViewRulesService.updateCompanyStatusViewRules(companyStatusId, companyViewRulesId, dto));
    }

    /**
     * Delete an company status view rules record by id.
     * @param companyStatusId company status id identifier
     * @param companyViewRulesId company view rules id identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Status View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{companyStatusId}/{companyViewRulesId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company status id identifier", required = true)
            @PathVariable UUID companyStatusId,
            @Parameter(description = "company view rules id identifier", required = true)
            @PathVariable UUID companyViewRulesId) {
        companyStatusViewRulesService.deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId);
        return ResponseEntity.noContent().build();
    }

}
