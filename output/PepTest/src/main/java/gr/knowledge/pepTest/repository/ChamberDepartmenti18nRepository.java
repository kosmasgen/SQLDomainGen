package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nPK;

@Repository
public interface ChamberDepartmenti18nRepository extends JpaRepository<ChamberDepartmenti18n, ChamberDepartmenti18nPK> {
}
