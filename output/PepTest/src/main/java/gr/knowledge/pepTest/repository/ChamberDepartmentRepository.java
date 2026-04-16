package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChamberDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link ChamberDepartment} entity.
 * Provides database access methods for ChamberDepartment.
 */
@Repository
public interface ChamberDepartmentRepository extends JpaRepository<ChamberDepartment, UUID> {

    /**
     * Checks if an entity exists with the given chamberId and chamberDepartmentId.
     *
     * @param chamberId value to check
     * @param chamberDepartmentId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByChamberIdAndChamberDepartmentId(Integer chamberId, Integer chamberDepartmentId);
}
