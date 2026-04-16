package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Municipality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Municipality} entity.
 * Provides database access methods for Municipality.
 */
@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberMunicipalityId.
     *
     * @param chamberId value to check
     * @param chamberMunicipalityId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberMunicipalityId(Long chamberId, Long chamberMunicipalityId);
}
