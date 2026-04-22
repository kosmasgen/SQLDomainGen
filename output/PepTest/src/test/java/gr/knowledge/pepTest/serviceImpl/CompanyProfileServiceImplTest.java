package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyProfile;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.repository.CompanyProfileRepository;
import gr.knowledge.pepTest.mapper.CompanyProfileMapper;
import java.util.UUID;
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
class CompanyProfileServiceImplTest {

    @Mock
    private CompanyProfileRepository companyProfileRepository;

    @Mock
    private CompanyProfileMapper companyProfileMapper;

    @InjectMocks
    private CompanyProfileServiceImpl companyProfileService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyProfile.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyProfile", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyProfilesIsCalled() {
        List<CompanyProfile> entityList = List.of(createSampleCompanyProfileEntity(), createAnotherCompanyProfileEntity());
        List<CompanyProfileDto> dtoList = List.of(createSampleCompanyProfileDto(), createAnotherCompanyProfileDto());

        given(companyProfileRepository.findAll()).willReturn(entityList);
        given(companyProfileMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyProfileDto> result = companyProfileService.getAllCompanyProfiles();

        assertSame(dtoList, result);
        verify(companyProfileRepository).findAll();
        verify(companyProfileMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyProfileByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfile companyProfile = createSampleCompanyProfileEntity();
        CompanyProfileDto companyProfileDto = createSampleCompanyProfileDto();

        given(companyProfileRepository.findById(id)).willReturn(Optional.of(companyProfile));
        given(companyProfileMapper.toDTO(companyProfile)).willReturn(companyProfileDto);

        CompanyProfileDto result = companyProfileService.getCompanyProfileById(id);

        assertSame(companyProfileDto, result);
        verify(companyProfileRepository).findById(id);
        verify(companyProfileMapper).toDTO(companyProfile);
    }

    @Test
    void shouldThrowWhenGetCompanyProfileByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyProfileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileService.getCompanyProfileById(id));

