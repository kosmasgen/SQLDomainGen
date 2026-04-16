package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;

class LanguagesDtoTest {

    /**
     * Tests the LanguagesDto no-args constructor
     */
    @Test
    void testLanguagesDtoNoArgsConstructor() {
        LanguagesDto dto = new LanguagesDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCd()).isNull();
        assertThat(dto.getDescr()).isNull();
        assertThat(dto.getChamberLanguageId()).isNull();
    }

    /**
     * Tests the LanguagesDto all-args constructor
     */
    @Test
    void testLanguagesDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String cd = "test-value";
        String descr = "test-value";
        Integer chamberLanguageId = 1;

        LanguagesDto dto = new LanguagesDto(id, chamberId, cd, descr, chamberLanguageId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getChamberLanguageId()).isEqualTo(chamberLanguageId);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testLanguagesDtoSettersAndGetters() {
        LanguagesDto dto = new LanguagesDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String cd = "test-value";
        String descr = "test-value";
        Integer chamberLanguageId = 1;

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCd(cd);
        dto.setDescr(descr);
        dto.setChamberLanguageId(chamberLanguageId);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCd()).isEqualTo(cd);
        assertThat(dto.getDescr()).isEqualTo(descr);
        assertThat(dto.getChamberLanguageId()).isEqualTo(chamberLanguageId);
    }

}
