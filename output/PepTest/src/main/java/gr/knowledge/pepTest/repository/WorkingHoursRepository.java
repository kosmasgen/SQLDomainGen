package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.WorkingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link WorkingHours} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface WorkingHoursRepository extends JpaRepository<WorkingHours, Long> {
}
