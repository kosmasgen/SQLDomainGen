package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.service.IncomeTransactionService;

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
 * REST controller for managing IncomeTransaction resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "IncomeTransaction", description = "IncomeTransaction API")
@RequestMapping("/api/income-transactions")
public class IncomeTransactionController {

    private final IncomeTransactionService incomeTransactionService;

    /**
     * Retrieves all records.
     *
     * @return list of IncomeTransactionDto
     */
    @Operation(summary = "Get all IncomeTransaction")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<IncomeTransactionDto>> getAll() {
        log.info("Fetching all incometransaction records.");
        return ResponseEntity.ok(incomeTransactionService.getAllIncomeTransaction());
    }

    /**
     * Retrieves a record by id.
     *
     * @return IncomeTransactionDto
     */
    @Operation(summary = "Get IncomeTransaction by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeTransactionDto> getById(
            @Parameter(description = "IncomeTransaction id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching incometransaction with id: {}", id);
        return ResponseEntity.ok(incomeTransactionService.getIncomeTransactionById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created IncomeTransactionDto
     */
    @Operation(summary = "Create IncomeTransaction")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeTransactionDto> create(@RequestBody IncomeTransactionDto dto) {
        log.info("Creating incometransaction.");
        IncomeTransactionDto created = incomeTransactionService.createIncomeTransaction(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated IncomeTransactionDto
     */
    @Operation(summary = "Update IncomeTransaction")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncomeTransactionDto> update(
            @Parameter(description = "IncomeTransaction id", required = true)
            @PathVariable UUID id,
            @RequestBody IncomeTransactionDto dto) {
        log.info("Updating incometransaction with id: {}", id);
        return ResponseEntity.ok(incomeTransactionService.updateIncomeTransaction(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete IncomeTransaction")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "IncomeTransaction id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting incometransaction with id: {}", id);
        incomeTransactionService.deleteIncomeTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
