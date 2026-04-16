package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompanyTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

/**
 * Repository for {@link TemporaryCompanyTitle} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface TemporaryCompanyTitleRepository extends JpaRepository<TemporaryCompanyTitle, BigInteger> {
}
