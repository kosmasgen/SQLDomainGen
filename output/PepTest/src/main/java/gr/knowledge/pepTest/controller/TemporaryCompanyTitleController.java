package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitleService;
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
import java.math.BigInteger;

/**
 * REST controller for managing Temporary Company Title resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Temporary Company Title", description = "Temporary Company Title API")
@RequestMapping("/api/temporary-company-title")
public class TemporaryCompanyTitleController {

    private final TemporaryCompanyTitleService temporaryCompanyTitleService;

    /**
     * Retrieves all temporary company titles.
     * @return list of TemporaryCompanyTitleDto
     */
    @Operation(summary = "Get all temporary company titles")
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyTitleDto>> getAll() {
        return ResponseEntity.ok(temporaryCompanyTitleService.getAllTemporaryCompanyTitles());
    }

    /**
     * Retrieves the temporary company title record by id.
     * @param id temporary company title identifier
     * @return TemporaryCompanyTitleDto
     */
    @Operation(summary = "Get Temporary Company Title by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitleDto> getById(
            @Parameter(description = "temporary company title identifier", required = true)
            @PathVariable BigInteger id) {
        return ResponseEntity.ok(temporaryCompanyTitleService.getTemporaryCompanyTitleById(id));
    }

    /**
     * Creates a new temporary company title record.
     * @param dto temporary company title payload
     * @return created TemporaryCompanyTitleDto
     */
    @Operation(summary = "Create Temporary Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyTitleDto> create(
            @Valid @RequestBody TemporaryCompanyTitleDto dto) {
        TemporaryCompanyTitleDto created = temporaryCompanyTitleService.createTemporaryCompanyTitle(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing temporary company title record.
     * Only fields that are not null in the request are updated.
     * @param id temporary company title identifier
     * @param dto partial temporary company title payload
     * @return updated TemporaryCompanyTitleDto
     */
    @Operation(summary = "Patch Temporary Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitleDto> patch(
            @Parameter(description = "temporary company title identifier", required = true)
            @PathVariable BigInteger id,
            @Valid @RequestBody TemporaryCompanyTitleDto dto) {
        return ResponseEntity.ok(temporaryCompanyTitleService.updateTemporaryCompanyTitle(id, dto));
    }

    /**
     * Delete an temporary company title record by id.
     * @param id temporary company title identifier
     * @return no content
     */
    @Operation(summary = "Delete Temporary Company Title")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "temporary company title identifier", required = true)
            @PathVariable BigInteger id) {
        temporaryCompanyTitleService.deleteTemporaryCompanyTitle(id);
        return ResponseEntity.noContent().build();
    }

}
