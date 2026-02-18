package gr.knowledge.schoolmanagement.controller;

import gr.knowledge.schoolmanagement.dto.StudentDto;
import gr.knowledge.schoolmanagement.service.StudentService;

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
 * REST controller for managing Student resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Student", description = "Student API")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Retrieves all records.
     *
     * @return list of StudentDto
     */
    @Operation(summary = "Get all Student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAll() {
        log.info("Fetching all student records.");
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    /**
     * Retrieves a record by id.
     *
     * @param id record id
     * @return StudentDto
     */
    @Operation(summary = "Get Student by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(
            @Parameter(description = "Student id", required = true)
            @PathVariable Long id) {
        log.info("Fetching student with id: {}", id);
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created StudentDto
     */
    @Operation(summary = "Create Student")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<StudentDto> create(@RequestBody StudentDto dto) {
        log.info("Creating student.");
        StudentDto created = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param id record id
     * @param dto payload
     * @return updated StudentDto
     */
    @Operation(summary = "Update Student")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(
            @Parameter(description = "Student id", required = true)
            @PathVariable Long id,
            @RequestBody StudentDto dto) {
        log.info("Updating student with id: {}", id);
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @param id record id
     */
    @Operation(summary = "Delete Student")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Student id", required = true)
            @PathVariable Long id) {
        log.info("Deleting student with id: {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
