package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.dto.CourseStudentDto;
import gr.knowledge.schoolmanagement.service.CourseStudentService;

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
 * REST controller for managing CourseStudent resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CourseStudent", description = "CourseStudent API")
@RequestMapping("/api/course-students")
public class CourseStudentController {

    private final CourseStudentService courseStudentService;

    /**
     * Retrieves all records.
     *
     * @return list of CourseStudentDto
     */
    @Operation(summary = "Get all CourseStudent")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CourseStudentDto>> getAll() {
        log.info("Fetching all coursestudent records.");
        return ResponseEntity.ok(courseStudentService.getAllCourseStudent());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id record id
     * @return CourseStudentDto
     */
    @Operation(summary = "Get CourseStudent by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CourseStudentDto> getById(
            @Parameter(description = "CourseStudent id", required = true)
            @PathVariable Long id) {
        log.info("Fetching coursestudent with id: {}", id);
        return ResponseEntity.ok(courseStudentService.getCourseStudentById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CourseStudentDto
     */
    @Operation(summary = "Create CourseStudent")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CourseStudentDto> create(@RequestBody CourseStudentDto dto) {
        log.info("Creating coursestudent.");
        CourseStudentDto created = courseStudentService.createCourseStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param id record id
     * @param dto payload
     * @return updated CourseStudentDto
     */
    @Operation(summary = "Update CourseStudent")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CourseStudentDto> update(
            @Parameter(description = "CourseStudent id", required = true)
            @PathVariable Long id,
            @RequestBody CourseStudentDto dto) {
        log.info("Updating coursestudent with id: {}", id);
        return ResponseEntity.ok(courseStudentService.updateCourseStudent(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @param id record id
     */
    @Operation(summary = "Delete CourseStudent")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CourseStudent id", required = true)
            @PathVariable Long id) {
        log.info("Deleting coursestudent with id: {}", id);
        courseStudentService.deleteCourseStudent(id);
        return ResponseEntity.noContent().build();
    }
}
