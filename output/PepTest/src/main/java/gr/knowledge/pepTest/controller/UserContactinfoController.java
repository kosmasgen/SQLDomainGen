package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserContactinfoDto;
import gr.knowledge.pepTest.service.UserContactinfoService;
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
 * REST controller for managing User Contactinfo resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "User Contactinfo", description = "User Contactinfo API")
@RequestMapping("/api/user-contactinfo")
public class UserContactinfoController {

    private final UserContactinfoService userContactinfoService;

    /**
     * Retrieves all user contactinfos.
     * @return list of UserContactinfoDto
     */
    @Operation(summary = "Get all user contactinfos")
    @GetMapping
    public ResponseEntity<List<UserContactinfoDto>> getAll() {
        return ResponseEntity.ok(userContactinfoService.getAllUserContactinfos());
    }

    /**
     * Retrieves the user contactinfo record by id.
     * @param id user contactinfo identifier
     * @return UserContactinfoDto
     */
    @Operation(summary = "Get User Contactinfo by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserContactinfoDto> getById(
            @Parameter(description = "user contactinfo identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(userContactinfoService.getUserContactinfoById(id));
    }

    /**
     * Creates a new user contactinfo record.
     * @param dto user contactinfo payload
     * @return created UserContactinfoDto
     */
    @Operation(summary = "Create User Contactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<UserContactinfoDto> create(
            @Valid @RequestBody UserContactinfoDto dto) {
        UserContactinfoDto created = userContactinfoService.createUserContactinfo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing user contactinfo record.
     * Only fields that are not null in the request are updated.
     * @param id user contactinfo identifier
     * @param dto partial user contactinfo payload
     * @return updated UserContactinfoDto
     */
    @Operation(summary = "Patch User Contactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserContactinfoDto> patch(
            @Parameter(description = "user contactinfo identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody UserContactinfoDto dto) {
        return ResponseEntity.ok(userContactinfoService.updateUserContactinfo(id, dto));
    }

    /**
     * Delete an user contactinfo record by id.
     * @param id user contactinfo identifier
     * @return no content
     */
    @Operation(summary = "Delete User Contactinfo")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "user contactinfo identifier", required = true)
            @PathVariable UUID id) {
        userContactinfoService.deleteUserContactinfo(id);
        return ResponseEntity.noContent().build();
    }

}
