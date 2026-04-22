package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.WorkingHoursDto;
import gr.knowledge.pepTest.service.WorkingHoursService;
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

/**
 * REST controller for managing Working Hours resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Working Hours", description = "Working Hours API")
@RequestMapping("/api/working-hours")
public class WorkingHoursController {

    private final WorkingHoursService workingHoursService;

    /**
     * Retrieves all working hourses.
     * @return list of WorkingHoursDto
     */
    @Operation(summary = "Get all working hourses")
    @GetMapping
    public ResponseEntity<List<WorkingHoursDto>> getAll() {
        return ResponseEntity.ok(workingHoursService.getAllWorkingHourses());
    }

    /**
     * Retrieves the working hours record by id.
     * @param id working hours identifier
     * @return WorkingHoursDto
     */
    @Operation(summary = "Get Working Hours by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> getById(
            @Parameter(description = "working hours identifier", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(workingHoursService.getWorkingHoursById(id));
    }

    /**
     * Creates a new working hours record.
     * @param dto working hours payload
     * @return created WorkingHoursDto
     */
    @Operation(summary = "Create Working Hours")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<WorkingHoursDto> create(
            @Valid @RequestBody WorkingHoursDto dto) {
        WorkingHoursDto created = workingHoursService.createWorkingHours(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing working hours record.
     * Only fields that are not null in the request are updated.
     * @param id working hours identifier
     * @param dto partial working hours payload
     * @return updated WorkingHoursDto
     */
    @Operation(summary = "Patch Working Hours")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<WorkingHoursDto> patch(
            @Parameter(description = "working hours identifier", required = true)
            @PathVariable Long id,
            @Valid @RequestBody WorkingHoursDto dto) {
        return ResponseEntity.ok(workingHoursService.updateWorkingHours(id, dto));
    }

    /**
     * Delete an working hours record by id.
     * @param id working hours identifier
     * @return no content
     */
    @Operation(summary = "Delete Working Hours")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "working hours identifier", required = true)
            @PathVariable Long id) {
        workingHoursService.deleteWorkingHours(id);
        return ResponseEntity.noContent().build();
    }

}
