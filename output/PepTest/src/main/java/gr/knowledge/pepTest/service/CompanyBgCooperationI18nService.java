package gr.knowledge.pepTest.service;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import java.util.UUID;
import java.util.List;

/**
 * Service contract for {@code CompanyBgCooperationI18n} domain operations.
 */
public interface CompanyBgCooperationI18nService {

    /**
     * Retrieves all records.
     *
     * @return non-null list of {@link CompanyBgCooperationI18nDto}
     */
    List<CompanyBgCooperationI18nDto> getAllCompanyBgCooperationI18n();

    /**
     * Retrieves a record by id.
     *
     * @param id the record id
     * @return the matching {@link CompanyBgCooperationI18nDto}
     */
    CompanyBgCooperationI18nDto getCompanyBgCooperationI18nById(UUID id);

    /**
     * Creates a new record.
     *
     * @param dto input payload
     * @return created {@link CompanyBgCooperationI18nDto}
     */
    CompanyBgCooperationI18nDto createCompanyBgCooperationI18n(CompanyBgCooperationI18nDto dto);

    /**
     * Updates an existing record.
     *
     * Note: current implementation performs a full update (PUT-style).
     * PATCH behavior (merge non-null fields) can be added via ModelMapper config.
     *
     * @param id  the record id
     * @param dto input payload
     * @return updated {@link CompanyBgCooperationI18nDto}
     */
    CompanyBgCooperationI18nDto updateCompanyBgCooperationI18n(UUID id, CompanyBgCooperationI18nDto dto);

    /**
     * Deletes a record by id.
     *
     * @param id the record id
     */
    void deleteCompanyBgCooperationI18n(UUID id);
}
