package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BgPoiDto;
import gr.knowledge.pepTest.service.BgPoiService;
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
 * REST controller for managing Bg Poi resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Bg Poi", description = "Bg Poi API")
@RequestMapping("/api/bg-poi")
public class BgPoiController {

    private final BgPoiService bgPoiService;

    /**
     * Retrieves all bg pois.
     * @return list of BgPoiDto
     */
    @Operation(summary = "Get all bg pois")
    @GetMapping
    public ResponseEntity<List<BgPoiDto>> getAll() {
        return ResponseEntity.ok(bgPoiService.getAllBgPois());
    }

    /**
     * Retrieves the bg poi record by id.
     * @param id bg poi identifier
     * @return BgPoiDto
     */
    @Operation(summary = "Get Bg Poi by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BgPoiDto> getById(
            @Parameter(description = "bg poi identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(bgPoiService.getBgPoiById(id));
    }

    /**
     * Creates a new bg poi record.
     * @param dto bg poi payload
     * @return created BgPoiDto
     */
    @Operation(summary = "Create Bg Poi")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BgPoiDto> create(
            @Valid @RequestBody BgPoiDto dto) {
        BgPoiDto created = bgPoiService.createBgPoi(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing bg poi record.
     * Only fields that are not null in the request are updated.
     * @param id bg poi identifier
     * @param dto partial bg poi payload
     * @return updated BgPoiDto
     */
    @Operation(summary = "Patch Bg Poi")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BgPoiDto> patch(
            @Parameter(description = "bg poi identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody BgPoiDto dto) {
        return ResponseEntity.ok(bgPoiService.updateBgPoi(id, dto));
    }

    /**
     * Delete an bg poi record by id.
     * @param id bg poi identifier
     * @return no content
     */
    @Operation(summary = "Delete Bg Poi")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "bg poi identifier", required = true)
            @PathVariable UUID id) {
        bgPoiService.deleteBgPoi(id);
        return ResponseEntity.noContent().build();
    }

}
