package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.service.Producti18nService;
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
 * REST controller for managing Producti18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Producti18n", description = "Producti18n API")
@RequestMapping("/api/producti18n")
public class Producti18nController {

    private final Producti18nService producti18nService;

    /**
     * Retrieves all producti18ns.
     * @return list of Producti18nDto
     */
    @Operation(summary = "Get all producti18ns")
    @GetMapping
    public ResponseEntity<List<Producti18nDto>> getAll() {
        return ResponseEntity.ok(producti18nService.getAllProducti18ns());
    }

    /**
     * Retrieves the producti18n record by id.
     * @param languageId language id identifier
     * @param productId product id identifier
     * @return Producti18nDto
     */
    @Operation(summary = "Get Producti18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{languageId}/{productId}")
    public ResponseEntity<Producti18nDto> getById(
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "product id identifier", required = true)
            @PathVariable UUID productId) {
        return ResponseEntity.ok(producti18nService.getProducti18nById(languageId, productId));
    }

    /**
     * Creates a new producti18n record.
     * @param dto producti18n payload
     * @return created Producti18nDto
     */
    @Operation(summary = "Create Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Producti18nDto> create(
            @Valid @RequestBody Producti18nDto dto) {
        Producti18nDto created = producti18nService.createProducti18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing producti18n record.
     * Only fields that are not null in the request are updated.
     * @param languageId language id identifier
     * @param productId product id identifier
     * @param dto partial producti18n payload
     * @return updated Producti18nDto
     */
    @Operation(summary = "Patch Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{languageId}/{productId}")
    public ResponseEntity<Producti18nDto> patch(
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "product id identifier", required = true)
            @PathVariable UUID productId,
            @Valid @RequestBody Producti18nDto dto) {
        return ResponseEntity.ok(producti18nService.updateProducti18n(languageId, productId, dto));
    }

    /**
     * Delete an producti18n record by id.
     * @param languageId language id identifier
     * @param productId product id identifier
     * @return no content
     */
    @Operation(summary = "Delete Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{languageId}/{productId}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "language id identifier", required = true)
            @PathVariable UUID languageId,
            @Parameter(description = "product id identifier", required = true)
            @PathVariable UUID productId) {
        producti18nService.deleteProducti18n(languageId, productId);
        return ResponseEntity.noContent().build();
    }

}
