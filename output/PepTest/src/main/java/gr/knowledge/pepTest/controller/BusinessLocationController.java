package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.service.BusinessLocationService;

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
 * REST controller for managing BusinessLocation resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "BusinessLocation", description = "BusinessLocation API")
@RequestMapping("/api/business-locations")
public class BusinessLocationController {

    private final BusinessLocationService businessLocationService;

    /**
     * Retrieves all records.
     *
     * @return list of BusinessLocationDto
     */
    @Operation(summary = "Get all BusinessLocation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<BusinessLocationDto>> getAll() {
        log.info("Fetching all businesslocation records.");
        return ResponseEntity.ok(businessLocationService.getAllBusinessLocation());
    }

    /**
     * Retrieves a record by id.
     *
     * @return BusinessLocationDto
     */
    @Operation(summary = "Get BusinessLocation by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BusinessLocationDto> getById(
            @Parameter(description = "BusinessLocation id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching businesslocation with id: {}", id);
        return ResponseEntity.ok(businessLocationService.getBusinessLocationById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created BusinessLocationDto
     */
    @Operation(summary = "Create BusinessLocation")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BusinessLocationDto> create(@RequestBody BusinessLocationDto dto) {
        log.info("Creating businesslocation.");
        BusinessLocationDto created = businessLocationService.createBusinessLocation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated BusinessLocationDto
     */
    @Operation(summary = "Update BusinessLocation")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BusinessLocationDto> update(
            @Parameter(description = "BusinessLocation id", required = true)
            @PathVariable UUID id,
            @RequestBody BusinessLocationDto dto) {
        log.info("Updating businesslocation with id: {}", id);
        return ResponseEntity.ok(businessLocationService.updateBusinessLocation(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete BusinessLocation")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "BusinessLocation id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting businesslocation with id: {}", id);
        businessLocationService.deleteBusinessLocation(id);
        return ResponseEntity.noContent().build();
    }
}
