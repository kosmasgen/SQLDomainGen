package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.service.IncomePaymentMethodService;
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
 * REST controller for managing Income Payment Method resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Income Payment Method", description = "Income Payment Method API")
@RequestMapping("/api/income-payment-method")
public class IncomePaymentMethodController {

    private final IncomePaymentMethodService incomePaymentMethodService;

    /**
     * Retrieves all income payment methods.
     * @return list of IncomePaymentMethodDto
     */
    @Operation(summary = "Get all income payment methods")
    @GetMapping
    public ResponseEntity<List<IncomePaymentMethodDto>> getAll() {
        return ResponseEntity.ok(incomePaymentMethodService.getAllIncomePaymentMethods());
    }

    /**
     * Retrieves the income payment method record by id.
     * @param id income payment method identifier
     * @return IncomePaymentMethodDto
     */
    @Operation(summary = "Get Income Payment Method by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomePaymentMethodDto> getById(
            @Parameter(description = "income payment method identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(incomePaymentMethodService.getIncomePaymentMethodById(id));
    }

    /**
     * Creates a new income payment method record.
     * @param dto income payment method payload
     * @return created IncomePaymentMethodDto
     */
    @Operation(summary = "Create Income Payment Method")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomePaymentMethodDto> create(
            @Valid @RequestBody IncomePaymentMethodDto dto) {
        IncomePaymentMethodDto created = incomePaymentMethodService.createIncomePaymentMethod(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing income payment method record.
     * Only fields that are not null in the request are updated.
     * @param id income payment method identifier
     * @param dto partial income payment method payload
     * @return updated IncomePaymentMethodDto
     */
    @Operation(summary = "Patch Income Payment Method")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<IncomePaymentMethodDto> patch(
            @Parameter(description = "income payment method identifier", required = true)
            @PathVariable UUID id,
            @RequestBody IncomePaymentMethodDto dto) {
        return ResponseEntity.ok(incomePaymentMethodService.updateIncomePaymentMethod(id, dto));
    }

    /**
     * Delete an income payment method record by id.
     * @param id income payment method identifier
     * @return no content
     */
    @Operation(summary = "Delete Income Payment Method")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "income payment method identifier", required = true)
            @PathVariable UUID id) {
        incomePaymentMethodService.deleteIncomePaymentMethod(id);
        return ResponseEntity.noContent().build();
    }

}
