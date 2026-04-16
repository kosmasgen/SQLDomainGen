package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigInteger;

class CompanyProfessionDtoTest {

    /**
     * Tests the CompanyProfessionDto no-args constructor
     */
    @Test
    void testCompanyProfessionDtoNoArgsConstructor() {
        CompanyProfessionDto dto = new CompanyProfessionDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberCompanyProfessionId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getProfession()).isNull();
        assertThat(dto.getProfessionKind()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getFromDate()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getToDate()).isNull();
        assertThat(dto.getProfile()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
    }

    /**
     * Tests the CompanyProfessionDto all-args constructor
     */
    @Test
    void testCompanyProfessionDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyProfessionId = 1;
        CompanyDto company = new CompanyDto();
        ProfessionDto profession = new ProfessionDto();
        ProfessionKindDto professionKind = new ProfessionKindDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyProfileDto profile = new CompanyProfileDto();
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        CompanyProfessionDto dto = new CompanyProfessionDto(id, chamberId, chamberCompanyProfessionId, company, profession, professionKind, dateCreated, fromDate, lastUpdated, recdeleted, toDate, profile, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberCompanyProfessionId()).isEqualTo(chamberCompanyProfessionId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getProfession()).isEqualTo(profession);
        assertThat(dto.getProfessionKind()).isEqualTo(professionKind);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getProfile()).isEqualTo(profile);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyProfessionDtoSettersAndGetters() {
        CompanyProfessionDto dto = new CompanyProfessionDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberCompanyProfessionId = 1;
        CompanyDto company = new CompanyDto();
        ProfessionDto profession = new ProfessionDto();
        ProfessionKindDto professionKind = new ProfessionKindDto();
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyProfileDto profile = new CompanyProfileDto();
        BigInteger gemiId = new BigInteger("1");
        LocalDateTime gemiDateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime gemiLastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberCompanyProfessionId(chamberCompanyProfessionId);
        dto.setCompany(company);
        dto.setProfession(profession);
        dto.setProfessionKind(professionKind);
        dto.setDateCreated(dateCreated);
        dto.setFromDate(fromDate);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setToDate(toDate);
        dto.setProfile(profile);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberCompanyProfessionId()).isEqualTo(chamberCompanyProfessionId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getProfession()).isEqualTo(profession);
        assertThat(dto.getProfessionKind()).isEqualTo(professionKind);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getProfile()).isEqualTo(profile);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
