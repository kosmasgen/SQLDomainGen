package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CountryI18n;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gr.knowledge.pepTest.entity.CountryI18nPK;

@Repository
public interface CountryI18nRepository extends JpaRepository<CountryI18n, CountryI18nPK> {
}
