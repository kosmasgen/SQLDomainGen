package com.sqldomaingen.controller;

import com.sqldomaingen.dto.StudentDTO;
import com.sqldomaingen.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Student entities.
 * Provides CRUD operations for the Student entity.
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;

    /**
     * Constructor for injecting the StudentService service.
     * @param studentService The service for the Student entity.
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Fetches all Student records.
     * @return A list of StudentDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        logger.info("Fetching all Student records");
        return ResponseEntity.ok(studentService.getAll());
    }

    /**
     * Fetches a specific Student by its ID.
     * @param id The ID of the Student entity.
     * @return The StudentDTO object corresponding to the ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getById(@PathVariable String id) {
        logger.info("Fetching Student with ID: {}", id);
        return ResponseEntity.ok(studentService.getById(id));
    }

    /**
     * Creates a new Student record.
     * @param dto The StudentDTO object to be created.
     * @return The created StudentDTO object.
     */
    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        logger.info("Creating new Student: {}", dto);
        return ResponseEntity.ok(studentService.create(dto));
    }

    /**
     * Updates an existing Student record.
     * @param id The ID of the Student to be updated.
     * @param dto The new data for the Student entity.
     * @return The updated StudentDTO object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable String id, @RequestBody StudentDTO dto) {
        logger.info("Updating Student with ID: {}", id);
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    /**
     * Deletes an existing Student record by its ID.
     * @param id The ID of the Student to be deleted.
     * @return A response with no content indicating the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        logger.info("Deleting Student with ID: {}", id);
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Checks if the Student exists by its ID.
     * @param id The ID of the Student entity.
     * @return `true` if the entity exists, otherwise `false`.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable String id) {
        logger.info("Checking if Student exists with ID: {}", id);
        boolean exists = studentService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    /**
     * Counts the number of Student records.
     * @return The count of Student records.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        logger.info("Counting Student records");
        return ResponseEntity.ok(studentService.count());
    }

}
