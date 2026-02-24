package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.service.IncomeGemiPaymentService;

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
 * REST controller for managing IncomeGemiPayment resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "IncomeGemiPayment", description = "IncomeGemiPayment API")
@RequestMapping("/api/income-gemi-payments")
public class IncomeGemiPaymentController {

    private final IncomeGemiPaymentService incomeGemiPaymentService;

    /**
     * Retrieves all records.
     *
     * @return list of IncomeGemiPaymentDto
     */
    @Operation(summary = "Get all IncomeGemiPayment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<IncomeGemiPaymentDto>> getAll() {
        log.info("Fetching all incomegemipayment records.");
        return ResponseEntity.ok(incomeGemiPaymentService.getAllIncomeGemiPayment());
    }

    /**
     * Retrieves a record by id.
     *
     * @return IncomeGemiPaymentDto
     */
    @Operation(summary = "Get IncomeGemiPayment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeGemiPaymentDto> getById(
            @Parameter(description = "IncomeGemiPayment id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching incomegemipayment with id: {}", id);
        return ResponseEntity.ok(incomeGemiPaymentService.getIncomeGemiPaymentById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created IncomeGemiPaymentDto
     */
    @Operation(summary = "Create IncomeGemiPayment")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeGemiPaymentDto> create(@RequestBody IncomeGemiPaymentDto dto) {
        log.info("Creating incomegemipayment.");
        IncomeGemiPaymentDto created = incomeGemiPaymentService.createIncomeGemiPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated IncomeGemiPaymentDto
     */
    @Operation(summary = "Update IncomeGemiPayment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncomeGemiPaymentDto> update(
            @Parameter(description = "IncomeGemiPayment id", required = true)
            @PathVariable UUID id,
            @RequestBody IncomeGemiPaymentDto dto) {
        log.info("Updating incomegemipayment with id: {}", id);
        return ResponseEntity.ok(incomeGemiPaymentService.updateIncomeGemiPayment(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete IncomeGemiPayment")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "IncomeGemiPayment id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting incomegemipayment with id: {}", id);
        incomeGemiPaymentService.deleteIncomeGemiPayment(id);
        return ResponseEntity.noContent().build();
    }
}
