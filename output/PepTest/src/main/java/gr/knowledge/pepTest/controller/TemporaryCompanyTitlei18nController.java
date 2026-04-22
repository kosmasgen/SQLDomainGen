package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;
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
 * REST controller for managing Temporary Company Titlei18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Temporary Company Titlei18n", description = "Temporary Company Titlei18n API")
@RequestMapping("/api/temporary-company-titlei18n")
public class TemporaryCompanyTitlei18nController {

    private final TemporaryCompanyTitlei18nService temporaryCompanyTitlei18nService;

    /**
     * Retrieves all temporary company titlei18ns.
     * @return list of TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Get all temporary company titlei18ns")
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyTitlei18nDto>> getAll() {
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18ns());
    }

    /**
     * Retrieves the temporary company titlei18n record by id.
     * @param id temporary company titlei18n identifier
     * @return TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Get Temporary Company Titlei18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitlei18nDto> getById(
            @Parameter(description = "temporary company titlei18n identifier", required = true)
            @PathVariable BigInteger id) {
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id));
    }

    /**
     * Creates a new temporary company titlei18n record.
     * @param dto temporary company titlei18n payload
     * @return created TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Create Temporary Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyTitlei18nDto> create(
            @Valid @RequestBody TemporaryCompanyTitlei18nDto dto) {
        TemporaryCompanyTitlei18nDto created = temporaryCompanyTitlei18nService.createTemporaryCompanyTitlei18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing temporary company titlei18n record.
     * Only fields that are not null in the request are updated.
     * @param id temporary company titlei18n identifier
     * @param dto partial temporary company titlei18n payload
     * @return updated TemporaryCompanyTitlei18nDto
     */
    @Operation(summary = "Patch Temporary Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemporaryCompanyTitlei18nDto> patch(
            @Parameter(description = "temporary company titlei18n identifier", required = true)
            @PathVariable BigInteger id,
            @Valid @RequestBody TemporaryCompanyTitlei18nDto dto) {
        return ResponseEntity.ok(temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(id, dto));
    }

    /**
     * Delete an temporary company titlei18n record by id.
     * @param id temporary company titlei18n identifier
     * @return no content
     */
    @Operation(summary = "Delete Temporary Company Titlei18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "temporary company titlei18n identifier", required = true)
            @PathVariable BigInteger id) {
        temporaryCompanyTitlei18nService.deleteTemporaryCompanyTitlei18n(id);
        return ResponseEntity.noContent().build();
    }

}
