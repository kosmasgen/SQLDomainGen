package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CountryI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CountryI18nKey;
import java.util.UUID;

/**
 * Repository for {@link CountryI18n} entity.
 * Provides database access methods for CountryI18n.
 */
@Repository
public interface CountryI18nRepository extends JpaRepository<CountryI18n, CountryI18nKey> {

    /**
     * Checks if an entity exists with the given countryId and chamberCountryI18nId.
     *
     * @param countryId value to check
     * @param chamberCountryI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdCountryIdAndChamberCountryI18nId(UUID countryId, Integer chamberCountryI18nId);
}
