package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.service.StatsExpenseService;
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
 * REST controller for managing Stats Expense resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Stats Expense", description = "Stats Expense API")
@RequestMapping("/api/stats-expense")
public class StatsExpenseController {

    private final StatsExpenseService statsExpenseService;

    /**
     * Retrieves all stats expenses.
     * @return list of StatsExpenseDto
     */
    @Operation(summary = "Get all stats expenses")
    @GetMapping
    public ResponseEntity<List<StatsExpenseDto>> getAll() {
        return ResponseEntity.ok(statsExpenseService.getAllStatsExpenses());
    }

    /**
     * Retrieves the stats expense record by id.
     * @param id stats expense identifier
     * @return StatsExpenseDto
     */
    @Operation(summary = "Get Stats Expense by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StatsExpenseDto> getById(
            @Parameter(description = "stats expense identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(statsExpenseService.getStatsExpenseById(id));
    }

    /**
     * Creates a new stats expense record.
     * @param dto stats expense payload
     * @return created StatsExpenseDto
     */
    @Operation(summary = "Create Stats Expense")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<StatsExpenseDto> create(
            @Valid @RequestBody StatsExpenseDto dto) {
        StatsExpenseDto created = statsExpenseService.createStatsExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing stats expense record.
     * Only fields that are not null in the request are updated.
     * @param id stats expense identifier
     * @param dto partial stats expense payload
     * @return updated StatsExpenseDto
     */
    @Operation(summary = "Patch Stats Expense")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<StatsExpenseDto> patch(
            @Parameter(description = "stats expense identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody StatsExpenseDto dto) {
        return ResponseEntity.ok(statsExpenseService.updateStatsExpense(id, dto));
    }

    /**
     * Delete an stats expense record by id.
     * @param id stats expense identifier
     * @return no content
     */
    @Operation(summary = "Delete Stats Expense")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "stats expense identifier", required = true)
            @PathVariable UUID id) {
        statsExpenseService.deleteStatsExpense(id);
        return ResponseEntity.noContent().build();
    }

}
