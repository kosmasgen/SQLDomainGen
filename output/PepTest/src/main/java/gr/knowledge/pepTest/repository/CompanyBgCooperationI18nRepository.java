package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyBgCooperationI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyBgCooperationI18n} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyBgCooperationI18nRepository extends JpaRepository<CompanyBgCooperationI18n, UUID> {
}
