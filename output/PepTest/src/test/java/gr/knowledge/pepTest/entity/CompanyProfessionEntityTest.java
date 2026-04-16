package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigInteger;

class CompanyProfessionEntityTest {

    /**
     * Tests the CompanyProfession no-args constructor.
     */
    @Test
    void testCompanyProfessionNoArgsConstructor() {
        CompanyProfession entity = new CompanyProfession();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyProfession all-args constructor.
     */
    @Test
    void testCompanyProfessionAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyProfessionId = 1;
        Company company = new Company();
        Profession profession = new Profession();
        ProfessionKind professionKind = new ProfessionKind();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        CompanyProfile profile = new CompanyProfile();
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        CompanyProfession entity = new CompanyProfession(id, chamberId, chamberCompanyProfessionId, company, profession, professionKind, dateCreated, fromDate, lastUpdated, recdeleted, toDate, profile, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberCompanyProfessionId()).isEqualTo(chamberCompanyProfessionId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getProfession()).isEqualTo(profession);
        assertThat(entity.getProfessionKind()).isEqualTo(professionKind);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getProfile()).isEqualTo(profile);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyProfessionSettersAndGetters() {
        CompanyProfession entity = new CompanyProfession();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyProfessionId = 1;
        Company company = new Company();
        Profession profession = new Profession();
        ProfessionKind professionKind = new ProfessionKind();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        CompanyProfile profile = new CompanyProfile();
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setChamberCompanyProfessionId(chamberCompanyProfessionId);
        entity.setCompany(company);
        entity.setProfession(profession);
        entity.setProfessionKind(professionKind);
        entity.setDateCreated(dateCreated);
        entity.setFromDate(fromDate);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setToDate(toDate);
        entity.setProfile(profile);
        entity.setGemiId(gemiId);
        entity.setGemiDateCreated(gemiDateCreated);
        entity.setGemiLastUpdated(gemiLastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getChamberCompanyProfessionId()).isEqualTo(chamberCompanyProfessionId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getProfession()).isEqualTo(profession);
        assertThat(entity.getProfessionKind()).isEqualTo(professionKind);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getFromDate()).isEqualTo(fromDate);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getToDate()).isEqualTo(toDate);
        assertThat(entity.getProfile()).isEqualTo(profile);
        assertThat(entity.getGemiId()).isEqualTo(gemiId);
        assertThat(entity.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(entity.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
