package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyArticleFileEntityTest {

    /**
     * Tests the CompanyArticleFile no-args constructor.
     */
    @Test
    void testCompanyArticleFileNoArgsConstructor() {
        CompanyArticleFile entity = new CompanyArticleFile();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyArticleFile all-args constructor.
     */
    @Test
    void testCompanyArticleFileAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticle article = new CompanyYpArticle();
        CompanyFile file = new CompanyFile();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        CompanyArticleFile entity = new CompanyArticleFile(id, article, file, orderSeq, dateCreated, lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getArticle()).isEqualTo(article);
        assertThat(entity.getFile()).isEqualTo(file);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyArticleFileSettersAndGetters() {
        CompanyArticleFile entity = new CompanyArticleFile();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticle article = new CompanyYpArticle();
        CompanyFile file = new CompanyFile();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);

        entity.setId(id);
        entity.setArticle(article);
        entity.setFile(file);
        entity.setOrderSeq(orderSeq);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getArticle()).isEqualTo(article);
        assertThat(entity.getFile()).isEqualTo(file);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
