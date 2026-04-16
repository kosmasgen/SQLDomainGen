package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

/**
 * Repository for {@link TemporaryCompany} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface TemporaryCompanyRepository extends JpaRepository<TemporaryCompany, BigInteger> {
}
