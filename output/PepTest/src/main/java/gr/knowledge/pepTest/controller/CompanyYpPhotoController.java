package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.service.CompanyYpPhotoService;
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
 * REST controller for managing Company Yp Photo resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Yp Photo", description = "Company Yp Photo API")
@RequestMapping("/api/company-yp-photo")
public class CompanyYpPhotoController {

    private final CompanyYpPhotoService companyYpPhotoService;

    /**
     * Retrieves all company yp photos.
     * @return list of CompanyYpPhotoDto
     */
    @Operation(summary = "Get all company yp photos")
    @GetMapping
    public ResponseEntity<List<CompanyYpPhotoDto>> getAll() {
        return ResponseEntity.ok(companyYpPhotoService.getAllCompanyYpPhotos());
    }

    /**
     * Retrieves the company yp photo record by id.
     * @param id company yp photo identifier
     * @return CompanyYpPhotoDto
     */
    @Operation(summary = "Get Company Yp Photo by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyYpPhotoDto> getById(
            @Parameter(description = "company yp photo identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyYpPhotoService.getCompanyYpPhotoById(id));
    }

    /**
     * Creates a new company yp photo record.
     * @param dto company yp photo payload
     * @return created CompanyYpPhotoDto
     */
    @Operation(summary = "Create Company Yp Photo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyYpPhotoDto> create(
            @Valid @RequestBody CompanyYpPhotoDto dto) {
        CompanyYpPhotoDto created = companyYpPhotoService.createCompanyYpPhoto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company yp photo record.
     * Only fields that are not null in the request are updated.
     * @param id company yp photo identifier
     * @param dto partial company yp photo payload
     * @return updated CompanyYpPhotoDto
     */
    @Operation(summary = "Patch Company Yp Photo")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyYpPhotoDto> patch(
            @Parameter(description = "company yp photo identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyYpPhotoDto dto) {
        return ResponseEntity.ok(companyYpPhotoService.updateCompanyYpPhoto(id, dto));
    }

    /**
     * Delete an company yp photo record by id.
     * @param id company yp photo identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Yp Photo")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company yp photo identifier", required = true)
            @PathVariable UUID id) {
        companyYpPhotoService.deleteCompanyYpPhoto(id);
        return ResponseEntity.noContent().build();
    }

}
