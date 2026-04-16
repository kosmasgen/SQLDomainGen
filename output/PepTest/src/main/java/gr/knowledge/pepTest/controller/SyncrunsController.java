package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.SyncrunsDto;
import gr.knowledge.pepTest.service.SyncrunsService;
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

/**
 * REST controller for managing Syncruns resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Syncruns", description = "Syncruns API")
@RequestMapping("/api/syncruns")
public class SyncrunsController {

    private final SyncrunsService syncrunsService;

    /**
     * Retrieves all syncrunses.
     * @return list of SyncrunsDto
     */
    @Operation(summary = "Get all syncrunses")
    @GetMapping
    public ResponseEntity<List<SyncrunsDto>> getAll() {
        return ResponseEntity.ok(syncrunsService.getAllSyncrunses());
    }

    /**
     * Retrieves the syncruns record by id.
     * @param id syncruns identifier
     * @return SyncrunsDto
     */
    @Operation(summary = "Get Syncruns by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SyncrunsDto> getById(
            @Parameter(description = "syncruns identifier", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(syncrunsService.getSyncrunsById(id));
    }

    /**
     * Creates a new syncruns record.
     * @param dto syncruns payload
     * @return created SyncrunsDto
     */
    @Operation(summary = "Create Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<SyncrunsDto> create(
            @Valid @RequestBody SyncrunsDto dto) {
        SyncrunsDto created = syncrunsService.createSyncruns(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing syncruns record.
     * Only fields that are not null in the request are updated.
     * @param id syncruns identifier
     * @param dto partial syncruns payload
     * @return updated SyncrunsDto
     */
    @Operation(summary = "Patch Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<SyncrunsDto> patch(
            @Parameter(description = "syncruns identifier", required = true)
            @PathVariable Long id,
            @RequestBody SyncrunsDto dto) {
        return ResponseEntity.ok(syncrunsService.updateSyncruns(id, dto));
    }

    /**
     * Delete an syncruns record by id.
     * @param id syncruns identifier
     * @return no content
     */
    @Operation(summary = "Delete Syncruns")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "syncruns identifier", required = true)
            @PathVariable Long id) {
        syncrunsService.deleteSyncruns(id);
        return ResponseEntity.noContent().build();
    }

}
