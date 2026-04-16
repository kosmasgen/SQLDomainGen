package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.UserContactinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link UserContactinfo} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface UserContactinfoRepository extends JpaRepository<UserContactinfo, UUID> {
}
