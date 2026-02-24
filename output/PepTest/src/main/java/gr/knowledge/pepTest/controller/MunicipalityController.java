package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.service.MunicipalityService;

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
 * REST controller for managing Municipality resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Municipality", description = "Municipality API")
@RequestMapping("/api/municipalitys")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    /**
     * Retrieves all records.
     *
     * @return list of MunicipalityDto
     */
    @Operation(summary = "Get all Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<MunicipalityDto>> getAll() {
        log.info("Fetching all municipality records.");
        return ResponseEntity.ok(municipalityService.getAllMunicipality());
    }

    /**
     * Retrieves a record by id.
     *
     * @return MunicipalityDto
     */
    @Operation(summary = "Get Municipality by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MunicipalityDto> getById(
            @Parameter(description = "Municipality id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching municipality with id: {}", id);
        return ResponseEntity.ok(municipalityService.getMunicipalityById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created MunicipalityDto
     */
    @Operation(summary = "Create Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<MunicipalityDto> create(@RequestBody MunicipalityDto dto) {
        log.info("Creating municipality.");
        MunicipalityDto created = municipalityService.createMunicipality(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated MunicipalityDto
     */
    @Operation(summary = "Update Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MunicipalityDto> update(
            @Parameter(description = "Municipality id", required = true)
            @PathVariable UUID id,
            @RequestBody MunicipalityDto dto) {
        log.info("Updating municipality with id: {}", id);
        return ResponseEntity.ok(municipalityService.updateMunicipality(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Municipality id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting municipality with id: {}", id);
        municipalityService.deleteMunicipality(id);
        return ResponseEntity.noContent().build();
    }
}
