package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.BgPoiI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link BgPoiI18n} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface BgPoiI18nRepository extends JpaRepository<BgPoiI18n, UUID> {
}
