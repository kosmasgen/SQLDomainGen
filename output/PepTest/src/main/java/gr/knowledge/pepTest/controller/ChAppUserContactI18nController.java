package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nPK;

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
 * REST controller for managing ChAppUserContactI18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ChAppUserContactI18n", description = "ChAppUserContactI18n API")
@RequestMapping("/api/ch-app-user-contact-i18ns")
public class ChAppUserContactI18nController {

    private final ChAppUserContactI18nService chAppUserContactI18nService;

    /**
     * Retrieves all records.
     *
     * @return list of ChAppUserContactI18nDto
     */
    @Operation(summary = "Get all ChAppUserContactI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ChAppUserContactI18nDto>> getAll() {
        log.info("Fetching all chappusercontacti18n records.");
        return ResponseEntity.ok(chAppUserContactI18nService.getAllChAppUserContactI18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ChAppUserContactI18nDto
     */
    @Operation(summary = "Get ChAppUserContactI18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<ChAppUserContactI18nDto> getById(
            @Parameter(description = "ch_app_user_contact_id", required = true)
            @RequestParam(name = "ch_app_user_contact_id") UUID chAppUserContactId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ChAppUserContactI18nPK id = buildChAppUserContactI18nId(chAppUserContactId, languageId);
        log.info("Fetching chappusercontacti18n with composite id: {}", id);
        return ResponseEntity.ok(chAppUserContactI18nService.getChAppUserContactI18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ChAppUserContactI18nDto
     */
    @Operation(summary = "Create ChAppUserContactI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChAppUserContactI18nDto> create(@RequestBody ChAppUserContactI18nDto dto) {
        log.info("Creating chappusercontacti18n.");
        ChAppUserContactI18nDto created = chAppUserContactI18nService.createChAppUserContactI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ChAppUserContactI18nDto
     */
    @Operation(summary = "Update ChAppUserContactI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<ChAppUserContactI18nDto> update(
            @Parameter(description = "ch_app_user_contact_id", required = true)
            @RequestParam(name = "ch_app_user_contact_id") UUID chAppUserContactId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @RequestBody ChAppUserContactI18nDto dto) {
        ChAppUserContactI18nPK id = buildChAppUserContactI18nId(chAppUserContactId, languageId);
        log.info("Updating chappusercontacti18n with composite id: {}", id);
        return ResponseEntity.ok(chAppUserContactI18nService.updateChAppUserContactI18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ChAppUserContactI18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ch_app_user_contact_id", required = true)
            @RequestParam(name = "ch_app_user_contact_id") UUID chAppUserContactId,
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId) {
        ChAppUserContactI18nPK id = buildChAppUserContactI18nId(chAppUserContactId, languageId);
        log.info("Deleting chappusercontacti18n with composite id: {}", id);
        chAppUserContactI18nService.deleteChAppUserContactI18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for ChAppUserContactI18n.
     */
    private ChAppUserContactI18nPK buildChAppUserContactI18nId(UUID chAppUserContactId, UUID languageId) {
        ChAppUserContactI18nPK id = new ChAppUserContactI18nPK();
        id.setChAppUserContactId(chAppUserContactId);
        id.setLanguageId(languageId);
        return id;
    }
}
