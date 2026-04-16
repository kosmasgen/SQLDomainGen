package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitlei18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

/**
 * Repository for {@link TemporaryCompanyTitlei18n} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface TemporaryCompanyTitlei18nRepository extends JpaRepository<TemporaryCompanyTitlei18n, BigInteger> {
}
