package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.service.CompanyContactMessageService;

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
 * REST controller for managing CompanyContactMessage resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "CompanyContactMessage", description = "CompanyContactMessage API")
@RequestMapping("/api/company-contact-messages")
public class CompanyContactMessageController {

    private final CompanyContactMessageService companyContactMessageService;

    /**
     * Retrieves all records.
     *
     * @return list of CompanyContactMessageDto
     */
    @Operation(summary = "Get all CompanyContactMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<CompanyContactMessageDto>> getAll() {
        log.info("Fetching all companycontactmessage records.");
        return ResponseEntity.ok(companyContactMessageService.getAllCompanyContactMessage());
    }

    /**
     * Retrieves a record by id.
     *
     * @return CompanyContactMessageDto
     */
    @Operation(summary = "Get CompanyContactMessage by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyContactMessageDto> getById(
            @Parameter(description = "CompanyContactMessage id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching companycontactmessage with id: {}", id);
        return ResponseEntity.ok(companyContactMessageService.getCompanyContactMessageById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created CompanyContactMessageDto
     */
    @Operation(summary = "Create CompanyContactMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyContactMessageDto> create(@RequestBody CompanyContactMessageDto dto) {
        log.info("Creating companycontactmessage.");
        CompanyContactMessageDto created = companyContactMessageService.createCompanyContactMessage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated CompanyContactMessageDto
     */
    @Operation(summary = "Update CompanyContactMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CompanyContactMessageDto> update(
            @Parameter(description = "CompanyContactMessage id", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyContactMessageDto dto) {
        log.info("Updating companycontactmessage with id: {}", id);
        return ResponseEntity.ok(companyContactMessageService.updateCompanyContactMessage(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete CompanyContactMessage")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "CompanyContactMessage id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting companycontactmessage with id: {}", id);
        companyContactMessageService.deleteCompanyContactMessage(id);
        return ResponseEntity.noContent().build();
    }
}
