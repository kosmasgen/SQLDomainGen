package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.service.UserGeodataService;

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
 * REST controller for managing UserGeodata resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "UserGeodata", description = "UserGeodata API")
@RequestMapping("/api/user-geodatas")
public class UserGeodataController {

    private final UserGeodataService userGeodataService;

    /**
     * Retrieves all records.
     *
     * @return list of UserGeodataDto
     */
    @Operation(summary = "Get all UserGeodata")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<UserGeodataDto>> getAll() {
        log.info("Fetching all usergeodata records.");
        return ResponseEntity.ok(userGeodataService.getAllUserGeodata());
    }

    /**
     * Retrieves a record by id.
     *
     * @return UserGeodataDto
     */
    @Operation(summary = "Get UserGeodata by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserGeodataDto> getById(
            @Parameter(description = "UserGeodata id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching usergeodata with id: {}", id);
        return ResponseEntity.ok(userGeodataService.getUserGeodataById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created UserGeodataDto
     */
    @Operation(summary = "Create UserGeodata")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<UserGeodataDto> create(@RequestBody UserGeodataDto dto) {
        log.info("Creating usergeodata.");
        UserGeodataDto created = userGeodataService.createUserGeodata(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated UserGeodataDto
     */
    @Operation(summary = "Update UserGeodata")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserGeodataDto> update(
            @Parameter(description = "UserGeodata id", required = true)
            @PathVariable UUID id,
            @RequestBody UserGeodataDto dto) {
        log.info("Updating usergeodata with id: {}", id);
        return ResponseEntity.ok(userGeodataService.updateUserGeodata(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete UserGeodata")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "UserGeodata id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting usergeodata with id: {}", id);
        userGeodataService.deleteUserGeodata(id);
        return ResponseEntity.noContent().build();
    }
}
