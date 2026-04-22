package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.service.CompanyProfessionService;
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
 * REST controller for managing Company Profession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Profession", description = "Company Profession API")
@RequestMapping("/api/company-profession")
public class CompanyProfessionController {

    private final CompanyProfessionService companyProfessionService;

    /**
     * Retrieves all company professions.
     * @return list of CompanyProfessionDto
     */
    @Operation(summary = "Get all company professions")
    @GetMapping
    public ResponseEntity<List<CompanyProfessionDto>> getAll() {
        return ResponseEntity.ok(companyProfessionService.getAllCompanyProfessions());
    }

    /**
     * Retrieves the company profession record by id.
     * @param id company profession identifier
     * @return CompanyProfessionDto
     */
    @Operation(summary = "Get Company Profession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyProfessionDto> getById(
            @Parameter(description = "company profession identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyProfessionService.getCompanyProfessionById(id));
    }

    /**
     * Creates a new company profession record.
     * @param dto company profession payload
     * @return created CompanyProfessionDto
     */
    @Operation(summary = "Create Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyProfessionDto> create(
            @Valid @RequestBody CompanyProfessionDto dto) {
        CompanyProfessionDto created = companyProfessionService.createCompanyProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company profession record.
     * Only fields that are not null in the request are updated.
     * @param id company profession identifier
     * @param dto partial company profession payload
     * @return updated CompanyProfessionDto
     */
    @Operation(summary = "Patch Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyProfessionDto> patch(
            @Parameter(description = "company profession identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CompanyProfessionDto dto) {
        return ResponseEntity.ok(companyProfessionService.updateCompanyProfession(id, dto));
    }

    /**
     * Delete an company profession record by id.
     * @param id company profession identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company profession identifier", required = true)
            @PathVariable UUID id) {
        companyProfessionService.deleteCompanyProfession(id);
        return ResponseEntity.noContent().build();
    }

}
