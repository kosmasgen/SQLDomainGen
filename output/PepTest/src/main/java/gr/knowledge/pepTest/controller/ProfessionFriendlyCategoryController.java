package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.service.ProfessionFriendlyCategoryService;
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

/**
 * REST controller for managing Profession Friendly Category resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Profession Friendly Category", description = "Profession Friendly Category API")
@RequestMapping("/api/profession-friendly-category")
public class ProfessionFriendlyCategoryController {

    private final ProfessionFriendlyCategoryService professionFriendlyCategoryService;

    /**
     * Retrieves all profession friendly categories.
     * @return list of ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Get all profession friendly categories")
    @GetMapping
    public ResponseEntity<List<ProfessionFriendlyCategoryDto>> getAll() {
        return ResponseEntity.ok(professionFriendlyCategoryService.getAllProfessionFriendlyCategories());
    }

    /**
     * Retrieves the profession friendly category record by id.
     * @param id profession friendly category identifier
     * @return ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Get Profession Friendly Category by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionFriendlyCategoryDto> getById(
            @Parameter(description = "profession friendly category identifier", required = true)
            @PathVariable String id) {
        return ResponseEntity.ok(professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id));
    }

    /**
     * Creates a new profession friendly category record.
     * @param dto profession friendly category payload
     * @return created ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Create Profession Friendly Category")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProfessionFriendlyCategoryDto> create(
            @Valid @RequestBody ProfessionFriendlyCategoryDto dto) {
        ProfessionFriendlyCategoryDto created = professionFriendlyCategoryService.createProfessionFriendlyCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing profession friendly category record.
     * Only fields that are not null in the request are updated.
     * @param id profession friendly category identifier
     * @param dto partial profession friendly category payload
     * @return updated ProfessionFriendlyCategoryDto
     */
    @Operation(summary = "Patch Profession Friendly Category")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProfessionFriendlyCategoryDto> patch(
            @Parameter(description = "profession friendly category identifier", required = true)
            @PathVariable String id,
            @Valid @RequestBody ProfessionFriendlyCategoryDto dto) {
        return ResponseEntity.ok(professionFriendlyCategoryService.updateProfessionFriendlyCategory(id, dto));
    }

    /**
     * Delete an profession friendly category record by id.
     * @param id profession friendly category identifier
     * @return no content
     */
    @Operation(summary = "Delete Profession Friendly Category")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "profession friendly category identifier", required = true)
            @PathVariable String id) {
        professionFriendlyCategoryService.deleteProfessionFriendlyCategory(id);
        return ResponseEntity.noContent().build();
    }

}
