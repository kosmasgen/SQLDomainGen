package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.entity.ChAppUserContactI18n;
import gr.knowledge.pepTest.entity.ChAppUserContactI18nKey;
import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyStatus;
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

class ChAppUserContactI18nMapperTest {

    private ChAppUserContactI18nMapper chAppUserContactI18nMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        chAppUserContactI18nMapper = new ChAppUserContactI18nMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapChAppUserContactI18nToChAppUserContactI18nDto() {
        ChAppUserContactI18n entity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18nDto expectedDto = createSampleChAppUserContactI18nDto();

        ChAppUserContactI18nDto actualDto = chAppUserContactI18nMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapChAppUserContactI18nDtoToChAppUserContactI18n() {
        ChAppUserContactI18nDto dto = createSampleChAppUserContactI18nDto();
        ChAppUserContactI18n expectedEntity = createSampleChAppUserContactI18nEntity();

        ChAppUserContactI18n actualEntity = chAppUserContactI18nMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapChAppUserContactI18nListToChAppUserContactI18nDtoList() {
        List<ChAppUserContactI18n> entityList = List.of(
                createSampleChAppUserContactI18nEntity(),
                createAnotherChAppUserContactI18nEntity()
        );
        List<ChAppUserContactI18nDto> expectedDtoList = List.of(
                createSampleChAppUserContactI18nDto(),
                createAnotherChAppUserContactI18nDto()
        );

        List<ChAppUserContactI18nDto> actualDtoList = chAppUserContactI18nMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapChAppUserContactI18nDtoListToChAppUserContactI18nList() {
        List<ChAppUserContactI18nDto> dtoList = List.of(
                createSampleChAppUserContactI18nDto(),
                createAnotherChAppUserContactI18nDto()
        );
        List<ChAppUserContactI18n> expectedEntityList = List.of(
                createSampleChAppUserContactI18nEntity(),
                createAnotherChAppUserContactI18nEntity()
        );

        List<ChAppUserContactI18n> actualEntityList = chAppUserContactI18nMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForChAppUserContactI18n() {
        ChAppUserContactI18n originalEntity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18n actualEntity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18nDto patchDto = createPatchChAppUserContactI18nDto();
        ChAppUserContactI18n patchEntity = chAppUserContactI18nMapper.toEntity(patchDto);

        chAppUserContactI18nMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .usingRecursiveComparison()
                .isEqualTo(originalEntity.getId());

        Object chAppUserContactExpectedValue = patchEntity.getChAppUserContact() != null ? patchEntity.getChAppUserContact() : originalEntity.getChAppUserContact();
        assertThat(actualEntity.getChAppUserContact())
                .usingRecursiveComparison()
                .isEqualTo(chAppUserContactExpectedValue);

        Object languageExpectedValue = patchEntity.getLanguage() != null ? patchEntity.getLanguage() : originalEntity.getLanguage();
        assertThat(actualEntity.getLanguage())
                .usingRecursiveComparison()
                .isEqualTo(languageExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object cityExpectedValue = patchEntity.getCity() != null ? patchEntity.getCity() : originalEntity.getCity();
        assertThat(actualEntity.getCity())
                .isEqualTo(cityExpectedValue);

        Object streetExpectedValue = patchEntity.getStreet() != null ? patchEntity.getStreet() : originalEntity.getStreet();
        assertThat(actualEntity.getStreet())
                .isEqualTo(streetExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChAppUserContactI18nDtoListForNullOrEmptyChAppUserContactI18nList() {
        assertThat(chAppUserContactI18nMapper.toDTOList(null)).isEmpty();
        assertThat(chAppUserContactI18nMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChAppUserContactI18nListForNullOrEmptyChAppUserContactI18nDtoList() {
        assertThat(chAppUserContactI18nMapper.toEntityList(null)).isEmpty();
        assertThat(chAppUserContactI18nMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ChAppUserContactI18n entity = createSampleChAppUserContactI18nEntity();
        ChAppUserContactI18n expectedEntity = createSampleChAppUserContactI18nEntity();

        chAppUserContactI18nMapper.partialUpdate(entity, null);
        chAppUserContactI18nMapper.partialUpdate(null, createPatchChAppUserContactI18nDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ChAppUserContactI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContactI18n createSampleChAppUserContactI18nEntity() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();
        ChAppUserContactI18nKey idFixture1 = new ChAppUserContactI18nKey();
        entity.setId(idFixture1);
        ChAppUserContact chAppUserContactFixture1 = new ChAppUserContact();
        chAppUserContactFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chAppUserContactFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chAppUserContactFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chAppUserContactFixture1.setPhone1("phone1Value1");
        chAppUserContactFixture1.setPhone2("phone2Value1");
        chAppUserContactFixture1.setMobile1("mobile1Value1");
        chAppUserContactFixture1.setMobile2("mobile2Value1");
        chAppUserContactFixture1.setEmail1("email1Value1");
        chAppUserContactFixture1.setEmail2("email2Value1");
        chAppUserContactFixture1.setUrl("urlValue1");
        chAppUserContactFixture1.setZipCode("zipCodeValue1");
        chAppUserContactFixture1.setLatitude("latitudeValue1");
        chAppUserContactFixture1.setLongitude("longitudeValue1");
        chAppUserContactFixture1.setStreetNumber("streetNumberValue1");
        chAppUserContactFixture1.setRecdeleted(true);
        chAppUserContactFixture1.setListingUrl("listingUrlValue1");
        chAppUserContactFixture1.setEmail("emailValue1");
        chAppUserContactFixture1.setMobile("mobileValue1");
        entity.setChAppUserContact(chAppUserContactFixture1);
        Languages languageFixture1 = new Languages();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        entity.setLanguage(languageFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setCity("cityValue1");
        entity.setStreet("streetValue1");
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactI18n fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContactI18n createAnotherChAppUserContactI18nEntity() {
        ChAppUserContactI18n entity = new ChAppUserContactI18n();
        ChAppUserContactI18nKey idFixture2 = new ChAppUserContactI18nKey();
        entity.setId(idFixture2);
        ChAppUserContact chAppUserContactFixture2 = new ChAppUserContact();
        chAppUserContactFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chAppUserContactFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chAppUserContactFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chAppUserContactFixture2.setPhone1("phone1Value2");
        chAppUserContactFixture2.setPhone2("phone2Value2");
        chAppUserContactFixture2.setMobile1("mobile1Value2");
        chAppUserContactFixture2.setMobile2("mobile2Value2");
        chAppUserContactFixture2.setEmail1("email1Value2");
        chAppUserContactFixture2.setEmail2("email2Value2");
        chAppUserContactFixture2.setUrl("urlValue2");
        chAppUserContactFixture2.setZipCode("zipCodeValue2");
        chAppUserContactFixture2.setLatitude("latitudeValue2");
        chAppUserContactFixture2.setLongitude("longitudeValue2");
        chAppUserContactFixture2.setStreetNumber("streetNumberValue2");
        chAppUserContactFixture2.setRecdeleted(false);
        chAppUserContactFixture2.setListingUrl("listingUrlValue2");
        chAppUserContactFixture2.setEmail("emailValue2");
        chAppUserContactFixture2.setMobile("mobileValue2");
        entity.setChAppUserContact(chAppUserContactFixture2);
        Languages languageFixture2 = new Languages();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        entity.setLanguage(languageFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setCity("cityValue2");
        entity.setStreet("streetValue2");
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createSampleChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        ChAppUserContactI18nKey idFixture1 = new ChAppUserContactI18nKey();
        dto.setId(idFixture1);
        ChAppUserContactDto chAppUserContactFixture1 = new ChAppUserContactDto();
        chAppUserContactFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chAppUserContactFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chAppUserContactFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chAppUserContactFixture1.setPhone1("phone1Value1");
        chAppUserContactFixture1.setPhone2("phone2Value1");
        chAppUserContactFixture1.setMobile1("mobile1Value1");
        chAppUserContactFixture1.setMobile2("mobile2Value1");
        chAppUserContactFixture1.setEmail1("email1Value1");
        chAppUserContactFixture1.setEmail2("email2Value1");
        chAppUserContactFixture1.setUrl("urlValue1");
        chAppUserContactFixture1.setZipCode("zipCodeValue1");
        chAppUserContactFixture1.setLatitude("latitudeValue1");
        chAppUserContactFixture1.setLongitude("longitudeValue1");
        chAppUserContactFixture1.setStreetNumber("streetNumberValue1");
        chAppUserContactFixture1.setRecdeleted(true);
        chAppUserContactFixture1.setListingUrl("listingUrlValue1");
        chAppUserContactFixture1.setEmail("emailValue1");
        chAppUserContactFixture1.setMobile("mobileValue1");
        dto.setChAppUserContact(chAppUserContactFixture1);
        LanguagesDto languageFixture1 = new LanguagesDto();
        languageFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        languageFixture1.setChamberId(10);
        languageFixture1.setCd("cdValue1");
        languageFixture1.setDescr("descrValue1");
        languageFixture1.setChamberLanguageId(10);
        dto.setLanguage(languageFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setCity("cityValue1");
        dto.setStreet("streetValue1");
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createAnotherChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        ChAppUserContactI18nKey idFixture2 = new ChAppUserContactI18nKey();
        dto.setId(idFixture2);
        ChAppUserContactDto chAppUserContactFixture2 = new ChAppUserContactDto();
        chAppUserContactFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chAppUserContactFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chAppUserContactFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chAppUserContactFixture2.setPhone1("phone1Value2");
        chAppUserContactFixture2.setPhone2("phone2Value2");
        chAppUserContactFixture2.setMobile1("mobile1Value2");
        chAppUserContactFixture2.setMobile2("mobile2Value2");
        chAppUserContactFixture2.setEmail1("email1Value2");
        chAppUserContactFixture2.setEmail2("email2Value2");
        chAppUserContactFixture2.setUrl("urlValue2");
        chAppUserContactFixture2.setZipCode("zipCodeValue2");
        chAppUserContactFixture2.setLatitude("latitudeValue2");
        chAppUserContactFixture2.setLongitude("longitudeValue2");
        chAppUserContactFixture2.setStreetNumber("streetNumberValue2");
        chAppUserContactFixture2.setRecdeleted(false);
        chAppUserContactFixture2.setListingUrl("listingUrlValue2");
        chAppUserContactFixture2.setEmail("emailValue2");
        chAppUserContactFixture2.setMobile("mobileValue2");
        dto.setChAppUserContact(chAppUserContactFixture2);
        LanguagesDto languageFixture2 = new LanguagesDto();
        languageFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        languageFixture2.setChamberId(20);
        languageFixture2.setCd("cdValue2");
        languageFixture2.setDescr("descrValue2");
        languageFixture2.setChamberLanguageId(20);
        dto.setLanguage(languageFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setCity("cityValue2");
        dto.setStreet("streetValue2");
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactI18nDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactI18nDto createPatchChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        ChAppUserContactDto chAppUserContactFixture3 = new ChAppUserContactDto();
        chAppUserContactFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        chAppUserContactFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        chAppUserContactFixture3.setPhone1("phone1Value3");
        chAppUserContactFixture3.setPhone2("phone2Value3");
        chAppUserContactFixture3.setMobile1("mobile1Value3");
        chAppUserContactFixture3.setMobile2("mobile2Value3");
        chAppUserContactFixture3.setEmail1("email1Value3");
        chAppUserContactFixture3.setEmail2("email2Value3");
        chAppUserContactFixture3.setZipCode("zipCodeValue3");
        chAppUserContactFixture3.setStreetNumber("streetNumberValue3");
        chAppUserContactFixture3.setRecdeleted(true);
        chAppUserContactFixture3.setEmail("emailValue3");
        chAppUserContactFixture3.setMobile("mobileValue3");
        dto.setChAppUserContact(chAppUserContactFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setStreet("streetValue3");
        dto.setRecdeleted(true);

        return dto;
    }

}
