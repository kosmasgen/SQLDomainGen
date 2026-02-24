package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Producti18nDto;
import gr.knowledge.pepTest.service.Producti18nService;
import gr.knowledge.pepTest.entity.Producti18nPK;

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
 * REST controller for managing Producti18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Producti18n", description = "Producti18n API")
@RequestMapping("/api/producti18ns")
public class Producti18nController {

    private final Producti18nService producti18nService;

    /**
     * Retrieves all records.
     *
     * @return list of Producti18nDto
     */
    @Operation(summary = "Get all Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<Producti18nDto>> getAll() {
        log.info("Fetching all producti18n records.");
        return ResponseEntity.ok(producti18nService.getAllProducti18n());
    }

    /**
     * Retrieves a record by id.
     *
     * @return Producti18nDto
     */
    @Operation(summary = "Get Producti18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/by-id")
    public ResponseEntity<Producti18nDto> getById(
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "product_id", required = true)
            @RequestParam(name = "product_id") UUID productId) {
        Producti18nPK id = buildProducti18nId(languageId, productId);
        log.info("Fetching producti18n with composite id: {}", id);
        return ResponseEntity.ok(producti18nService.getProducti18nById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created Producti18nDto
     */
    @Operation(summary = "Create Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<Producti18nDto> create(@RequestBody Producti18nDto dto) {
        log.info("Creating producti18n.");
        Producti18nDto created = producti18nService.createProducti18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated Producti18nDto
     */
    @Operation(summary = "Update Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/by-id")
    public ResponseEntity<Producti18nDto> update(
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "product_id", required = true)
            @RequestParam(name = "product_id") UUID productId,
            @RequestBody Producti18nDto dto) {
        Producti18nPK id = buildProducti18nId(languageId, productId);
        log.info("Updating producti18n with composite id: {}", id);
        return ResponseEntity.ok(producti18nService.updateProducti18n(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Producti18n")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/by-id")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "language_id", required = true)
            @RequestParam(name = "language_id") UUID languageId,
            @Parameter(description = "product_id", required = true)
            @RequestParam(name = "product_id") UUID productId) {
        Producti18nPK id = buildProducti18nId(languageId, productId);
        log.info("Deleting producti18n with composite id: {}", id);
        producti18nService.deleteProducti18n(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Builds composite id object for Producti18n.
     */
    private Producti18nPK buildProducti18nId(UUID languageId, UUID productId) {
        Producti18nPK id = new Producti18nPK();
        id.setLanguageId(languageId);
        id.setProductId(productId);
        return id;
    }
}
