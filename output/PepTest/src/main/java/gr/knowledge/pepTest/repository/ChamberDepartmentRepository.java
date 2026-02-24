package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChamberDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ChamberDepartmentRepository extends JpaRepository<ChamberDepartment, UUID> {
}
