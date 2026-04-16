package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationService;
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
 * REST controller for managing Company Bg Cooperation resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Bg Cooperation", description = "Company Bg Cooperation API")
@RequestMapping("/api/company-bg-cooperation")
public class CompanyBgCooperationController {

    private final CompanyBgCooperationService companyBgCooperationService;

    /**
     * Retrieves all company bg cooperations.
     * @return list of CompanyBgCooperationDto
     */
    @Operation(summary = "Get all company bg cooperations")
    @GetMapping
    public ResponseEntity<List<CompanyBgCooperationDto>> getAll() {
        return ResponseEntity.ok(companyBgCooperationService.getAllCompanyBgCooperations());
    }

    /**
     * Retrieves the company bg cooperation record by id.
     * @param id company bg cooperation identifier
     * @return CompanyBgCooperationDto
     */
    @Operation(summary = "Get Company Bg Cooperation by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationDto> getById(
            @Parameter(description = "company bg cooperation identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyBgCooperationService.getCompanyBgCooperationById(id));
    }

    /**
     * Creates a new company bg cooperation record.
     * @param dto company bg cooperation payload
     * @return created CompanyBgCooperationDto
     */
    @Operation(summary = "Create Company Bg Cooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyBgCooperationDto> create(
            @Valid @RequestBody CompanyBgCooperationDto dto) {
        CompanyBgCooperationDto created = companyBgCooperationService.createCompanyBgCooperation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company bg cooperation record.
     * Only fields that are not null in the request are updated.
     * @param id company bg cooperation identifier
     * @param dto partial company bg cooperation payload
     * @return updated CompanyBgCooperationDto
     */
    @Operation(summary = "Patch Company Bg Cooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationDto> patch(
            @Parameter(description = "company bg cooperation identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyBgCooperationDto dto) {
        return ResponseEntity.ok(companyBgCooperationService.updateCompanyBgCooperation(id, dto));
    }

    /**
     * Delete an company bg cooperation record by id.
     * @param id company bg cooperation identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Bg Cooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company bg cooperation identifier", required = true)
            @PathVariable UUID id) {
        companyBgCooperationService.deleteCompanyBgCooperation(id);
        return ResponseEntity.noContent().build();
    }

}
