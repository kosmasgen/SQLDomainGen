package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.service.ProfessionKindi18nService;
import gr.knowledge.pepTest.entity.ProfessionKindi18nPK;

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
 * REST controller for managing ProfessionKindi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ProfessionKindi18n", description = "ProfessionKindi18n API")
@RequestMapping("/api/profession-kindi18ns")
public class ProfessionKindi18nController {

    private final ProfessionKindi18nService professionKindi18nService;

    /**
     * Retrieves all records.
     *
     * @return list of ProfessionKindi18nDto
     */
    @Operation(summary = "Get all ProfessionKindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProfessionKindi18nDto>> getAll() {
        log.info("Fetching all professionkindi18n records.");
        return ResponseEntity.ok(professionKindi18nService.getAllProfessionKindi18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProfessionKindi18nDto
     */
    @Operation(summary = "Get ProfessionKindi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<ProfessionKindi18nDto> getById(
            @Parameter(description = "profession_kind_id", required = true)
            @RequestParam(name = "profession_kind_id") UUID professionKindId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ProfessionKindi18nPK id = buildProfessionKindi18nId(professionKindId, languageId);
        log.info("Fetching professionkindi18n with composite id: {}", id);
        return ResponseEntity.ok(professionKindi18nService.getProfessionKindi18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProfessionKindi18nDto
     */
    @Operation(summary = "Create ProfessionKindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionKindi18nDto> create(@RequestBody ProfessionKindi18nDto dto) {
        log.info("Creating professionkindi18n.");
        ProfessionKindi18nDto created = professionKindi18nService.createProfessionKindi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProfessionKindi18nDto
     */
    @Operation(summary = "Update ProfessionKindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<ProfessionKindi18nDto> update(
            @Parameter(description = "profession_kind_id", required = true)
            @RequestParam(name = "profession_kind_id") UUID professionKindId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody ProfessionKindi18nDto dto) {
        ProfessionKindi18nPK id = buildProfessionKindi18nId(professionKindId, languageId);
        log.info("Updating professionkindi18n with composite id: {}", id);
        return ResponseEntity.ok(professionKindi18nService.updateProfessionKindi18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ProfessionKindi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession_kind_id", required = true)
            @RequestParam(name = "profession_kind_id") UUID professionKindId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ProfessionKindi18nPK id = buildProfessionKindi18nId(professionKindId, languageId);
        log.info("Deleting professionkindi18n with composite id: {}", id);
        professionKindi18nService.deleteProfessionKindi18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for ProfessionKindi18n.
     */
    private ProfessionKindi18nPK buildProfessionKindi18nId(UUID professionKindId, UUID languageId) {
        ProfessionKindi18nPK id = new ProfessionKindi18nPK();
        id.setProfessionKindId(professionKindId);
        id.setLanguageId(languageId);
        return id;
    }
}
