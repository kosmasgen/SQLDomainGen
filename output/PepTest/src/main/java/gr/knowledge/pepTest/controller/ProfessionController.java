package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.service.ProfessionService;
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
 * REST controller for managing Profession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession", description = "Profession API")
@RequestMapping("/api/profession")
public class ProfessionController {

    private final ProfessionService professionService;

    /**
     * Retrieves all professions.
     * @return list of ProfessionDto
     */
    @Operation(summary = "Get all professions")
    @GetMapping
    public ResponseEntity<List<ProfessionDto>> getAll() {
        return ResponseEntity.ok(professionService.getAllProfessions());
    }

    /**
     * Retrieves the profession record by id.
     * @param id profession identifier
     * @return ProfessionDto
     */
    @Operation(summary = "Get Profession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionDto> getById(
            @Parameter(description = "profession identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(professionService.getProfessionById(id));
    }

    /**
     * Creates a new profession record.
     * @param dto profession payload
     * @return created ProfessionDto
     */
    @Operation(summary = "Create Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionDto> create(
            @Valid @RequestBody ProfessionDto dto) {
        ProfessionDto created = professionService.createProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing profession record.
     * Only fields that are not null in the request are updated.
     * @param id profession identifier
     * @param dto partial profession payload
     * @return updated ProfessionDto
     */
    @Operation(summary = "Patch Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionDto> patch(
            @Parameter(description = "profession identifier", required = true)
            @PathVariable UUID id,
            @RequestBody ProfessionDto dto) {
        return ResponseEntity.ok(professionService.updateProfession(id, dto));
    }

    /**
     * Delete an profession record by id.
     * @param id profession identifier
     * @return no content
     */
    @Operation(summary = "Delete Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession identifier", required = true)
            @PathVariable UUID id) {
        professionService.deleteProfession(id);
        return ResponseEntity.noContent().build();
    }

}
