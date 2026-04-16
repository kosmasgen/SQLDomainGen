package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Folder} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface FolderRepository extends JpaRepository<Folder, UUID> {
}
