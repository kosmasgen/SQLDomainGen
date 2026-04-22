package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.service.DataStagingService;
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

/**
 * REST controller for managing Data Staging resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Data Staging", description = "Data Staging API")
@RequestMapping("/api/data-staging")
public class DataStagingController {

    private final DataStagingService dataStagingService;

    /**
     * Retrieves all data stagings.
     * @return list of DataStagingDto
     */
    @Operation(summary = "Get all data stagings")
    @GetMapping
    public ResponseEntity<List<DataStagingDto>> getAll() {
        return ResponseEntity.ok(dataStagingService.getAllDataStagings());
    }

    /**
     * Retrieves the data staging record by id.
     * @param id data staging identifier
     * @return DataStagingDto
     */
    @Operation(summary = "Get Data Staging by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DataStagingDto> getById(
            @Parameter(description = "data staging identifier", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(dataStagingService.getDataStagingById(id));
    }

    /**
     * Creates a new data staging record.
     * @param dto data staging payload
     * @return created DataStagingDto
     */
    @Operation(summary = "Create Data Staging")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<DataStagingDto> create(
            @Valid @RequestBody DataStagingDto dto) {
        DataStagingDto created = dataStagingService.createDataStaging(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing data staging record.
     * Only fields that are not null in the request are updated.
     * @param id data staging identifier
     * @param dto partial data staging payload
     * @return updated DataStagingDto
     */
    @Operation(summary = "Patch Data Staging")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<DataStagingDto> patch(
            @Parameter(description = "data staging identifier", required = true)
            @PathVariable Long id,
            @Valid @RequestBody DataStagingDto dto) {
        return ResponseEntity.ok(dataStagingService.updateDataStaging(id, dto));
    }

    /**
     * Delete an data staging record by id.
     * @param id data staging identifier
     * @return no content
     */
    @Operation(summary = "Delete Data Staging")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "data staging identifier", required = true)
            @PathVariable Long id) {
        dataStagingService.deleteDataStaging(id);
        return ResponseEntity.noContent().build();
    }

}
