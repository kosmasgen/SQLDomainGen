package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;

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

import java.util.List;

/**
 * REST controller for managing ProfessionFriendlyCategory resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "ProfessionFriendlyCategory", description = "ProfessionFriendlyCategory API")
@RequestMapping("/api/profession-friendly-categorys")
public class ProfessionFriendlyCategoryController {

    private final ProfessionFriendlyCategoryService professionFriendlyCategoryService;

    /**
     * Retrieves all records.
     *
     * @return list of ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Get all ProfessionFriendlyCategory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProfessionFriendlyCategoryDto>> getAll() {
        log.info("Fetching all professionfriendlycategory records.");
        return ResponseEntity.ok(professionFriendlyCategoryService.getAllProfessionFriendlyCategory());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Get ProfessionFriendlyCategory by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionFriendlyCategoryDto> getById(
            @Parameter(description = "ProfessionFriendlyCategory id", required = true)
            @PathVariable String id) {
        log.info("Fetching professionfriendlycategory with id: {}", id);
        return ResponseEntity.ok(professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Create ProfessionFriendlyCategory")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionFriendlyCategoryDto> create(@RequestBody ProfessionFriendlyCategoryDto dto) {
        log.info("Creating professionfriendlycategory.");
        ProfessionFriendlyCategoryDto created = professionFriendlyCategoryService.createProfessionFriendlyCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Update ProfessionFriendlyCategory")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionFriendlyCategoryDto> update(
            @Parameter(description = "ProfessionFriendlyCategory id", required = true)
            @PathVariable String id,
            @RequestBody ProfessionFriendlyCategoryDto dto) {
        log.info("Updating professionfriendlycategory with id: {}", id);
        return ResponseEntity.ok(professionFriendlyCategoryService.updateProfessionFriendlyCategory(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete ProfessionFriendlyCategory")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "ProfessionFriendlyCategory id", required = true)
            @PathVariable String id) {
        log.info("Deleting professionfriendlycategory with id: {}", id);
        professionFriendlyCategoryService.deleteProfessionFriendlyCategory(id);
        return ResponseEntity.noContent().build();
    }
}
