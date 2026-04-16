package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.service.MunicipalityService;
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
 * REST controller for managing Municipality resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Municipality", description = "Municipality API")
@RequestMapping("/api/municipality")
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    /**
     * Retrieves all municipalities.
     * @return list of MunicipalityDto
     */
    @Operation(summary = "Get all municipalities")
    @GetMapping
    public ResponseEntity<List<MunicipalityDto>> getAll() {
        return ResponseEntity.ok(municipalityService.getAllMunicipalities());
    }

    /**
     * Retrieves the municipality record by id.
     * @param id municipality identifier
     * @return MunicipalityDto
     */
    @Operation(summary = "Get Municipality by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MunicipalityDto> getById(
            @Parameter(description = "municipality identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(municipalityService.getMunicipalityById(id));
    }

    /**
     * Creates a new municipality record.
     * @param dto municipality payload
     * @return created MunicipalityDto
     */
    @Operation(summary = "Create Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<MunicipalityDto> create(
            @Valid @RequestBody MunicipalityDto dto) {
        MunicipalityDto created = municipalityService.createMunicipality(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing municipality record.
     * Only fields that are not null in the request are updated.
     * @param id municipality identifier
     * @param dto partial municipality payload
     * @return updated MunicipalityDto
     */
    @Operation(summary = "Patch Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<MunicipalityDto> patch(
            @Parameter(description = "municipality identifier", required = true)
            @PathVariable UUID id,
            @RequestBody MunicipalityDto dto) {
        return ResponseEntity.ok(municipalityService.updateMunicipality(id, dto));
    }

    /**
     * Delete an municipality record by id.
     * @param id municipality identifier
     * @return no content
     */
    @Operation(summary = "Delete Municipality")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "municipality identifier", required = true)
            @PathVariable UUID id) {
        municipalityService.deleteMunicipality(id);
        return ResponseEntity.noContent().build();
    }

}
