package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.service.LanguagesService;
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
 * REST controller for managing Languages resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Languages", description = "Languages API")
@RequestMapping("/api/languages")
public class LanguagesController {

    private final LanguagesService languagesService;

    /**
     * Retrieves all languageses.
     * @return list of LanguagesDto
     */
    @Operation(summary = "Get all languageses")
    @GetMapping
    public ResponseEntity<List<LanguagesDto>> getAll() {
        return ResponseEntity.ok(languagesService.getAllLanguageses());
    }

    /**
     * Retrieves the languages record by id.
     * @param id languages identifier
     * @return LanguagesDto
     */
    @Operation(summary = "Get Languages by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LanguagesDto> getById(
            @Parameter(description = "languages identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(languagesService.getLanguagesById(id));
    }

    /**
     * Creates a new languages record.
     * @param dto languages payload
     * @return created LanguagesDto
     */
    @Operation(summary = "Create Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<LanguagesDto> create(
            @Valid @RequestBody LanguagesDto dto) {
        LanguagesDto created = languagesService.createLanguages(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing languages record.
     * Only fields that are not null in the request are updated.
     * @param id languages identifier
     * @param dto partial languages payload
     * @return updated LanguagesDto
     */
    @Operation(summary = "Patch Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<LanguagesDto> patch(
            @Parameter(description = "languages identifier", required = true)
            @PathVariable UUID id,
            @RequestBody LanguagesDto dto) {
        return ResponseEntity.ok(languagesService.updateLanguages(id, dto));
    }

    /**
     * Delete an languages record by id.
     * @param id languages identifier
     * @return no content
     */
    @Operation(summary = "Delete Languages")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "languages identifier", required = true)
            @PathVariable UUID id) {
        languagesService.deleteLanguages(id);
        return ResponseEntity.noContent().build();
    }

}
