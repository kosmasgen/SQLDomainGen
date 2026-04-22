package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.service.ChamberAppUserService;
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
 * REST controller for managing Chamber App User resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Chamber App User", description = "Chamber App User API")
@RequestMapping("/api/chamber-app-user")
public class ChamberAppUserController {

    private final ChamberAppUserService chamberAppUserService;

    /**
     * Retrieves all chamber app users.
     * @return list of ChamberAppUserDto
     */
    @Operation(summary = "Get all chamber app users")
    @GetMapping
    public ResponseEntity<List<ChamberAppUserDto>> getAll() {
        return ResponseEntity.ok(chamberAppUserService.getAllChamberAppUsers());
    }

    /**
     * Retrieves the chamber app user record by id.
     * @param id chamber app user identifier
     * @return ChamberAppUserDto
     */
    @Operation(summary = "Get Chamber App User by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChamberAppUserDto> getById(
            @Parameter(description = "chamber app user identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(chamberAppUserService.getChamberAppUserById(id));
    }

    /**
     * Creates a new chamber app user record.
     * @param dto chamber app user payload
     * @return created ChamberAppUserDto
     */
    @Operation(summary = "Create Chamber App User")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberAppUserDto> create(
            @Valid @RequestBody ChamberAppUserDto dto) {
        ChamberAppUserDto created = chamberAppUserService.createChamberAppUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing chamber app user record.
     * Only fields that are not null in the request are updated.
     * @param id chamber app user identifier
     * @param dto partial chamber app user payload
     * @return updated ChamberAppUserDto
     */
    @Operation(summary = "Patch Chamber App User")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ChamberAppUserDto> patch(
            @Parameter(description = "chamber app user identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ChamberAppUserDto dto) {
        return ResponseEntity.ok(chamberAppUserService.updateChamberAppUser(id, dto));
    }

    /**
     * Delete an chamber app user record by id.
     * @param id chamber app user identifier
     * @return no content
     */
    @Operation(summary = "Delete Chamber App User")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "chamber app user identifier", required = true)
            @PathVariable UUID id) {
        chamberAppUserService.deleteChamberAppUser(id);
        return ResponseEntity.noContent().build();
    }

}
