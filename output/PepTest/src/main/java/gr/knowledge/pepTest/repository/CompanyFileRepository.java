package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link CompanyFile} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface CompanyFileRepository extends JpaRepository<CompanyFile, UUID> {
}
