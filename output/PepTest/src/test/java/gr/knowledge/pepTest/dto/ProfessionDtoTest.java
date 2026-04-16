package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;
import java.math.BigInteger;

class ProfessionDtoTest {

    /**
     * Tests the ProfessionDto no-args constructor
     */
    @Test
    void testProfessionDtoNoArgsConstructor() {
        ProfessionDto dto = new ProfessionDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getChamberProfessionId()).isNull();
        assertThat(dto.getParentProfession()).isNull();
        assertThat(dto.getProfessionSystem()).isNull();
        assertThat(dto.getCode()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getProteasId()).isNull();
        assertThat(dto.getFriendlyCat()).isNull();
    }

    /**
     * Tests the ProfessionDto all-args constructor
     */
    @Test
    void testProfessionDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfessionId = 1;
        ProfessionDto parentProfession = new ProfessionDto();
        ProfessionSystemDto professionSystem = new ProfessionSystemDto();
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger proteasId = new BigInteger("1");
        ProfessionFriendlyCategoryDto friendlyCat = new ProfessionFriendlyCategoryDto();

        ProfessionDto dto = new ProfessionDto(id, chamberId, chamberProfessionId, parentProfession, professionSystem, code, dateCreated, lastUpdated, recdeleted, proteasId, friendlyCat);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberProfessionId()).isEqualTo(chamberProfessionId);
        assertThat(dto.getParentProfession()).isEqualTo(parentProfession);
        assertThat(dto.getProfessionSystem()).isEqualTo(professionSystem);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getProteasId()).isEqualTo(proteasId);
        assertThat(dto.getFriendlyCat()).isEqualTo(friendlyCat);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testProfessionDtoSettersAndGetters() {
        ProfessionDto dto = new ProfessionDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Integer chamberProfessionId = 1;
        ProfessionDto parentProfession = new ProfessionDto();
        ProfessionSystemDto professionSystem = new ProfessionSystemDto();
        String code = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        BigInteger proteasId = new BigInteger("1");
        ProfessionFriendlyCategoryDto friendlyCat = new ProfessionFriendlyCategoryDto();

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setChamberProfessionId(chamberProfessionId);
        dto.setParentProfession(parentProfession);
        dto.setProfessionSystem(professionSystem);
        dto.setCode(code);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setProteasId(proteasId);
        dto.setFriendlyCat(friendlyCat);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getChamberProfessionId()).isEqualTo(chamberProfessionId);
        assertThat(dto.getParentProfession()).isEqualTo(parentProfession);
        assertThat(dto.getProfessionSystem()).isEqualTo(professionSystem);
        assertThat(dto.getCode()).isEqualTo(code);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getProteasId()).isEqualTo(proteasId);
        assertThat(dto.getFriendlyCat()).isEqualTo(friendlyCat);
    }

}
