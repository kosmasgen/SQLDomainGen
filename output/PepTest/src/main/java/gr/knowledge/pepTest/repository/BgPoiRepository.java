package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.BgPoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link BgPoi} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface BgPoiRepository extends JpaRepository<BgPoi, UUID> {
}
