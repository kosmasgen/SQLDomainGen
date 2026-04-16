package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nKey;
import java.util.UUID;

/**
 * Repository for {@link ChamberDepartmenti18n} entity.
 * Provides database access methods for ChamberDepartmenti18n.
 */
@Repository
public interface ChamberDepartmenti18nRepository extends JpaRepository<ChamberDepartmenti18n, ChamberDepartmenti18nKey> {

    /**
     * Checks if an entity exists with the given departmentId and chamberI18nId.
     *
     * @param departmentId value to check
     * @param chamberI18nId value to check
     * @return true if exists, false otherwise
     */
    boolean existsByIdDepartmentIdAndChamberI18nId(UUID departmentId, Integer chamberI18nId);
}
