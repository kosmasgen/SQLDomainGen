package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Country} entity.
 * Provides database access methods for Country.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberCountryId.
     *
     * @param chamberId value to check
     * @param chamberCountryId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberCountryId(Integer chamberId, Integer chamberCountryId);
}
