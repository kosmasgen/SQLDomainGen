package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpPhotoDtoTest {

    /**
     * Tests the CompanyYpPhotoDto no-args constructor
     */
    @Test
    void testCompanyYpPhotoDtoNoArgsConstructor() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getFileName()).isNull();
        assertThat(dto.getMimeType()).isNull();
        assertThat(dto.getFileSize()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getBlobUri()).isNull();
    }

    /**
     * Tests the CompanyYpPhotoDto all-args constructor
     */
    @Test
    void testCompanyYpPhotoDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        CompanyYpPhotoDto dto = new CompanyYpPhotoDto(id, chamberId, company, fileName, mimeType, fileSize, title, orderSeq, dateCreated, lastUpdated, recdeleted, blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getMimeType()).isEqualTo(mimeType);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyYpPhotoDtoSettersAndGetters() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        CompanyDto company = new CompanyDto();
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        Boolean recdeleted = Boolean.TRUE;
        String blobUri = "test-value";

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setCompany(company);
        dto.setFileName(fileName);
        dto.setMimeType(mimeType);
        dto.setFileSize(fileSize);
        dto.setTitle(title);
        dto.setOrderSeq(orderSeq);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setRecdeleted(recdeleted);
        dto.setBlobUri(blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getMimeType()).isEqualTo(mimeType);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

}
