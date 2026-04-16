package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpFileEntityTest {

    /**
     * Tests the CompanyYpFile no-args constructor.
     */
    @Test
    void testCompanyYpFileNoArgsConstructor() {
        CompanyYpFile entity = new CompanyYpFile();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyYpFile all-args constructor.
     */
    @Test
    void testCompanyYpFileAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Languages language = new Languages();
        String blobUri = "test-value";

        CompanyYpFile entity = new CompanyYpFile(id, chamberId, fileName, mimeType, fileSize, title, orderSeq, recdeleted, dateCreated, lastUpdated, company, language, blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getMimeType()).isEqualTo(mimeType);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyYpFileSettersAndGetters() {
        CompanyYpFile entity = new CompanyYpFile();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Languages language = new Languages();
        String blobUri = "test-value";

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setFileName(fileName);
        entity.setMimeType(mimeType);
        entity.setFileSize(fileSize);
        entity.setTitle(title);
        entity.setOrderSeq(orderSeq);
        entity.setRecdeleted(recdeleted);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setCompany(company);
        entity.setLanguage(language);
        entity.setBlobUri(blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getMimeType()).isEqualTo(mimeType);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

}
