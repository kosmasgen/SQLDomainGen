package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.service.IncomePaymentMethodService;

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
 * REST controller for managing IncomePaymentMethod resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "IncomePaymentMethod", description = "IncomePaymentMethod API")
@RequestMapping("/api/income-payment-methods")
public class IncomePaymentMethodController {

    private final IncomePaymentMethodService incomePaymentMethodService;

    /**
     * Retrieves all records.
     *
     * @return list of IncomePaymentMethodDto
     */
    @Operation(summary = "Get all IncomePaymentMethod")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<IncomePaymentMethodDto>> getAll() {
        log.info("Fetching all incomepaymentmethod records.");
        return ResponseEntity.ok(incomePaymentMethodService.getAllIncomePaymentMethod());
    }

    /**
     * Retrieves a record by id.
     *
     * @return IncomePaymentMethodDto
     */
    @Operation(summary = "Get IncomePaymentMethod by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomePaymentMethodDto> getById(
            @Parameter(description = "IncomePaymentMethod id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching incomepaymentmethod with id: {}", id);
        return ResponseEntity.ok(incomePaymentMethodService.getIncomePaymentMethodById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created IncomePaymentMethodDto
     */
    @Operation(summary = "Create IncomePaymentMethod")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomePaymentMethodDto> create(@RequestBody IncomePaymentMethodDto dto) {
        log.info("Creating incomepaymentmethod.");
        IncomePaymentMethodDto created = incomePaymentMethodService.createIncomePaymentMethod(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated IncomePaymentMethodDto
     */
    @Operation(summary = "Update IncomePaymentMethod")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncomePaymentMethodDto> update(
            @Parameter(description = "IncomePaymentMethod id", required = true)
            @PathVariable UUID id,
            @RequestBody IncomePaymentMethodDto dto) {
        log.info("Updating incomepaymentmethod with id: {}", id);
        return ResponseEntity.ok(incomePaymentMethodService.updateIncomePaymentMethod(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete IncomePaymentMethod")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "IncomePaymentMethod id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting incomepaymentmethod with id: {}", id);
        incomePaymentMethodService.deleteIncomePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }
}
