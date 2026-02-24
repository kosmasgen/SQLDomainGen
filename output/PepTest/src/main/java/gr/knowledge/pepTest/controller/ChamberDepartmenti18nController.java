package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nPK;

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
 * REST controller for managing ChamberDepartmenti18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ChamberDepartmenti18n", description = "ChamberDepartmenti18n API")
@RequestMapping("/api/chamber-departmenti18ns")
public class ChamberDepartmenti18nController {

    private final ChamberDepartmenti18nService chamberDepartmenti18nService;

    /**
     * Retrieves all records.
     *
     * @return list of ChamberDepartmenti18nDto
     */
    @Operation(summary = "Get all ChamberDepartmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ChamberDepartmenti18nDto>> getAll() {
        log.info("Fetching all chamberdepartmenti18n records.");
        return ResponseEntity.ok(chamberDepartmenti18nService.getAllChamberDepartmenti18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ChamberDepartmenti18nDto
     */
    @Operation(summary = "Get ChamberDepartmenti18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<ChamberDepartmenti18nDto> getById(
            @Parameter(description = "department_id", required = true)
            @RequestParam(name = "department_id") UUID departmentId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ChamberDepartmenti18nPK id = buildChamberDepartmenti18nId(departmentId, languageId);
        log.info("Fetching chamberdepartmenti18n with composite id: {}", id);
        return ResponseEntity.ok(chamberDepartmenti18nService.getChamberDepartmenti18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ChamberDepartmenti18nDto
     */
    @Operation(summary = "Create ChamberDepartmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberDepartmenti18nDto> create(@RequestBody ChamberDepartmenti18nDto dto) {
        log.info("Creating chamberdepartmenti18n.");
        ChamberDepartmenti18nDto created = chamberDepartmenti18nService.createChamberDepartmenti18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ChamberDepartmenti18nDto
     */
    @Operation(summary = "Update ChamberDepartmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<ChamberDepartmenti18nDto> update(
            @Parameter(description = "department_id", required = true)
            @RequestParam(name = "department_id") UUID departmentId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody ChamberDepartmenti18nDto dto) {
        ChamberDepartmenti18nPK id = buildChamberDepartmenti18nId(departmentId, languageId);
        log.info("Updating chamberdepartmenti18n with composite id: {}", id);
        return ResponseEntity.ok(chamberDepartmenti18nService.updateChamberDepartmenti18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ChamberDepartmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "department_id", required = true)
            @RequestParam(name = "department_id") UUID departmentId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ChamberDepartmenti18nPK id = buildChamberDepartmenti18nId(departmentId, languageId);
        log.info("Deleting chamberdepartmenti18n with composite id: {}", id);
        chamberDepartmenti18nService.deleteChamberDepartmenti18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for ChamberDepartmenti18n.
     */
    private ChamberDepartmenti18nPK buildChamberDepartmenti18nId(UUID departmentId, UUID languageId) {
        ChamberDepartmenti18nPK id = new ChamberDepartmenti18nPK();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);
        return id;
    }
}
