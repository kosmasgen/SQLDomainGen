package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.service.ChamberDepartmentService;
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
 * REST controller for managing Chamber Department resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Chamber Department", description = "Chamber Department API")
@RequestMapping("/api/chamber-department")
public class ChamberDepartmentController {

    private final ChamberDepartmentService chamberDepartmentService;

    /**
     * Retrieves all chamber departments.
     * @return list of ChamberDepartmentDto
     */
    @Operation(summary = "Get all chamber departments")
    @GetMapping
    public ResponseEntity<List<ChamberDepartmentDto>> getAll() {
        return ResponseEntity.ok(chamberDepartmentService.getAllChamberDepartments());
    }

    /**
     * Retrieves the chamber department record by id.
     * @param id chamber department identifier
     * @return ChamberDepartmentDto
     */
    @Operation(summary = "Get Chamber Department by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChamberDepartmentDto> getById(
            @Parameter(description = "chamber department identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(chamberDepartmentService.getChamberDepartmentById(id));
    }

    /**
     * Creates a new chamber department record.
     * @param dto chamber department payload
     * @return created ChamberDepartmentDto
     */
    @Operation(summary = "Create Chamber Department")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberDepartmentDto> create(
            @Valid @RequestBody ChamberDepartmentDto dto) {
        ChamberDepartmentDto created = chamberDepartmentService.createChamberDepartment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing chamber department record.
     * Only fields that are not null in the request are updated.
     * @param id chamber department identifier
     * @param dto partial chamber department payload
     * @return updated ChamberDepartmentDto
     */
    @Operation(summary = "Patch Chamber Department")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ChamberDepartmentDto> patch(
            @Parameter(description = "chamber department identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ChamberDepartmentDto dto) {
        return ResponseEntity.ok(chamberDepartmentService.updateChamberDepartment(id, dto));
    }

    /**
     * Delete an chamber department record by id.
     * @param id chamber department identifier
     * @return no content
     */
    @Operation(summary = "Delete Chamber Department")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "chamber department identifier", required = true)
            @PathVariable UUID id) {
        chamberDepartmentService.deleteChamberDepartment(id);
        return ResponseEntity.noContent().build();
    }

}
