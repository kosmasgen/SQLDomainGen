package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;
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
 * REST controller for managing Company Bg Cooperation I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Bg Cooperation I18n", description = "Company Bg Cooperation I18n API")
@RequestMapping("/api/company-bg-cooperation-i18n")
public class CompanyBgCooperationI18nController {

    private final CompanyBgCooperationI18nService companyBgCooperationI18nService;

    /**
     * Retrieves all company bg cooperation i18ns.
     * @return list of CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Get all company bg cooperation i18ns")
    @GetMapping
    public ResponseEntity<List<CompanyBgCooperationI18nDto>> getAll() {
        return ResponseEntity.ok(companyBgCooperationI18nService.getAllCompanyBgCooperationI18ns());
    }

    /**
     * Retrieves the company bg cooperation i18n record by id.
     * @param id company bg cooperation i18n identifier
     * @return CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Get Company Bg Cooperation I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationI18nDto> getById(
            @Parameter(description = "company bg cooperation i18n identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id));
    }

    /**
     * Creates a new company bg cooperation i18n record.
     * @param dto company bg cooperation i18n payload
     * @return created CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Create Company Bg Cooperation I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyBgCooperationI18nDto> create(
            @Valid @RequestBody CompanyBgCooperationI18nDto dto) {
        CompanyBgCooperationI18nDto created = companyBgCooperationI18nService.createCompanyBgCooperationI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company bg cooperation i18n record.
     * Only fields that are not null in the request are updated.
     * @param id company bg cooperation i18n identifier
     * @param dto partial company bg cooperation i18n payload
     * @return updated CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Patch Company Bg Cooperation I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationI18nDto> patch(
            @Parameter(description = "company bg cooperation i18n identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyBgCooperationI18nDto dto) {
        return ResponseEntity.ok(companyBgCooperationI18nService.updateCompanyBgCooperationI18n(id, dto));
    }

    /**
     * Delete an company bg cooperation i18n record by id.
     * @param id company bg cooperation i18n identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Bg Cooperation I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company bg cooperation i18n identifier", required = true)
            @PathVariable UUID id) {
        companyBgCooperationI18nService.deleteCompanyBgCooperationI18n(id);
        return ResponseEntity.noContent().build();
    }

}
