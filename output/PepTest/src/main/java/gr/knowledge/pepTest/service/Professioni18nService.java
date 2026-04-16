package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import java.util.List;
import java.util.UUID;

/**
 * Service contract for {@code Professioni18n} domain operations.
 */
public interface Professioni18nService {

    /**
     * Retrieves all professioni18ns.
     * @return non-null list of {@link Professioni18nDto}
     */
    List<Professioni18nDto> getAllProfessioni18ns();

    /**
     * Retrieves a record by id.
     * @param professionId the profession_id value
     * @param languageId the language_id value
     * @return the matching {@link Professioni18nDto}
     */
    Professioni18nDto getProfessioni18nById(UUID professionId, UUID languageId);

    /**
     * Creates a new record.
     * @param dto input payload
     * @return created {@link Professioni18nDto}
     */
    Professioni18nDto createProfessioni18n(Professioni18nDto dto);

    /**
     * Updates an existing record.
     * <p>
     * Only non-null fields from the DTO are applied to the existing entity.
     * @param professionId the profession_id value
     * @param languageId the language_id value
     * @param dto input payload with partial fields
     * @return updated {@link Professioni18nDto}
     */
    Professioni18nDto updateProfessioni18n(UUID professionId, UUID languageId, Professioni18nDto dto);

    /**
     * Deletes a record by id.
     * @param professionId the profession_id value
     * @param languageId the language_id value
     */
    void deleteProfessioni18n(UUID professionId, UUID languageId);
}
