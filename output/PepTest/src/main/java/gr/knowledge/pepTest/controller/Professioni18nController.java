package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.service.Professioni18nService;
import gr.knowledge.pepTest.entity.Professioni18nPK;

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
 * REST controller for managing Professioni18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Professioni18n", description = "Professioni18n API")
@RequestMapping("/api/professioni18ns")
public class Professioni18nController {

    private final Professioni18nService professioni18nService;

    /**
     * Retrieves all records.
     *
     * @return list of Professioni18nDto
     */
    @Operation(summary = "Get all Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<Professioni18nDto>> getAll() {
        log.info("Fetching all professioni18n records.");
        return ResponseEntity.ok(professioni18nService.getAllProfessioni18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return Professioni18nDto
     */
    @Operation(summary = "Get Professioni18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<Professioni18nDto> getById(
            @Parameter(description = "profession_id", required = true)
            @RequestParam(name = "profession_id") UUID professionId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        Professioni18nPK id = buildProfessioni18nId(professionId, languageId);
        log.info("Fetching professioni18n with composite id: {}", id);
        return ResponseEntity.ok(professioni18nService.getProfessioni18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created Professioni18nDto
     */
    @Operation(summary = "Create Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Professioni18nDto> create(@RequestBody Professioni18nDto dto) {
        log.info("Creating professioni18n.");
        Professioni18nDto created = professioni18nService.createProfessioni18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated Professioni18nDto
     */
    @Operation(summary = "Update Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<Professioni18nDto> update(
            @Parameter(description = "profession_id", required = true)
            @RequestParam(name = "profession_id") UUID professionId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody Professioni18nDto dto) {
        Professioni18nPK id = buildProfessioni18nId(professionId, languageId);
        log.info("Updating professioni18n with composite id: {}", id);
        return ResponseEntity.ok(professioni18nService.updateProfessioni18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Professioni18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession_id", required = true)
            @RequestParam(name = "profession_id") UUID professionId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        Professioni18nPK id = buildProfessioni18nId(professionId, languageId);
        log.info("Deleting professioni18n with composite id: {}", id);
        professioni18nService.deleteProfessioni18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for Professioni18n.
     */
    private Professioni18nPK buildProfessioni18nId(UUID professionId, UUID languageId) {
        Professioni18nPK id = new Professioni18nPK();
        id.setProfessionId(professionId);
        id.setLanguageId(languageId);
        return id;
    }
}
