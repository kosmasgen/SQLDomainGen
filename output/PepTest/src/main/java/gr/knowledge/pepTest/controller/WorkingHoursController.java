package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.service.WorkingHoursService;

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

import java.util.List;

/**
 * REST controller for managing WorkingHours resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "WorkingHours", description = "WorkingHours API")
@RequestMapping("/api/working-hourss")
public class WorkingHoursController {

    private final WorkingHoursService workingHoursService;

    /**
     * Retrieves all records.
     *
     * @return list of WorkingHoursDto
     */
    @Operation(summary = "Get all WorkingHours")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<WorkingHoursDto>> getAll() {
        log.info("Fetching all workinghours records.");
        return ResponseEntity.ok(workingHoursService.getAllWorkingHours());
    }

    /**
     * Retrieves a record by id.
     *
     * @return WorkingHoursDto
     */
    @Operation(summary = "Get WorkingHours by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> getById(
            @Parameter(description = "WorkingHours id", required = true)
            @PathVariable Long id) {
        log.info("Fetching workinghours with id: {}", id);
        return ResponseEntity.ok(workingHoursService.getWorkingHoursById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created WorkingHoursDto
     */
    @Operation(summary = "Create WorkingHours")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<WorkingHoursDto> create(@RequestBody WorkingHoursDto dto) {
        log.info("Creating workinghours.");
        WorkingHoursDto created = workingHoursService.createWorkingHours(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated WorkingHoursDto
     */
    @Operation(summary = "Update WorkingHours")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> update(
            @Parameter(description = "WorkingHours id", required = true)
            @PathVariable Long id,
            @RequestBody WorkingHoursDto dto) {
        log.info("Updating workinghours with id: {}", id);
        return ResponseEntity.ok(workingHoursService.updateWorkingHours(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete WorkingHours")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "WorkingHours id", required = true)
            @PathVariable Long id) {
        log.info("Deleting workinghours with id: {}", id);
        workingHoursService.deleteWorkingHours(id);
        return ResponseEntity.noContent().build();
    }
}
