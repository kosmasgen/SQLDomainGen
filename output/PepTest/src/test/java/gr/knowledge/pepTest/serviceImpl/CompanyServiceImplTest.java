package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Company;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.repository.CompanyRepository;
import gr.knowledge.pepTest.mapper.CompanyMapper;
import java.util.UUID;
import java.math.BigInteger;
import java.time.LocalDateTime;

import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Company.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Company", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompaniesIsCalled() {
        List<Company> entityList = List.of(createSampleCompanyEntity(), createAnotherCompanyEntity());
        List<CompanyDto> dtoList = List.of(createSampleCompanyDto(), createAnotherCompanyDto());

        given(companyRepository.findAll()).willReturn(entityList);
        given(companyMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyDto> result = companyService.getAllCompanies();

        assertSame(dtoList, result);
        verify(companyRepository).findAll();
        verify(companyMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Company company = createSampleCompanyEntity();
        CompanyDto companyDto = createSampleCompanyDto();

        given(companyRepository.findById(id)).willReturn(Optional.of(company));
        given(companyMapper.toDTO(company)).willReturn(companyDto);

        CompanyDto result = companyService.getCompanyById(id);

        assertSame(companyDto, result);
        verify(companyRepository).findById(id);
        verify(companyMapper).toDTO(company);
    }

    @Test
    void shouldThrowWhenGetCompanyByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyService.getCompanyById(id));

        verify(companyRepository).findById(id);
        verify(companyMapper, never()).toDTO(any(Company.class));
    }

    @Test
    void shouldCreateCompanyWhenCreateCompanyIsCalled() {
        CompanyDto requestDto = createSampleCompanyDto();
        Company mappedEntity = createSampleCompanyEntity();
        Company savedEntity = createAnotherCompanyEntity();
        CompanyDto responseDto = createAnotherCompanyDto();

        given(companyMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyDto result = companyService.createCompany(requestDto);

        assertSame(responseDto, result);
        verify(companyMapper).toEntity(requestDto);
        verify(companyRepository).save(mappedEntity);
        verify(companyMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyDto requestDto = createPatchCompanyDto();
        Company existingEntity = createSampleCompanyEntity();
        Company savedEntity = createAnotherCompanyEntity();
        CompanyDto responseDto = createAnotherCompanyDto();

        given(companyRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyDto result = companyService.updateCompany(id, requestDto);

        assertSame(responseDto, result);
        verify(companyRepository).findById(id);
        verify(companyMapper).partialUpdate(existingEntity, requestDto);
        verify(companyRepository).save(existingEntity);
        verify(companyMapper).toDTO(savedEntity);
        verify(companyMapper, never()).toEntity(any(CompanyDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyDto requestDto = createPatchCompanyDto();

        given(companyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyService.updateCompany(id, requestDto));

        verify(companyRepository).findById(id);
        verify(companyMapper, never()).partialUpdate(any(), any());
        verify(companyRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Company existingEntity = createSampleCompanyEntity();

        given(companyRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyService.deleteCompany(id);

        verify(companyRepository).findById(id);
        verify(companyRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyService.deleteCompany(id));

        verify(companyRepository).findById(id);
        verify(companyRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Company fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Company createSampleCompanyEntity() {
        Company entity = new Company();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setAfm("afm-value-1");
        entity.setAm(new BigInteger("1"));
        entity.setGemiId(new BigInteger("1"));
        entity.setCoName("coName-value-1");
        entity.setChamberCompanyId(new BigInteger("1"));
        entity.setChamberId(1);
        entity.setCancelDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateInterruption(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setMember(new BigInteger("1"));
        entity.setRecType("recType-value-1");
        entity.setRecdeleted(true);
        entity.setAddressCity("addressCity-value-1");
        entity.setAddressLatitude("addressLatitude-value-1");
        entity.setAddressLongitude("addressLongitude-value-1");
        entity.setAddressRegion("addressRegion-value-1");
        entity.setAddressStreet("addressStreet-value-1");
        entity.setAddressStreetNumber("addressStreetNumber-value-1");
        entity.setAddressZipCode("addressZipCode-value-1");
        entity.setBranchTypeId(new BigInteger("1"));
        entity.setChamberGemiResponsibleId(new BigInteger("1"));
        entity.setCoNameNrm("coNameNrm-value-1");
        entity.setContactEmail("contactEmail-value-1");
        entity.setContactMobile("contactMobile-value-1");
        entity.setContactPhone1("contactPhone1-value-1");
        entity.setContactPhone2("contactPhone2-value-1");
        entity.setContactUrl("contactUrl-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setDateRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiNumber("gemiNumber-value-1");
        entity.setObjective("objective-value-1");
        entity.setReceiveNewsletter(true);
        entity.setIsChamberCompany(true);
        entity.setIsTradesCompany(true);
        entity.setShowBusinessGuide(true);
        entity.setHasActiveProfiles(true);
        entity.setIsProteasData(true);
        entity.setResponsibleName("responsibleName-value-1");
        entity.setAddressZoomLevel(new BigInteger("1"));
        entity.setContactPhone3("contactPhone3-value-1");
        entity.setDateProfessionStarted(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setFoundationDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setMeCriteria1Id(new BigInteger("1"));
        entity.setMeCriteria2Id(new BigInteger("1"));
        entity.setMeCriteria3Id(new BigInteger("1"));
        entity.setMemberDues(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setJbUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setJbDescription("jbDescription-value-1");
        entity.setJbNumberEmployees(1L);
        entity.setJbMotto("jbMotto-value-1");
        entity.setJbEmail("jbEmail-value-1");
        entity.setJbLinkedInUrl("jbLinkedInUrl-value-1");
        entity.setJbFacebookUrl("jbFacebookUrl-value-1");
        entity.setJbRegistrationStatus("jbRegistrationStatus-value-1");
        entity.setJbLogoId("jbLogoId-value-1");
        entity.setJbCoverId("jbCoverId-value-1");
        entity.setJbLocationId(1);
        entity.setJbIndustryId(1);
        entity.setJbIsvalid(true);
        entity.setJbActivationStatus("jbActivationStatus-value-1");

        return entity;
    }

    /**
     * Creates a populated Company fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Company createAnotherCompanyEntity() {
        Company entity = new Company();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setAfm("afm-value-2");
        entity.setAm(new BigInteger("2"));
        entity.setGemiId(new BigInteger("2"));
        entity.setCoName("coName-value-2");
        entity.setChamberCompanyId(new BigInteger("2"));
        entity.setChamberId(2);
        entity.setCancelDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateInterruption(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setMember(new BigInteger("2"));
        entity.setRecType("recType-value-2");
        entity.setRecdeleted(false);
        entity.setAddressCity("addressCity-value-2");
        entity.setAddressLatitude("addressLatitude-value-2");
        entity.setAddressLongitude("addressLongitude-value-2");
        entity.setAddressRegion("addressRegion-value-2");
        entity.setAddressStreet("addressStreet-value-2");
        entity.setAddressStreetNumber("addressStreetNumber-value-2");
        entity.setAddressZipCode("addressZipCode-value-2");
        entity.setBranchTypeId(new BigInteger("2"));
        entity.setChamberGemiResponsibleId(new BigInteger("2"));
        entity.setCoNameNrm("coNameNrm-value-2");
        entity.setContactEmail("contactEmail-value-2");
        entity.setContactMobile("contactMobile-value-2");
        entity.setContactPhone1("contactPhone1-value-2");
        entity.setContactPhone2("contactPhone2-value-2");
        entity.setContactUrl("contactUrl-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setDateRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiNumber("gemiNumber-value-2");
        entity.setObjective("objective-value-2");
        entity.setReceiveNewsletter(false);
        entity.setIsChamberCompany(false);
        entity.setIsTradesCompany(false);
        entity.setShowBusinessGuide(false);
        entity.setHasActiveProfiles(false);
        entity.setIsProteasData(false);
        entity.setResponsibleName("responsibleName-value-2");
        entity.setAddressZoomLevel(new BigInteger("2"));
        entity.setContactPhone3("contactPhone3-value-2");
        entity.setDateProfessionStarted(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setFoundationDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setMeCriteria1Id(new BigInteger("2"));
        entity.setMeCriteria2Id(new BigInteger("2"));
        entity.setMeCriteria3Id(new BigInteger("2"));
        entity.setMemberDues(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setJbUuid(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setJbDescription("jbDescription-value-2");
        entity.setJbNumberEmployees(2L);
        entity.setJbMotto("jbMotto-value-2");
        entity.setJbEmail("jbEmail-value-2");
        entity.setJbLinkedInUrl("jbLinkedInUrl-value-2");
        entity.setJbFacebookUrl("jbFacebookUrl-value-2");
        entity.setJbRegistrationStatus("jbRegistrationStatus-value-2");
        entity.setJbLogoId("jbLogoId-value-2");
        entity.setJbCoverId("jbCoverId-value-2");
        entity.setJbLocationId(2);
        entity.setJbIndustryId(2);
        entity.setJbIsvalid(false);
        entity.setJbActivationStatus("jbActivationStatus-value-2");

        return entity;
    }

    /**
     * Creates a populated CompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createSampleCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setAfm("afm-value-1");
        dto.setAm(new BigInteger("1"));
        dto.setGemiId(new BigInteger("1"));
        dto.setCoName("coName-value-1");
        dto.setChamberCompanyId(new BigInteger("1"));
        dto.setChamberId(1);
        dto.setCancelDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setMember(new BigInteger("1"));
        dto.setRecType("recType-value-1");
        dto.setRecdeleted(true);
        dto.setAddressCity("addressCity-value-1");
        dto.setAddressLatitude("addressLatitude-value-1");
        dto.setAddressLongitude("addressLongitude-value-1");
        dto.setAddressRegion("addressRegion-value-1");
        dto.setAddressStreet("addressStreet-value-1");
        dto.setAddressStreetNumber("addressStreetNumber-value-1");
        dto.setAddressZipCode("addressZipCode-value-1");
        dto.setBranchTypeId(new BigInteger("1"));
        dto.setChamberGemiResponsibleId(new BigInteger("1"));
        dto.setCoNameNrm("coNameNrm-value-1");
        dto.setContactEmail("contactEmail-value-1");
        dto.setContactMobile("contactMobile-value-1");
        dto.setContactPhone1("contactPhone1-value-1");
        dto.setContactPhone2("contactPhone2-value-1");
        dto.setContactUrl("contactUrl-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiNumber("gemiNumber-value-1");
        dto.setObjective("objective-value-1");
        dto.setReceiveNewsletter(true);
        dto.setIsChamberCompany(true);
        dto.setIsTradesCompany(true);
        dto.setShowBusinessGuide(true);
        dto.setHasActiveProfiles(true);
        dto.setIsProteasData(true);
        dto.setResponsibleName("responsibleName-value-1");
        dto.setAddressZoomLevel(new BigInteger("1"));
        dto.setContactPhone3("contactPhone3-value-1");
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setMeCriteria1Id(new BigInteger("1"));
        dto.setMeCriteria2Id(new BigInteger("1"));
        dto.setMeCriteria3Id(new BigInteger("1"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setJbUuid(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setJbDescription("jbDescription-value-1");
        dto.setJbNumberEmployees(1L);
        dto.setJbMotto("jbMotto-value-1");
        dto.setJbEmail("jbEmail-value-1");
        dto.setJbLinkedInUrl("jbLinkedInUrl-value-1");
        dto.setJbFacebookUrl("jbFacebookUrl-value-1");
        dto.setJbRegistrationStatus("jbRegistrationStatus-value-1");
        dto.setJbLogoId("jbLogoId-value-1");
        dto.setJbCoverId("jbCoverId-value-1");
        dto.setJbLocationId(1);
        dto.setJbIndustryId(1);
        dto.setJbIsvalid(true);
        dto.setJbActivationStatus("jbActivationStatus-value-1");

        return dto;
    }

    /**
     * Creates a populated CompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createAnotherCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setAfm("afm-value-2");
        dto.setAm(new BigInteger("2"));
        dto.setGemiId(new BigInteger("2"));
        dto.setCoName("coName-value-2");
        dto.setChamberCompanyId(new BigInteger("2"));
        dto.setChamberId(2);
        dto.setCancelDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setMember(new BigInteger("2"));
        dto.setRecType("recType-value-2");
        dto.setRecdeleted(false);
        dto.setAddressCity("addressCity-value-2");
        dto.setAddressLatitude("addressLatitude-value-2");
        dto.setAddressLongitude("addressLongitude-value-2");
        dto.setAddressRegion("addressRegion-value-2");
        dto.setAddressStreet("addressStreet-value-2");
        dto.setAddressStreetNumber("addressStreetNumber-value-2");
        dto.setAddressZipCode("addressZipCode-value-2");
        dto.setBranchTypeId(new BigInteger("2"));
        dto.setChamberGemiResponsibleId(new BigInteger("2"));
        dto.setCoNameNrm("coNameNrm-value-2");
        dto.setContactEmail("contactEmail-value-2");
        dto.setContactMobile("contactMobile-value-2");
        dto.setContactPhone1("contactPhone1-value-2");
        dto.setContactPhone2("contactPhone2-value-2");
        dto.setContactUrl("contactUrl-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiNumber("gemiNumber-value-2");
        dto.setObjective("objective-value-2");
        dto.setReceiveNewsletter(false);
        dto.setIsChamberCompany(false);
        dto.setIsTradesCompany(false);
        dto.setShowBusinessGuide(false);
        dto.setHasActiveProfiles(false);
        dto.setIsProteasData(false);
        dto.setResponsibleName("responsibleName-value-2");
        dto.setAddressZoomLevel(new BigInteger("2"));
        dto.setContactPhone3("contactPhone3-value-2");
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setMeCriteria1Id(new BigInteger("2"));
        dto.setMeCriteria2Id(new BigInteger("2"));
        dto.setMeCriteria3Id(new BigInteger("2"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setJbUuid(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setJbDescription("jbDescription-value-2");
        dto.setJbNumberEmployees(2L);
        dto.setJbMotto("jbMotto-value-2");
        dto.setJbEmail("jbEmail-value-2");
        dto.setJbLinkedInUrl("jbLinkedInUrl-value-2");
        dto.setJbFacebookUrl("jbFacebookUrl-value-2");
        dto.setJbRegistrationStatus("jbRegistrationStatus-value-2");
        dto.setJbLogoId("jbLogoId-value-2");
        dto.setJbCoverId("jbCoverId-value-2");
        dto.setJbLocationId(2);
        dto.setJbIndustryId(2);
        dto.setJbIsvalid(false);
        dto.setJbActivationStatus("jbActivationStatus-value-2");

        return dto;
    }

    /**
     * Creates a populated CompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyDto createPatchCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setAfm("afm-value-3");
        dto.setAm(new BigInteger("3"));
        dto.setGemiId(new BigInteger("3"));
        dto.setCoName("coName-value-3");
        dto.setChamberCompanyId(new BigInteger("3"));
        dto.setChamberId(3);
        dto.setCancelDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateInterruption(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setMember(new BigInteger("3"));
        dto.setRecType("recType-value-3");
        dto.setRecdeleted(true);
        dto.setAddressCity("addressCity-value-3");
        dto.setAddressLatitude("addressLatitude-value-3");
        dto.setAddressLongitude("addressLongitude-value-3");
        dto.setAddressRegion("addressRegion-value-3");
        dto.setAddressStreet("addressStreet-value-3");
        dto.setAddressStreetNumber("addressStreetNumber-value-3");
        dto.setAddressZipCode("addressZipCode-value-3");
        dto.setBranchTypeId(new BigInteger("3"));
        dto.setChamberGemiResponsibleId(new BigInteger("3"));
        dto.setCoNameNrm("coNameNrm-value-3");
        dto.setContactEmail("contactEmail-value-3");
        dto.setContactMobile("contactMobile-value-3");
        dto.setContactPhone1("contactPhone1-value-3");
        dto.setContactPhone2("contactPhone2-value-3");
        dto.setContactUrl("contactUrl-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateIncorporated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setDateRegistered(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiNumber("gemiNumber-value-3");
        dto.setObjective("objective-value-3");
        dto.setReceiveNewsletter(true);
        dto.setIsChamberCompany(true);
        dto.setIsTradesCompany(true);
        dto.setShowBusinessGuide(true);
        dto.setHasActiveProfiles(true);
        dto.setIsProteasData(true);
        dto.setResponsibleName("responsibleName-value-3");
        dto.setAddressZoomLevel(new BigInteger("3"));
        dto.setContactPhone3("contactPhone3-value-3");
        dto.setDateProfessionStarted(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setFoundationDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setMeCriteria1Id(new BigInteger("3"));
        dto.setMeCriteria2Id(new BigInteger("3"));
        dto.setMeCriteria3Id(new BigInteger("3"));
        dto.setMemberDues(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setJbUuid(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));
        dto.setJbDescription("jbDescription-value-3");
        dto.setJbNumberEmployees(3L);
        dto.setJbMotto("jbMotto-value-3");
        dto.setJbEmail("jbEmail-value-3");
        dto.setJbLinkedInUrl("jbLinkedInUrl-value-3");
        dto.setJbFacebookUrl("jbFacebookUrl-value-3");
        dto.setJbRegistrationStatus("jbRegistrationStatus-value-3");
        dto.setJbLogoId("jbLogoId-value-3");
        dto.setJbCoverId("jbCoverId-value-3");
        dto.setJbLocationId(3);
        dto.setJbIndustryId(3);
        dto.setJbIsvalid(true);
        dto.setJbActivationStatus("jbActivationStatus-value-3");

        return dto;
    }

}
