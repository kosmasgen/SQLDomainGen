package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.service.ProfessionKindService;

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
 * REST controller for managing ProfessionKind resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ProfessionKind", description = "ProfessionKind API")
@RequestMapping("/api/profession-kinds")
public class ProfessionKindController {

    private final ProfessionKindService professionKindService;

    /**
     * Retrieves all records.
     *
     * @return list of ProfessionKindDto
     */
    @Operation(summary = "Get all ProfessionKind")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProfessionKindDto>> getAll() {
        log.info("Fetching all professionkind records.");
        return ResponseEntity.ok(professionKindService.getAllProfessionKind());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProfessionKindDto
     */
    @Operation(summary = "Get ProfessionKind by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionKindDto> getById(
            @Parameter(description = "ProfessionKind id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching professionkind with id: {}", id);
        return ResponseEntity.ok(professionKindService.getProfessionKindById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProfessionKindDto
     */
    @Operation(summary = "Create ProfessionKind")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionKindDto> create(@RequestBody ProfessionKindDto dto) {
        log.info("Creating professionkind.");
        ProfessionKindDto created = professionKindService.createProfessionKind(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProfessionKindDto
     */
    @Operation(summary = "Update ProfessionKind")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionKindDto> update(
            @Parameter(description = "ProfessionKind id", required = true)
            @PathVariable UUID id,
            @RequestBody ProfessionKindDto dto) {
        log.info("Updating professionkind with id: {}", id);
        return ResponseEntity.ok(professionKindService.updateProfessionKind(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ProfessionKind")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ProfessionKind id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting professionkind with id: {}", id);
        professionKindService.deleteProfessionKind(id);
        return ResponseEntity.noContent().build();
    }
}
