package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Producti18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Producti18n} domain operations.
 */
public interface Producti18nService {

    /**
     * Retrieves all producti18ns.
     * @return non-null list of {@link Producti18nDto}
     */
    List<Producti18nDto> getAllProducti18ns();

    /**
     * Retrieves a record by id.
     * @param languageId the language_id value
     * @param productId the product_id value
     * @return the matching {@link Producti18nDto}
     */
    Producti18nDto getProducti18nById(UUID languageId, UUID productId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link Producti18nDto}
     */
    Producti18nDto createProducti18n(Producti18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param languageId the language_id value
     * @param productId the product_id value
     * @param dto input payload with partial fields
     * @return updated {@link Producti18nDto}
     */
    Producti18nDto updateProducti18n(UUID languageId, UUID productId, Producti18nDto dto);

    /**
     * Deletes a record by id.
     * @param languageId the language_id value
     * @param productId the product_id value
     */
    void deleteProducti18n(UUID languageId, UUID productId);
}
