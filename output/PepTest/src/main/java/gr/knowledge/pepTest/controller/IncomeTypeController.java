package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.service.IncomeTypeService;

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
 * REST controller for managing IncomeType resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "IncomeType", description = "IncomeType API")
@RequestMapping("/api/income-types")
public class IncomeTypeController {

    private final IncomeTypeService incomeTypeService;

    /**
     * Retrieves all records.
     *
     * @return list of IncomeTypeDto
     */
    @Operation(summary = "Get all IncomeType")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<IncomeTypeDto>> getAll() {
        log.info("Fetching all incometype records.");
        return ResponseEntity.ok(incomeTypeService.getAllIncomeType());
    }

    /**
     * Retrieves a record by id.
     *
     * @return IncomeTypeDto
     */
    @Operation(summary = "Get IncomeType by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeTypeDto> getById(
            @Parameter(description = "IncomeType id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching incometype with id: {}", id);
        return ResponseEntity.ok(incomeTypeService.getIncomeTypeById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created IncomeTypeDto
     */
    @Operation(summary = "Create IncomeType")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeTypeDto> create(@RequestBody IncomeTypeDto dto) {
        log.info("Creating incometype.");
        IncomeTypeDto created = incomeTypeService.createIncomeType(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated IncomeTypeDto
     */
    @Operation(summary = "Update IncomeType")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncomeTypeDto> update(
            @Parameter(description = "IncomeType id", required = true)
            @PathVariable UUID id,
            @RequestBody IncomeTypeDto dto) {
        log.info("Updating incometype with id: {}", id);
        return ResponseEntity.ok(incomeTypeService.updateIncomeType(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete IncomeType")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "IncomeType id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting incometype with id: {}", id);
        incomeTypeService.deleteIncomeType(id);
        return ResponseEntity.noContent().build();
    }
}
