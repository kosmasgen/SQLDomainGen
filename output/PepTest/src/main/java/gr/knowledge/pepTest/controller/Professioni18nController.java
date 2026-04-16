package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.service.Professioni18nService;
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
 * REST controller for managing Professioni18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Professioni18n", description = "Professioni18n API")
@RequestMapping("/api/professioni18n")
public class Professioni18nController {

    private final Professioni18nService professioni18nService;

    /**
     * Retrieves all professioni18ns.
     * @return list of Professioni18nDto
     */
    @Operation(summary = "Get all professioni18ns")
    @GetMapping
    public ResponseEntity<List<Professioni18nDto>> getAll() {
        return ResponseEntity.ok(professioni18nService.getAllProfessioni18ns());
    }

    /**
     * Retrieves the professioni18n record by id.
     * @param professionId profession id identifier
     * @param languageId language id identifier
     * @return Professioni18nDto
     */
    @Operation(summary = "Get Professioni18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{professionId}/{languageId}")
    public ResponseEntity<Professioni18nDto> getById(
            @Parameter(description = "profession id identifier", required = true)
            @PathVariable UUID professionId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(professioni18nService.getProfessioni18nById(professionId, languageId));
    }

    /**
     * Creates a new professioni18n record.
     * @param dto professioni18n payload
     * @return created Professioni18nDto
     */
    @Operation(summary = "Create Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Professioni18nDto> create(
            @Valid @RequestBody Professioni18nDto dto) {
        Professioni18nDto created = professioni18nService.createProfessioni18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing professioni18n record.
     * Only fields that are not null in the request are updated.
     * @param professionId profession id identifier
     * @param languageId language id identifier
     * @param dto partial professioni18n payload
     * @return updated Professioni18nDto
     */
    @Operation(summary = "Patch Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{professionId}/{languageId}")
    public ResponseEntity<Professioni18nDto> patch(
            @Parameter(description = "profession id identifier", required = true)
            @PathVariable UUID professionId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @RequestBody Professioni18nDto dto) {
        return ResponseEntity.ok(professioni18nService.updateProfessioni18n(professionId, languageId, dto));
    }

    /**
     * Delete an professioni18n record by id.
     * @param professionId profession id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{professionId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession id identifier", required = true)
            @PathVariable UUID professionId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        professioni18nService.deleteProfessioni18n(professionId, languageId);
        return ResponseEntity.noContent().build();
    }

}
