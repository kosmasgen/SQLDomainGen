package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.CompanyYpArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CompanyYpArticleRepository extends JpaRepository<CompanyYpArticle, UUID> {
}
