package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyi18nService;
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
 * REST controller for managing Temporary Companyi18n resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Temporary Companyi18n", description = "Temporary Companyi18n API")
@RequestMapping("/api/temporary-companyi18n")
public class TemporaryCompanyi18nController {

    private final TemporaryCompanyi18nService temporaryCompanyi18nService;

    /**
     * Retrieves all temporary companyi18ns.
     * @return list of TemporaryCompanyi18nDto
     */
    @Operation(summary = "Get all temporary companyi18ns")
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyi18nDto>> getAll() {
        return ResponseEntity.ok(temporaryCompanyi18nService.getAllTemporaryCompanyi18ns());
    }

    /**
     * Retrieves the temporary companyi18n record by id.
     * @param id temporary companyi18n identifier
     * @return TemporaryCompanyi18nDto
     */
    @Operation(summary = "Get Temporary Companyi18n by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyi18nDto> getById(
            @Parameter(description = "temporary companyi18n identifier", required = true)
            @PathVariable BigInteger id) {
        return ResponseEntity.ok(temporaryCompanyi18nService.getTemporaryCompanyi18nById(id));
    }

    /**
     * Creates a new temporary companyi18n record.
     * @param dto temporary companyi18n payload
     * @return created TemporaryCompanyi18nDto
     */
    @Operation(summary = "Create Temporary Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyi18nDto> create(
            @Valid @RequestBody TemporaryCompanyi18nDto dto) {
        TemporaryCompanyi18nDto created = temporaryCompanyi18nService.createTemporaryCompanyi18n(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing temporary companyi18n record.
     * Only fields that are not null in the request are updated.
     * @param id temporary companyi18n identifier
     * @param dto partial temporary companyi18n payload
     * @return updated TemporaryCompanyi18nDto
     */
    @Operation(summary = "Patch Temporary Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemporaryCompanyi18nDto> patch(
            @Parameter(description = "temporary companyi18n identifier", required = true)
            @PathVariable BigInteger id,
            @RequestBody TemporaryCompanyi18nDto dto) {
        return ResponseEntity.ok(temporaryCompanyi18nService.updateTemporaryCompanyi18n(id, dto));
    }

    /**
     * Delete an temporary companyi18n record by id.
     * @param id temporary companyi18n identifier
     * @return no content
     */
    @Operation(summary = "Delete Temporary Companyi18n")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "temporary companyi18n identifier", required = true)
            @PathVariable BigInteger id) {
        temporaryCompanyi18nService.deleteTemporaryCompanyi18n(id);
        return ResponseEntity.noContent().build();
    }

}
