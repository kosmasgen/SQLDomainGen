package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpPhotoEntityTest {

    /**
     * Tests the CompanyYpPhoto no-args constructor.
     */
    @Test
    void testCompanyYpPhotoNoArgsConstructor() {
        CompanyYpPhoto entity = new CompanyYpPhoto();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyYpPhoto all-args constructor.
     */
    @Test
    void testCompanyYpPhotoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        CompanyYpPhoto entity = new CompanyYpPhoto(id, chamberId, company, fileName, mimeType, fileSize, title, orderSeq, dateCreated, lastUpdated, recdeleted, blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getMimeType()).isEqualTo(mimeType);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyYpPhotoSettersAndGetters() {
        CompanyYpPhoto entity = new CompanyYpPhoto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        Company company = new Company();
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setCompany(company);
        entity.setFileName(fileName);
        entity.setMimeType(mimeType);
        entity.setFileSize(fileSize);
        entity.setTitle(title);
        entity.setOrderSeq(orderSeq);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setRecdeleted(recdeleted);
        entity.setBlobUri(blobUri);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getMimeType()).isEqualTo(mimeType);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getTitle()).isEqualTo(title);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
    }

}
