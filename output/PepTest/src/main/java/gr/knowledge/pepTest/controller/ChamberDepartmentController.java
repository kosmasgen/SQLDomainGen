package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.service.ChamberDepartmentService;

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
 * REST controller for managing ChamberDepartment resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ChamberDepartment", description = "ChamberDepartment API")
@RequestMapping("/api/chamber-departments")
public class ChamberDepartmentController {

    private final ChamberDepartmentService chamberDepartmentService;

    /**
     * Retrieves all records.
     *
     * @return list of ChamberDepartmentDto
     */
    @Operation(summary = "Get all ChamberDepartment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ChamberDepartmentDto>> getAll() {
        log.info("Fetching all chamberdepartment records.");
        return ResponseEntity.ok(chamberDepartmentService.getAllChamberDepartment());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ChamberDepartmentDto
     */
    @Operation(summary = "Get ChamberDepartment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChamberDepartmentDto> getById(
            @Parameter(description = "ChamberDepartment id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching chamberdepartment with id: {}", id);
        return ResponseEntity.ok(chamberDepartmentService.getChamberDepartmentById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ChamberDepartmentDto
     */
    @Operation(summary = "Create ChamberDepartment")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberDepartmentDto> create(@RequestBody ChamberDepartmentDto dto) {
        log.info("Creating chamberdepartment.");
        ChamberDepartmentDto created = chamberDepartmentService.createChamberDepartment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ChamberDepartmentDto
     */
    @Operation(summary = "Update ChamberDepartment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ChamberDepartmentDto> update(
            @Parameter(description = "ChamberDepartment id", required = true)
            @PathVariable UUID id,
            @RequestBody ChamberDepartmentDto dto) {
        log.info("Updating chamberdepartment with id: {}", id);
        return ResponseEntity.ok(chamberDepartmentService.updateChamberDepartment(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ChamberDepartment")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ChamberDepartment id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting chamberdepartment with id: {}", id);
        chamberDepartmentService.deleteChamberDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
