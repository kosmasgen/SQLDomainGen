package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.MunicipalityI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.MunicipalityI18nPK;

@Repository
public interface MunicipalityI18nRepository extends JpaRepository<MunicipalityI18n, MunicipalityI18nPK> {
}
