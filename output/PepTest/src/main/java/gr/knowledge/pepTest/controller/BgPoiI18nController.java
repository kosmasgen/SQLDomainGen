package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.service.BgPoiI18nService;
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
 * REST controller for managing Bg Poi I18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Bg Poi I18n", description = "Bg Poi I18n API")
@RequestMapping("/api/bg-poi-i18n")
public class BgPoiI18nController {

    private final BgPoiI18nService bgPoiI18nService;

    /**
     * Retrieves all bg poi i18ns.
     * @return list of BgPoiI18nDto
     */
    @Operation(summary = "Get all bg poi i18ns")
    @GetMapping
    public ResponseEntity<List<BgPoiI18nDto>> getAll() {
        return ResponseEntity.ok(bgPoiI18nService.getAllBgPoiI18ns());
    }

    /**
     * Retrieves the bg poi i18n record by id.
     * @param id bg poi i18n identifier
     * @return BgPoiI18nDto
     */
    @Operation(summary = "Get Bg Poi I18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BgPoiI18nDto> getById(
            @Parameter(description = "bg poi i18n identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(bgPoiI18nService.getBgPoiI18nById(id));
    }

    /**
     * Creates a new bg poi i18n record.
     * @param dto bg poi i18n payload
     * @return created BgPoiI18nDto
     */
    @Operation(summary = "Create Bg Poi I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<BgPoiI18nDto> create(
            @Valid @RequestBody BgPoiI18nDto dto) {
        BgPoiI18nDto created = bgPoiI18nService.createBgPoiI18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing bg poi i18n record.
     * Only fields that are not null in the request are updated.
     * @param id bg poi i18n identifier
     * @param dto partial bg poi i18n payload
     * @return updated BgPoiI18nDto
     */
    @Operation(summary = "Patch Bg Poi I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BgPoiI18nDto> patch(
            @Parameter(description = "bg poi i18n identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody BgPoiI18nDto dto) {
        return ResponseEntity.ok(bgPoiI18nService.updateBgPoiI18n(id, dto));
    }

    /**
     * Delete an bg poi i18n record by id.
     * @param id bg poi i18n identifier
     * @return no content
     */
    @Operation(summary = "Delete Bg Poi I18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "bg poi i18n identifier", required = true)
            @PathVariable UUID id) {
        bgPoiI18nService.deleteBgPoiI18n(id);
        return ResponseEntity.noContent().build();
    }

}
