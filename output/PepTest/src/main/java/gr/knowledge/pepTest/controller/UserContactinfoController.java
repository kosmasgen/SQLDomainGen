package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.service.UserContactinfoService;

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
 * REST controller for managing UserContactinfo resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "UserContactinfo", description = "UserContactinfo API")
@RequestMapping("/api/user-contactinfos")
public class UserContactinfoController {

    private final UserContactinfoService userContactinfoService;

    /**
     * Retrieves all records.
     *
     * @return list of UserContactinfoDto
     */
    @Operation(summary = "Get all UserContactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<UserContactinfoDto>> getAll() {
        log.info("Fetching all usercontactinfo records.");
        return ResponseEntity.ok(userContactinfoService.getAllUserContactinfo());
    }

    /**
     * Retrieves a record by id.
     *
     * @return UserContactinfoDto
     */
    @Operation(summary = "Get UserContactinfo by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserContactinfoDto> getById(
            @Parameter(description = "UserContactinfo id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching usercontactinfo with id: {}", id);
        return ResponseEntity.ok(userContactinfoService.getUserContactinfoById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created UserContactinfoDto
     */
    @Operation(summary = "Create UserContactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<UserContactinfoDto> create(@RequestBody UserContactinfoDto dto) {
        log.info("Creating usercontactinfo.");
        UserContactinfoDto created = userContactinfoService.createUserContactinfo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated UserContactinfoDto
     */
    @Operation(summary = "Update UserContactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserContactinfoDto> update(
            @Parameter(description = "UserContactinfo id", required = true)
            @PathVariable UUID id,
            @RequestBody UserContactinfoDto dto) {
        log.info("Updating usercontactinfo with id: {}", id);
        return ResponseEntity.ok(userContactinfoService.updateUserContactinfo(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete UserContactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "UserContactinfo id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting usercontactinfo with id: {}", id);
        userContactinfoService.deleteUserContactinfo(id);
        return ResponseEntity.noContent().build();
    }
}
