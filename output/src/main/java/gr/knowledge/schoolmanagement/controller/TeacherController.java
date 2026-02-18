package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.dto.TeacherDto;
import gr.knowledge.schoolmanagement.service.TeacherService;

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
 * REST controller for managing Teacher resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Teacher", description = "Teacher API")
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * Retrieves all records.
     *
     * @return list of TeacherDto
     */
    @Operation(summary = "Get all Teacher")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        log.info("Fetching all teacher records.");
        return ResponseEntity.ok(teacherService.getAllTeacher());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id record id
     * @return TeacherDto
     */
    @Operation(summary = "Get Teacher by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(
            @Parameter(description = "Teacher id", required = true)
            @PathVariable Long id) {
        log.info("Fetching teacher with id: {}", id);
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TeacherDto
     */
    @Operation(summary = "Create Teacher")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TeacherDto> create(@RequestBody TeacherDto dto) {
        log.info("Creating teacher.");
        TeacherDto created = teacherService.createTeacher(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param id record id
     * @param dto payload
     * @return updated TeacherDto
     */
    @Operation(summary = "Update Teacher")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> update(
            @Parameter(description = "Teacher id", required = true)
            @PathVariable Long id,
            @RequestBody TeacherDto dto) {
        log.info("Updating teacher with id: {}", id);
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @param id record id
     */
    @Operation(summary = "Delete Teacher")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Teacher id", required = true)
            @PathVariable Long id) {
        log.info("Deleting teacher with id: {}", id);
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
