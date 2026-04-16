package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.service.TemporaryCompanyService;
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
import java.math.BigInteger;

/**
 * REST controller for managing Temporary Company resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Temporary Company", description = "Temporary Company API")
@RequestMapping("/api/temporary-company")
public class TemporaryCompanyController {

    private final TemporaryCompanyService temporaryCompanyService;

    /**
     * Retrieves all temporary companies.
     * @return list of TemporaryCompanyDto
     */
    @Operation(summary = "Get all temporary companies")
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyDto>> getAll() {
        return ResponseEntity.ok(temporaryCompanyService.getAllTemporaryCompanies());
    }

    /**
     * Retrieves the temporary company record by id.
     * @param id temporary company identifier
     * @return TemporaryCompanyDto
     */
    @Operation(summary = "Get Temporary Company by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyDto> getById(
            @Parameter(description = "temporary company identifier", required = true)
            @PathVariable BigInteger id) {
        return ResponseEntity.ok(temporaryCompanyService.getTemporaryCompanyById(id));
    }

    /**
     * Creates a new temporary company record.
     * @param dto temporary company payload
     * @return created TemporaryCompanyDto
     */
    @Operation(summary = "Create Temporary Company")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyDto> create(
            @Valid @RequestBody TemporaryCompanyDto dto) {
        TemporaryCompanyDto created = temporaryCompanyService.createTemporaryCompany(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing temporary company record.
     * Only fields that are not null in the request are updated.
     * @param id temporary company identifier
     * @param dto partial temporary company payload
     * @return updated TemporaryCompanyDto
     */
    @Operation(summary = "Patch Temporary Company")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemporaryCompanyDto> patch(
            @Parameter(description = "temporary company identifier", required = true)
            @PathVariable BigInteger id,
            @RequestBody TemporaryCompanyDto dto) {
        return ResponseEntity.ok(temporaryCompanyService.updateTemporaryCompany(id, dto));
    }

    /**
     * Delete an temporary company record by id.
     * @param id temporary company identifier
     * @return no content
     */
    @Operation(summary = "Delete Temporary Company")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "temporary company identifier", required = true)
            @PathVariable BigInteger id) {
        temporaryCompanyService.deleteTemporaryCompany(id);
        return ResponseEntity.noContent().build();
    }

}
