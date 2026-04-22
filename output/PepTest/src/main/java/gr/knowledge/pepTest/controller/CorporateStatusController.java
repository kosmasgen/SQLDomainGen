package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.service.CorporateStatusService;
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
 * REST controller for managing Corporate Status resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Corporate Status", description = "Corporate Status API")
@RequestMapping("/api/corporate-status")
public class CorporateStatusController {

    private final CorporateStatusService corporateStatusService;

    /**
     * Retrieves all corporate statuses.
     * @return list of CorporateStatusDto
     */
    @Operation(summary = "Get all corporate statuses")
    @GetMapping
    public ResponseEntity<List<CorporateStatusDto>> getAll() {
        return ResponseEntity.ok(corporateStatusService.getAllCorporateStatuses());
    }

    /**
     * Retrieves the corporate status record by id.
     * @param id corporate status identifier
     * @return CorporateStatusDto
     */
    @Operation(summary = "Get Corporate Status by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CorporateStatusDto> getById(
            @Parameter(description = "corporate status identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(corporateStatusService.getCorporateStatusById(id));
    }

    /**
     * Creates a new corporate status record.
     * @param dto corporate status payload
     * @return created CorporateStatusDto
     */
    @Operation(summary = "Create Corporate Status")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CorporateStatusDto> create(
            @Valid @RequestBody CorporateStatusDto dto) {
        CorporateStatusDto created = corporateStatusService.createCorporateStatus(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing corporate status record.
     * Only fields that are not null in the request are updated.
     * @param id corporate status identifier
     * @param dto partial corporate status payload
     * @return updated CorporateStatusDto
     */
    @Operation(summary = "Patch Corporate Status")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CorporateStatusDto> patch(
            @Parameter(description = "corporate status identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CorporateStatusDto dto) {
        return ResponseEntity.ok(corporateStatusService.updateCorporateStatus(id, dto));
    }

    /**
     * Delete an corporate status record by id.
     * @param id corporate status identifier
     * @return no content
     */
    @Operation(summary = "Delete Corporate Status")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "corporate status identifier", required = true)
            @PathVariable UUID id) {
        corporateStatusService.deleteCorporateStatus(id);
        return ResponseEntity.noContent().build();
    }

}
