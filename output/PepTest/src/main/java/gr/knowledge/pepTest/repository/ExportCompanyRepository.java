package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ExportCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ExportCompany} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface ExportCompanyRepository extends JpaRepository<ExportCompany, UUID> {
}
