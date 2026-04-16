package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.service.ChAppUserContactService;
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
 * REST controller for managing Ch App User Contact resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Ch App User Contact", description = "Ch App User Contact API")
@RequestMapping("/api/ch-app-user-contact")
public class ChAppUserContactController {

    private final ChAppUserContactService chAppUserContactService;

    /**
     * Retrieves all ch app user contacts.
     * @return list of ChAppUserContactDto
     */
    @Operation(summary = "Get all ch app user contacts")
    @GetMapping
    public ResponseEntity<List<ChAppUserContactDto>> getAll() {
        return ResponseEntity.ok(chAppUserContactService.getAllChAppUserContacts());
    }

    /**
     * Retrieves the ch app user contact record by id.
     * @param id ch app user contact identifier
     * @return ChAppUserContactDto
     */
    @Operation(summary = "Get Ch App User Contact by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChAppUserContactDto> getById(
            @Parameter(description = "ch app user contact identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(chAppUserContactService.getChAppUserContactById(id));
    }

    /**
     * Creates a new ch app user contact record.
     * @param dto ch app user contact payload
     * @return created ChAppUserContactDto
     */
    @Operation(summary = "Create Ch App User Contact")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChAppUserContactDto> create(
            @Valid @RequestBody ChAppUserContactDto dto) {
        ChAppUserContactDto created = chAppUserContactService.createChAppUserContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing ch app user contact record.
     * Only fields that are not null in the request are updated.
     * @param id ch app user contact identifier
     * @param dto partial ch app user contact payload
     * @return updated ChAppUserContactDto
     */
    @Operation(summary = "Patch Ch App User Contact")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ChAppUserContactDto> patch(
            @Parameter(description = "ch app user contact identifier", required = true)
            @PathVariable UUID id,
            @RequestBody ChAppUserContactDto dto) {
        return ResponseEntity.ok(chAppUserContactService.updateChAppUserContact(id, dto));
    }

    /**
     * Delete an ch app user contact record by id.
     * @param id ch app user contact identifier
     * @return no content
     */
    @Operation(summary = "Delete Ch App User Contact")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ch app user contact identifier", required = true)
            @PathVariable UUID id) {
        chAppUserContactService.deleteChAppUserContact(id);
        return ResponseEntity.noContent().build();
    }

}
