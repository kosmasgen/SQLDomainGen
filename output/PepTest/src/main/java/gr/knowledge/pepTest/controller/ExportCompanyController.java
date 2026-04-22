package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.service.ExportCompanyService;
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
 * REST controller for managing Export Company resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Export Company", description = "Export Company API")
@RequestMapping("/api/export-company")
public class ExportCompanyController {

    private final ExportCompanyService exportCompanyService;

    /**
     * Retrieves all export companies.
     * @return list of ExportCompanyDto
     */
    @Operation(summary = "Get all export companies")
    @GetMapping
    public ResponseEntity<List<ExportCompanyDto>> getAll() {
        return ResponseEntity.ok(exportCompanyService.getAllExportCompanies());
    }

    /**
     * Retrieves the export company record by id.
     * @param id export company identifier
     * @return ExportCompanyDto
     */
    @Operation(summary = "Get Export Company by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExportCompanyDto> getById(
            @Parameter(description = "export company identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(exportCompanyService.getExportCompanyById(id));
    }

    /**
     * Creates a new export company record.
     * @param dto export company payload
     * @return created ExportCompanyDto
     */
    @Operation(summary = "Create Export Company")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ExportCompanyDto> create(
            @Valid @RequestBody ExportCompanyDto dto) {
        ExportCompanyDto created = exportCompanyService.createExportCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing export company record.
     * Only fields that are not null in the request are updated.
     * @param id export company identifier
     * @param dto partial export company payload
     * @return updated ExportCompanyDto
     */
    @Operation(summary = "Patch Export Company")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ExportCompanyDto> patch(
            @Parameter(description = "export company identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ExportCompanyDto dto) {
        return ResponseEntity.ok(exportCompanyService.updateExportCompany(id, dto));
    }

    /**
     * Delete an export company record by id.
     * @param id export company identifier
     * @return no content
     */
    @Operation(summary = "Delete Export Company")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "export company identifier", required = true)
            @PathVariable UUID id) {
        exportCompanyService.deleteExportCompany(id);
        return ResponseEntity.noContent().build();
    }

}
