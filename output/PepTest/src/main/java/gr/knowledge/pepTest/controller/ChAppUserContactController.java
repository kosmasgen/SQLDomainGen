package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.service.ChAppUserContactService;

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
 * REST controller for managing ChAppUserContact resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ChAppUserContact", description = "ChAppUserContact API")
@RequestMapping("/api/ch-app-user-contacts")
public class ChAppUserContactController {

    private final ChAppUserContactService chAppUserContactService;

    /**
     * Retrieves all records.
     *
     * @return list of ChAppUserContactDto
     */
    @Operation(summary = "Get all ChAppUserContact")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ChAppUserContactDto>> getAll() {
        log.info("Fetching all chappusercontact records.");
        return ResponseEntity.ok(chAppUserContactService.getAllChAppUserContact());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ChAppUserContactDto
     */
    @Operation(summary = "Get ChAppUserContact by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChAppUserContactDto> getById(
            @Parameter(description = "ChAppUserContact id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching chappusercontact with id: {}", id);
        return ResponseEntity.ok(chAppUserContactService.getChAppUserContactById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ChAppUserContactDto
     */
    @Operation(summary = "Create ChAppUserContact")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChAppUserContactDto> create(@RequestBody ChAppUserContactDto dto) {
        log.info("Creating chappusercontact.");
        ChAppUserContactDto created = chAppUserContactService.createChAppUserContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ChAppUserContactDto
     */
    @Operation(summary = "Update ChAppUserContact")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ChAppUserContactDto> update(
            @Parameter(description = "ChAppUserContact id", required = true)
            @PathVariable UUID id,
            @RequestBody ChAppUserContactDto dto) {
        log.info("Updating chappusercontact with id: {}", id);
        return ResponseEntity.ok(chAppUserContactService.updateChAppUserContact(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ChAppUserContact")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ChAppUserContact id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting chappusercontact with id: {}", id);
        chAppUserContactService.deleteChAppUserContact(id);
        return ResponseEntity.noContent().build();
    }
}
