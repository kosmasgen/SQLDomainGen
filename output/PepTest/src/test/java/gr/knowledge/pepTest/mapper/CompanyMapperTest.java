package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.entity.BusinessLocation;
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

class CompanyMapperTest {

    private CompanyMapper companyMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        companyMapper = new CompanyMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyToCompanyDto() {
        Company entity = createSampleCompanyEntity();
        CompanyDto expectedDto = createSampleCompanyDto();

        CompanyDto actualDto = companyMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapCompanyDtoToCompany() {
        CompanyDto dto = createSampleCompanyDto();
        Company expectedEntity = createSampleCompanyEntity();

        Company actualEntity = companyMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapCompanyListToCompanyDtoList() {
        List<Company> entityList = List.of(
                createSampleCompanyEntity(),
                createAnotherCompanyEntity()
        );
        List<CompanyDto> expectedDtoList = List.of(
                createSampleCompanyDto(),
                createAnotherCompanyDto()
        );

        List<CompanyDto> actualDtoList = companyMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapCompanyDtoListToCompanyList() {
        List<CompanyDto> dtoList = List.of(
                createSampleCompanyDto(),
                createAnotherCompanyDto()
        );
        List<Company> expectedEntityList = List.of(
                createSampleCompanyEntity(),
                createAnotherCompanyEntity()
        );

        List<Company> actualEntityList = companyMapper.toEntityList(dtoList);

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
    void shouldApplyFullPartialUpdateForCompany() {
        Company originalEntity = createSampleCompanyEntity();
        Company actualEntity = createSampleCompanyEntity();
        CompanyDto patchDto = createPatchCompanyDto();
        Company patchEntity = companyMapper.toEntity(patchDto);

        companyMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object afmExpectedValue = patchEntity.getAfm() != null ? patchEntity.getAfm() : originalEntity.getAfm();
        assertThat(actualEntity.getAfm())
                .isEqualTo(afmExpectedValue);

        Object amExpectedValue = patchEntity.getAm() != null ? patchEntity.getAm() : originalEntity.getAm();
        assertThat(actualEntity.getAm())
                .isEqualTo(amExpectedValue);

        Object gemiIdExpectedValue = patchEntity.getGemiId() != null ? patchEntity.getGemiId() : originalEntity.getGemiId();
        assertThat(actualEntity.getGemiId())
                .isEqualTo(gemiIdExpectedValue);

        Object coNameExpectedValue = patchEntity.getCoName() != null ? patchEntity.getCoName() : originalEntity.getCoName();
        assertThat(actualEntity.getCoName())
                .isEqualTo(coNameExpectedValue);

        Object chamberCompanyIdExpectedValue = patchEntity.getChamberCompanyId() != null ? patchEntity.getChamberCompanyId() : originalEntity.getChamberCompanyId();
        assertThat(actualEntity.getChamberCompanyId())
                .isEqualTo(chamberCompanyIdExpectedValue);

        Object chamberIdExpectedValue = patchEntity.getChamberId() != null ? patchEntity.getChamberId() : originalEntity.getChamberId();
        assertThat(actualEntity.getChamberId())
                .isEqualTo(chamberIdExpectedValue);

        Object cancelDateExpectedValue = patchEntity.getCancelDate() != null ? patchEntity.getCancelDate() : originalEntity.getCancelDate();
        assertThat(actualEntity.getCancelDate())
                .isEqualTo(cancelDateExpectedValue);

        Object dateInterruptionExpectedValue = patchEntity.getDateInterruption() != null ? patchEntity.getDateInterruption() : originalEntity.getDateInterruption();
        assertThat(actualEntity.getDateInterruption())
                .isEqualTo(dateInterruptionExpectedValue);

        Object memberExpectedValue = patchEntity.getMember() != null ? patchEntity.getMember() : originalEntity.getMember();
        assertThat(actualEntity.getMember())
                .isEqualTo(memberExpectedValue);

        Object recTypeExpectedValue = patchEntity.getRecType() != null ? patchEntity.getRecType() : originalEntity.getRecType();
        assertThat(actualEntity.getRecType())
                .isEqualTo(recTypeExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

        Object addressCityExpectedValue = patchEntity.getAddressCity() != null ? patchEntity.getAddressCity() : originalEntity.getAddressCity();
        assertThat(actualEntity.getAddressCity())
                .isEqualTo(addressCityExpectedValue);

        Object addressLatitudeExpectedValue = patchEntity.getAddressLatitude() != null ? patchEntity.getAddressLatitude() : originalEntity.getAddressLatitude();
        assertThat(actualEntity.getAddressLatitude())
                .isEqualTo(addressLatitudeExpectedValue);

        Object addressLongitudeExpectedValue = patchEntity.getAddressLongitude() != null ? patchEntity.getAddressLongitude() : originalEntity.getAddressLongitude();
        assertThat(actualEntity.getAddressLongitude())
                .isEqualTo(addressLongitudeExpectedValue);

        Object addressMunicipalityPriExpectedValue = patchEntity.getAddressMunicipalityPri() != null ? patchEntity.getAddressMunicipalityPri() : originalEntity.getAddressMunicipalityPri();
        assertThat(actualEntity.getAddressMunicipalityPri())
                .usingRecursiveComparison()
                .isEqualTo(addressMunicipalityPriExpectedValue);

        Object addressRegionExpectedValue = patchEntity.getAddressRegion() != null ? patchEntity.getAddressRegion() : originalEntity.getAddressRegion();
        assertThat(actualEntity.getAddressRegion())
                .isEqualTo(addressRegionExpectedValue);

        Object addressStreetExpectedValue = patchEntity.getAddressStreet() != null ? patchEntity.getAddressStreet() : originalEntity.getAddressStreet();
        assertThat(actualEntity.getAddressStreet())
                .isEqualTo(addressStreetExpectedValue);

        Object addressStreetNumberExpectedValue = patchEntity.getAddressStreetNumber() != null ? patchEntity.getAddressStreetNumber() : originalEntity.getAddressStreetNumber();
        assertThat(actualEntity.getAddressStreetNumber())
                .isEqualTo(addressStreetNumberExpectedValue);

        Object addressZipCodeExpectedValue = patchEntity.getAddressZipCode() != null ? patchEntity.getAddressZipCode() : originalEntity.getAddressZipCode();
        assertThat(actualEntity.getAddressZipCode())
                .isEqualTo(addressZipCodeExpectedValue);

        Object branchTypeIdExpectedValue = patchEntity.getBranchTypeId() != null ? patchEntity.getBranchTypeId() : originalEntity.getBranchTypeId();
        assertThat(actualEntity.getBranchTypeId())
                .isEqualTo(branchTypeIdExpectedValue);

        Object chamberDepartmentExpectedValue = patchEntity.getChamberDepartment() != null ? patchEntity.getChamberDepartment() : originalEntity.getChamberDepartment();
        assertThat(actualEntity.getChamberDepartment())
                .usingRecursiveComparison()
                .isEqualTo(chamberDepartmentExpectedValue);

        Object chamberGemiResponsibleIdExpectedValue = patchEntity.getChamberGemiResponsibleId() != null ? patchEntity.getChamberGemiResponsibleId() : originalEntity.getChamberGemiResponsibleId();
        assertThat(actualEntity.getChamberGemiResponsibleId())
                .isEqualTo(chamberGemiResponsibleIdExpectedValue);

        Object coNameNrmExpectedValue = patchEntity.getCoNameNrm() != null ? patchEntity.getCoNameNrm() : originalEntity.getCoNameNrm();
        assertThat(actualEntity.getCoNameNrm())
                .isEqualTo(coNameNrmExpectedValue);

        Object companyStatusExpectedValue = patchEntity.getCompanyStatus() != null ? patchEntity.getCompanyStatus() : originalEntity.getCompanyStatus();
        assertThat(actualEntity.getCompanyStatus())
                .usingRecursiveComparison()
                .isEqualTo(companyStatusExpectedValue);

        Object contactEmailExpectedValue = patchEntity.getContactEmail() != null ? patchEntity.getContactEmail() : originalEntity.getContactEmail();
        assertThat(actualEntity.getContactEmail())
                .isEqualTo(contactEmailExpectedValue);

        Object contactMobileExpectedValue = patchEntity.getContactMobile() != null ? patchEntity.getContactMobile() : originalEntity.getContactMobile();
        assertThat(actualEntity.getContactMobile())
                .isEqualTo(contactMobileExpectedValue);

        Object contactPhone1ExpectedValue = patchEntity.getContactPhone1() != null ? patchEntity.getContactPhone1() : originalEntity.getContactPhone1();
        assertThat(actualEntity.getContactPhone1())
                .isEqualTo(contactPhone1ExpectedValue);

        Object contactPhone2ExpectedValue = patchEntity.getContactPhone2() != null ? patchEntity.getContactPhone2() : originalEntity.getContactPhone2();
        assertThat(actualEntity.getContactPhone2())
                .isEqualTo(contactPhone2ExpectedValue);

        Object contactUrlExpectedValue = patchEntity.getContactUrl() != null ? patchEntity.getContactUrl() : originalEntity.getContactUrl();
        assertThat(actualEntity.getContactUrl())
                .isEqualTo(contactUrlExpectedValue);

        Object corporateStatusExpectedValue = patchEntity.getCorporateStatus() != null ? patchEntity.getCorporateStatus() : originalEntity.getCorporateStatus();
        assertThat(actualEntity.getCorporateStatus())
                .usingRecursiveComparison()
                .isEqualTo(corporateStatusExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object dateIncorporatedExpectedValue = patchEntity.getDateIncorporated() != null ? patchEntity.getDateIncorporated() : originalEntity.getDateIncorporated();
        assertThat(actualEntity.getDateIncorporated())
                .isEqualTo(dateIncorporatedExpectedValue);

        Object dateRegisteredExpectedValue = patchEntity.getDateRegistered() != null ? patchEntity.getDateRegistered() : originalEntity.getDateRegistered();
        assertThat(actualEntity.getDateRegistered())
                .isEqualTo(dateRegisteredExpectedValue);

        Object gemiNumberExpectedValue = patchEntity.getGemiNumber() != null ? patchEntity.getGemiNumber() : originalEntity.getGemiNumber();
        assertThat(actualEntity.getGemiNumber())
                .isEqualTo(gemiNumberExpectedValue);

        Object objectiveExpectedValue = patchEntity.getObjective() != null ? patchEntity.getObjective() : originalEntity.getObjective();
        assertThat(actualEntity.getObjective())
                .isEqualTo(objectiveExpectedValue);

        Object receiveNewsletterExpectedValue = patchEntity.getReceiveNewsletter() != null ? patchEntity.getReceiveNewsletter() : originalEntity.getReceiveNewsletter();
        assertThat(actualEntity.getReceiveNewsletter())
                .isEqualTo(receiveNewsletterExpectedValue);

        Object isChamberCompanyExpectedValue = patchEntity.getIsChamberCompany() != null ? patchEntity.getIsChamberCompany() : originalEntity.getIsChamberCompany();
        assertThat(actualEntity.getIsChamberCompany())
                .isEqualTo(isChamberCompanyExpectedValue);

        Object isTradesCompanyExpectedValue = patchEntity.getIsTradesCompany() != null ? patchEntity.getIsTradesCompany() : originalEntity.getIsTradesCompany();
        assertThat(actualEntity.getIsTradesCompany())
                .isEqualTo(isTradesCompanyExpectedValue);

        Object showBusinessGuideExpectedValue = patchEntity.getShowBusinessGuide() != null ? patchEntity.getShowBusinessGuide() : originalEntity.getShowBusinessGuide();
        assertThat(actualEntity.getShowBusinessGuide())
                .isEqualTo(showBusinessGuideExpectedValue);

        Object businessLocationExpectedValue = patchEntity.getBusinessLocation() != null ? patchEntity.getBusinessLocation() : originalEntity.getBusinessLocation();
        assertThat(actualEntity.getBusinessLocation())
                .usingRecursiveComparison()
                .isEqualTo(businessLocationExpectedValue);

        Object hasActiveProfilesExpectedValue = patchEntity.getHasActiveProfiles() != null ? patchEntity.getHasActiveProfiles() : originalEntity.getHasActiveProfiles();
        assertThat(actualEntity.getHasActiveProfiles())
                .isEqualTo(hasActiveProfilesExpectedValue);

        Object isProteasDataExpectedValue = patchEntity.getIsProteasData() != null ? patchEntity.getIsProteasData() : originalEntity.getIsProteasData();
        assertThat(actualEntity.getIsProteasData())
                .isEqualTo(isProteasDataExpectedValue);

        Object responsibleNameExpectedValue = patchEntity.getResponsibleName() != null ? patchEntity.getResponsibleName() : originalEntity.getResponsibleName();
        assertThat(actualEntity.getResponsibleName())
                .isEqualTo(responsibleNameExpectedValue);

        Object addressCountryExpectedValue = patchEntity.getAddressCountry() != null ? patchEntity.getAddressCountry() : originalEntity.getAddressCountry();
        assertThat(actualEntity.getAddressCountry())
                .usingRecursiveComparison()
                .isEqualTo(addressCountryExpectedValue);

        Object addressZoomLevelExpectedValue = patchEntity.getAddressZoomLevel() != null ? patchEntity.getAddressZoomLevel() : originalEntity.getAddressZoomLevel();
        assertThat(actualEntity.getAddressZoomLevel())
                .isEqualTo(addressZoomLevelExpectedValue);

        Object contactPhone3ExpectedValue = patchEntity.getContactPhone3() != null ? patchEntity.getContactPhone3() : originalEntity.getContactPhone3();
        assertThat(actualEntity.getContactPhone3())
                .isEqualTo(contactPhone3ExpectedValue);

        Object dateProfessionStartedExpectedValue = patchEntity.getDateProfessionStarted() != null ? patchEntity.getDateProfessionStarted() : originalEntity.getDateProfessionStarted();
        assertThat(actualEntity.getDateProfessionStarted())
                .isEqualTo(dateProfessionStartedExpectedValue);

        Object foundationDateExpectedValue = patchEntity.getFoundationDate() != null ? patchEntity.getFoundationDate() : originalEntity.getFoundationDate();
        assertThat(actualEntity.getFoundationDate())
                .isEqualTo(foundationDateExpectedValue);

        Object meCriteria1IdExpectedValue = patchEntity.getMeCriteria1Id() != null ? patchEntity.getMeCriteria1Id() : originalEntity.getMeCriteria1Id();
        assertThat(actualEntity.getMeCriteria1Id())
                .isEqualTo(meCriteria1IdExpectedValue);

        Object meCriteria2IdExpectedValue = patchEntity.getMeCriteria2Id() != null ? patchEntity.getMeCriteria2Id() : originalEntity.getMeCriteria2Id();
        assertThat(actualEntity.getMeCriteria2Id())
                .isEqualTo(meCriteria2IdExpectedValue);

        Object meCriteria3IdExpectedValue = patchEntity.getMeCriteria3Id() != null ? patchEntity.getMeCriteria3Id() : originalEntity.getMeCriteria3Id();
        assertThat(actualEntity.getMeCriteria3Id())
                .isEqualTo(meCriteria3IdExpectedValue);

        Object memberDuesExpectedValue = patchEntity.getMemberDues() != null ? patchEntity.getMemberDues() : originalEntity.getMemberDues();
        assertThat(actualEntity.getMemberDues())
                .isEqualTo(memberDuesExpectedValue);

        Object jbUuidExpectedValue = patchEntity.getJbUuid() != null ? patchEntity.getJbUuid() : originalEntity.getJbUuid();
        assertThat(actualEntity.getJbUuid())
                .isEqualTo(jbUuidExpectedValue);

        Object jbDescriptionExpectedValue = patchEntity.getJbDescription() != null ? patchEntity.getJbDescription() : originalEntity.getJbDescription();
        assertThat(actualEntity.getJbDescription())
                .isEqualTo(jbDescriptionExpectedValue);

        Object jbNumberEmployeesExpectedValue = patchEntity.getJbNumberEmployees() != null ? patchEntity.getJbNumberEmployees() : originalEntity.getJbNumberEmployees();
        assertThat(actualEntity.getJbNumberEmployees())
                .isEqualTo(jbNumberEmployeesExpectedValue);

        Object jbMottoExpectedValue = patchEntity.getJbMotto() != null ? patchEntity.getJbMotto() : originalEntity.getJbMotto();
        assertThat(actualEntity.getJbMotto())
                .isEqualTo(jbMottoExpectedValue);

        Object jbEmailExpectedValue = patchEntity.getJbEmail() != null ? patchEntity.getJbEmail() : originalEntity.getJbEmail();
        assertThat(actualEntity.getJbEmail())
                .isEqualTo(jbEmailExpectedValue);

        Object jbLinkedInUrlExpectedValue = patchEntity.getJbLinkedInUrl() != null ? patchEntity.getJbLinkedInUrl() : originalEntity.getJbLinkedInUrl();
        assertThat(actualEntity.getJbLinkedInUrl())
                .isEqualTo(jbLinkedInUrlExpectedValue);

        Object jbFacebookUrlExpectedValue = patchEntity.getJbFacebookUrl() != null ? patchEntity.getJbFacebookUrl() : originalEntity.getJbFacebookUrl();
        assertThat(actualEntity.getJbFacebookUrl())
                .isEqualTo(jbFacebookUrlExpectedValue);

        Object jbRegistrationStatusExpectedValue = patchEntity.getJbRegistrationStatus() != null ? patchEntity.getJbRegistrationStatus() : originalEntity.getJbRegistrationStatus();
        assertThat(actualEntity.getJbRegistrationStatus())
                .isEqualTo(jbRegistrationStatusExpectedValue);

        Object jbLogoIdExpectedValue = patchEntity.getJbLogoId() != null ? patchEntity.getJbLogoId() : originalEntity.getJbLogoId();
        assertThat(actualEntity.getJbLogoId())
                .isEqualTo(jbLogoIdExpectedValue);

        Object jbCoverIdExpectedValue = patchEntity.getJbCoverId() != null ? patchEntity.getJbCoverId() : originalEntity.getJbCoverId();
        assertThat(actualEntity.getJbCoverId())
                .isEqualTo(jbCoverIdExpectedValue);

        Object jbLocationIdExpectedValue = patchEntity.getJbLocationId() != null ? patchEntity.getJbLocationId() : originalEntity.getJbLocationId();
        assertThat(actualEntity.getJbLocationId())
                .isEqualTo(jbLocationIdExpectedValue);

        Object jbIndustryIdExpectedValue = patchEntity.getJbIndustryId() != null ? patchEntity.getJbIndustryId() : originalEntity.getJbIndustryId();
        assertThat(actualEntity.getJbIndustryId())
                .isEqualTo(jbIndustryIdExpectedValue);

        Object jbIsvalidExpectedValue = patchEntity.getJbIsvalid() != null ? patchEntity.getJbIsvalid() : originalEntity.getJbIsvalid();
        assertThat(actualEntity.getJbIsvalid())
                .isEqualTo(jbIsvalidExpectedValue);

        Object jbActivationStatusExpectedValue = patchEntity.getJbActivationStatus() != null ? patchEntity.getJbActivationStatus() : originalEntity.getJbActivationStatus();
        assertThat(actualEntity.getJbActivationStatus())
                .isEqualTo(jbActivationStatusExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyDtoListForNullOrEmptyCompanyList() {
        assertThat(companyMapper.toDTOList(null)).isEmpty();
        assertThat(companyMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyCompanyListForNullOrEmptyCompanyDtoList() {
        assertThat(companyMapper.toEntityList(null)).isEmpty();
        assertThat(companyMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Company entity = createSampleCompanyEntity();
        Company expectedEntity = createSampleCompanyEntity();

        companyMapper.partialUpdate(entity, null);
        companyMapper.partialUpdate(null, createPatchCompanyDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Company fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Company createSampleCompanyEntity() {
        Company entity = new Company();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setAfm("afmValue1");
        entity.setAm(new BigInteger("1000"));
        entity.setGemiId(new BigInteger("1000"));
        entity.setCoName("coNameValue1");
        entity.setChamberCompanyId(new BigInteger("1000"));
        entity.setChamberId(10);
        entity.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setMember(new BigInteger("1000"));
        entity.setRecType("recTypeValue1");
        entity.setRecdeleted(true);
        entity.setAddressCity("addressCityValue1");
        entity.setAddressLatitude("addressLatitudeValue1");
        entity.setAddressLongitude("addressLongitudeValue1");
        Municipality addressMunicipalityPriFixture1 = new Municipality();
        addressMunicipalityPriFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressMunicipalityPriFixture1.setChamberId(100L);
        addressMunicipalityPriFixture1.setChamberMunicipalityId(100L);
        addressMunicipalityPriFixture1.setDescription("descriptionValue1");
        addressMunicipalityPriFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressMunicipalityPriFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressMunicipalityPriFixture1.setRecdeleted(true);
        addressMunicipalityPriFixture1.setCd("cdValue1");
        addressMunicipalityPriFixture1.setIsProteasData(true);
        entity.setAddressMunicipalityPri(addressMunicipalityPriFixture1);
        entity.setAddressRegion("addressRegionValue1");
        entity.setAddressStreet("addressStreetValue1");
        entity.setAddressStreetNumber("addressStreetNumberValue1");
        entity.setAddressZipCode("addressZipCodeValue1");
        entity.setBranchTypeId(new BigInteger("1000"));
        ChamberDepartment chamberDepartmentFixture1 = new ChamberDepartment();
        chamberDepartmentFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberDepartmentFixture1.setChamberDepartmentId(10);
        chamberDepartmentFixture1.setChamberId(10);
        chamberDepartmentFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberDepartmentFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberDepartmentFixture1.setRecdeleted(true);
        chamberDepartmentFixture1.setCd("cdValue1");
        entity.setChamberDepartment(chamberDepartmentFixture1);
        entity.setChamberGemiResponsibleId(new BigInteger("1000"));
        entity.setCoNameNrm("coNameNrmValue1");
        CompanyStatus companyStatusFixture1 = new CompanyStatus();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        entity.setCompanyStatus(companyStatusFixture1);
        entity.setContactEmail("contactEmailValue1");
        entity.setContactMobile("contactMobileValue1");
        entity.setContactPhone1("contactPhone1Value1");
        entity.setContactPhone2("contactPhone2Value1");
        entity.setContactUrl("contactUrlValue1");
        CorporateStatus corporateStatusFixture1 = new CorporateStatus();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        entity.setCorporateStatus(corporateStatusFixture1);
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setGemiNumber("gemiNumberValue1");
        entity.setObjective("objectiveValue1");
        entity.setReceiveNewsletter(true);
        entity.setIsChamberCompany(true);
        entity.setIsTradesCompany(true);
        entity.setShowBusinessGuide(true);
        BusinessLocation businessLocationFixture1 = new BusinessLocation();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        entity.setBusinessLocation(businessLocationFixture1);
        entity.setHasActiveProfiles(true);
        entity.setIsProteasData(true);
        entity.setResponsibleName("responsibleNameValue1");
        Country addressCountryFixture1 = new Country();
        addressCountryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressCountryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressCountryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressCountryFixture1.setRecdeleted(true);
        addressCountryFixture1.setChamberId(10);
        addressCountryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressCountryFixture1.setChamberCountryId(10);
        entity.setAddressCountry(addressCountryFixture1);
        entity.setAddressZoomLevel(new BigInteger("1000"));
        entity.setContactPhone3("contactPhone3Value1");
        entity.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setMeCriteria1Id(new BigInteger("1000"));
        entity.setMeCriteria2Id(new BigInteger("1000"));
        entity.setMeCriteria3Id(new BigInteger("1000"));
        entity.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setJbDescription("jbDescriptionValue1");
        entity.setJbNumberEmployees(100L);
        entity.setJbMotto("jbMottoValue1");
        entity.setJbEmail("jbEmailValue1");
        entity.setJbLinkedInUrl("jbLinkedInUrlValue1");
        entity.setJbFacebookUrl("jbFacebookUrlValue1");
        entity.setJbRegistrationStatus("jbRegistrationStatusValue1");
        entity.setJbLogoId("jbLogoIdValue1");
        entity.setJbCoverId("jbCoverIdValue1");
        entity.setJbLocationId(10);
        entity.setJbIndustryId(10);
        entity.setJbIsvalid(true);
        entity.setJbActivationStatus("jbActivationStatusValue1");

        return entity;
    }

    /**
     * Creates a populated Company fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Company createAnotherCompanyEntity() {
        Company entity = new Company();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setAfm("afmValue2");
        entity.setAm(new BigInteger("2000"));
        entity.setGemiId(new BigInteger("2000"));
        entity.setCoName("coNameValue2");
        entity.setChamberCompanyId(new BigInteger("2000"));
        entity.setChamberId(20);
        entity.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setMember(new BigInteger("2000"));
        entity.setRecType("recTypeValue2");
        entity.setRecdeleted(false);
        entity.setAddressCity("addressCityValue2");
        entity.setAddressLatitude("addressLatitudeValue2");
        entity.setAddressLongitude("addressLongitudeValue2");
        Municipality addressMunicipalityPriFixture2 = new Municipality();
        addressMunicipalityPriFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressMunicipalityPriFixture2.setChamberId(200L);
        addressMunicipalityPriFixture2.setChamberMunicipalityId(200L);
        addressMunicipalityPriFixture2.setDescription("descriptionValue2");
        addressMunicipalityPriFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressMunicipalityPriFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressMunicipalityPriFixture2.setRecdeleted(false);
        addressMunicipalityPriFixture2.setCd("cdValue2");
        addressMunicipalityPriFixture2.setIsProteasData(false);
        entity.setAddressMunicipalityPri(addressMunicipalityPriFixture2);
        entity.setAddressRegion("addressRegionValue2");
        entity.setAddressStreet("addressStreetValue2");
        entity.setAddressStreetNumber("addressStreetNumberValue2");
        entity.setAddressZipCode("addressZipCodeValue2");
        entity.setBranchTypeId(new BigInteger("2000"));
        ChamberDepartment chamberDepartmentFixture2 = new ChamberDepartment();
        chamberDepartmentFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberDepartmentFixture2.setChamberDepartmentId(20);
        chamberDepartmentFixture2.setChamberId(20);
        chamberDepartmentFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberDepartmentFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberDepartmentFixture2.setRecdeleted(false);
        chamberDepartmentFixture2.setCd("cdValue2");
        entity.setChamberDepartment(chamberDepartmentFixture2);
        entity.setChamberGemiResponsibleId(new BigInteger("2000"));
        entity.setCoNameNrm("coNameNrmValue2");
        CompanyStatus companyStatusFixture2 = new CompanyStatus();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        entity.setCompanyStatus(companyStatusFixture2);
        entity.setContactEmail("contactEmailValue2");
        entity.setContactMobile("contactMobileValue2");
        entity.setContactPhone1("contactPhone1Value2");
        entity.setContactPhone2("contactPhone2Value2");
        entity.setContactUrl("contactUrlValue2");
        CorporateStatus corporateStatusFixture2 = new CorporateStatus();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        entity.setCorporateStatus(corporateStatusFixture2);
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setGemiNumber("gemiNumberValue2");
        entity.setObjective("objectiveValue2");
        entity.setReceiveNewsletter(false);
        entity.setIsChamberCompany(false);
        entity.setIsTradesCompany(false);
        entity.setShowBusinessGuide(false);
        BusinessLocation businessLocationFixture2 = new BusinessLocation();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        entity.setBusinessLocation(businessLocationFixture2);
        entity.setHasActiveProfiles(false);
        entity.setIsProteasData(false);
        entity.setResponsibleName("responsibleNameValue2");
        Country addressCountryFixture2 = new Country();
        addressCountryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressCountryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressCountryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressCountryFixture2.setRecdeleted(false);
        addressCountryFixture2.setChamberId(20);
        addressCountryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressCountryFixture2.setChamberCountryId(20);
        entity.setAddressCountry(addressCountryFixture2);
        entity.setAddressZoomLevel(new BigInteger("2000"));
        entity.setContactPhone3("contactPhone3Value2");
        entity.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setMeCriteria1Id(new BigInteger("2000"));
        entity.setMeCriteria2Id(new BigInteger("2000"));
        entity.setMeCriteria3Id(new BigInteger("2000"));
        entity.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setJbDescription("jbDescriptionValue2");
        entity.setJbNumberEmployees(200L);
        entity.setJbMotto("jbMottoValue2");
        entity.setJbEmail("jbEmailValue2");
        entity.setJbLinkedInUrl("jbLinkedInUrlValue2");
        entity.setJbFacebookUrl("jbFacebookUrlValue2");
        entity.setJbRegistrationStatus("jbRegistrationStatusValue2");
        entity.setJbLogoId("jbLogoIdValue2");
        entity.setJbCoverId("jbCoverIdValue2");
        entity.setJbLocationId(20);
        entity.setJbIndustryId(20);
        entity.setJbIsvalid(false);
        entity.setJbActivationStatus("jbActivationStatusValue2");

        return entity;
    }

    /**
     * Creates a populated CompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createSampleCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setAfm("afmValue1");
        dto.setAm(new BigInteger("1000"));
        dto.setGemiId(new BigInteger("1000"));
        dto.setCoName("coNameValue1");
        dto.setChamberCompanyId(new BigInteger("1000"));
        dto.setChamberId(10);
        dto.setCancelDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateInterruption(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setMember(new BigInteger("1000"));
        dto.setRecType("recTypeValue1");
        dto.setRecdeleted(true);
        dto.setAddressCity("addressCityValue1");
        dto.setAddressLatitude("addressLatitudeValue1");
        dto.setAddressLongitude("addressLongitudeValue1");
        MunicipalityDto addressMunicipalityPriFixture1 = new MunicipalityDto();
        addressMunicipalityPriFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressMunicipalityPriFixture1.setChamberId(100L);
        addressMunicipalityPriFixture1.setChamberMunicipalityId(100L);
        addressMunicipalityPriFixture1.setDescription("descriptionValue1");
        addressMunicipalityPriFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressMunicipalityPriFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressMunicipalityPriFixture1.setRecdeleted(true);
        addressMunicipalityPriFixture1.setCd("cdValue1");
        addressMunicipalityPriFixture1.setIsProteasData(true);
        dto.setAddressMunicipalityPri(addressMunicipalityPriFixture1);
        dto.setAddressRegion("addressRegionValue1");
        dto.setAddressStreet("addressStreetValue1");
        dto.setAddressStreetNumber("addressStreetNumberValue1");
        dto.setAddressZipCode("addressZipCodeValue1");
        dto.setBranchTypeId(new BigInteger("1000"));
        ChamberDepartmentDto chamberDepartmentFixture1 = new ChamberDepartmentDto();
        chamberDepartmentFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        chamberDepartmentFixture1.setChamberDepartmentId(10);
        chamberDepartmentFixture1.setChamberId(10);
        chamberDepartmentFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberDepartmentFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        chamberDepartmentFixture1.setRecdeleted(true);
        chamberDepartmentFixture1.setCd("cdValue1");
        dto.setChamberDepartment(chamberDepartmentFixture1);
        dto.setChamberGemiResponsibleId(new BigInteger("1000"));
        dto.setCoNameNrm("coNameNrmValue1");
        CompanyStatusDto companyStatusFixture1 = new CompanyStatusDto();
        companyStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        companyStatusFixture1.setChamberId(10);
        companyStatusFixture1.setChamberCompanyStatusId(10);
        companyStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        companyStatusFixture1.setRecdeleted(true);
        dto.setCompanyStatus(companyStatusFixture1);
        dto.setContactEmail("contactEmailValue1");
        dto.setContactMobile("contactMobileValue1");
        dto.setContactPhone1("contactPhone1Value1");
        dto.setContactPhone2("contactPhone2Value1");
        dto.setContactUrl("contactUrlValue1");
        CorporateStatusDto corporateStatusFixture1 = new CorporateStatusDto();
        corporateStatusFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        corporateStatusFixture1.setChamberCorporateStatusId(10);
        corporateStatusFixture1.setChamberId(10);
        corporateStatusFixture1.setCd("cdValue1");
        corporateStatusFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        corporateStatusFixture1.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture1);
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateIncorporated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setDateRegistered(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setGemiNumber("gemiNumberValue1");
        dto.setObjective("objectiveValue1");
        dto.setReceiveNewsletter(true);
        dto.setIsChamberCompany(true);
        dto.setIsTradesCompany(true);
        dto.setShowBusinessGuide(true);
        BusinessLocationDto businessLocationFixture1 = new BusinessLocationDto();
        businessLocationFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        businessLocationFixture1.setCode("codeValue1");
        businessLocationFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        businessLocationFixture1.setRecdeleted(true);
        businessLocationFixture1.setBlobUri("blobUriValue1");
        dto.setBusinessLocation(businessLocationFixture1);
        dto.setHasActiveProfiles(true);
        dto.setIsProteasData(true);
        dto.setResponsibleName("responsibleNameValue1");
        CountryDto addressCountryFixture1 = new CountryDto();
        addressCountryFixture1.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressCountryFixture1.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressCountryFixture1.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        addressCountryFixture1.setRecdeleted(true);
        addressCountryFixture1.setChamberId(10);
        addressCountryFixture1.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        addressCountryFixture1.setChamberCountryId(10);
        dto.setAddressCountry(addressCountryFixture1);
        dto.setAddressZoomLevel(new BigInteger("1000"));
        dto.setContactPhone3("contactPhone3Value1");
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setFoundationDate(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setMeCriteria1Id(new BigInteger("1000"));
        dto.setMeCriteria2Id(new BigInteger("1000"));
        dto.setMeCriteria3Id(new BigInteger("1000"));
        dto.setMemberDues(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setJbDescription("jbDescriptionValue1");
        dto.setJbNumberEmployees(100L);
        dto.setJbMotto("jbMottoValue1");
        dto.setJbEmail("jbEmailValue1");
        dto.setJbLinkedInUrl("jbLinkedInUrlValue1");
        dto.setJbFacebookUrl("jbFacebookUrlValue1");
        dto.setJbRegistrationStatus("jbRegistrationStatusValue1");
        dto.setJbLogoId("jbLogoIdValue1");
        dto.setJbCoverId("jbCoverIdValue1");
        dto.setJbLocationId(10);
        dto.setJbIndustryId(10);
        dto.setJbIsvalid(true);
        dto.setJbActivationStatus("jbActivationStatusValue1");

        return dto;
    }

    /**
     * Creates a populated CompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createAnotherCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setAfm("afmValue2");
        dto.setAm(new BigInteger("2000"));
        dto.setGemiId(new BigInteger("2000"));
        dto.setCoName("coNameValue2");
        dto.setChamberCompanyId(new BigInteger("2000"));
        dto.setChamberId(20);
        dto.setCancelDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateInterruption(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setMember(new BigInteger("2000"));
        dto.setRecType("recTypeValue2");
        dto.setRecdeleted(false);
        dto.setAddressCity("addressCityValue2");
        dto.setAddressLatitude("addressLatitudeValue2");
        dto.setAddressLongitude("addressLongitudeValue2");
        MunicipalityDto addressMunicipalityPriFixture2 = new MunicipalityDto();
        addressMunicipalityPriFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressMunicipalityPriFixture2.setChamberId(200L);
        addressMunicipalityPriFixture2.setChamberMunicipalityId(200L);
        addressMunicipalityPriFixture2.setDescription("descriptionValue2");
        addressMunicipalityPriFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressMunicipalityPriFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressMunicipalityPriFixture2.setRecdeleted(false);
        addressMunicipalityPriFixture2.setCd("cdValue2");
        addressMunicipalityPriFixture2.setIsProteasData(false);
        dto.setAddressMunicipalityPri(addressMunicipalityPriFixture2);
        dto.setAddressRegion("addressRegionValue2");
        dto.setAddressStreet("addressStreetValue2");
        dto.setAddressStreetNumber("addressStreetNumberValue2");
        dto.setAddressZipCode("addressZipCodeValue2");
        dto.setBranchTypeId(new BigInteger("2000"));
        ChamberDepartmentDto chamberDepartmentFixture2 = new ChamberDepartmentDto();
        chamberDepartmentFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        chamberDepartmentFixture2.setChamberDepartmentId(20);
        chamberDepartmentFixture2.setChamberId(20);
        chamberDepartmentFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberDepartmentFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        chamberDepartmentFixture2.setRecdeleted(false);
        chamberDepartmentFixture2.setCd("cdValue2");
        dto.setChamberDepartment(chamberDepartmentFixture2);
        dto.setChamberGemiResponsibleId(new BigInteger("2000"));
        dto.setCoNameNrm("coNameNrmValue2");
        CompanyStatusDto companyStatusFixture2 = new CompanyStatusDto();
        companyStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        companyStatusFixture2.setChamberId(20);
        companyStatusFixture2.setChamberCompanyStatusId(20);
        companyStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        companyStatusFixture2.setRecdeleted(false);
        dto.setCompanyStatus(companyStatusFixture2);
        dto.setContactEmail("contactEmailValue2");
        dto.setContactMobile("contactMobileValue2");
        dto.setContactPhone1("contactPhone1Value2");
        dto.setContactPhone2("contactPhone2Value2");
        dto.setContactUrl("contactUrlValue2");
        CorporateStatusDto corporateStatusFixture2 = new CorporateStatusDto();
        corporateStatusFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        corporateStatusFixture2.setChamberCorporateStatusId(20);
        corporateStatusFixture2.setChamberId(20);
        corporateStatusFixture2.setCd("cdValue2");
        corporateStatusFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        corporateStatusFixture2.setRecdeleted(false);
        dto.setCorporateStatus(corporateStatusFixture2);
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateIncorporated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setDateRegistered(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setGemiNumber("gemiNumberValue2");
        dto.setObjective("objectiveValue2");
        dto.setReceiveNewsletter(false);
        dto.setIsChamberCompany(false);
        dto.setIsTradesCompany(false);
        dto.setShowBusinessGuide(false);
        BusinessLocationDto businessLocationFixture2 = new BusinessLocationDto();
        businessLocationFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        businessLocationFixture2.setCode("codeValue2");
        businessLocationFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        businessLocationFixture2.setRecdeleted(false);
        businessLocationFixture2.setBlobUri("blobUriValue2");
        dto.setBusinessLocation(businessLocationFixture2);
        dto.setHasActiveProfiles(false);
        dto.setIsProteasData(false);
        dto.setResponsibleName("responsibleNameValue2");
        CountryDto addressCountryFixture2 = new CountryDto();
        addressCountryFixture2.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressCountryFixture2.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressCountryFixture2.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        addressCountryFixture2.setRecdeleted(false);
        addressCountryFixture2.setChamberId(20);
        addressCountryFixture2.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        addressCountryFixture2.setChamberCountryId(20);
        dto.setAddressCountry(addressCountryFixture2);
        dto.setAddressZoomLevel(new BigInteger("2000"));
        dto.setContactPhone3("contactPhone3Value2");
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setFoundationDate(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setMeCriteria1Id(new BigInteger("2000"));
        dto.setMeCriteria2Id(new BigInteger("2000"));
        dto.setMeCriteria3Id(new BigInteger("2000"));
        dto.setMemberDues(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setJbDescription("jbDescriptionValue2");
        dto.setJbNumberEmployees(200L);
        dto.setJbMotto("jbMottoValue2");
        dto.setJbEmail("jbEmailValue2");
        dto.setJbLinkedInUrl("jbLinkedInUrlValue2");
        dto.setJbFacebookUrl("jbFacebookUrlValue2");
        dto.setJbRegistrationStatus("jbRegistrationStatusValue2");
        dto.setJbLogoId("jbLogoIdValue2");
        dto.setJbCoverId("jbCoverIdValue2");
        dto.setJbLocationId(20);
        dto.setJbIndustryId(20);
        dto.setJbIsvalid(false);
        dto.setJbActivationStatus("jbActivationStatusValue2");

        return dto;
    }

    /**
     * Creates a populated CompanyDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createPatchCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setAfm("afmValue3");
        dto.setAm(new BigInteger("3000"));
        dto.setGemiId(new BigInteger("3000"));
        dto.setChamberId(30);
        dto.setDateInterruption(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setMember(new BigInteger("3000"));
        dto.setRecdeleted(true);
        dto.setAddressLatitude("addressLatitudeValue3");
        MunicipalityDto addressMunicipalityPriFixture3 = new MunicipalityDto();
        addressMunicipalityPriFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        addressMunicipalityPriFixture3.setChamberId(300L);
        addressMunicipalityPriFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        addressMunicipalityPriFixture3.setRecdeleted(true);
        addressMunicipalityPriFixture3.setCd("cdValue3");
        addressMunicipalityPriFixture3.setIsProteasData(true);
        dto.setAddressMunicipalityPri(addressMunicipalityPriFixture3);
        dto.setAddressRegion("addressRegionValue3");
        dto.setAddressStreet("addressStreetValue3");
        dto.setAddressStreetNumber("addressStreetNumberValue3");
        dto.setAddressZipCode("addressZipCodeValue3");
        ChamberDepartmentDto chamberDepartmentFixture3 = new ChamberDepartmentDto();
        chamberDepartmentFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        chamberDepartmentFixture3.setChamberDepartmentId(30);
        chamberDepartmentFixture3.setChamberId(30);
        chamberDepartmentFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        chamberDepartmentFixture3.setRecdeleted(true);
        chamberDepartmentFixture3.setCd("cdValue3");
        dto.setChamberDepartment(chamberDepartmentFixture3);
        dto.setChamberGemiResponsibleId(new BigInteger("3000"));
        dto.setContactEmail("contactEmailValue3");
        dto.setContactPhone2("contactPhone2Value3");
        dto.setContactUrl("contactUrlValue3");
        CorporateStatusDto corporateStatusFixture3 = new CorporateStatusDto();
        corporateStatusFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        corporateStatusFixture3.setChamberCorporateStatusId(30);
        corporateStatusFixture3.setChamberId(30);
        corporateStatusFixture3.setCd("cdValue3");
        corporateStatusFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        corporateStatusFixture3.setRecdeleted(true);
        dto.setCorporateStatus(corporateStatusFixture3);
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setDateRegistered(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setReceiveNewsletter(true);
        dto.setIsChamberCompany(true);
        dto.setIsTradesCompany(true);
        BusinessLocationDto businessLocationFixture3 = new BusinessLocationDto();
        businessLocationFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        businessLocationFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        businessLocationFixture3.setRecdeleted(true);
        businessLocationFixture3.setBlobUri("blobUriValue3");
        dto.setBusinessLocation(businessLocationFixture3);
        dto.setHasActiveProfiles(true);
        dto.setIsProteasData(true);
        CountryDto addressCountryFixture3 = new CountryDto();
        addressCountryFixture3.setId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        addressCountryFixture3.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        addressCountryFixture3.setRecdeleted(true);
        addressCountryFixture3.setChamberId(30);
        addressCountryFixture3.setRegionId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setAddressCountry(addressCountryFixture3);
        dto.setAddressZoomLevel(new BigInteger("3000"));
        dto.setContactPhone3("contactPhone3Value3");
        dto.setDateProfessionStarted(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setMeCriteria1Id(new BigInteger("3000"));
        dto.setMeCriteria2Id(new BigInteger("3000"));
        dto.setMemberDues(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setJbUuid(UUID.fromString("00000000-0000-0000-0000-000000000003"));
        dto.setJbDescription("jbDescriptionValue3");
        dto.setJbNumberEmployees(300L);
        dto.setJbMotto("jbMottoValue3");
        dto.setJbLinkedInUrl("jbLinkedInUrlValue3");
        dto.setJbRegistrationStatus("jbRegistrationStatusValue3");
        dto.setJbCoverId("jbCoverIdValue3");
        dto.setJbIndustryId(30);
        dto.setJbIsvalid(true);

        return dto;
    }

}
