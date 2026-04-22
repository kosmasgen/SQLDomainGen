package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.service.IncomeTransactionService;
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
 * REST controller for managing Income Transaction resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Income Transaction", description = "Income Transaction API")
@RequestMapping("/api/income-transaction")
public class IncomeTransactionController {

    private final IncomeTransactionService incomeTransactionService;

    /**
     * Retrieves all income transactions.
     * @return list of IncomeTransactionDto
     */
    @Operation(summary = "Get all income transactions")
    @GetMapping
    public ResponseEntity<List<IncomeTransactionDto>> getAll() {
        return ResponseEntity.ok(incomeTransactionService.getAllIncomeTransactions());
    }

    /**
     * Retrieves the income transaction record by id.
     * @param id income transaction identifier
     * @return IncomeTransactionDto
     */
    @Operation(summary = "Get Income Transaction by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeTransactionDto> getById(
            @Parameter(description = "income transaction identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(incomeTransactionService.getIncomeTransactionById(id));
    }

    /**
     * Creates a new income transaction record.
     * @param dto income transaction payload
     * @return created IncomeTransactionDto
     */
    @Operation(summary = "Create Income Transaction")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeTransactionDto> create(
            @Valid @RequestBody IncomeTransactionDto dto) {
        IncomeTransactionDto created = incomeTransactionService.createIncomeTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing income transaction record.
     * Only fields that are not null in the request are updated.
     * @param id income transaction identifier
     * @param dto partial income transaction payload
     * @return updated IncomeTransactionDto
     */
    @Operation(summary = "Patch Income Transaction")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<IncomeTransactionDto> patch(
            @Parameter(description = "income transaction identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody IncomeTransactionDto dto) {
        return ResponseEntity.ok(incomeTransactionService.updateIncomeTransaction(id, dto));
    }

    /**
     * Delete an income transaction record by id.
     * @param id income transaction identifier
     * @return no content
     */
    @Operation(summary = "Delete Income Transaction")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "income transaction identifier", required = true)
            @PathVariable UUID id) {
        incomeTransactionService.deleteIncomeTransaction(id);
        return ResponseEntity.noContent().build();
    }

}
