package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.service.IncomeTypeService;
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
 * REST controller for managing Income Type resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Income Type", description = "Income Type API")
@RequestMapping("/api/income-type")
public class IncomeTypeController {

    private final IncomeTypeService incomeTypeService;

    /**
     * Retrieves all income types.
     * @return list of IncomeTypeDto
     */
    @Operation(summary = "Get all income types")
    @GetMapping
    public ResponseEntity<List<IncomeTypeDto>> getAll() {
        return ResponseEntity.ok(incomeTypeService.getAllIncomeTypes());
    }

    /**
     * Retrieves the income type record by id.
     * @param id income type identifier
     * @return IncomeTypeDto
     */
    @Operation(summary = "Get Income Type by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeTypeDto> getById(
            @Parameter(description = "income type identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(incomeTypeService.getIncomeTypeById(id));
    }

    /**
     * Creates a new income type record.
     * @param dto income type payload
     * @return created IncomeTypeDto
     */
    @Operation(summary = "Create Income Type")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeTypeDto> create(
            @Valid @RequestBody IncomeTypeDto dto) {
        IncomeTypeDto created = incomeTypeService.createIncomeType(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing income type record.
     * Only fields that are not null in the request are updated.
     * @param id income type identifier
     * @param dto partial income type payload
     * @return updated IncomeTypeDto
     */
    @Operation(summary = "Patch Income Type")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<IncomeTypeDto> patch(
            @Parameter(description = "income type identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody IncomeTypeDto dto) {
        return ResponseEntity.ok(incomeTypeService.updateIncomeType(id, dto));
    }

    /**
     * Delete an income type record by id.
     * @param id income type identifier
     * @return no content
     */
    @Operation(summary = "Delete Income Type")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "income type identifier", required = true)
            @PathVariable UUID id) {
        incomeTypeService.deleteIncomeType(id);
        return ResponseEntity.noContent().build();
    }

}