        verify(companyProfileRepository).findById(id);
        verify(companyProfileMapper, never()).toDTO(any(CompanyProfile.class));
    }

    @Test
    void shouldCreateCompanyProfileWhenCreateCompanyProfileIsCalled() {
        CompanyProfileDto requestDto = createSampleCompanyProfileDto();
        CompanyProfile mappedEntity = createSampleCompanyProfileEntity();
        CompanyProfile savedEntity = createAnotherCompanyProfileEntity();
        CompanyProfileDto responseDto = createAnotherCompanyProfileDto();

        given(companyProfileMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyProfileRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyProfileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfileDto result = companyProfileService.createCompanyProfile(requestDto);

        assertSame(responseDto, result);
        verify(companyProfileMapper).toEntity(requestDto);
        verify(companyProfileRepository).save(mappedEntity);
        verify(companyProfileMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyProfileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileDto requestDto = createPatchCompanyProfileDto();
        CompanyProfile existingEntity = createSampleCompanyProfileEntity();
        CompanyProfile savedEntity = createAnotherCompanyProfileEntity();
        CompanyProfileDto responseDto = createAnotherCompanyProfileDto();

        given(companyProfileRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyProfileRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyProfileMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfileDto result = companyProfileService.updateCompanyProfile(id, requestDto);

        assertSame(responseDto, result);
        verify(companyProfileRepository).findById(id);
        verify(companyProfileMapper).partialUpdate(existingEntity, requestDto);
        verify(companyProfileRepository).save(existingEntity);
        verify(companyProfileMapper).toDTO(savedEntity);
        verify(companyProfileMapper, never()).toEntity(any(CompanyProfileDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyProfileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfileDto requestDto = createPatchCompanyProfileDto();

        given(companyProfileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileService.updateCompanyProfile(id, requestDto));

        verify(companyProfileRepository).findById(id);
        verify(companyProfileMapper, never()).partialUpdate(any(), any());
        verify(companyProfileRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyProfileWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfile existingEntity = createSampleCompanyProfileEntity();

        given(companyProfileRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyProfileService.deleteCompanyProfile(id);

        verify(companyProfileRepository).findById(id);
        verify(companyProfileRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyProfileCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyProfileRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfileService.deleteCompanyProfile(id));

        verify(companyProfileRepository).findById(id);
        verify(companyProfileRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyProfile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfile createSampleCompanyProfileEntity() {
        CompanyProfile entity = new CompanyProfile();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setName("name-value-1");
        entity.setAddressCity("addressCity-value-1");
        entity.setAddressLatitude("addressLatitude-value-1");
        entity.setAddressLongitude("addressLongitude-value-1");
        entity.setAddressRegion("addressRegion-value-1");
        entity.setAddressStreet("addressStreet-value-1");
        entity.setAddressStreetNumber("addressStreetNumber-value-1");
        entity.setAddressZipCode("addressZipCode-value-1");
        entity.setContactEmail("contactEmail-value-1");
        entity.setContactMobile("contactMobile-value-1");
        entity.setContactPhone1("contactPhone1-value-1");
        entity.setContactPhone2("contactPhone2-value-1");
        entity.setContactPhone3("contactPhone3-value-1");
        entity.setContactUrl("contactUrl-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setShowBusinessGuide(true);

        return entity;
    }

    /**
     * Creates a populated CompanyProfile fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfile createAnotherCompanyProfileEntity() {
        CompanyProfile entity = new CompanyProfile();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setName("name-value-2");
        entity.setAddressCity("addressCity-value-2");
        entity.setAddressLatitude("addressLatitude-value-2");
        entity.setAddressLongitude("addressLongitude-value-2");
        entity.setAddressRegion("addressRegion-value-2");
        entity.setAddressStreet("addressStreet-value-2");
        entity.setAddressStreetNumber("addressStreetNumber-value-2");
        entity.setAddressZipCode("addressZipCode-value-2");
        entity.setContactEmail("contactEmail-value-2");
        entity.setContactMobile("contactMobile-value-2");
        entity.setContactPhone1("contactPhone1-value-2");
        entity.setContactPhone2("contactPhone2-value-2");
        entity.setContactPhone3("contactPhone3-value-2");
        entity.setContactUrl("contactUrl-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setShowBusinessGuide(false);

        return entity;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createSampleCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setName("name-value-1");
        dto.setAddressCity("addressCity-value-1");
        dto.setAddressLatitude("addressLatitude-value-1");
        dto.setAddressLongitude("addressLongitude-value-1");
        dto.setAddressRegion("addressRegion-value-1");
        dto.setAddressStreet("addressStreet-value-1");
        dto.setAddressStreetNumber("addressStreetNumber-value-1");
        dto.setAddressZipCode("addressZipCode-value-1");
        dto.setContactEmail("contactEmail-value-1");
        dto.setContactMobile("contactMobile-value-1");
        dto.setContactPhone1("contactPhone1-value-1");
        dto.setContactPhone2("contactPhone2-value-1");
        dto.setContactPhone3("contactPhone3-value-1");
        dto.setContactUrl("contactUrl-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setBusinessLocation(new BusinessLocationDto());
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);
        dto.setShowBusinessGuide(true);

        return dto;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createAnotherCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setName("name-value-2");
        dto.setAddressCity("addressCity-value-2");
        dto.setAddressLatitude("addressLatitude-value-2");
        dto.setAddressLongitude("addressLongitude-value-2");
        dto.setAddressRegion("addressRegion-value-2");
        dto.setAddressStreet("addressStreet-value-2");
        dto.setAddressStreetNumber("addressStreetNumber-value-2");
        dto.setAddressZipCode("addressZipCode-value-2");
        dto.setContactEmail("contactEmail-value-2");
        dto.setContactMobile("contactMobile-value-2");
        dto.setContactPhone1("contactPhone1-value-2");
        dto.setContactPhone2("contactPhone2-value-2");
        dto.setContactPhone3("contactPhone3-value-2");
        dto.setContactUrl("contactUrl-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setBusinessLocation(new BusinessLocationDto());
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(false);
        dto.setShowBusinessGuide(false);

        return dto;
    }

    /**
     * Creates a populated CompanyProfileDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfileDto createPatchCompanyProfileDto() {
        CompanyProfileDto dto = new CompanyProfileDto();
        dto.setName("name-value-3");
        dto.setAddressCity("addressCity-value-3");
        dto.setAddressLatitude("addressLatitude-value-3");
        dto.setAddressLongitude("addressLongitude-value-3");
        dto.setAddressRegion("addressRegion-value-3");
        dto.setAddressStreet("addressStreet-value-3");
        dto.setAddressStreetNumber("addressStreetNumber-value-3");
        dto.setAddressZipCode("addressZipCode-value-3");
        dto.setContactEmail("contactEmail-value-3");
        dto.setContactMobile("contactMobile-value-3");
        dto.setContactPhone1("contactPhone1-value-3");
        dto.setContactPhone2("contactPhone2-value-3");
        dto.setContactPhone3("contactPhone3-value-3");
        dto.setContactUrl("contactUrl-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setBusinessLocation(new BusinessLocationDto());
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);
        dto.setShowBusinessGuide(true);

        return dto;
    }

}
