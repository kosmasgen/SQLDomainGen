package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ChamberDepartmentEntityTest {

    /**
     * Tests the ChamberDepartment no-args constructor.
     */
    @Test
    void testChamberDepartmentNoArgsConstructor() {
        ChamberDepartment entity = new ChamberDepartment();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ChamberDepartment all-args constructor.
     */
    @Test
    void testChamberDepartmentAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberDepartmentId = 1;
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";

        ChamberDepartment entity = new ChamberDepartment(id, chamberDepartmentId, chamberId, dateCreated, lastUpdated, recdeleted, cd);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberDepartmentId()).isEqualTo(chamberDepartmentId);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCd()).isEqualTo(cd);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testChamberDepartmentSettersAndGetters() {
        ChamberDepartment entity = new ChamberDepartment();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberDepartmentId = 1;
        Integer chamberId = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String cd = "test-value";

        entity.setId(id);
        entity.setChamberDepartmentId(chamberDepartmentId);
        entity.setChamberId(chamberId);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setCd(cd);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberDepartmentId()).isEqualTo(chamberDepartmentId);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getCd()).isEqualTo(cd);
    }

}
