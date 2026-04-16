package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Municipality;
import gr.knowledge.pepTest.dto.MunicipalityDto;
import gr.knowledge.pepTest.repository.MunicipalityRepository;
import gr.knowledge.pepTest.mapper.MunicipalityMapper;
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
class MunicipalityServiceImplTest {

    @Mock
    private MunicipalityRepository municipalityRepository;

    @Mock
    private MunicipalityMapper municipalityMapper;

    @InjectMocks
    private MunicipalityServiceImpl municipalityService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Municipality.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Municipality", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllMunicipalitiesIsCalled() {
        List<Municipality> entityList = List.of(createSampleMunicipalityEntity(), createAnotherMunicipalityEntity());
        List<MunicipalityDto> dtoList = List.of(createSampleMunicipalityDto(), createAnotherMunicipalityDto());

        given(municipalityRepository.findAll()).willReturn(entityList);
        given(municipalityMapper.toDTOList(entityList)).willReturn(dtoList);

        List<MunicipalityDto> result = municipalityService.getAllMunicipalities();

        assertSame(dtoList, result);
        verify(municipalityRepository).findAll();
        verify(municipalityMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetMunicipalityByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Municipality municipality = createSampleMunicipalityEntity();
        MunicipalityDto municipalityDto = createSampleMunicipalityDto();

        given(municipalityRepository.findById(id)).willReturn(Optional.of(municipality));
        given(municipalityMapper.toDTO(municipality)).willReturn(municipalityDto);

        MunicipalityDto result = municipalityService.getMunicipalityById(id);

        assertSame(municipalityDto, result);
        verify(municipalityRepository).findById(id);
        verify(municipalityMapper).toDTO(municipality);
    }

    @Test
    void shouldThrowWhenGetMunicipalityByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(municipalityRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityService.getMunicipalityById(id));

        verify(municipalityRepository).findById(id);
        verify(municipalityMapper, never()).toDTO(any(Municipality.class));
    }

    @Test
    void shouldCreateMunicipalityWhenCreateMunicipalityIsCalled() {
        MunicipalityDto requestDto = createSampleMunicipalityDto();
        Municipality mappedEntity = createSampleMunicipalityEntity();
        Municipality savedEntity = createAnotherMunicipalityEntity();
        MunicipalityDto responseDto = createAnotherMunicipalityDto();

        given(municipalityMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(municipalityRepository.save(mappedEntity)).willReturn(savedEntity);
        given(municipalityMapper.toDTO(savedEntity)).willReturn(responseDto);

        MunicipalityDto result = municipalityService.createMunicipality(requestDto);

        assertSame(responseDto, result);
        verify(municipalityMapper).toEntity(requestDto);
        verify(municipalityRepository).save(mappedEntity);
        verify(municipalityMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateMunicipalityWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityDto requestDto = createPatchMunicipalityDto();
        Municipality existingEntity = createSampleMunicipalityEntity();
        Municipality savedEntity = createAnotherMunicipalityEntity();
        MunicipalityDto responseDto = createAnotherMunicipalityDto();

        given(municipalityRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(municipalityRepository.save(existingEntity)).willReturn(savedEntity);
        given(municipalityMapper.toDTO(savedEntity)).willReturn(responseDto);

        MunicipalityDto result = municipalityService.updateMunicipality(id, requestDto);

        assertSame(responseDto, result);
        verify(municipalityRepository).findById(id);
        verify(municipalityMapper).partialUpdate(existingEntity, requestDto);
        verify(municipalityRepository).save(existingEntity);
        verify(municipalityMapper).toDTO(savedEntity);
        verify(municipalityMapper, never()).toEntity(any(MunicipalityDto.class));
    }

    @Test
    void shouldThrowWhenUpdateMunicipalityCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        MunicipalityDto requestDto = createPatchMunicipalityDto();

        given(municipalityRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityService.updateMunicipality(id, requestDto));

        verify(municipalityRepository).findById(id);
        verify(municipalityMapper, never()).partialUpdate(any(), any());
        verify(municipalityRepository, never()).save(any());
    }

    @Test
    void shouldDeleteMunicipalityWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Municipality existingEntity = createSampleMunicipalityEntity();

        given(municipalityRepository.findById(id)).willReturn(Optional.of(existingEntity));

        municipalityService.deleteMunicipality(id);

        verify(municipalityRepository).findById(id);
        verify(municipalityRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteMunicipalityCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(municipalityRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> municipalityService.deleteMunicipality(id));

        verify(municipalityRepository).findById(id);
        verify(municipalityRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Municipality fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Municipality createSampleMunicipalityEntity() {
        Municipality entity = new Municipality();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1L);
        entity.setChamberMunicipalityId(1L);
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setCd("cd-value-1");
        entity.setIsProteasData(true);

        return entity;
    }

    /**
     * Creates a populated Municipality fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Municipality createAnotherMunicipalityEntity() {
        Municipality entity = new Municipality();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2L);
        entity.setChamberMunicipalityId(2L);
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setCd("cd-value-2");
        entity.setIsProteasData(false);

        return entity;
    }

    /**
     * Creates a populated MunicipalityDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createSampleMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1L);
        dto.setChamberMunicipalityId(1L);
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCd("cd-value-1");
        dto.setIsProteasData(true);

        return dto;
    }

    /**
     * Creates a populated MunicipalityDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createAnotherMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2L);
        dto.setChamberMunicipalityId(2L);
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setCd("cd-value-2");
        dto.setIsProteasData(false);

        return dto;
    }

    /**
     * Creates a populated MunicipalityDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private MunicipalityDto createPatchMunicipalityDto() {
        MunicipalityDto dto = new MunicipalityDto();
        dto.setChamberId(3L);
        dto.setChamberMunicipalityId(3L);
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setCd("cd-value-3");
        dto.setIsProteasData(true);

        return dto;
    }

}
