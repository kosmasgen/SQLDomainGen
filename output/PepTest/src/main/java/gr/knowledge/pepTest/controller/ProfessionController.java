package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.service.ProfessionService;

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

import java.util.UUID;
import java.util.List;

/**
 * REST controller for managing Profession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession", description = "Profession API")
@RequestMapping("/api/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    /**
     * Retrieves all records.
     *
     * @return list of ProfessionDto
     */
    @Operation(summary = "Get all Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProfessionDto>> getAll() {
        log.info("Fetching all profession records.");
        return ResponseEntity.ok(professionService.getAllProfession());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProfessionDto
     */
    @Operation(summary = "Get Profession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDto> getById(
            @Parameter(description = "Profession id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching profession with id: {}", id);
        return ResponseEntity.ok(professionService.getProfessionById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProfessionDto
     */
    @Operation(summary = "Create Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionDto> create(@RequestBody ProfessionDto dto) {
        log.info("Creating profession.");
        ProfessionDto created = professionService.createProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProfessionDto
     */
    @Operation(summary = "Update Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionDto> update(
            @Parameter(description = "Profession id", required = true)
            @PathVariable UUID id,
            @RequestBody ProfessionDto dto) {
        log.info("Updating profession with id: {}", id);
        return ResponseEntity.ok(professionService.updateProfession(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Profession id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting profession with id: {}", id);
        professionService.deleteProfession(id);
        return ResponseEntity.noContent().build();
    }
}
