package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.BusinessLocation;
import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.repository.BusinessLocationRepository;
import gr.knowledge.pepTest.mapper.BusinessLocationMapper;
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
class BusinessLocationServiceImplTest {

    @Mock
    private BusinessLocationRepository businessLocationRepository;

    @Mock
    private BusinessLocationMapper businessLocationMapper;

    @InjectMocks
    private BusinessLocationServiceImpl businessLocationService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for BusinessLocation.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("BusinessLocation", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllBusinessLocationsIsCalled() {
        List<BusinessLocation> entityList = List.of(createSampleBusinessLocationEntity(), createAnotherBusinessLocationEntity());
        List<BusinessLocationDto> dtoList = List.of(createSampleBusinessLocationDto(), createAnotherBusinessLocationDto());

        given(businessLocationRepository.findAll()).willReturn(entityList);
        given(businessLocationMapper.toDTOList(entityList)).willReturn(dtoList);

        List<BusinessLocationDto> result = businessLocationService.getAllBusinessLocations();

        assertSame(dtoList, result);
        verify(businessLocationRepository).findAll();
        verify(businessLocationMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetBusinessLocationByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocation businessLocation = createSampleBusinessLocationEntity();
        BusinessLocationDto businessLocationDto = createSampleBusinessLocationDto();

        given(businessLocationRepository.findById(id)).willReturn(Optional.of(businessLocation));
        given(businessLocationMapper.toDTO(businessLocation)).willReturn(businessLocationDto);

        BusinessLocationDto result = businessLocationService.getBusinessLocationById(id);

        assertSame(businessLocationDto, result);
        verify(businessLocationRepository).findById(id);
        verify(businessLocationMapper).toDTO(businessLocation);
    }

    @Test
    void shouldThrowWhenGetBusinessLocationByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(businessLocationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationService.getBusinessLocationById(id));

        verify(businessLocationRepository).findById(id);
        verify(businessLocationMapper, never()).toDTO(any(BusinessLocation.class));
    }

    @Test
    void shouldCreateBusinessLocationWhenCreateBusinessLocationIsCalled() {
        BusinessLocationDto requestDto = createSampleBusinessLocationDto();
        BusinessLocation mappedEntity = createSampleBusinessLocationEntity();
        BusinessLocation savedEntity = createAnotherBusinessLocationEntity();
        BusinessLocationDto responseDto = createAnotherBusinessLocationDto();

        given(businessLocationMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(businessLocationRepository.save(mappedEntity)).willReturn(savedEntity);
        given(businessLocationMapper.toDTO(savedEntity)).willReturn(responseDto);

        BusinessLocationDto result = businessLocationService.createBusinessLocation(requestDto);

        assertSame(responseDto, result);
        verify(businessLocationMapper).toEntity(requestDto);
        verify(businessLocationRepository).save(mappedEntity);
        verify(businessLocationMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateBusinessLocationWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationDto requestDto = createPatchBusinessLocationDto();
        BusinessLocation existingEntity = createSampleBusinessLocationEntity();
        BusinessLocation savedEntity = createAnotherBusinessLocationEntity();
        BusinessLocationDto responseDto = createAnotherBusinessLocationDto();

        given(businessLocationRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(businessLocationRepository.save(existingEntity)).willReturn(savedEntity);
        given(businessLocationMapper.toDTO(savedEntity)).willReturn(responseDto);

        BusinessLocationDto result = businessLocationService.updateBusinessLocation(id, requestDto);

        assertSame(responseDto, result);
        verify(businessLocationRepository).findById(id);
        verify(businessLocationMapper).partialUpdate(existingEntity, requestDto);
        verify(businessLocationRepository).save(existingEntity);
        verify(businessLocationMapper).toDTO(savedEntity);
        verify(businessLocationMapper, never()).toEntity(any(BusinessLocationDto.class));
    }

    @Test
    void shouldThrowWhenUpdateBusinessLocationCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocationDto requestDto = createPatchBusinessLocationDto();

        given(businessLocationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationService.updateBusinessLocation(id, requestDto));

        verify(businessLocationRepository).findById(id);
        verify(businessLocationMapper, never()).partialUpdate(any(), any());
        verify(businessLocationRepository, never()).save(any());
    }

    @Test
    void shouldDeleteBusinessLocationWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        BusinessLocation existingEntity = createSampleBusinessLocationEntity();

        given(businessLocationRepository.findById(id)).willReturn(Optional.of(existingEntity));

        businessLocationService.deleteBusinessLocation(id);

        verify(businessLocationRepository).findById(id);
        verify(businessLocationRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteBusinessLocationCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(businessLocationRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> businessLocationService.deleteBusinessLocation(id));

        verify(businessLocationRepository).findById(id);
        verify(businessLocationRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated BusinessLocation fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocation createSampleBusinessLocationEntity() {
        BusinessLocation entity = new BusinessLocation();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setCode("code-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setBlobUri("blobUri-value-1");

        return entity;
    }

    /**
     * Creates a populated BusinessLocation fixture for service tests.
     *
     * @return populated entity fixture
     */
    private BusinessLocation createAnotherBusinessLocationEntity() {
        BusinessLocation entity = new BusinessLocation();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setCode("code-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setBlobUri("blobUri-value-2");

        return entity;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createSampleBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setCode("code-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUri-value-1");

        return dto;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createAnotherBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setCode("code-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setBlobUri("blobUri-value-2");

        return dto;
    }

    /**
     * Creates a populated BusinessLocationDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private BusinessLocationDto createPatchBusinessLocationDto() {
        BusinessLocationDto dto = new BusinessLocationDto();
        dto.setCode("code-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setBlobUri("blobUri-value-3");

        return dto;
    }

}
