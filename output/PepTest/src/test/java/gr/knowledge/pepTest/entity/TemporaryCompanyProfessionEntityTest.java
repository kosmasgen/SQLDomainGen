package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyProfessionEntityTest {

    /**
     * Tests the TemporaryCompanyProfession no-args constructor.
     */
    @Test
    void testTemporaryCompanyProfessionNoArgsConstructor() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the TemporaryCompanyProfession all-args constructor.
     */
    @Test
    void testTemporaryCompanyProfessionAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger professionId = new BigInteger("1");
        BigInteger professionKindId = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyProfession entity = new TemporaryCompanyProfession(id, version, companyId, dateCreated, fromDate, lastUpdated, professionId, professionKindId, recdeleted, toDate, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getProfessionId()).isEqualTo(professionId);
        assertThat(entity.getProfessionKindId()).isEqualTo(professionKindId);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testTemporaryCompanyProfessionSettersAndGetters() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger professionId = new BigInteger("1");
        BigInteger professionKindId = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        entity.setId(id);
        entity.setVersion(version);
        entity.setCompanyId(companyId);
        entity.setDateCreated(dateCreated);
        entity.setFromDate(fromDate);
        entity.setLastUpdated(lastUpdated);
        entity.setProfessionId(professionId);
        entity.setProfessionKindId(professionKindId);
        entity.setRecdeleted(recdeleted);
        entity.setToDate(toDate);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVersion()).isEqualTo(version);
        assertThat(entity.getCompanyId()).isEqualTo(companyId);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getProfessionId()).isEqualTo(professionId);
        assertThat(entity.getProfessionKindId()).isEqualTo(professionKindId);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
