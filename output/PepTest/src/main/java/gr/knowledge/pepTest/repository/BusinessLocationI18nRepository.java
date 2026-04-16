package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.BusinessLocationI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.BusinessLocationI18nKey;

/**
 * Repository for {@link BusinessLocationI18n} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface BusinessLocationI18nRepository extends JpaRepository<BusinessLocationI18n, BusinessLocationI18nKey> {
}
