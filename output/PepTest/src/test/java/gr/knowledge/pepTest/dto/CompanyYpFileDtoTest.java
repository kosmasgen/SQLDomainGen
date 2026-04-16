package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyYpFileDtoTest {

    /**
     * Tests the CompanyYpFileDto no-args constructor
     */
    @Test
    void testCompanyYpFileDtoNoArgsConstructor() {
        CompanyYpFileDto dto = new CompanyYpFileDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getFileName()).isNull();
        assertThat(dto.getMimeType()).isNull();
        assertThat(dto.getFileSize()).isNull();
        assertThat(dto.getTitle()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getBlobUri()).isNull();
    }

    /**
     * Tests the CompanyYpFileDto all-args constructor
     */
    @Test
    void testCompanyYpFileDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        String blobUri = "test-value";

        CompanyYpFileDto dto = new CompanyYpFileDto(id, chamberId, fileName, mimeType, fileSize, title, orderSeq, recdeleted, dateCreated, lastUpdated, company, language, blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getMimeType()).isEqualTo(mimeType);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyYpFileDtoSettersAndGetters() {
        CompanyYpFileDto dto = new CompanyYpFileDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        String mimeType = "test-value";
        Integer fileSize = 1;
        String title = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        String blobUri = "test-value";

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setFileName(fileName);
        dto.setMimeType(mimeType);
        dto.setFileSize(fileSize);
        dto.setTitle(title);
        dto.setOrderSeq(orderSeq);
        dto.setRecdeleted(recdeleted);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setCompany(company);
        dto.setLanguage(language);
        dto.setBlobUri(blobUri);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getMimeType()).isEqualTo(mimeType);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getTitle()).isEqualTo(title);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
    }

}
