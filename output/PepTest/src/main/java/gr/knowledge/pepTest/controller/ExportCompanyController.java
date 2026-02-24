package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.service.ExportCompanyService;

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
 * REST controller for managing ExportCompany resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ExportCompany", description = "ExportCompany API")
@RequestMapping("/api/export-companys")
public class ExportCompanyController {

    private final ExportCompanyService exportCompanyService;

    /**
     * Retrieves all records.
     *
     * @return list of ExportCompanyDto
     */
    @Operation(summary = "Get all ExportCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ExportCompanyDto>> getAll() {
        log.info("Fetching all exportcompany records.");
        return ResponseEntity.ok(exportCompanyService.getAllExportCompany());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ExportCompanyDto
     */
    @Operation(summary = "Get ExportCompany by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExportCompanyDto> getById(
            @Parameter(description = "ExportCompany id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching exportcompany with id: {}", id);
        return ResponseEntity.ok(exportCompanyService.getExportCompanyById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ExportCompanyDto
     */
    @Operation(summary = "Create ExportCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ExportCompanyDto> create(@RequestBody ExportCompanyDto dto) {
        log.info("Creating exportcompany.");
        ExportCompanyDto created = exportCompanyService.createExportCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ExportCompanyDto
     */
    @Operation(summary = "Update ExportCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExportCompanyDto> update(
            @Parameter(description = "ExportCompany id", required = true)
            @PathVariable UUID id,
            @RequestBody ExportCompanyDto dto) {
        log.info("Updating exportcompany with id: {}", id);
        return ResponseEntity.ok(exportCompanyService.updateExportCompany(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ExportCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ExportCompany id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting exportcompany with id: {}", id);
        exportCompanyService.deleteExportCompany(id);
        return ResponseEntity.noContent().build();
    }
}
