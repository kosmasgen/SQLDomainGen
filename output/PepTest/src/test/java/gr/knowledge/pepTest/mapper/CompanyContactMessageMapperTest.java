package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.entity.CompanyContactMessage;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CompanyContactMessageMapperTest {

    private CompanyContactMessageMapper companyContactMessageMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyContactMessageMapper = new CompanyContactMessageMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyContactMessageToCompanyContactMessageDto() {
        CompanyContactMessage entity = createSampleCompanyContactMessageEntity();
        CompanyContactMessageDto expectedDto = createSampleCompanyContactMessageDto();

        CompanyContactMessageDto actualDto = companyContactMessageMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyContactMessageDtoToCompanyContactMessage() {
        CompanyContactMessageDto dto = createSampleCompanyContactMessageDto();
        CompanyContactMessage expectedEntity = createSampleCompanyContactMessageEntity();

        CompanyContactMessage actualEntity = companyContactMessageMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyContactMessageListToCompanyContactMessageDtoList() {
        List<CompanyContactMessage> entityList = List.of(
                createSampleCompanyContactMessageEntity(),
                createAnotherCompanyContactMessageEntity()
        );
        List<CompanyContactMessageDto> expectedDtoList = List.of(
                createSampleCompanyContactMessageDto(),
                createAnotherCompanyContactMessageDto()
        );

        List<CompanyContactMessageDto> actualDtoList = companyContactMessageMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyContactMessageDtoListToCompanyContactMessageList() {
        List<CompanyContactMessageDto> dtoList = List.of(
                createSampleCompanyContactMessageDto(),
                createAnotherCompanyContactMessageDto()
        );
        List<CompanyContactMessage> expectedEntityList = List.of(
                createSampleCompanyContactMessageEntity(),
                createAnotherCompanyContactMessageEntity()
        );

        List<CompanyContactMessage> actualEntityList = companyContactMessageMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompanyContactMessage() {
        CompanyContactMessage originalEntity = createSampleCompanyContactMessageEntity();
        CompanyContactMessage actualEntity = createSampleCompanyContactMessageEntity();
        CompanyContactMessageDto patchDto = createPatchCompanyContactMessageDto();
        CompanyContactMessage patchEntity = companyContactMessageMapper.toEntity(patchDto);

        companyContactMessageMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object fullNameExpectedValue = patchEntity.getFullName() != null ? patchEntity.getFullName() : originalEntity.getFullName();
        assertThat(actualEntity.getFullName())
                .isEqualTo(fullNameExpectedValue);

        Object senderEmailExpectedValue = patchEntity.getSenderEmail() != null ? patchEntity.getSenderEmail() : originalEntity.getSenderEmail();
        assertThat(actualEntity.getSenderEmail())
                .isEqualTo(senderEmailExpectedValue);

        Object subjectExpectedValue = patchEntity.getSubject() != null ? patchEntity.getSubject() : originalEntity.getSubject();
        assertThat(actualEntity.getSubject())
                .isEqualTo(subjectExpectedValue);

        Object messageExpectedValue = patchEntity.getMessage() != null ? patchEntity.getMessage() : originalEntity.getMessage();
        assertThat(actualEntity.getMessage())
                .isEqualTo(messageExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object companyIdExpectedValue = patchEntity.getCompanyId() != null ? patchEntity.getCompanyId() : originalEntity.getCompanyId();
        assertThat(actualEntity.getCompanyId())
                .isEqualTo(companyIdExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyContactMessageDtoListForNullOrEmptyCompanyContactMessageList() {
        assertThat(companyContactMessageMapper.toDTOList(null)).isEmpty();
        assertThat(companyContactMessageMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyContactMessageListForNullOrEmptyCompanyContactMessageDtoList() {
        assertThat(companyContactMessageMapper.toEntityList(null)).isEmpty();
        assertThat(companyContactMessageMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        CompanyContactMessage entity = createSampleCompanyContactMessageEntity();
        CompanyContactMessage expectedEntity = createSampleCompanyContactMessageEntity();

        companyContactMessageMapper.partialUpdate(entity, null);
        companyContactMessageMapper.partialUpdate(null, createPatchCompanyContactMessageDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated CompanyContactMessage fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyContactMessage createSampleCompanyContactMessageEntity() {
        CompanyContactMessage entity = new CompanyContactMessage();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setFullName("fullNameValue1");
        entity.setSenderEmail("senderEmailValue1");
        entity.setSubject("subjectValue1");
        entity.setMessage("messageValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        return entity;
    }

    /**
     * Creates a populated CompanyContactMessage fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private CompanyContactMessage createAnotherCompanyContactMessageEntity() {
        CompanyContactMessage entity = new CompanyContactMessage();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setFullName("fullNameValue2");
        entity.setSenderEmail("senderEmailValue2");
        entity.setSubject("subjectValue2");
        entity.setMessage("messageValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000002"));

        return entity;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createSampleCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setFullName("fullNameValue1");
        dto.setSenderEmail("senderEmailValue1");
        dto.setSubject("subjectValue1");
        dto.setMessage("messageValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        return dto;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createAnotherCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setFullName("fullNameValue2");
        dto.setSenderEmail("senderEmailValue2");
        dto.setSubject("subjectValue2");
        dto.setMessage("messageValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000002"));

        return dto;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createPatchCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setFullName("fullNameValue3");
        dto.setSenderEmail("senderEmailValue3");
        dto.setSubject("subjectValue3");
        dto.setMessage("messageValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setCompanyId(UUID.fromString("00000000-0000-0000-0000-000000000003"));

        return dto;
    }

}
