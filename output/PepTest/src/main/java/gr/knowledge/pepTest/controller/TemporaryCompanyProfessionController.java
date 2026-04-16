package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;
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
 * REST controller for managing Temporary Company Profession resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Temporary Company Profession", description = "Temporary Company Profession API")
@RequestMapping("/api/temporary-company-profession")
public class TemporaryCompanyProfessionController {

    private final TemporaryCompanyProfessionService temporaryCompanyProfessionService;

    /**
     * Retrieves all temporary company professions.
     * @return list of TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Get all temporary company professions")
    @GetMapping
    public ResponseEntity<List<TemporaryCompanyProfessionDto>> getAll() {
        return ResponseEntity.ok(temporaryCompanyProfessionService.getAllTemporaryCompanyProfessions());
    }

    /**
     * Retrieves the temporary company profession record by id.
     * @param id temporary company profession identifier
     * @return TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Get Temporary Company Profession by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TemporaryCompanyProfessionDto> getById(
            @Parameter(description = "temporary company profession identifier", required = true)
            @PathVariable BigInteger id) {
        return ResponseEntity.ok(temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id));
    }

    /**
     * Creates a new temporary company profession record.
     * @param dto temporary company profession payload
     * @return created TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Create Temporary Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<TemporaryCompanyProfessionDto> create(
            @Valid @RequestBody TemporaryCompanyProfessionDto dto) {
        TemporaryCompanyProfessionDto created = temporaryCompanyProfessionService.createTemporaryCompanyProfession(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing temporary company profession record.
     * Only fields that are not null in the request are updated.
     * @param id temporary company profession identifier
     * @param dto partial temporary company profession payload
     * @return updated TemporaryCompanyProfessionDto
     */
    @Operation(summary = "Patch Temporary Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<TemporaryCompanyProfessionDto> patch(
            @Parameter(description = "temporary company profession identifier", required = true)
            @PathVariable BigInteger id,
            @RequestBody TemporaryCompanyProfessionDto dto) {
        return ResponseEntity.ok(temporaryCompanyProfessionService.updateTemporaryCompanyProfession(id, dto));
    }

    /**
     * Delete an temporary company profession record by id.
     * @param id temporary company profession identifier
     * @return no content
     */
    @Operation(summary = "Delete Temporary Company Profession")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "temporary company profession identifier", required = true)
            @PathVariable BigInteger id) {
        temporaryCompanyProfessionService.deleteTemporaryCompanyProfession(id);
        return ResponseEntity.noContent().build();
    }

}
