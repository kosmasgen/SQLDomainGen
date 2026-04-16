package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.service.CompanyFavouritesService;
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
 * REST controller for managing Company Favourites resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Company Favourites", description = "Company Favourites API")
@RequestMapping("/api/company-favourites")
public class CompanyFavouritesController {

    private final CompanyFavouritesService companyFavouritesService;

    /**
     * Retrieves all company favouriteses.
     * @return list of CompanyFavouritesDto
     */
    @Operation(summary = "Get all company favouriteses")
    @GetMapping
    public ResponseEntity<List<CompanyFavouritesDto>> getAll() {
        return ResponseEntity.ok(companyFavouritesService.getAllCompanyFavouriteses());
    }

    /**
     * Retrieves the company favourites record by id.
     * @param id company favourites identifier
     * @return CompanyFavouritesDto
     */
    @Operation(summary = "Get Company Favourites by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CompanyFavouritesDto> getById(
            @Parameter(description = "company favourites identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(companyFavouritesService.getCompanyFavouritesById(id));
    }

    /**
     * Creates a new company favourites record.
     * @param dto company favourites payload
     * @return created CompanyFavouritesDto
     */
    @Operation(summary = "Create Company Favourites")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<CompanyFavouritesDto> create(
            @Valid @RequestBody CompanyFavouritesDto dto) {
        CompanyFavouritesDto created = companyFavouritesService.createCompanyFavourites(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing company favourites record.
     * Only fields that are not null in the request are updated.
     * @param id company favourites identifier
     * @param dto partial company favourites payload
     * @return updated CompanyFavouritesDto
     */
    @Operation(summary = "Patch Company Favourites")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CompanyFavouritesDto> patch(
            @Parameter(description = "company favourites identifier", required = true)
            @PathVariable UUID id,
            @RequestBody CompanyFavouritesDto dto) {
        return ResponseEntity.ok(companyFavouritesService.updateCompanyFavourites(id, dto));
    }

    /**
     * Delete an company favourites record by id.
     * @param id company favourites identifier
     * @return no content
     */
    @Operation(summary = "Delete Company Favourites")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "company favourites identifier", required = true)
            @PathVariable UUID id) {
        companyFavouritesService.deleteCompanyFavourites(id);
        return ResponseEntity.noContent().build();
    }

}
