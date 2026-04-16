package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;

class ProfessionFriendlyCategoryDtoTest {

    /**
     * Tests the ProfessionFriendlyCategoryDto no-args constructor
     */
    @Test
    void testProfessionFriendlyCategoryDtoNoArgsConstructor() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getDescr()).isNull();
    }

    /**
     * Tests the ProfessionFriendlyCategoryDto all-args constructor
     */
    @Test
    void testProfessionFriendlyCategoryDtoAllArgsConstructor() {
        String id = "test-value";
        String descr = "test-value";

        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto(id, descr);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescr()).isEqualTo(descr);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testProfessionFriendlyCategoryDtoSettersAndGetters() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();

        String id = "test-value";
        String descr = "test-value";

        dto.setId(id);
        dto.setDescr(descr);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getDescr()).isEqualTo(descr);
    }

}
