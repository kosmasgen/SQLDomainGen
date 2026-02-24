package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.service.DataStagingService;

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

import java.util.List;

/**
 * REST controller for managing DataStaging resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "DataStaging", description = "DataStaging API")
@RequestMapping("/api/data-stagings")
public class DataStagingController {

    private final DataStagingService dataStagingService;

    /**
     * Retrieves all records.
     *
     * @return list of DataStagingDto
     */
    @Operation(summary = "Get all DataStaging")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<DataStagingDto>> getAll() {
        log.info("Fetching all datastaging records.");
        return ResponseEntity.ok(dataStagingService.getAllDataStaging());
    }

    /**
     * Retrieves a record by id.
     *
     * @return DataStagingDto
     */
    @Operation(summary = "Get DataStaging by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DataStagingDto> getById(
            @Parameter(description = "DataStaging id", required = true)
            @PathVariable Long id) {
        log.info("Fetching datastaging with id: {}", id);
        return ResponseEntity.ok(dataStagingService.getDataStagingById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created DataStagingDto
     */
    @Operation(summary = "Create DataStaging")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<DataStagingDto> create(@RequestBody DataStagingDto dto) {
        log.info("Creating datastaging.");
        DataStagingDto created = dataStagingService.createDataStaging(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated DataStagingDto
     */
    @Operation(summary = "Update DataStaging")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<DataStagingDto> update(
            @Parameter(description = "DataStaging id", required = true)
            @PathVariable Long id,
            @RequestBody DataStagingDto dto) {
        log.info("Updating datastaging with id: {}", id);
        return ResponseEntity.ok(dataStagingService.updateDataStaging(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete DataStaging")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "DataStaging id", required = true)
            @PathVariable Long id) {
        log.info("Deleting datastaging with id: {}", id);
        dataStagingService.deleteDataStaging(id);
        return ResponseEntity.noContent().build();
    }
}
