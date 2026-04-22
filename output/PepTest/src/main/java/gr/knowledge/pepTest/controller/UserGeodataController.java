package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.UserGeodataDto;
import gr.knowledge.pepTest.service.UserGeodataService;
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
 * REST controller for managing User Geodata resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "User Geodata", description = "User Geodata API")
@RequestMapping("/api/user-geodata")
public class UserGeodataController {

    private final UserGeodataService userGeodataService;

    /**
     * Retrieves all user geodatas.
     * @return list of UserGeodataDto
     */
    @Operation(summary = "Get all user geodatas")
    @GetMapping
    public ResponseEntity<List<UserGeodataDto>> getAll() {
        return ResponseEntity.ok(userGeodataService.getAllUserGeodatas());
    }

    /**
     * Retrieves the user geodata record by id.
     * @param id user geodata identifier
     * @return UserGeodataDto
     */
    @Operation(summary = "Get User Geodata by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserGeodataDto> getById(
            @Parameter(description = "user geodata identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(userGeodataService.getUserGeodataById(id));
    }

    /**
     * Creates a new user geodata record.
     * @param dto user geodata payload
     * @return created UserGeodataDto
     */
    @Operation(summary = "Create User Geodata")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<UserGeodataDto> create(
            @Valid @RequestBody UserGeodataDto dto) {
        UserGeodataDto created = userGeodataService.createUserGeodata(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing user geodata record.
     * Only fields that are not null in the request are updated.
     * @param id user geodata identifier
     * @param dto partial user geodata payload
     * @return updated UserGeodataDto
     */
    @Operation(summary = "Patch User Geodata")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<UserGeodataDto> patch(
            @Parameter(description = "user geodata identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody UserGeodataDto dto) {
        return ResponseEntity.ok(userGeodataService.updateUserGeodata(id, dto));
    }

    /**
     * Delete an user geodata record by id.
     * @param id user geodata identifier
     * @return no content
     */
    @Operation(summary = "Delete User Geodata")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "user geodata identifier", required = true)
            @PathVariable UUID id) {
        userGeodataService.deleteUserGeodata(id);
        return ResponseEntity.noContent().build();
    }

}
