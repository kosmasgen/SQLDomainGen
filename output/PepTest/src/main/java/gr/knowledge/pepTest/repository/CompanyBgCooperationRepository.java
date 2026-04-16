package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyBgCooperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyBgCooperation} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyBgCooperationRepository extends JpaRepository<CompanyBgCooperation, UUID> {
}
