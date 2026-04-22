package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.service.ProfessionSystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing Profession System resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession System", description = "Profession System API")
@RequestMapping("/api/profession-system")
public class ProfessionSystemController {

    private final ProfessionSystemService professionSystemService;

    /**
     * Retrieves all profession systems.
     * @return list of ProfessionSystemDto
     */
    @Operation(summary = "Get all profession systems")
    @GetMapping
    public ResponseEntity<List<ProfessionSystemDto>> getAll() {
        return ResponseEntity.ok(professionSystemService.getAllProfessionSystems());
    }

    /**
     * Retrieves the profession system record by id.
     * @param id profession system identifier
     * @return ProfessionSystemDto
     */
    @Operation(summary = "Get Profession System by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionSystemDto> getById(
            @Parameter(description = "profession system identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(professionSystemService.getProfessionSystemById(id));
    }

    /**
     * Creates a new profession system record.
     * @param dto profession system payload
     * @return created ProfessionSystemDto
     */
    @Operation(summary = "Create Profession System")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionSystemDto> create(
            @Valid @RequestBody ProfessionSystemDto dto) {
        ProfessionSystemDto created = professionSystemService.createProfessionSystem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing profession system record.
     * Only fields that are not null in the request are updated.
     * @param id profession system identifier
     * @param dto partial profession system payload
     * @return updated ProfessionSystemDto
     */
    @Operation(summary = "Patch Profession System")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionSystemDto> patch(
            @Parameter(description = "profession system identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ProfessionSystemDto dto) {
        return ResponseEntity.ok(professionSystemService.updateProfessionSystem(id, dto));
    }

    /**
     * Delete an profession system record by id.
     * @param id profession system identifier
     * @return no content
     */
    @Operation(summary = "Delete Profession System")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession system identifier", required = true)
            @PathVariable UUID id) {
        professionSystemService.deleteProfessionSystem(id);
        return ResponseEntity.noContent().build();
    }

}
