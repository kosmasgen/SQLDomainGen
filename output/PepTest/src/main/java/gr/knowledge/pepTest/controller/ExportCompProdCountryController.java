package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.service.ExportCompProdCountryService;
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
 * REST controller for managing Export Comp Prod Country resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Export Comp Prod Country", description = "Export Comp Prod Country API")
@RequestMapping("/api/export-comp-prod-country")
public class ExportCompProdCountryController {

    private final ExportCompProdCountryService exportCompProdCountryService;

    /**
     * Retrieves all export comp prod countries.
     * @return list of ExportCompProdCountryDto
     */
    @Operation(summary = "Get all export comp prod countries")
    @GetMapping
    public ResponseEntity<List<ExportCompProdCountryDto>> getAll() {
        return ResponseEntity.ok(exportCompProdCountryService.getAllExportCompProdCountries());
    }

    /**
     * Retrieves the export comp prod country record by id.
     * @param id export comp prod country identifier
     * @return ExportCompProdCountryDto
     */
    @Operation(summary = "Get Export Comp Prod Country by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExportCompProdCountryDto> getById(
            @Parameter(description = "export comp prod country identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(exportCompProdCountryService.getExportCompProdCountryById(id));
    }

    /**
     * Creates a new export comp prod country record.
     * @param dto export comp prod country payload
     * @return created ExportCompProdCountryDto
     */
    @Operation(summary = "Create Export Comp Prod Country")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ExportCompProdCountryDto> create(
            @Valid @RequestBody ExportCompProdCountryDto dto) {
        ExportCompProdCountryDto created = exportCompProdCountryService.createExportCompProdCountry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing export comp prod country record.
     * Only fields that are not null in the request are updated.
     * @param id export comp prod country identifier
     * @param dto partial export comp prod country payload
     * @return updated ExportCompProdCountryDto
     */
    @Operation(summary = "Patch Export Comp Prod Country")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ExportCompProdCountryDto> patch(
            @Parameter(description = "export comp prod country identifier", required = true)
            @PathVariable UUID id,
            @RequestBody ExportCompProdCountryDto dto) {
        return ResponseEntity.ok(exportCompProdCountryService.updateExportCompProdCountry(id, dto));
    }

    /**
     * Delete an export comp prod country record by id.
     * @param id export comp prod country identifier
     * @return no content
     */
    @Operation(summary = "Delete Export Comp Prod Country")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "export comp prod country identifier", required = true)
            @PathVariable UUID id) {
        exportCompProdCountryService.deleteExportCompProdCountry(id);
        return ResponseEntity.noContent().build();
    }

}
