package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class ChamberAppUserEntityTest {

    /**
     * Tests the ChamberAppUser no-args constructor.
     */
    @Test
    void testChamberAppUserNoArgsConstructor() {
        ChamberAppUser entity = new ChamberAppUser();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ChamberAppUser all-args constructor.
     */
    @Test
    void testChamberAppUserAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberId = 1;
        UUID chamberAppId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Company company = new Company();
        Boolean recdeleted = Boolean.TRUE;
        UUID profileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID personId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUser entity = new ChamberAppUser(id, dateCreated, lastUpdated, chamberId, chamberAppId, company, recdeleted, profileId, personId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberAppId()).isEqualTo(chamberAppId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getProfileId()).isEqualTo(profileId);
        assertThat(entity.getPersonId()).isEqualTo(personId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testChamberAppUserSettersAndGetters() {
        ChamberAppUser entity = new ChamberAppUser();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Integer chamberId = 1;
        UUID chamberAppId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Company company = new Company();
        Boolean recdeleted = Boolean.TRUE;
        UUID profileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID personId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        entity.setId(id);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setChamberId(chamberId);
        entity.setChamberAppId(chamberAppId);
        entity.setCompany(company);
        entity.setRecdeleted(recdeleted);
        entity.setProfileId(profileId);
        entity.setPersonId(personId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberAppId()).isEqualTo(chamberAppId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getProfileId()).isEqualTo(profileId);
        assertThat(entity.getPersonId()).isEqualTo(personId);
    }

}
