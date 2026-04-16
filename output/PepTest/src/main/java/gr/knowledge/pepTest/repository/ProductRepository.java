package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository for {@link Product} entity.
 * Provides basic CRUD operations using JpaRepository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
}
