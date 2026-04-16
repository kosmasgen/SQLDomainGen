package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class ChamberAppUserDtoTest {

    /**
     * Tests the ChamberAppUserDto no-args constructor
     */
    @Test
    void testChamberAppUserDtoNoArgsConstructor() {
        ChamberAppUserDto dto = new ChamberAppUserDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberAppId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getProfileId()).isNull();
        assertThat(dto.getPersonId()).isNull();
    }

    /**
     * Tests the ChamberAppUserDto all-args constructor
     */
    @Test
    void testChamberAppUserDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberId = 1;
        UUID chamberAppId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto company = new CompanyDto();
        Boolean recdeleted = Boolean.TRUE;
        UUID profileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID personId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberAppUserDto dto = new ChamberAppUserDto(id, dateCreated, lastUpdated, chamberId, chamberAppId, company, recdeleted, profileId, personId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberAppId()).isEqualTo(chamberAppId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getProfileId()).isEqualTo(profileId);
        assertThat(dto.getPersonId()).isEqualTo(personId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testChamberAppUserDtoSettersAndGetters() {
        ChamberAppUserDto dto = new ChamberAppUserDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Integer chamberId = 1;
        UUID chamberAppId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto company = new CompanyDto();
        Boolean recdeleted = Boolean.TRUE;
        UUID profileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID personId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        dto.setId(id);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setChamberId(chamberId);
        dto.setChamberAppId(chamberAppId);
        dto.setCompany(company);
        dto.setRecdeleted(recdeleted);
        dto.setProfileId(profileId);
        dto.setPersonId(personId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberAppId()).isEqualTo(chamberAppId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getProfileId()).isEqualTo(profileId);
        assertThat(dto.getPersonId()).isEqualTo(personId);
    }

}
