package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.dto.CourseDto;
import gr.knowledge.schoolmanagement.service.CourseService;

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
 * REST controller for managing Course resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Course", description = "Course API")
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    /**
     * Retrieves all records.
     *
     * @return list of CourseDto
     */
    @Operation(summary = "Get all Course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        log.info("Fetching all course records.");
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id record id
     * @return CourseDto
     */
    @Operation(summary = "Get Course by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(
            @Parameter(description = "Course id", required = true)
            @PathVariable Long id) {
        log.info("Fetching course with id: {}", id);
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CourseDto
     */
    @Operation(summary = "Create Course")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody CourseDto dto) {
        log.info("Creating course.");
        CourseDto created = courseService.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param id record id
     * @param dto payload
     * @return updated CourseDto
     */
    @Operation(summary = "Update Course")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(
            @Parameter(description = "Course id", required = true)
            @PathVariable Long id,
            @RequestBody CourseDto dto) {
        log.info("Updating course with id: {}", id);
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @param id record id
     */
    @Operation(summary = "Delete Course")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Course id", required = true)
            @PathVariable Long id) {
        log.info("Deleting course with id: {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
