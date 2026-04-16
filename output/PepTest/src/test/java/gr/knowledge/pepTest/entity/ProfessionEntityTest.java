package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigInteger;

class ProfessionEntityTest {

    /**
     * Tests the Profession no-args constructor.
     */
    @Test
    void testProfessionNoArgsConstructor() {
        Profession entity = new Profession();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Profession all-args constructor.
     */
    @Test
    void testProfessionAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfessionId = 1;
        Profession parentProfession = new Profession();
        ProfessionSystem professionSystem = new ProfessionSystem();
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger proteasId = new BigInteger("1");
        ProfessionFriendlyCategory friendlyCat = new ProfessionFriendlyCategory();

        Profession entity = new Profession(id, chamberId, chamberProfessionId, parentProfession, professionSystem, code, dateCreated, lastUpdated, recdeleted, proteasId, friendlyCat);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfessionId()).isEqualTo(chamberProfessionId);
        assertThat(entity.getParentProfession()).isEqualTo(parentProfession);
        assertThat(entity.getProfessionSystem()).isEqualTo(professionSystem);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getProteasId()).isEqualTo(proteasId);
        assertThat(entity.getFriendlyCat()).isEqualTo(friendlyCat);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProfessionSettersAndGetters() {
        Profession entity = new Profession();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfessionId = 1;
        Profession parentProfession = new Profession();
        ProfessionSystem professionSystem = new ProfessionSystem();
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger proteasId = new BigInteger("1");
        ProfessionFriendlyCategory friendlyCat = new ProfessionFriendlyCategory();

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberProfessionId(chamberProfessionId);
        entity.setParentProfession(parentProfession);
        entity.setProfessionSystem(professionSystem);
        entity.setCode(code);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setProteasId(proteasId);
        entity.setFriendlyCat(friendlyCat);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberProfessionId()).isEqualTo(chamberProfessionId);
        assertThat(entity.getParentProfession()).isEqualTo(parentProfession);
        assertThat(entity.getProfessionSystem()).isEqualTo(professionSystem);
        assertThat(entity.getCode()).isEqualTo(code);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getProteasId()).isEqualTo(proteasId);
        assertThat(entity.getFriendlyCat()).isEqualTo(friendlyCat);
    }

}
