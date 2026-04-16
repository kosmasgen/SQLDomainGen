package gr.knowledge.pepTest.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.math.*;
import java.time.*;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyFileDtoTest {

    /**
     * Tests the CompanyFileDto no-args constructor
     */
    @Test
    void testCompanyFileDtoNoArgsConstructor() {
        CompanyFileDto dto = new CompanyFileDto();

        assertThat(dto.getId()).isNull();
        assertThat(dto.getChamberId()).isNull();
        assertThat(dto.getFileName()).isNull();
        assertThat(dto.getFileSize()).isNull();
        assertThat(dto.getBlobUri()).isNull();
        assertThat(dto.getOrderSeq()).isNull();
        assertThat(dto.getRecdeleted()).isNull();
        assertThat(dto.getDateCreated()).isNull();
        assertThat(dto.getLastUpdated()).isNull();
        assertThat(dto.getCompany()).isNull();
        assertThat(dto.getLanguage()).isNull();
        assertThat(dto.getIsLogo()).isNull();
        assertThat(dto.getIsBackground()).isNull();
        assertThat(dto.getCompanyProfile()).isNull();
        assertThat(dto.getIsEmbedded()).isNull();
    }

    /**
     * Tests the CompanyFileDto all-args constructor
     */
    @Test
    void testCompanyFileDtoAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        Integer fileSize = 1;
        String blobUri = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        Boolean isLogo = Boolean.TRUE;
        Boolean isBackground = Boolean.TRUE;
        CompanyProfileDto companyProfile = new CompanyProfileDto();
        Boolean isEmbedded = Boolean.TRUE;

        CompanyFileDto dto = new CompanyFileDto(id, chamberId, fileName, fileSize, blobUri, orderSeq, recdeleted, dateCreated, lastUpdated, company, language, isLogo, isBackground, companyProfile, isEmbedded);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getIsLogo()).isEqualTo(isLogo);
        assertThat(dto.getIsBackground()).isEqualTo(isBackground);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(dto.getIsEmbedded()).isEqualTo(isEmbedded);
    }

    /**
     * Tests setters / getters symmetry
     */
    @Test
    void testCompanyFileDtoSettersAndGetters() {
        CompanyFileDto dto = new CompanyFileDto();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        Integer fileSize = 1;
        String blobUri = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0);
        CompanyDto company = new CompanyDto();
        LanguagesDto language = new LanguagesDto();
        Boolean isLogo = Boolean.TRUE;
        Boolean isBackground = Boolean.TRUE;
        CompanyProfileDto companyProfile = new CompanyProfileDto();
        Boolean isEmbedded = Boolean.TRUE;

        dto.setId(id);
        dto.setChamberId(chamberId);
        dto.setFileName(fileName);
        dto.setFileSize(fileSize);
        dto.setBlobUri(blobUri);
        dto.setOrderSeq(orderSeq);
        dto.setRecdeleted(recdeleted);
        dto.setDateCreated(dateCreated);
        dto.setLastUpdated(lastUpdated);
        dto.setCompany(company);
        dto.setLanguage(language);
        dto.setIsLogo(isLogo);
        dto.setIsBackground(isBackground);
        dto.setCompanyProfile(companyProfile);
        dto.setIsEmbedded(isEmbedded);

        assertThat(dto.getId()).isEqualTo(id);
        assertThat(dto.getChamberId()).isEqualTo(chamberId);
        assertThat(dto.getFileName()).isEqualTo(fileName);
        assertThat(dto.getFileSize()).isEqualTo(fileSize);
        assertThat(dto.getBlobUri()).isEqualTo(blobUri);
        assertThat(dto.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(dto.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(dto.getDateCreated()).isEqualTo(dateCreated);
        assertThat(dto.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(dto.getCompany()).isEqualTo(company);
        assertThat(dto.getLanguage()).isEqualTo(language);
        assertThat(dto.getIsLogo()).isEqualTo(isLogo);
        assertThat(dto.getIsBackground()).isEqualTo(isBackground);
        assertThat(dto.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(dto.getIsEmbedded()).isEqualTo(isEmbedded);
    }

}
