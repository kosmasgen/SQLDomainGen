package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.service.ProfessionSystemService;

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
 * REST controller for managing ProfessionSystem resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ProfessionSystem", description = "ProfessionSystem API")
@RequestMapping("/api/profession-systems")
public class ProfessionSystemController {

    private final ProfessionSystemService professionSystemService;

    /**
     * Retrieves all records.
     *
     * @return list of ProfessionSystemDto
     */
    @Operation(summary = "Get all ProfessionSystem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProfessionSystemDto>> getAll() {
        log.info("Fetching all professionsystem records.");
        return ResponseEntity.ok(professionSystemService.getAllProfessionSystem());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProfessionSystemDto
     */
    @Operation(summary = "Get ProfessionSystem by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionSystemDto> getById(
            @Parameter(description = "ProfessionSystem id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching professionsystem with id: {}", id);
        return ResponseEntity.ok(professionSystemService.getProfessionSystemById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProfessionSystemDto
     */
    @Operation(summary = "Create ProfessionSystem")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionSystemDto> create(@RequestBody ProfessionSystemDto dto) {
        log.info("Creating professionsystem.");
        ProfessionSystemDto created = professionSystemService.createProfessionSystem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProfessionSystemDto
     */
    @Operation(summary = "Update ProfessionSystem")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionSystemDto> update(
            @Parameter(description = "ProfessionSystem id", required = true)
            @PathVariable UUID id,
            @RequestBody ProfessionSystemDto dto) {
        log.info("Updating professionsystem with id: {}", id);
        return ResponseEntity.ok(professionSystemService.updateProfessionSystem(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ProfessionSystem")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ProfessionSystem id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting professionsystem with id: {}", id);
        professionSystemService.deleteProfessionSystem(id);
        return ResponseEntity.noContent().build();
    }
}
