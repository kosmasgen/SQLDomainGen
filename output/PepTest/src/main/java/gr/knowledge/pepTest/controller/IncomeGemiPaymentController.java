package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.service.IncomeGemiPaymentService;
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
 * REST controller for managing Income Gemi Payment resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Income Gemi Payment", description = "Income Gemi Payment API")
@RequestMapping("/api/income-gemi-payment")
public class IncomeGemiPaymentController {

    private final IncomeGemiPaymentService incomeGemiPaymentService;

    /**
     * Retrieves all income gemi payments.
     * @return list of IncomeGemiPaymentDto
     */
    @Operation(summary = "Get all income gemi payments")
    @GetMapping
    public ResponseEntity<List<IncomeGemiPaymentDto>> getAll() {
        return ResponseEntity.ok(incomeGemiPaymentService.getAllIncomeGemiPayments());
    }

    /**
     * Retrieves the income gemi payment record by id.
     * @param id income gemi payment identifier
     * @return IncomeGemiPaymentDto
     */
    @Operation(summary = "Get Income Gemi Payment by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncomeGemiPaymentDto> getById(
            @Parameter(description = "income gemi payment identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(incomeGemiPaymentService.getIncomeGemiPaymentById(id));
    }

    /**
     * Creates a new income gemi payment record.
     * @param dto income gemi payment payload
     * @return created IncomeGemiPaymentDto
     */
    @Operation(summary = "Create Income Gemi Payment")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<IncomeGemiPaymentDto> create(
            @Valid @RequestBody IncomeGemiPaymentDto dto) {
        IncomeGemiPaymentDto created = incomeGemiPaymentService.createIncomeGemiPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing income gemi payment record.
     * Only fields that are not null in the request are updated.
     * @param id income gemi payment identifier
     * @param dto partial income gemi payment payload
     * @return updated IncomeGemiPaymentDto
     */
    @Operation(summary = "Patch Income Gemi Payment")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<IncomeGemiPaymentDto> patch(
            @Parameter(description = "income gemi payment identifier", required = true)
            @PathVariable UUID id,
            @RequestBody IncomeGemiPaymentDto dto) {
        return ResponseEntity.ok(incomeGemiPaymentService.updateIncomeGemiPayment(id, dto));
    }

    /**
     * Delete an income gemi payment record by id.
     * @param id income gemi payment identifier
     * @return no content
     */
    @Operation(summary = "Delete Income Gemi Payment")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "income gemi payment identifier", required = true)
            @PathVariable UUID id) {
        incomeGemiPaymentService.deleteIncomeGemiPayment(id);
        return ResponseEntity.noContent().build();
    }

}
