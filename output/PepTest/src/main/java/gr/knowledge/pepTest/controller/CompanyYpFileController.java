package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.service.CompanyYpFileService;
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
 * REST controller for managing Company Yp File resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Yp File", description = "Company Yp File API")
@RequestMapping("/api/company-yp-file")
public class CompanyYpFileController {

    private final CompanyYpFileService companyYpFileService;

    /**
     * Retrieves all company yp files.
     * @return list of CompanyYpFileDto
     */
    @Operation(summary = "Get all company yp files")
    @GetMapping
    public ResponseEntity<List<CompanyYpFileDto>> getAll() {
        return ResponseEntity.ok(companyYpFileService.getAllCompanyYpFiles());
    }

    /**
     * Retrieves the company yp file record by id.
     * @param id company yp file identifier
     * @return CompanyYpFileDto
     */
    @Operation(summary = "Get Company Yp File by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpFileDto> getById(
            @Parameter(description = "company yp file identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyYpFileService.getCompanyYpFileById(id));
    }

    /**
     * Creates a new company yp file record.
     * @param dto company yp file payload
     * @return created CompanyYpFileDto
     */
    @Operation(summary = "Create Company Yp File")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpFileDto> create(
            @Valid @RequestBody CompanyYpFileDto dto) {
        CompanyYpFileDto created = companyYpFileService.createCompanyYpFile(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company yp file record.
     * Only fields that are not null in the request are updated.
     * @param id company yp file identifier
     * @param dto partial company yp file payload
     * @return updated CompanyYpFileDto
     */
    @Operation(summary = "Patch Company Yp File")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyYpFileDto> patch(
            @Parameter(description = "company yp file identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody CompanyYpFileDto dto) {
        return ResponseEntity.ok(companyYpFileService.updateCompanyYpFile(id, dto));
    }

    /**
     * Delete an company yp file record by id.
     * @param id company yp file identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Yp File")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company yp file identifier", required = true)
            @PathVariable UUID id) {
        companyYpFileService.deleteCompanyYpFile(id);
        return ResponseEntity.noContent().build();
    }

}
