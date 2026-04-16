package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalDate;

class TemporaryCompanyProfessionDtoTest {

    /**
     * Tests the TemporaryCompanyProfessionDto no-args constructor
     */
    @Test
    void testTemporaryCompanyProfessionDtoNoArgsConstructor() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getVersion()).isNull();
        assertThat(dto.getCompanyId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getFromDate()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getProfessionId()).isNull();
        assertThat(dto.getProfessionKindId()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getToDate()).isNull();
        assertThat(dto.getGemiId()).isNull();
        assertThat(dto.getGemiDateCreated()).isNull();
        assertThat(dto.getGemiLastUpdated()).isNull();
    }

    /**
     * Tests the TemporaryCompanyProfessionDto all-args constructor
     */
    @Test
    void testTemporaryCompanyProfessionDtoAllArgsConstructor() {
        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger professionId = new BigInteger("1");
        BigInteger professionKindId = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto(id, version, companyId, dateCreated, fromDate, lastUpdated, professionId, professionKindId, recdeleted, toDate, gemiId, gemiDateCreated, gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getProfessionId()).isEqualTo(professionId);
        assertThat(dto.getProfessionKindId()).isEqualTo(professionKindId);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testTemporaryCompanyProfessionDtoSettersAndGetters() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();

        BigInteger id = new BigInteger("1");
        BigInteger version = new BigInteger("1");
        BigInteger companyId = new BigInteger("1");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime fromDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger professionId = new BigInteger("1");
        BigInteger professionKindId = new BigInteger("1");
        BigInteger recdeleted = new BigInteger("1");
        LocalDateTime toDate = LocalDateTime.of(2025, 1, 1, 10, 0);
        BigInteger gemiId = new BigInteger("1");
        LocalDate gemiDateCreated = LocalDate.of(2025, 1, 1);
        LocalDate gemiLastUpdated = LocalDate.of(2025, 1, 1);

        dto.setId(id);
        dto.setVersion(version);
        dto.setCompanyId(companyId);
        dto.setDateCreated(dateCreated);
        dto.setFromDate(fromDate);
        dto.setLastUpdated(lastUpdated);
        dto.setProfessionId(professionId);
        dto.setProfessionKindId(professionKindId);
        dto.setRecdeleted(recdeleted);
        dto.setToDate(toDate);
        dto.setGemiId(gemiId);
        dto.setGemiDateCreated(gemiDateCreated);
        dto.setGemiLastUpdated(gemiLastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getVersion()).isEqualTo(version);
        assertThat(dto.getCompanyId()).isEqualTo(companyId);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getFromDate()).isEqualTo(fromDate);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getProfessionId()).isEqualTo(professionId);
        assertThat(dto.getProfessionKindId()).isEqualTo(professionKindId);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getToDate()).isEqualTo(toDate);
        assertThat(dto.getGemiId()).isEqualTo(gemiId);
        assertThat(dto.getGemiDateCreated()).isEqualTo(gemiDateCreated);
        assertThat(dto.getGemiLastUpdated()).isEqualTo(gemiLastUpdated);
    }

}
