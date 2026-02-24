package gr.knowledge.pepTest.repository;

import gr.knowledge.pepTest.entity.DataStaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataStagingRepository extends JpaRepository<DataStaging, Long> {
}
