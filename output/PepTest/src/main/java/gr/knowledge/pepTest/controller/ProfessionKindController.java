package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.service.ProfessionKindService;
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
 * REST controller for managing Profession Kind resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession Kind", description = "Profession Kind API")
@RequestMapping("/api/profession-kind")
public class ProfessionKindController {

    private final ProfessionKindService professionKindService;

    /**
     * Retrieves all profession kinds.
     * @return list of ProfessionKindDto
     */
    @Operation(summary = "Get all profession kinds")
    @GetMapping
    public ResponseEntity<List<ProfessionKindDto>> getAll() {
        return ResponseEntity.ok(professionKindService.getAllProfessionKinds());
    }

    /**
     * Retrieves the profession kind record by id.
     * @param id profession kind identifier
     * @return ProfessionKindDto
     */
    @Operation(summary = "Get Profession Kind by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionKindDto> getById(
            @Parameter(description = "profession kind identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(professionKindService.getProfessionKindById(id));
    }

    /**
     * Creates a new profession kind record.
     * @param dto profession kind payload
     * @return created ProfessionKindDto
     */
    @Operation(summary = "Create Profession Kind")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionKindDto> create(
            @Valid @RequestBody ProfessionKindDto dto) {
        ProfessionKindDto created = professionKindService.createProfessionKind(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing profession kind record.
     * Only fields that are not null in the request are updated.
     * @param id profession kind identifier
     * @param dto partial profession kind payload
     * @return updated ProfessionKindDto
     */
    @Operation(summary = "Patch Profession Kind")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionKindDto> patch(
            @Parameter(description = "profession kind identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ProfessionKindDto dto) {
        return ResponseEntity.ok(professionKindService.updateProfessionKind(id, dto));
    }

    /**
     * Delete an profession kind record by id.
     * @param id profession kind identifier
     * @return no content
     */
    @Operation(summary = "Delete Profession Kind")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession kind identifier", required = true)
            @PathVariable UUID id) {
        professionKindService.deleteProfessionKind(id);
        return ResponseEntity.noContent().build();
    }

}
