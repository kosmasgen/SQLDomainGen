package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.service.CompanyService;
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
 * REST controller for managing Company resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company", description = "Company API")
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    /**
     * Retrieves all companies.
     * @return list of CompanyDto
     */
    @Operation(summary = "Get all companies")
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAll() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    /**
     * Retrieves the company record by id.
     * @param id company identifier
     * @return CompanyDto
     */
    @Operation(summary = "Get Company by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getById(
            @Parameter(description = "company identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    /**
     * Creates a new company record.
     * @param dto company payload
     * @return created CompanyDto
     */
    @Operation(summary = "Create Company")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyDto> create(
            @Valid @RequestBody CompanyDto dto) {
        CompanyDto created = companyService.createCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company record.
     * Only fields that are not null in the request are updated.
     * @param id company identifier
     * @param dto partial company payload
     * @return updated CompanyDto
     */
    @Operation(summary = "Patch Company")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyDto> patch(
            @Parameter(description = "company identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyDto dto) {
        return ResponseEntity.ok(companyService.updateCompany(id, dto));
    }

    /**
     * Delete an company record by id.
     * @param id company identifier
     * @return no content
     */
    @Operation(summary = "Delete Company")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company identifier", required = true)
            @PathVariable UUID id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}
