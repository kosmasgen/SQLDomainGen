package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyYpFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyYpFile} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyYpFileRepository extends JpaRepository<CompanyYpFile, UUID> {
}
