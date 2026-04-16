package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.service.CompanyFileService;
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
 * REST controller for managing Company File resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company File", description = "Company File API")
@RequestMapping("/api/company-file")
public class CompanyFileController {

    private final CompanyFileService companyFileService;

    /**
     * Retrieves all company files.
     * @return list of CompanyFileDto
     */
    @Operation(summary = "Get all company files")
    @GetMapping
    public ResponseEntity<List<CompanyFileDto>> getAll() {
        return ResponseEntity.ok(companyFileService.getAllCompanyFiles());
    }

    /**
     * Retrieves the company file record by id.
     * @param id company file identifier
     * @return CompanyFileDto
     */
    @Operation(summary = "Get Company File by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyFileDto> getById(
            @Parameter(description = "company file identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyFileService.getCompanyFileById(id));
    }

    /**
     * Creates a new company file record.
     * @param dto company file payload
     * @return created CompanyFileDto
     */
    @Operation(summary = "Create Company File")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyFileDto> create(
            @Valid @RequestBody CompanyFileDto dto) {
        CompanyFileDto created = companyFileService.createCompanyFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company file record.
     * Only fields that are not null in the request are updated.
     * @param id company file identifier
     * @param dto partial company file payload
     * @return updated CompanyFileDto
     */
    @Operation(summary = "Patch Company File")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyFileDto> patch(
            @Parameter(description = "company file identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyFileDto dto) {
        return ResponseEntity.ok(companyFileService.updateCompanyFile(id, dto));
    }

    /**
     * Delete an company file record by id.
     * @param id company file identifier
     * @return no content
     */
    @Operation(summary = "Delete Company File")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company file identifier", required = true)
            @PathVariable UUID id) {
        companyFileService.deleteCompanyFile(id);
        return ResponseEntity.noContent().build();
    }

}
