package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.TemporaryCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporaryCompanyRepository extends JpaRepository<TemporaryCompany, Long> {
}
