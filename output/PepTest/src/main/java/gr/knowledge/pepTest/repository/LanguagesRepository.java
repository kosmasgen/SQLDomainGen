package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Languages} entity.
 * Provides database access methods for Languages.
 */
@Repository
public interface LanguagesRepository extends JpaRepository<Languages, UUID> {

    /**
     * Checks if an entity exists with the given cd.
     *
     * @param cd value to check
     * @return true if exists, false otherwise
     */
    boolean existsByCd(String cd);

    /**
     * Checks if an entity exists with the given chamberLanguageId.
     *
     * @param chamberLanguageId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberLanguageId(Integer chamberLanguageId);
}
