package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.service.ChamberAppUserService;

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
 * REST controller for managing ChamberAppUser resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ChamberAppUser", description = "ChamberAppUser API")
@RequestMapping("/api/chamber-app-users")
public class ChamberAppUserController {

    private final ChamberAppUserService chamberAppUserService;

    /**
     * Retrieves all records.
     *
     * @return list of ChamberAppUserDto
     */
    @Operation(summary = "Get all ChamberAppUser")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ChamberAppUserDto>> getAll() {
        log.info("Fetching all chamberappuser records.");
        return ResponseEntity.ok(chamberAppUserService.getAllChamberAppUser());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ChamberAppUserDto
     */
    @Operation(summary = "Get ChamberAppUser by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ChamberAppUserDto> getById(
            @Parameter(description = "ChamberAppUser id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching chamberappuser with id: {}", id);
        return ResponseEntity.ok(chamberAppUserService.getChamberAppUserById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ChamberAppUserDto
     */
    @Operation(summary = "Create ChamberAppUser")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ChamberAppUserDto> create(@RequestBody ChamberAppUserDto dto) {
        log.info("Creating chamberappuser.");
        ChamberAppUserDto created = chamberAppUserService.createChamberAppUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ChamberAppUserDto
     */
    @Operation(summary = "Update ChamberAppUser")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ChamberAppUserDto> update(
            @Parameter(description = "ChamberAppUser id", required = true)
            @PathVariable UUID id,
            @RequestBody ChamberAppUserDto dto) {
        log.info("Updating chamberappuser with id: {}", id);
        return ResponseEntity.ok(chamberAppUserService.updateChamberAppUser(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ChamberAppUser")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ChamberAppUser id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting chamberappuser with id: {}", id);
        chamberAppUserService.deleteChamberAppUser(id);
        return ResponseEntity.noContent().build();
    }
}
