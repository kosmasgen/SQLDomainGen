package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.service.FolderService;
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
 * REST controller for managing Folder resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Folder", description = "Folder API")
@RequestMapping("/api/folder")
public class FolderController {

    private final FolderService folderService;

    /**
     * Retrieves all folders.
     * @return list of FolderDto
     */
    @Operation(summary = "Get all folders")
    @GetMapping
    public ResponseEntity<List<FolderDto>> getAll() {
        return ResponseEntity.ok(folderService.getAllFolders());
    }

    /**
     * Retrieves the folder record by id.
     * @param id folder identifier
     * @return FolderDto
     */
    @Operation(summary = "Get Folder by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FolderDto> getById(
            @Parameter(description = "folder identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    /**
     * Creates a new folder record.
     * @param dto folder payload
     * @return created FolderDto
     */
    @Operation(summary = "Create Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<FolderDto> create(
            @Valid @RequestBody FolderDto dto) {
        FolderDto created = folderService.createFolder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing folder record.
     * Only fields that are not null in the request are updated.
     * @param id folder identifier
     * @param dto partial folder payload
     * @return updated FolderDto
     */
    @Operation(summary = "Patch Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<FolderDto> patch(
            @Parameter(description = "folder identifier", required = true)
            @PathVariable UUID id,
            @RequestBody FolderDto dto) {
        return ResponseEntity.ok(folderService.updateFolder(id, dto));
    }

    /**
     * Delete an folder record by id.
     * @param id folder identifier
     * @return no content
     */
    @Operation(summary = "Delete Folder")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "folder identifier", required = true)
            @PathVariable UUID id) {
        folderService.deleteFolder(id);
        return ResponseEntity.noContent().build();
    }

}
