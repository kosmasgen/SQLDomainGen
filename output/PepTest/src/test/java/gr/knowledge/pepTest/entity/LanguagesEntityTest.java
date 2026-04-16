package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;

class LanguagesEntityTest {

    /**
     * Tests the Languages no-args constructor.
     */
    @Test
    void testLanguagesNoArgsConstructor() {
        Languages entity = new Languages();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the Languages all-args constructor.
     */
    @Test
    void testLanguagesAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String cd = "test-value";
        String descr = "test-value";
        Integer chamberLanguageId = 1;

        Languages entity = new Languages(id, chamberId, cd, descr, chamberLanguageId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getChamberLanguageId()).isEqualTo(chamberLanguageId);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testLanguagesSettersAndGetters() {
        Languages entity = new Languages();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String cd = "test-value";
        String descr = "test-value";
        Integer chamberLanguageId = 1;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCd(cd);
        entity.setDescr(descr);
        entity.setChamberLanguageId(chamberLanguageId);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCd()).isEqualTo(cd);
        assertThat(entity.getDescr()).isEqualTo(descr);
        assertThat(entity.getChamberLanguageId()).isEqualTo(chamberLanguageId);
    }

}
