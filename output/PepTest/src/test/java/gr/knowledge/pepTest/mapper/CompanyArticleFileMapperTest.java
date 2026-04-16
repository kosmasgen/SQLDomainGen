package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyArticleFile;
import gr.knowledge.pepTest.entity.CompanyFile;
import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CompanyYpArticle;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.entity.Municipality;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyArticleFileMapperTest {

    private CompanyArticleFileMapper companyArticleFileMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyArticleFileMapper = new CompanyArticleFileMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyArticleFileToCompanyArticleFileDto() {
        CompanyArticleFile entity = createSampleCompanyArticleFileEntity();
        CompanyArticleFileDto expectedDto = createSampleCompanyArticleFileDto();

        CompanyArticleFileDto actualDto = companyArticleFileMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyArticleFileDtoToCompanyArticleFile() {
        CompanyArticleFileDto dto = createSampleCompanyArticleFileDto();
        CompanyArticleFile expectedEntity = createSampleCompanyArticleFileEntity();

        CompanyArticleFile actualEntity = companyArticleFileMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyArticleFileListToCompanyArticleFileDtoList() {
        List<CompanyArticleFile> entityList = List.of(
                createSampleCompanyArticleFileEntity(),
                createAnotherCompanyArticleFileEntity()
        );
        List<CompanyArticleFileDto> expectedDtoList = List.of(
                createSampleCompanyArticleFileDto(),
                createAnotherCompanyArticleFileDto()
        );

        List<CompanyArticleFileDto> actualDtoList = companyArticleFileMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyArticleFileDtoListToCompanyArticleFileList() {
        List<CompanyArticleFileDto> dtoList = List.of(
                createSampleCompanyArticleFileDto(),
                createAnotherCompanyArticleFileDto()
        );
        List<CompanyArticleFile> expectedEntityList = List.of(
                createSampleCompanyArticleFileEntity(),
                createAnotherCompanyArticleFileEntity()
        );

        List<CompanyArticleFile> actualEntityList = companyArticleFileMapper.toEntityList(dtoList);

        assertThat(actualEntityList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedEntityList);
    }

    /**
     * Verifies that partialUpdate replaces every non-null mapped field,
     * preserves null-patched fields from the original entity,
     * and never changes primary key fields.
     */
    @Test
    void shouldApplyFullPartialUpdateForCompanyArticleFile() {
        CompanyArticleFile originalEntity = createSampleCompanyArticleFileEntity();
        CompanyArticleFile actualEntity = createSampleCompanyArticleFileEntity();
        CompanyArticleFileDto patchDto = createPatchCompanyArticleFileDto();
        CompanyArticleFile patchEntity = companyArticleFileMapper.toEntity(patchDto);

        companyArticleFileMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object articleExpectedValue = patchEntity.getArticle() != null ? patchEntity.getArticle() : originalEntity.getArticle();
        assertThat(actualEntity.getArticle())
                .usingRecursiveComparison()
                .isEqualTo(articleExpectedValue);

        Object fileExpectedValue = patchEntity.getFile() != null ? patchEntity.getFile() : originalEntity.getFile();
        assertThat(actualEntity.getFile())
                .usingRecursiveComparison()
                .isEqualTo(fileExpectedValue);

        Object orderSeqExpectedValue = patchEntity.getOrderSeq() != null ? patchEntity.getOrderSeq() : originalEntity.getOrderSeq();
        assertThat(actualEntity.getOrderSeq())
                .isEqualTo(orderSeqExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyArticleFileDtoListForNullOrEmptyCompanyArticleFileList() {
        assertThat(companyArticleFileMapper.toDTOList(null)).isEmpty();
        assertThat(companyArticleFileMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyArticleFileListForNullOrEmptyCompanyArticleFileDtoList() {
        assertThat(companyArticleFileMapper.toEntityList(null)).isEmpty();
        assertThat(companyArticleFileMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyArticleFile entity = createSampleCompanyArticleFileEntity();
        CompanyArticleFile expectedEntity = createSampleCompanyArticleFileEntity();

        companyArticleFileMapper.partialUpdate(entity, null);
        companyArticleFileMapper.partialUpdate(null, createPatchCompanyArticleFileDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyArticleFile fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyArticleFile createSampleCompanyArticleFileEntity() {
        CompanyArticleFile entity = new CompanyArticleFile();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        CompanyYpArticle articleFixture1 = new CompanyYpArticle();
        articleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        articleFixture1.setChamberId(10);
        articleFixture1.setTitle("titleValue1");
        articleFixture1.setHtml("htmlValue1");
        articleFixture1.setOrderSeq(10);
        articleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        articleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        articleFixture1.setRecdeleted(true);
        articleFixture1.setIsPublished(true);
        entity.setArticle(articleFixture1);
        CompanyFile fileFixture1 = new CompanyFile();
        fileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        fileFixture1.setChamberId(10);
        fileFixture1.setFileName("fileNameValue1");
        fileFixture1.setFileSize(10);
        fileFixture1.setBlobUri("blobUriValue1");
        fileFixture1.setOrderSeq(10);
        fileFixture1.setRecdeleted(true);
        fileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        fileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        fileFixture1.setIsLogo(true);
        fileFixture1.setIsBackground(true);
        fileFixture1.setIsEmbedded(true);
        entity.setFile(fileFixture1);
        entity.setOrderSeq(10);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return entity;
    }

    /**
     * Creates a populated CompanyArticleFile fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyArticleFile createAnotherCompanyArticleFileEntity() {
        CompanyArticleFile entity = new CompanyArticleFile();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        CompanyYpArticle articleFixture2 = new CompanyYpArticle();
        articleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        articleFixture2.setChamberId(20);
        articleFixture2.setTitle("titleValue2");
        articleFixture2.setHtml("htmlValue2");
        articleFixture2.setOrderSeq(20);
        articleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        articleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        articleFixture2.setRecdeleted(false);
        articleFixture2.setIsPublished(false);
        entity.setArticle(articleFixture2);
        CompanyFile fileFixture2 = new CompanyFile();
        fileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        fileFixture2.setChamberId(20);
        fileFixture2.setFileName("fileNameValue2");
        fileFixture2.setFileSize(20);
        fileFixture2.setBlobUri("blobUriValue2");
        fileFixture2.setOrderSeq(20);
        fileFixture2.setRecdeleted(false);
        fileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        fileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        fileFixture2.setIsLogo(false);
        fileFixture2.setIsBackground(false);
        fileFixture2.setIsEmbedded(false);
        entity.setFile(fileFixture2);
        entity.setOrderSeq(20);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return entity;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createSampleCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        CompanyYpArticleDto articleFixture1 = new CompanyYpArticleDto();
        articleFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        articleFixture1.setChamberId(10);
        articleFixture1.setTitle("titleValue1");
        articleFixture1.setHtml("htmlValue1");
        articleFixture1.setOrderSeq(10);
        articleFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        articleFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        articleFixture1.setRecdeleted(true);
        articleFixture1.setIsPublished(true);
        dto.setArticle(articleFixture1);
        CompanyFileDto fileFixture1 = new CompanyFileDto();
        fileFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        fileFixture1.setChamberId(10);
        fileFixture1.setFileName("fileNameValue1");
        fileFixture1.setFileSize(10);
        fileFixture1.setBlobUri("blobUriValue1");
        fileFixture1.setOrderSeq(10);
        fileFixture1.setRecdeleted(true);
        fileFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        fileFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        fileFixture1.setIsLogo(true);
        fileFixture1.setIsBackground(true);
        fileFixture1.setIsEmbedded(true);
        dto.setFile(fileFixture1);
        dto.setOrderSeq(10);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));

        return dto;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createAnotherCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        CompanyYpArticleDto articleFixture2 = new CompanyYpArticleDto();
        articleFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        articleFixture2.setChamberId(20);
        articleFixture2.setTitle("titleValue2");
        articleFixture2.setHtml("htmlValue2");
        articleFixture2.setOrderSeq(20);
        articleFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        articleFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        articleFixture2.setRecdeleted(false);
        articleFixture2.setIsPublished(false);
        dto.setArticle(articleFixture2);
        CompanyFileDto fileFixture2 = new CompanyFileDto();
        fileFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        fileFixture2.setChamberId(20);
        fileFixture2.setFileName("fileNameValue2");
        fileFixture2.setFileSize(20);
        fileFixture2.setBlobUri("blobUriValue2");
        fileFixture2.setOrderSeq(20);
        fileFixture2.setRecdeleted(false);
        fileFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        fileFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        fileFixture2.setIsLogo(false);
        fileFixture2.setIsBackground(false);
        fileFixture2.setIsEmbedded(false);
        dto.setFile(fileFixture2);
        dto.setOrderSeq(20);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));

        return dto;
    }

    /**
     * Creates a populated CompanyArticleFileDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyArticleFileDto createPatchCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        CompanyYpArticleDto articleFixture3 = new CompanyYpArticleDto();
        articleFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        articleFixture3.setChamberId(30);
        articleFixture3.setHtml("htmlValue3");
        articleFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        articleFixture3.setRecdeleted(true);
        articleFixture3.setIsPublished(true);
        dto.setArticle(articleFixture3);
        CompanyFileDto fileFixture3 = new CompanyFileDto();
        fileFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        fileFixture3.setChamberId(30);
        fileFixture3.setFileName("fileNameValue3");
        fileFixture3.setFileSize(30);
        fileFixture3.setBlobUri("blobUriValue3");
        fileFixture3.setRecdeleted(true);
        fileFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        fileFixture3.setIsLogo(true);
        fileFixture3.setIsBackground(true);
        dto.setFile(fileFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));

        return dto;
    }

}
