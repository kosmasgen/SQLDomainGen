package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.service.BusinessLocationService;
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
 * REST controller for managing Business Location resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Business Location", description = "Business Location API")
@RequestMapping("/api/business-location")
public class BusinessLocationController {

    private final BusinessLocationService businessLocationService;

    /**
     * Retrieves all business locations.
     * @return list of BusinessLocationDto
     */
    @Operation(summary = "Get all business locations")
    @GetMapping
    public ResponseEntity<List<BusinessLocationDto>> getAll() {
        return ResponseEntity.ok(businessLocationService.getAllBusinessLocations());
    }

    /**
     * Retrieves the business location record by id.
     * @param id business location identifier
     * @return BusinessLocationDto
     */
    @Operation(summary = "Get Business Location by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BusinessLocationDto> getById(
            @Parameter(description = "business location identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(businessLocationService.getBusinessLocationById(id));
    }

    /**
     * Creates a new business location record.
     * @param dto business location payload
     * @return created BusinessLocationDto
     */
    @Operation(summary = "Create Business Location")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BusinessLocationDto> create(
            @Valid @RequestBody BusinessLocationDto dto) {
        BusinessLocationDto created = businessLocationService.createBusinessLocation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing business location record.
     * Only fields that are not null in the request are updated.
     * @param id business location identifier
     * @param dto partial business location payload
     * @return updated BusinessLocationDto
     */
    @Operation(summary = "Patch Business Location")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BusinessLocationDto> patch(
            @Parameter(description = "business location identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody BusinessLocationDto dto) {
        return ResponseEntity.ok(businessLocationService.updateBusinessLocation(id, dto));
    }

    /**
     * Delete an business location record by id.
     * @param id business location identifier
     * @return no content
     */
    @Operation(summary = "Delete Business Location")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "business location identifier", required = true)
            @PathVariable UUID id) {
        businessLocationService.deleteBusinessLocation(id);
        return ResponseEntity.noContent().build();
    }

}
