package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ProfessionFriendlyCategoryEntityTest {

    /**
     * Tests the ProfessionFriendlyCategory no-args constructor.
     */
    @Test
    void testProfessionFriendlyCategoryNoArgsConstructor() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the ProfessionFriendlyCategory all-args constructor.
     */
    @Test
    void testProfessionFriendlyCategoryAllArgsConstructor() {
        String id = "test-value";
        String descr = "test-value";

        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory(id, descr);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescr()).isEqualTo(descr);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testProfessionFriendlyCategorySettersAndGetters() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();

        String id = "test-value";
        String descr = "test-value";

        entity.setId(id);
        entity.setDescr(descr);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getDescr()).isEqualTo(descr);
    }

}
