package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.service.ProductService;
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
 * REST controller for managing Product resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Product", description = "Product API")
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all products.
     * @return list of ProductDto
     */
    @Operation(summary = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Retrieves the product record by id.
     * @param id product identifier
     * @return ProductDto
     */
    @Operation(summary = "Get Product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(
            @Parameter(description = "product identifier", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Creates a new product record.
     * @param dto product payload
     * @return created ProductDto
     */
    @Operation(summary = "Create Product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProductDto> create(
            @Valid @RequestBody ProductDto dto) {
        ProductDto created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Partially updates an existing product record.
     * Only fields that are not null in the request are updated.
     * @param id product identifier
     * @param dto partial product payload
     * @return updated ProductDto
     */
    @Operation(summary = "Patch Product")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patch(
            @Parameter(description = "product identifier", required = true)
            @PathVariable UUID id,
            @Valid @RequestBody ProductDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    /**
     * Delete an product record by id.
     * @param id product identifier
     * @return no content
     */
    @Operation(summary = "Delete Product")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "product identifier", required = true)
            @PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
