package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.service.ProfessionKindi18nService;
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
 * REST controller for managing Profession Kindi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession Kindi18n", description = "Profession Kindi18n API")
@RequestMapping("/api/profession-kindi18n")
public class ProfessionKindi18nController {

    private final ProfessionKindi18nService professionKindi18nService;

    /**
     * Retrieves all profession kindi18ns.
     * @return list of ProfessionKindi18nDto
     */
    @Operation(summary = "Get all profession kindi18ns")
    @GetMapping
    public ResponseEntity<List<ProfessionKindi18nDto>> getAll() {
        return ResponseEntity.ok(professionKindi18nService.getAllProfessionKindi18ns());
    }

    /**
     * Retrieves the profession kindi18n record by id.
     * @param professionKindId profession kind id identifier
     * @param languageId language id identifier
     * @return ProfessionKindi18nDto
     */
    @Operation(summary = "Get Profession Kindi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{professionKindId}/{languageId}")
    public ResponseEntity<ProfessionKindi18nDto> getById(
            @Parameter(description = "profession kind id identifier", required = true)
            @PathVariable UUID professionKindId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(professionKindi18nService.getProfessionKindi18nById(professionKindId, languageId));
    }

    /**
     * Creates a new profession kindi18n record.
     * @param dto profession kindi18n payload
     * @return created ProfessionKindi18nDto
     */
    @Operation(summary = "Create Profession Kindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionKindi18nDto> create(
            @Valid @RequestBody ProfessionKindi18nDto dto) {
        ProfessionKindi18nDto created = professionKindi18nService.createProfessionKindi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing profession kindi18n record.
     * Only fields that are not null in the request are updated.
     * @param professionKindId profession kind id identifier
     * @param languageId language id identifier
     * @param dto partial profession kindi18n payload
     * @return updated ProfessionKindi18nDto
     */
    @Operation(summary = "Patch Profession Kindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{professionKindId}/{languageId}")
    public ResponseEntity<ProfessionKindi18nDto> patch(
            @Parameter(description = "profession kind id identifier", required = true)
            @PathVariable UUID professionKindId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @RequestBody ProfessionKindi18nDto dto) {
        return ResponseEntity.ok(professionKindi18nService.updateProfessionKindi18n(professionKindId, languageId, dto));
    }

    /**
     * Delete an profession kindi18n record by id.
     * @param professionKindId profession kind id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Profession Kindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{professionKindId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession kind id identifier", required = true)
            @PathVariable UUID professionKindId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        professionKindi18nService.deleteProfessionKindi18n(professionKindId, languageId);
        return ResponseEntity.noContent().build();
    }

}
