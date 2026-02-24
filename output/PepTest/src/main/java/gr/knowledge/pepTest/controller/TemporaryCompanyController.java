package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.service.TemporaryCompanyService;

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
 * REST controller for managing TemporaryCompany resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "TemporaryCompany", description = "TemporaryCompany API")
@RequestMapping("/api/temporary-companys")
public class TemporaryCompanyController {

    private final TemporaryCompanyService temporaryCompanyService;

    /**
     * Retrieves all records.
     *
     * @return list of TemporaryCompanyDto
     */
    @Operation(summary = "Get all TemporaryCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyDto>> getAll() {
        log.info("Fetching all temporarycompany records.");
        return ResponseEntity.ok(temporaryCompanyService.getAllTemporaryCompany());
    }

    /**
     * Retrieves a record by id.
     *
     * @return TemporaryCompanyDto
     */
    @Operation(summary = "Get TemporaryCompany by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyDto> getById(
            @Parameter(description = "TemporaryCompany id", required = true)
            @PathVariable Long id) {
        log.info("Fetching temporarycompany with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyService.getTemporaryCompanyById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created TemporaryCompanyDto
     */
    @Operation(summary = "Create TemporaryCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyDto> create(@RequestBody TemporaryCompanyDto dto) {
        log.info("Creating temporarycompany.");
        TemporaryCompanyDto created = temporaryCompanyService.createTemporaryCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated TemporaryCompanyDto
     */
    @Operation(summary = "Update TemporaryCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TemporaryCompanyDto> update(
            @Parameter(description = "TemporaryCompany id", required = true)
            @PathVariable Long id,
            @RequestBody TemporaryCompanyDto dto) {
        log.info("Updating temporarycompany with id: {}", id);
        return ResponseEntity.ok(temporaryCompanyService.updateTemporaryCompany(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete TemporaryCompany")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "TemporaryCompany id", required = true)
            @PathVariable Long id) {
        log.info("Deleting temporarycompany with id: {}", id);
        temporaryCompanyService.deleteTemporaryCompany(id);
        return ResponseEntity.noContent().build();
    }
}
