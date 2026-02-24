package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyFavourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CompanyFavouritesRepository extends JpaRepository<CompanyFavourites, UUID> {
}
