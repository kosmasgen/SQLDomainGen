package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.service.FolderService;

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
 * REST controller for managing Folder resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Folder", description = "Folder API")
@RequestMapping("/api/folders")
public class FolderController {

    private final FolderService folderService;

    /**
     * Retrieves all records.
     *
     * @return list of FolderDto
     */
    @Operation(summary = "Get all Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<FolderDto>> getAll() {
        log.info("Fetching all folder records.");
        return ResponseEntity.ok(folderService.getAllFolder());
    }

    /**
     * Retrieves a record by id.
     *
     * @return FolderDto
     */
    @Operation(summary = "Get Folder by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FolderDto> getById(
            @Parameter(description = "Folder id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching folder with id: {}", id);
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created FolderDto
     */
    @Operation(summary = "Create Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<FolderDto> create(@RequestBody FolderDto dto) {
        log.info("Creating folder.");
        FolderDto created = folderService.createFolder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated FolderDto
     */
    @Operation(summary = "Update Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FolderDto> update(
            @Parameter(description = "Folder id", required = true)
            @PathVariable UUID id,
            @RequestBody FolderDto dto) {
        log.info("Updating folder with id: {}", id);
        return ResponseEntity.ok(folderService.updateFolder(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Folder id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting folder with id: {}", id);
        folderService.deleteFolder(id);
        return ResponseEntity.noContent().build();
    }
}
