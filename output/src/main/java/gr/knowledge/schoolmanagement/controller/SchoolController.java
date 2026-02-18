package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.dto.SchoolDto;
import gr.knowledge.schoolmanagement.service.SchoolService;

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
 * REST controller for managing School resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "School", description = "School API")
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    /**
     * Retrieves all records.
     *
     * @return list of SchoolDto
     */
    @Operation(summary = "Get all School")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<SchoolDto>> getAll() {
        log.info("Fetching all school records.");
        return ResponseEntity.ok(schoolService.getAllSchool());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id record id
     * @return SchoolDto
     */
    @Operation(summary = "Get School by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> getById(
            @Parameter(description = "School id", required = true)
            @PathVariable Long id) {
        log.info("Fetching school with id: {}", id);
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created SchoolDto
     */
    @Operation(summary = "Create School")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SchoolDto> create(@RequestBody SchoolDto dto) {
        log.info("Creating school.");
        SchoolDto created = schoolService.createSchool(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param id record id
     * @param dto payload
     * @return updated SchoolDto
     */
    @Operation(summary = "Update School")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SchoolDto> update(
            @Parameter(description = "School id", required = true)
            @PathVariable Long id,
            @RequestBody SchoolDto dto) {
        log.info("Updating school with id: {}", id);
        return ResponseEntity.ok(schoolService.updateSchool(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @param id record id
     */
    @Operation(summary = "Delete School")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "School id", required = true)
            @PathVariable Long id) {
        log.info("Deleting school with id: {}", id);
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
