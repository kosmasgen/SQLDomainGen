package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.service.ChamberDepartmenti18nService;
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
 * REST controller for managing Chamber Departmenti18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Chamber Departmenti18n", description = "Chamber Departmenti18n API")
@RequestMapping("/api/chamber-departmenti18n")
public class ChamberDepartmenti18nController {

    private final ChamberDepartmenti18nService chamberDepartmenti18nService;

    /**
     * Retrieves all chamber departmenti18ns.
     * @return list of ChamberDepartmenti18nDto
     */
    @Operation(summary = "Get all chamber departmenti18ns")
    @GetMapping
    public ResponseEntity<List<ChamberDepartmenti18nDto>> getAll() {
        return ResponseEntity.ok(chamberDepartmenti18nService.getAllChamberDepartmenti18ns());
    }

    /**
     * Retrieves the chamber departmenti18n record by id.
     * @param departmentId department id identifier
     * @param languageId language id identifier
     * @return ChamberDepartmenti18nDto
     */
    @Operation(summary = "Get Chamber Departmenti18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{departmentId}/{languageId}")
    public ResponseEntity<ChamberDepartmenti18nDto> getById(
            @Parameter(description = "department id identifier", required = true)
            @PathVariable UUID departmentId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(chamberDepartmenti18nService.getChamberDepartmenti18nById(departmentId, languageId));
    }

    /**
     * Creates a new chamber departmenti18n record.
     * @param dto chamber departmenti18n payload
     * @return created ChamberDepartmenti18nDto
     */
    @Operation(summary = "Create Chamber Departmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberDepartmenti18nDto> create(
            @Valid @RequestBody ChamberDepartmenti18nDto dto) {
        ChamberDepartmenti18nDto created = chamberDepartmenti18nService.createChamberDepartmenti18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing chamber departmenti18n record.
     * Only fields that are not null in the request are updated.
     * @param departmentId department id identifier
     * @param languageId language id identifier
     * @param dto partial chamber departmenti18n payload
     * @return updated ChamberDepartmenti18nDto
     */
    @Operation(summary = "Patch Chamber Departmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{departmentId}/{languageId}")
    public ResponseEntity<ChamberDepartmenti18nDto> patch(
            @Parameter(description = "department id identifier", required = true)
            @PathVariable UUID departmentId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Valid @RequestBody ChamberDepartmenti18nDto dto) {
        return ResponseEntity.ok(chamberDepartmenti18nService.updateChamberDepartmenti18n(departmentId, languageId, dto));
    }

    /**
     * Delete an chamber departmenti18n record by id.
     * @param departmentId department id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Chamber Departmenti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{departmentId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "department id identifier", required = true)
            @PathVariable UUID departmentId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        chamberDepartmenti18nService.deleteChamberDepartmenti18n(departmentId, languageId);
        return ResponseEntity.noContent().build();
    }

}
