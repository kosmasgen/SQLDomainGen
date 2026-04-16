package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.entity.ChamberAppUser;
import gr.knowledge.pepTest.entity.ChamberDepartment;
import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.entity.CompanyStatus;
import gr.knowledge.pepTest.entity.CorporateStatus;
import gr.knowledge.pepTest.entity.Country;
import gr.knowledge.pepTest.entity.Municipality;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class ChAppUserContactMapperTest {

    private ChAppUserContactMapper chAppUserContactMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        chAppUserContactMapper = new ChAppUserContactMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapChAppUserContactToChAppUserContactDto() {
        ChAppUserContact entity = createSampleChAppUserContactEntity();
        ChAppUserContactDto expectedDto = createSampleChAppUserContactDto();

        ChAppUserContactDto actualDto = chAppUserContactMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapChAppUserContactDtoToChAppUserContact() {
        ChAppUserContactDto dto = createSampleChAppUserContactDto();
        ChAppUserContact expectedEntity = createSampleChAppUserContactEntity();

        ChAppUserContact actualEntity = chAppUserContactMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapChAppUserContactListToChAppUserContactDtoList() {
        List<ChAppUserContact> entityList = List.of(
                createSampleChAppUserContactEntity(),
                createAnotherChAppUserContactEntity()
        );
        List<ChAppUserContactDto> expectedDtoList = List.of(
                createSampleChAppUserContactDto(),
                createAnotherChAppUserContactDto()
        );

        List<ChAppUserContactDto> actualDtoList = chAppUserContactMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapChAppUserContactDtoListToChAppUserContactList() {
        List<ChAppUserContactDto> dtoList = List.of(
                createSampleChAppUserContactDto(),
                createAnotherChAppUserContactDto()
        );
        List<ChAppUserContact> expectedEntityList = List.of(
                createSampleChAppUserContactEntity(),
                createAnotherChAppUserContactEntity()
        );

        List<ChAppUserContact> actualEntityList = chAppUserContactMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForChAppUserContact() {
        ChAppUserContact originalEntity = createSampleChAppUserContactEntity();
        ChAppUserContact actualEntity = createSampleChAppUserContactEntity();
        ChAppUserContactDto patchDto = createPatchChAppUserContactDto();
        ChAppUserContact patchEntity = chAppUserContactMapper.toEntity(patchDto);

        chAppUserContactMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object chamberAppUserExpectedValue = patchEntity.getChamberAppUser() != null ? patchEntity.getChamberAppUser() : originalEntity.getChamberAppUser();
        assertThat(actualEntity.getChamberAppUser())
                .usingRecursiveComparison()
                .isEqualTo(chamberAppUserExpectedValue);

        Object phone1ExpectedValue = patchEntity.getPhone1() != null ? patchEntity.getPhone1() : originalEntity.getPhone1();
        assertThat(actualEntity.getPhone1())
                .isEqualTo(phone1ExpectedValue);

        Object phone2ExpectedValue = patchEntity.getPhone2() != null ? patchEntity.getPhone2() : originalEntity.getPhone2();
        assertThat(actualEntity.getPhone2())
                .isEqualTo(phone2ExpectedValue);

        Object mobile1ExpectedValue = patchEntity.getMobile1() != null ? patchEntity.getMobile1() : originalEntity.getMobile1();
        assertThat(actualEntity.getMobile1())
                .isEqualTo(mobile1ExpectedValue);

        Object mobile2ExpectedValue = patchEntity.getMobile2() != null ? patchEntity.getMobile2() : originalEntity.getMobile2();
        assertThat(actualEntity.getMobile2())
                .isEqualTo(mobile2ExpectedValue);

        Object email1ExpectedValue = patchEntity.getEmail1() != null ? patchEntity.getEmail1() : originalEntity.getEmail1();
        assertThat(actualEntity.getEmail1())
                .isEqualTo(email1ExpectedValue);

        Object email2ExpectedValue = patchEntity.getEmail2() != null ? patchEntity.getEmail2() : originalEntity.getEmail2();
        assertThat(actualEntity.getEmail2())
                .isEqualTo(email2ExpectedValue);

        Object urlExpectedValue = patchEntity.getUrl() != null ? patchEntity.getUrl() : originalEntity.getUrl();
        assertThat(actualEntity.getUrl())
                .isEqualTo(urlExpectedValue);

        Object zipCodeExpectedValue = patchEntity.getZipCode() != null ? patchEntity.getZipCode() : originalEntity.getZipCode();
        assertThat(actualEntity.getZipCode())
                .isEqualTo(zipCodeExpectedValue);

        Object latitudeExpectedValue = patchEntity.getLatitude() != null ? patchEntity.getLatitude() : originalEntity.getLatitude();
        assertThat(actualEntity.getLatitude())
                .isEqualTo(latitudeExpectedValue);

        Object longitudeExpectedValue = patchEntity.getLongitude() != null ? patchEntity.getLongitude() : originalEntity.getLongitude();
        assertThat(actualEntity.getLongitude())
                .isEqualTo(longitudeExpectedValue);

        Object streetNumberExpectedValue = patchEntity.getStreetNumber() != null ? patchEntity.getStreetNumber() : originalEntity.getStreetNumber();
        assertThat(actualEntity.getStreetNumber())
                .isEqualTo(streetNumberExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object listingUrlExpectedValue = patchEntity.getListingUrl() != null ? patchEntity.getListingUrl() : originalEntity.getListingUrl();
        assertThat(actualEntity.getListingUrl())
                .isEqualTo(listingUrlExpectedValue);

        Object emailExpectedValue = patchEntity.getEmail() != null ? patchEntity.getEmail() : originalEntity.getEmail();
        assertThat(actualEntity.getEmail())
                .isEqualTo(emailExpectedValue);

        Object mobileExpectedValue = patchEntity.getMobile() != null ? patchEntity.getMobile() : originalEntity.getMobile();
        assertThat(actualEntity.getMobile())
                .isEqualTo(mobileExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChAppUserContactDtoListForNullOrEmptyChAppUserContactList() {
        assertThat(chAppUserContactMapper.toDTOList(null)).isEmpty();
        assertThat(chAppUserContactMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyChAppUserContactListForNullOrEmptyChAppUserContactDtoList() {
        assertThat(chAppUserContactMapper.toEntityList(null)).isEmpty();
        assertThat(chAppUserContactMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        ChAppUserContact entity = createSampleChAppUserContactEntity();
        ChAppUserContact expectedEntity = createSampleChAppUserContactEntity();

        chAppUserContactMapper.partialUpdate(entity, null);
        chAppUserContactMapper.partialUpdate(null, createPatchChAppUserContactDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated ChAppUserContact fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContact createSampleChAppUserContactEntity() {
        ChAppUserContact entity = new ChAppUserContact();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        ChamberAppUser chamberAppUserFixture1 = new ChamberAppUser();
        chamberAppUserFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberAppUserFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberAppUserFixture1.setChamberId(10);
        chamberAppUserFixture1.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setRecdeleted(true);
        chamberAppUserFixture1.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setChamberAppUser(chamberAppUserFixture1);
        entity.setPhone1("phone1Value1");
        entity.setPhone2("phone2Value1");
        entity.setMobile1("mobile1Value1");
        entity.setMobile2("mobile2Value1");
        entity.setEmail1("email1Value1");
        entity.setEmail2("email2Value1");
        entity.setUrl("urlValue1");
        entity.setZipCode("zipCodeValue1");
        entity.setLatitude("latitudeValue1");
        entity.setLongitude("longitudeValue1");
        entity.setStreetNumber("streetNumberValue1");
        entity.setRecdeleted(true);
        entity.setListingUrl("listingUrlValue1");
        entity.setEmail("emailValue1");
        entity.setMobile("mobileValue1");

        return entity;
    }

    /**
     * Creates a populated ChAppUserContact fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContact createAnotherChAppUserContactEntity() {
        ChAppUserContact entity = new ChAppUserContact();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        ChamberAppUser chamberAppUserFixture2 = new ChamberAppUser();
        chamberAppUserFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberAppUserFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberAppUserFixture2.setChamberId(20);
        chamberAppUserFixture2.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setRecdeleted(false);
        chamberAppUserFixture2.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setChamberAppUser(chamberAppUserFixture2);
        entity.setPhone1("phone1Value2");
        entity.setPhone2("phone2Value2");
        entity.setMobile1("mobile1Value2");
        entity.setMobile2("mobile2Value2");
        entity.setEmail1("email1Value2");
        entity.setEmail2("email2Value2");
        entity.setUrl("urlValue2");
        entity.setZipCode("zipCodeValue2");
        entity.setLatitude("latitudeValue2");
        entity.setLongitude("longitudeValue2");
        entity.setStreetNumber("streetNumberValue2");
        entity.setRecdeleted(false);
        entity.setListingUrl("listingUrlValue2");
        entity.setEmail("emailValue2");
        entity.setMobile("mobileValue2");

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createSampleChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        ChamberAppUserDto chamberAppUserFixture1 = new ChamberAppUserDto();
        chamberAppUserFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberAppUserFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberAppUserFixture1.setChamberId(10);
        chamberAppUserFixture1.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setRecdeleted(true);
        chamberAppUserFixture1.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberAppUserFixture1.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setChamberAppUser(chamberAppUserFixture1);
        dto.setPhone1("phone1Value1");
        dto.setPhone2("phone2Value1");
        dto.setMobile1("mobile1Value1");
        dto.setMobile2("mobile2Value1");
        dto.setEmail1("email1Value1");
        dto.setEmail2("email2Value1");
        dto.setUrl("urlValue1");
        dto.setZipCode("zipCodeValue1");
        dto.setLatitude("latitudeValue1");
        dto.setLongitude("longitudeValue1");
        dto.setStreetNumber("streetNumberValue1");
        dto.setRecdeleted(true);
        dto.setListingUrl("listingUrlValue1");
        dto.setEmail("emailValue1");
        dto.setMobile("mobileValue1");

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createAnotherChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        ChamberAppUserDto chamberAppUserFixture2 = new ChamberAppUserDto();
        chamberAppUserFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberAppUserFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberAppUserFixture2.setChamberId(20);
        chamberAppUserFixture2.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setRecdeleted(false);
        chamberAppUserFixture2.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberAppUserFixture2.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setChamberAppUser(chamberAppUserFixture2);
        dto.setPhone1("phone1Value2");
        dto.setPhone2("phone2Value2");
        dto.setMobile1("mobile1Value2");
        dto.setMobile2("mobile2Value2");
        dto.setEmail1("email1Value2");
        dto.setEmail2("email2Value2");
        dto.setUrl("urlValue2");
        dto.setZipCode("zipCodeValue2");
        dto.setLatitude("latitudeValue2");
        dto.setLongitude("longitudeValue2");
        dto.setStreetNumber("streetNumberValue2");
        dto.setRecdeleted(false);
        dto.setListingUrl("listingUrlValue2");
        dto.setEmail("emailValue2");
        dto.setMobile("mobileValue2");

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createPatchChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        ChamberAppUserDto chamberAppUserFixture3 = new ChamberAppUserDto();
        chamberAppUserFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        chamberAppUserFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        chamberAppUserFixture3.setChamberId(30);
        chamberAppUserFixture3.setChamberAppId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        chamberAppUserFixture3.setRecdeleted(true);
        chamberAppUserFixture3.setProfileId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        chamberAppUserFixture3.setPersonId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setChamberAppUser(chamberAppUserFixture3);
        dto.setPhone1("phone1Value3");
        dto.setPhone2("phone2Value3");
        dto.setMobile1("mobile1Value3");
        dto.setMobile2("mobile2Value3");
        dto.setEmail1("email1Value3");
        dto.setEmail2("email2Value3");
        dto.setZipCode("zipCodeValue3");
        dto.setStreetNumber("streetNumberValue3");
        dto.setRecdeleted(true);
        dto.setEmail("emailValue3");
        dto.setMobile("mobileValue3");

        return dto;
    }

}
