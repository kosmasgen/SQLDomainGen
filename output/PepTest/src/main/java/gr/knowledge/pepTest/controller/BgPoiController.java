package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.service.BgPoiService;

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
 * REST controller for managing BgPoi resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "BgPoi", description = "BgPoi API")
@RequestMapping("/api/bg-pois")
public class BgPoiController {

    private final BgPoiService bgPoiService;

    /**
     * Retrieves all records.
     *
     * @return list of BgPoiDto
     */
    @Operation(summary = "Get all BgPoi")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<BgPoiDto>> getAll() {
        log.info("Fetching all bgpoi records.");
        return ResponseEntity.ok(bgPoiService.getAllBgPoi());
    }

    /**
     * Retrieves a record by id.
     *
     * @return BgPoiDto
     */
    @Operation(summary = "Get BgPoi by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BgPoiDto> getById(
            @Parameter(description = "BgPoi id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching bgpoi with id: {}", id);
        return ResponseEntity.ok(bgPoiService.getBgPoiById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created BgPoiDto
     */
    @Operation(summary = "Create BgPoi")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BgPoiDto> create(@RequestBody BgPoiDto dto) {
        log.info("Creating bgpoi.");
        BgPoiDto created = bgPoiService.createBgPoi(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated BgPoiDto
     */
    @Operation(summary = "Update BgPoi")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BgPoiDto> update(
            @Parameter(description = "BgPoi id", required = true)
            @PathVariable UUID id,
            @RequestBody BgPoiDto dto) {
        log.info("Updating bgpoi with id: {}", id);
        return ResponseEntity.ok(bgPoiService.updateBgPoi(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete BgPoi")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "BgPoi id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting bgpoi with id: {}", id);
        bgPoiService.deleteBgPoi(id);
        return ResponseEntity.noContent().build();
    }
}
