package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.service.ExportCompProdCountryService;

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
 * REST controller for managing ExportCompProdCountry resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ExportCompProdCountry", description = "ExportCompProdCountry API")
@RequestMapping("/api/export-comp-prod-countrys")
public class ExportCompProdCountryController {

    private final ExportCompProdCountryService exportCompProdCountryService;

    /**
     * Retrieves all records.
     *
     * @return list of ExportCompProdCountryDto
     */
    @Operation(summary = "Get all ExportCompProdCountry")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ExportCompProdCountryDto>> getAll() {
        log.info("Fetching all exportcompprodcountry records.");
        return ResponseEntity.ok(exportCompProdCountryService.getAllExportCompProdCountry());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ExportCompProdCountryDto
     */
    @Operation(summary = "Get ExportCompProdCountry by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExportCompProdCountryDto> getById(
            @Parameter(description = "ExportCompProdCountry id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching exportcompprodcountry with id: {}", id);
        return ResponseEntity.ok(exportCompProdCountryService.getExportCompProdCountryById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ExportCompProdCountryDto
     */
    @Operation(summary = "Create ExportCompProdCountry")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ExportCompProdCountryDto> create(@RequestBody ExportCompProdCountryDto dto) {
        log.info("Creating exportcompprodcountry.");
        ExportCompProdCountryDto created = exportCompProdCountryService.createExportCompProdCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ExportCompProdCountryDto
     */
    @Operation(summary = "Update ExportCompProdCountry")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExportCompProdCountryDto> update(
            @Parameter(description = "ExportCompProdCountry id", required = true)
            @PathVariable UUID id,
            @RequestBody ExportCompProdCountryDto dto) {
        log.info("Updating exportcompprodcountry with id: {}", id);
        return ResponseEntity.ok(exportCompProdCountryService.updateExportCompProdCountry(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ExportCompProdCountry")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ExportCompProdCountry id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting exportcompprodcountry with id: {}", id);
        exportCompProdCountryService.deleteExportCompProdCountry(id);
        return ResponseEntity.noContent().build();
    }
}
