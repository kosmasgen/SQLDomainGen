package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationService;

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
 * REST controller for managing CompanyBgCooperation resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyBgCooperation", description = "CompanyBgCooperation API")
@RequestMapping("/api/company-bg-cooperations")
public class CompanyBgCooperationController {

    private final CompanyBgCooperationService companyBgCooperationService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyBgCooperationDto
     */
    @Operation(summary = "Get all CompanyBgCooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyBgCooperationDto>> getAll() {
        log.info("Fetching all companybgcooperation records.");
        return ResponseEntity.ok(companyBgCooperationService.getAllCompanyBgCooperation());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyBgCooperationDto
     */
    @Operation(summary = "Get CompanyBgCooperation by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationDto> getById(
            @Parameter(description = "CompanyBgCooperation id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companybgcooperation with id: {}", id);
        return ResponseEntity.ok(companyBgCooperationService.getCompanyBgCooperationById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyBgCooperationDto
     */
    @Operation(summary = "Create CompanyBgCooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyBgCooperationDto> create(@RequestBody CompanyBgCooperationDto dto) {
        log.info("Creating companybgcooperation.");
        CompanyBgCooperationDto created = companyBgCooperationService.createCompanyBgCooperation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyBgCooperationDto
     */
    @Operation(summary = "Update CompanyBgCooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyBgCooperationDto> update(
            @Parameter(description = "CompanyBgCooperation id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyBgCooperationDto dto) {
        log.info("Updating companybgcooperation with id: {}", id);
        return ResponseEntity.ok(companyBgCooperationService.updateCompanyBgCooperation(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyBgCooperation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyBgCooperation id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companybgcooperation with id: {}", id);
        companyBgCooperationService.deleteCompanyBgCooperation(id);
        return ResponseEntity.noContent().build();
    }
}
