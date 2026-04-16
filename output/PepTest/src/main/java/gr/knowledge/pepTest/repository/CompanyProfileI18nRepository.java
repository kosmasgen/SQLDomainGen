package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyProfileI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CompanyProfileI18nKey;

/**
 * Repository for {@link CompanyProfileI18n} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyProfileI18nRepository extends JpaRepository<CompanyProfileI18n, CompanyProfileI18nKey> {
}
