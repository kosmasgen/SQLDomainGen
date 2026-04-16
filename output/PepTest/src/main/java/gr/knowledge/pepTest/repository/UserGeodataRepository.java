package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.UserGeodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link UserGeodata} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface UserGeodataRepository extends JpaRepository<UserGeodata, UUID> {
}
