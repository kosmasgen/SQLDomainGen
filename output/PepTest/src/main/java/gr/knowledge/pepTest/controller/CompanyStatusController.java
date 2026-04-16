package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.service.CompanyStatusService;
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
 * REST controller for managing Company Status resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Status", description = "Company Status API")
@RequestMapping("/api/company-status")
public class CompanyStatusController {

    private final CompanyStatusService companyStatusService;

    /**
     * Retrieves all company statuses.
     * @return list of CompanyStatusDto
     */
    @Operation(summary = "Get all company statuses")
    @GetMapping
    public ResponseEntity<List<CompanyStatusDto>> getAll() {
        return ResponseEntity.ok(companyStatusService.getAllCompanyStatuses());
    }

    /**
     * Retrieves the company status record by id.
     * @param id company status identifier
     * @return CompanyStatusDto
     */
    @Operation(summary = "Get Company Status by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyStatusDto> getById(
            @Parameter(description = "company status identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyStatusService.getCompanyStatusById(id));
    }

    /**
     * Creates a new company status record.
     * @param dto company status payload
     * @return created CompanyStatusDto
     */
    @Operation(summary = "Create Company Status")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyStatusDto> create(
            @Valid @RequestBody CompanyStatusDto dto) {
        CompanyStatusDto created = companyStatusService.createCompanyStatus(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company status record.
     * Only fields that are not null in the request are updated.
     * @param id company status identifier
     * @param dto partial company status payload
     * @return updated CompanyStatusDto
     */
    @Operation(summary = "Patch Company Status")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyStatusDto> patch(
            @Parameter(description = "company status identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyStatusDto dto) {
        return ResponseEntity.ok(companyStatusService.updateCompanyStatus(id, dto));
    }

    /**
     * Delete an company status record by id.
     * @param id company status identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Status")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company status identifier", required = true)
            @PathVariable UUID id) {
        companyStatusService.deleteCompanyStatus(id);
        return ResponseEntity.noContent().build();
    }

}
