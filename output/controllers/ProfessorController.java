package com.sqldomaingen.controller;

import com.sqldomaingen.dto.ProfessorDTO;
import com.sqldomaingen.service.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for managing Professor entities.
 * Provides CRUD operations for the Professor entity.
 */
@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);
    private final ProfessorService professorService;

    /**
     * Constructor for injecting the ProfessorService service.
     * @param professorService The service for the Professor entity.
     */
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * Fetches all Professor records.
     * @return A list of ProfessorDTO objects.
     */
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAll() {
        logger.info("Fetching all Professor records");
        return ResponseEntity.ok(professorService.getAll());
    }

    /**
     * Fetches a specific Professor by its ID.
     * @param id The ID of the Professor entity.
     * @return The ProfessorDTO object corresponding to the ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> getById(@PathVariable UUID id) {
        logger.info("Fetching Professor with ID: {}", id);
        return ResponseEntity.ok(professorService.getById(id));
    }

    /**
     * Creates a new Professor record.
     * @param dto The ProfessorDTO object to be created.
     * @return The created ProfessorDTO object.
     */
    @PostMapping
    public ResponseEntity<ProfessorDTO> create(@RequestBody ProfessorDTO dto) {
        logger.info("Creating new Professor: {}", dto);
        return ResponseEntity.ok(professorService.create(dto));
    }

    /**
     * Updates an existing Professor record.
     * @param id The ID of the Professor to be updated.
     * @param dto The new data for the Professor entity.
     * @return The updated ProfessorDTO object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable UUID id, @RequestBody ProfessorDTO dto) {
        logger.info("Updating Professor with ID: {}", id);
        return ResponseEntity.ok(professorService.update(id, dto));
    }

    /**
     * Deletes an existing Professor record by its ID.
     * @param id The ID of the Professor to be deleted.
     * @return A response with no content indicating the deletion is successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        logger.info("Deleting Professor with ID: {}", id);
        professorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Checks if the Professor exists by its ID.
     * @param id The ID of the Professor entity.
     * @return `true` if the entity exists, otherwise `false`.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsById(@PathVariable UUID id) {
        logger.info("Checking if Professor exists with ID: {}", id);
        boolean exists = professorService.existsById(id);
        return ResponseEntity.ok(exists);
    }
    /**
     * Counts the number of Professor records.
     * @return The count of Professor records.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        logger.info("Counting Professor records");
        return ResponseEntity.ok(professorService.count());
    }

}
