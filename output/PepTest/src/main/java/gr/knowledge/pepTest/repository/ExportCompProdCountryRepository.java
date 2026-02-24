package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ExportCompProdCountryRepository extends JpaRepository<ExportCompProdCountry, UUID> {
}
