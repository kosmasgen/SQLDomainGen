package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
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
 * REST controller for managing Ch App User Contact I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Ch App User Contact I18n", description = "Ch App User Contact I18n API")
@RequestMapping("/api/ch-app-user-contact-i18n")
public class ChAppUserContactI18nController {

    private final ChAppUserContactI18nService chAppUserContactI18nService;

    /**
     * Retrieves all ch app user contact i18ns.
     * @return list of ChAppUserContactI18nDto
     */
    @Operation(summary = "Get all ch app user contact i18ns")
    @GetMapping
    public ResponseEntity<List<ChAppUserContactI18nDto>> getAll() {
        return ResponseEntity.ok(chAppUserContactI18nService.getAllChAppUserContactI18ns());
    }

    /**
     * Retrieves the ch app user contact i18n record by id.
     * @param chAppUserContactId ch app user contact id identifier
     * @param languageId language id identifier
     * @return ChAppUserContactI18nDto
     */
    @Operation(summary = "Get Ch App User Contact I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{chAppUserContactId}/{languageId}")
    public ResponseEntity<ChAppUserContactI18nDto> getById(
            @Parameter(description = "ch app user contact id identifier", required = true)
            @PathVariable UUID chAppUserContactId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        return ResponseEntity.ok(chAppUserContactI18nService.getChAppUserContactI18nById(chAppUserContactId, languageId));
    }

    /**
     * Creates a new ch app user contact i18n record.
     * @param dto ch app user contact i18n payload
     * @return created ChAppUserContactI18nDto
     */
    @Operation(summary = "Create Ch App User Contact I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChAppUserContactI18nDto> create(
            @Valid @RequestBody ChAppUserContactI18nDto dto) {
        ChAppUserContactI18nDto created = chAppUserContactI18nService.createChAppUserContactI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing ch app user contact i18n record.
     * Only fields that are not null in the request are updated.
     * @param chAppUserContactId ch app user contact id identifier
     * @param languageId language id identifier
     * @param dto partial ch app user contact i18n payload
     * @return updated ChAppUserContactI18nDto
     */
    @Operation(summary = "Patch Ch App User Contact I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{chAppUserContactId}/{languageId}")
    public ResponseEntity<ChAppUserContactI18nDto> patch(
            @Parameter(description = "ch app user contact id identifier", required = true)
            @PathVariable UUID chAppUserContactId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @RequestBody ChAppUserContactI18nDto dto) {
        return ResponseEntity.ok(chAppUserContactI18nService.updateChAppUserContactI18n(chAppUserContactId, languageId, dto));
    }

    /**
     * Delete an ch app user contact i18n record by id.
     * @param chAppUserContactId ch app user contact id identifier
     * @param languageId language id identifier
     * @return no content
     */
    @Operation(summary = "Delete Ch App User Contact I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{chAppUserContactId}/{languageId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ch app user contact id identifier", required = true)
            @PathVariable UUID chAppUserContactId,
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId) {
        chAppUserContactI18nService.deleteChAppUserContactI18n(chAppUserContactId, languageId);
        return ResponseEntity.noContent().build();
    }

}
