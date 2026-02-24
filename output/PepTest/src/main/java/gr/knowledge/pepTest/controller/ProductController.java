package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProductDto;
import gr.knowledge.pepTest.service.ProductService;

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
 * REST controller for managing Product resources.
 * Generated automatically by SQLDomainGen.
 */
@RestController
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Product", description = "Product API")
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Retrieves all records.
     *
     * @return list of ProductDto
     */
    @Operation(summary = "Get all Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        log.info("Fetching all product records.");
        return ResponseEntity.ok(productService.getAllProduct());
    }

    /**
     * Retrieves a record by id.
     *
     * @return ProductDto
     */
    @Operation(summary = "Get Product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(
            @Parameter(description = "Product id", required = true)
            @PathVariable UUID id) {
        log.info("Fetching product with id: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Creates a new record.
     *
     * @param dto payload
     * @return created ProductDto
     */
    @Operation(summary = "Create Product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created")
    })
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
        log.info("Creating product.");
        ProductDto created = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing record (PUT-style).
     *
     * @param dto payload
     * @return updated ProductDto
     */
    @Operation(summary = "Update Product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(
            @Parameter(description = "Product id", required = true)
            @PathVariable UUID id,
            @RequestBody ProductDto dto) {
        log.info("Updating product with id: {}", id);
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    /**
     * Deletes a record by id.
     *
     * @return no content
     */
    @Operation(summary = "Delete Product")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @Parameter(description = "Product id", required = true)
            @PathVariable UUID id) {
        log.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
