package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;

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
 * REST controller for managing CompanyBgCooperationI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyBgCooperationI18n", description = "CompanyBgCooperationI18n API")
@RequestMapping("/api/company-bg-cooperation-i18ns")
public class CompanyBgCooperationI18nController {

    private final CompanyBgCooperationI18nService companyBgCooperationI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Get all CompanyBgCooperationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyBgCooperationI18nDto>> getAll() {
        log.info("Fetching all companybgcooperationi18n records.");
        return ResponseEntity.ok(companyBgCooperationI18nService.getAllCompanyBgCooperationI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Get CompanyBgCooperationI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationI18nDto> getById(
            @Parameter(description = "CompanyBgCooperationI18n id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companybgcooperationi18n with id: {}", id);
        return ResponseEntity.ok(companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Create CompanyBgCooperationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyBgCooperationI18nDto> create(@RequestBody CompanyBgCooperationI18nDto dto) {
        log.info("Creating companybgcooperationi18n.");
        CompanyBgCooperationI18nDto created = companyBgCooperationI18nService.createCompanyBgCooperationI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyBgCooperationI18nDto
     */
    @Operation(summary = "Update CompanyBgCooperationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationI18nDto> update(
            @Parameter(description = "CompanyBgCooperationI18n id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyBgCooperationI18nDto dto) {
        log.info("Updating companybgcooperationi18n with id: {}", id);
        return ResponseEntity.ok(companyBgCooperationI18nService.updateCompanyBgCooperationI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyBgCooperationI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyBgCooperationI18n id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companybgcooperationi18n with id: {}", id);
        companyBgCooperationI18nService.deleteCompanyBgCooperationI18n(id);
        return ResponseEntity.noContent().build();
    }
}
