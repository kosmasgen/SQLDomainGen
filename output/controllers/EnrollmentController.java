package com.sqldomaingen.controller;

import com.sqldomaingen.dto.EnrollmentDTO;
import com.sqldomaingen.service.EnrollmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Enrollment entities.
 * Provides CRUD operations for the Enrollment entity.
 */
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
    private final EnrollmentService enrollmentService;

    /**
     * Constructor for injecting the EnrollmentService service.
     * @param enrollmentService The service for the Enrollment entity.
     */
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Fetches all Enrollment records.
     * @return A list of EnrollmentDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll() {
        logger.info("Fetching all Enrollment records");
        return ResponseEntity.ok(enrollmentService.getAll());
    }

    /**
     * Fetches a specific Enrollment by its ID.
     * @param id The ID of the Enrollment entity.
     * @return The EnrollmentDTO object corresponding to the ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> getById(@PathVariable Long id) {
        logger.info("Fetching Enrollment with ID: {}", id);
        return ResponseEntity.ok(enrollmentService.getById(id));
    }

    /**
     * Creates a new Enrollment record.
     * @param dto The EnrollmentDTO object to be created.
     * @return The created EnrollmentDTO object.
     */
    @PostMapping
    public ResponseEntity<EnrollmentDTO> create(@RequestBody EnrollmentDTO dto) {
        logger.info("Creating new Enrollment: {}", dto);
        return ResponseEntity.ok(enrollmentService.create(dto));
    }

    /**
     * Updates an existing Enrollment record.
     * @param id The ID of the Enrollment to be updated.
     * @param dto The new data for the Enrollment entity.
     * @return The updated EnrollmentDTO object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@PathVariable Long id, @RequestBody EnrollmentDTO dto) {
        logger.info("Updating Enrollment with ID: {}", id);
        return ResponseEntity.ok(enrollmentService.update(id, dto));
    }

    /**
     * Deletes an existing Enrollment record by its ID.
     * @param id The ID of the Enrollment to be deleted.
     * @return A response with no content indicating the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        logger.info("Deleting Enrollment with ID: {}", id);
        enrollmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Checks if the Enrollment exists by its ID.
     * @param id The ID of the Enrollment entity.
     * @return `true` if the entity exists, otherwise `false`.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        logger.info("Checking if Enrollment exists with ID: {}", id);
        boolean exists = enrollmentService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    /**
     * Counts the number of Enrollment records.
     * @return The count of Enrollment records.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        logger.info("Counting Enrollment records");
        return ResponseEntity.ok(enrollmentService.count());
    }

}
