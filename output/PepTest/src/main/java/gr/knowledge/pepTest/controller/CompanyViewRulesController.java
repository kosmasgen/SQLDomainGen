package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.service.CompanyViewRulesService;
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
 * REST controller for managing Company View Rules resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company View Rules", description = "Company View Rules API")
@RequestMapping("/api/company-view-rules")
public class CompanyViewRulesController {

    private final CompanyViewRulesService companyViewRulesService;

    /**
     * Retrieves all company view ruleses.
     * @return list of CompanyViewRulesDto
     */
    @Operation(summary = "Get all company view ruleses")
    @GetMapping
    public ResponseEntity<List<CompanyViewRulesDto>> getAll() {
        return ResponseEntity.ok(companyViewRulesService.getAllCompanyViewRuleses());
    }

    /**
     * Retrieves the company view rules record by id.
     * @param id company view rules identifier
     * @return CompanyViewRulesDto
     */
    @Operation(summary = "Get Company View Rules by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyViewRulesDto> getById(
            @Parameter(description = "company view rules identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyViewRulesService.getCompanyViewRulesById(id));
    }

    /**
     * Creates a new company view rules record.
     * @param dto company view rules payload
     * @return created CompanyViewRulesDto
     */
    @Operation(summary = "Create Company View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyViewRulesDto> create(
            @Valid @RequestBody CompanyViewRulesDto dto) {
        CompanyViewRulesDto created = companyViewRulesService.createCompanyViewRules(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company view rules record.
     * Only fields that are not null in the request are updated.
     * @param id company view rules identifier
     * @param dto partial company view rules payload
     * @return updated CompanyViewRulesDto
     */
    @Operation(summary = "Patch Company View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyViewRulesDto> patch(
            @Parameter(description = "company view rules identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyViewRulesDto dto) {
        return ResponseEntity.ok(companyViewRulesService.updateCompanyViewRules(id, dto));
    }

    /**
     * Delete an company view rules record by id.
     * @param id company view rules identifier
     * @return no content
     */
    @Operation(summary = "Delete Company View Rules")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company view rules identifier", required = true)
            @PathVariable UUID id) {
        companyViewRulesService.deleteCompanyViewRules(id);
        return ResponseEntity.noContent().build();
    }

}
