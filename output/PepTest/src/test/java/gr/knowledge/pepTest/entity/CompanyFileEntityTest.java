package gr.knowledge.pepTest.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.UUID;
import java.time.LocalDateTime;

class CompanyFileEntityTest {

    /**
     * Tests the CompanyFile no-args constructor.
     */
    @Test
    void testCompanyFileNoArgsConstructor() {
        CompanyFile entity = new CompanyFile();

        assertThat(entity).isNotNull();
    }

    /**
     * Tests the CompanyFile all-args constructor.
     */
    @Test
    void testCompanyFileAllArgsConstructor() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        Integer fileSize = 1;
        String blobUri = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Languages language = new Languages();
        Boolean isLogo = Boolean.TRUE;
        Boolean isBackground = Boolean.TRUE;
        CompanyProfile companyProfile = new CompanyProfile();
        Boolean isEmbedded = Boolean.TRUE;

        CompanyFile entity = new CompanyFile(id, chamberId, fileName, fileSize, blobUri, orderSeq, recdeleted, dateCreated, lastUpdated, company, language, isLogo, isBackground, companyProfile, isEmbedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getIsLogo()).isEqualTo(isLogo);
        assertThat(entity.getIsBackground()).isEqualTo(isBackground);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(entity.getIsEmbedded()).isEqualTo(isEmbedded);
    }

    /**
     * Tests setters and getters symmetry.
     */
    @Test
    void testCompanyFileSettersAndGetters() {
        CompanyFile entity = new CompanyFile();

        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        Integer chamberId = 1;
        String fileName = "test-value";
        Integer fileSize = 1;
        String blobUri = "test-value";
        Integer orderSeq = 1;
        Boolean recdeleted = Boolean.TRUE;
        LocalDateTime dateCreated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        LocalDateTime lastUpdated = LocalDateTime.of(2025, 1, 1, 10, 0, 0);
        Company company = new Company();
        Languages language = new Languages();
        Boolean isLogo = Boolean.TRUE;
        Boolean isBackground = Boolean.TRUE;
        CompanyProfile companyProfile = new CompanyProfile();
        Boolean isEmbedded = Boolean.TRUE;

        entity.setId(id);
        entity.setChamberId(chamberId);
        entity.setFileName(fileName);
        entity.setFileSize(fileSize);
        entity.setBlobUri(blobUri);
        entity.setOrderSeq(orderSeq);
        entity.setRecdeleted(recdeleted);
        entity.setDateCreated(dateCreated);
        entity.setLastUpdated(lastUpdated);
        entity.setCompany(company);
        entity.setLanguage(language);
        entity.setIsLogo(isLogo);
        entity.setIsBackground(isBackground);
        entity.setCompanyProfile(companyProfile);
        entity.setIsEmbedded(isEmbedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getChamberId()).isEqualTo(chamberId);
        assertThat(entity.getFileName()).isEqualTo(fileName);
        assertThat(entity.getFileSize()).isEqualTo(fileSize);
        assertThat(entity.getBlobUri()).isEqualTo(blobUri);
        assertThat(entity.getOrderSeq()).isEqualTo(orderSeq);
        assertThat(entity.getRecdeleted()).isEqualTo(recdeleted);
        assertThat(entity.getDateCreated()).isEqualTo(dateCreated);
        assertThat(entity.getLastUpdated()).isEqualTo(lastUpdated);
        assertThat(entity.getCompany()).isEqualTo(company);
        assertThat(entity.getLanguage()).isEqualTo(language);
        assertThat(entity.getIsLogo()).isEqualTo(isLogo);
        assertThat(entity.getIsBackground()).isEqualTo(isBackground);
        assertThat(entity.getCompanyProfile()).isEqualTo(companyProfile);
        assertThat(entity.getIsEmbedded()).isEqualTo(isEmbedded);
    }

}
