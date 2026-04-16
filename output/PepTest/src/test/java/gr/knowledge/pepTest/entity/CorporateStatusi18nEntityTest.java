package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

class CorporateStatusi18nEntityTest {

    /**
     * Tests the CorporateStatusi18n no-args constructor.
     */
    @Test
    void testCorporateStatusi18nNoArgsConstructor() {
        CorporateStatusi18n entity = new CorporateStatusi18n();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CorporateStatusi18n all-args constructor.
     */
    @Test
    void testCorporateStatusi18nAllArgsConstructor() {
        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        CorporateStatus corporateStatus = new CorporateStatus();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;
        String groupedDescription = "test-value";

        CorporateStatusi18n entity = new CorporateStatusi18n(id, corporateStatus, language, description, dateCreated, lastUpdated, recdeleted, chamberI18nId, groupedDescription);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(entity.getGroupedDescription()).isEqualTo(groupedDescription);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCorporateStatusi18nSettersAndGetters() {
        CorporateStatusi18n entity = new CorporateStatusi18n();

        CorporateStatusi18nKey id = new CorporateStatusi18nKey();
        CorporateStatus corporateStatus = new CorporateStatus();
        Languages language = new Languages();
        String description = "test-value";
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        Integer chamberI18nId = 1;
        String groupedDescription = "test-value";

        entity.setId(id);
        entity.setCorporateStatus(corporateStatus);
        entity.setLanguage(language);
        entity.setDescription(description);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setChamberI18nId(chamberI18nId);
        entity.setGroupedDescription(groupedDescription);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getCorporateStatus()).isEqualTo(corporateStatus);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getDescription()).isEqualTo(description);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getChamberI18nId()).isEqualTo(chamberI18nId);
        assertThat(entity.getGroupedDescription()).isEqualTo(groupedDescription);
    }

}
