package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;

/**
 * Repository for {@link TemporaryCompanyProfession} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface TemporaryCompanyProfessionRepository extends JpaRepository<TemporaryCompanyProfession, BigInteger> {
}
