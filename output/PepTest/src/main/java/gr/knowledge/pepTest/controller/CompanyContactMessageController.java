package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.service.CompanyContactMessageService;
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
 * REST controller for managing Company Contact Message resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Contact Message", description = "Company Contact Message API")
@RequestMapping("/api/company-contact-message")
public class CompanyContactMessageController {

    private final CompanyContactMessageService companyContactMessageService;

    /**
     * Retrieves all company contact messages.
     * @return list of CompanyContactMessageDto
     */
    @Operation(summary = "Get all company contact messages")
    @GetMapping
    public ResponseEntity<List<CompanyContactMessageDto>> getAll() {
        return ResponseEntity.ok(companyContactMessageService.getAllCompanyContactMessages());
    }

    /**
     * Retrieves the company contact message record by id.
     * @param id company contact message identifier
     * @return CompanyContactMessageDto
     */
    @Operation(summary = "Get Company Contact Message by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyContactMessageDto> getById(
            @Parameter(description = "company contact message identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyContactMessageService.getCompanyContactMessageById(id));
    }

    /**
     * Creates a new company contact message record.
     * @param dto company contact message payload
     * @return created CompanyContactMessageDto
     */
    @Operation(summary = "Create Company Contact Message")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyContactMessageDto> create(
            @Valid @RequestBody CompanyContactMessageDto dto) {
        CompanyContactMessageDto created = companyContactMessageService.createCompanyContactMessage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company contact message record.
     * Only fields that are not null in the request are updated.
     * @param id company contact message identifier
     * @param dto partial company contact message payload
     * @return updated CompanyContactMessageDto
     */
    @Operation(summary = "Patch Company Contact Message")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyContactMessageDto> patch(
            @Parameter(description = "company contact message identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyContactMessageDto dto) {
        return ResponseEntity.ok(companyContactMessageService.updateCompanyContactMessage(id, dto));
    }

    /**
     * Delete an company contact message record by id.
     * @param id company contact message identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Contact Message")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company contact message identifier", required = true)
            @PathVariable UUID id) {
        companyContactMessageService.deleteCompanyContactMessage(id);
        return ResponseEntity.noContent().build();
    }

}
