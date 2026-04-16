package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyArticleFileDtoTest {

    /**
     * Tests the CompanyArticleFileDto no-args constructor
     */
    @Test
    void testCompanyArticleFileDtoNoArgsConstructor() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getArticle()).isNull();
        assertThat(dto.getFile()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
    }

    /**
     * Tests the CompanyArticleFileDto all-args constructor
     */
    @Test
    void testCompanyArticleFileDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticleDto article = new CompanyYpArticleDto();
        CompanyFileDto file = new CompanyFileDto();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        CompanyArticleFileDto dto = new CompanyArticleFileDto(id, article, file, orderSeq, dateCreated, lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getArticle()).isEqualTo(article);
        assertThat(dto.getFile()).isEqualTo(file);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyArticleFileDtoSettersAndGetters() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticleDto article = new CompanyYpArticleDto();
        CompanyFileDto file = new CompanyFileDto();
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);

        dto.setId(id);
        dto.setArticle(article);
        dto.setFile(file);
        dto.setOrderSeq(orderSeq);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getArticle()).isEqualTo(article);
        assertThat(dto.getFile()).isEqualTo(file);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
    }

}
