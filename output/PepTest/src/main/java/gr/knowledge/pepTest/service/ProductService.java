package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProductDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Product} domain operations.
 */
public interface ProductService {

    /**
     * Retrieves all products.
     * @return non-null list of {@link ProductDto}
     */
    List<ProductDto> getAllProducts();

    /**
     * Retrieves a record by id.
     * @param id the record id
     * @return the matching {@link ProductDto}
     */
    ProductDto getProductById(UUID id);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link ProductDto}
     */
    ProductDto createProduct(ProductDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param id the record id
     * @param dto input payload with partial fields
     * @return updated {@link ProductDto}
     */
    ProductDto updateProduct(UUID id, ProductDto dto);

    /**
     * Deletes a record by id.
     * @param id the record id
     */
    void deleteProduct(UUID id);
}
