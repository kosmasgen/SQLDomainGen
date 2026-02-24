package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.ProductDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code Product} domain operations.
 */
public interface ProductService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link ProductDto}
     */
    List<ProductDto> getAllProduct();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link ProductDto}
     */
    ProductDto getProductById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link ProductDto}
     */
    ProductDto createProduct(ProductDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link ProductDto}
     */
    ProductDto updateProduct(UUID id, ProductDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteProduct(UUID id);
}
