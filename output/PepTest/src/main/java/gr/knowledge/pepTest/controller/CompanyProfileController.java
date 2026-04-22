package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.service.CompanyProfileService;
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
 * REST controller for managing Company Profile resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Profile", description = "Company Profile API")
@RequestMapping("/api/company-profile")
public class CompanyProfileController {

    private final CompanyProfileService companyProfileService;

    /**
     * Retrieves all company profiles.
     * @return list of CompanyProfileDto
     */
    @Operation(summary = "Get all company profiles")
    @GetMapping
    public ResponseEntity<List<CompanyProfileDto>> getAll() {
        return ResponseEntity.ok(companyProfileService.getAllCompanyProfiles());
    }

    /**
     * Retrieves the company profile record by id.
     * @param id company profile identifier
     * @return CompanyProfileDto
     */
    @Operation(summary = "Get Company Profile by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfileDto> getById(
            @Parameter(description = "company profile identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyProfileService.getCompanyProfileById(id));
    }

    /**
     * Creates a new company profile record.
     * @param dto company profile payload
     * @return created CompanyProfileDto
     */
    @Operation(summary = "Create Company Profile")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfileDto> create(
            @Valid @RequestBody CompanyProfileDto dto) {
        CompanyProfileDto created = companyProfileService.createCompanyProfile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company profile record.
     * Only fields that are not null in the request are updated.
     * @param id company profile identifier
     * @param dto partial company profile payload
     * @return updated CompanyProfileDto
     */
    @Operation(summary = "Patch Company Profile")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyProfileDto> patch(
            @Parameter(description = "company profile identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CompanyProfileDto dto) {
        return ResponseEntity.ok(companyProfileService.updateCompanyProfile(id, dto));
    }

    /**
     * Delete an company profile record by id.
     * @param id company profile identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Profile")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company profile identifier", required = true)
            @PathVariable UUID id) {
        companyProfileService.deleteCompanyProfile(id);
        return ResponseEntity.noContent().build();
    }

}
