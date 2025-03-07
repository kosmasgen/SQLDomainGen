package com.sqldomaingen.controller;

import com.sqldomaingen.dto.CourseDTO;
import com.sqldomaingen.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Course entities.
 * Provides CRUD operations for the Course entity.
 */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;

    /**
     * Constructor for injecting the CourseService service.
     * @param courseService The service for the Course entity.
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Fetches all Course records.
     * @return A list of CourseDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() {
        logger.info("Fetching all Course records");
        return ResponseEntity.ok(courseService.getAll());
    }

    /**
     * Fetches a specific Course by its ID.
     * @param id The ID of the Course entity.
     * @return The CourseDTO object corresponding to the ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable Long id) {
        logger.info("Fetching Course with ID: {}", id);
        return ResponseEntity.ok(courseService.getById(id));
    }

    /**
     * Creates a new Course record.
     * @param dto The CourseDTO object to be created.
     * @return The created CourseDTO object.
     */
    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO dto) {
        logger.info("Creating new Course: {}", dto);
        return ResponseEntity.ok(courseService.create(dto));
    }

    /**
     * Updates an existing Course record.
     * @param id The ID of the Course to be updated.
     * @param dto The new data for the Course entity.
     * @return The updated CourseDTO object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable Long id, @RequestBody CourseDTO dto) {
        logger.info("Updating Course with ID: {}", id);
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    /**
     * Deletes an existing Course record by its ID.
     * @param id The ID of the Course to be deleted.
     * @return A response with no content indicating the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        logger.info("Deleting Course with ID: {}", id);
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Checks if the Course exists by its ID.
     * @param id The ID of the Course entity.
     * @return `true` if the entity exists, otherwise `false`.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        logger.info("Checking if Course exists with ID: {}", id);
        boolean exists = courseService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    /**
     * Counts the number of Course records.
     * @return The count of Course records.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        logger.info("Counting Course records");
        return ResponseEntity.ok(courseService.count());
    }

}
