package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ProfessionSystem;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.repository.ProfessionSystemRepository;
import gr.knowledge.pepTest.mapper.ProfessionSystemMapper;
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
class ProfessionSystemServiceImplTest {

    @Mock
    private ProfessionSystemRepository professionSystemRepository;

    @Mock
    private ProfessionSystemMapper professionSystemMapper;

    @InjectMocks
    private ProfessionSystemServiceImpl professionSystemService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ProfessionSystem.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ProfessionSystem", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessionSystemsIsCalled() {
        List<ProfessionSystem> entityList = List.of(createSampleProfessionSystemEntity(), createAnotherProfessionSystemEntity());
        List<ProfessionSystemDto> dtoList = List.of(createSampleProfessionSystemDto(), createAnotherProfessionSystemDto());

        given(professionSystemRepository.findAll()).willReturn(entityList);
        given(professionSystemMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProfessionSystemDto> result = professionSystemService.getAllProfessionSystems();

        assertSame(dtoList, result);
        verify(professionSystemRepository).findAll();
        verify(professionSystemMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessionSystemByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionSystem professionSystem = createSampleProfessionSystemEntity();
        ProfessionSystemDto professionSystemDto = createSampleProfessionSystemDto();

        given(professionSystemRepository.findById(id)).willReturn(Optional.of(professionSystem));
        given(professionSystemMapper.toDTO(professionSystem)).willReturn(professionSystemDto);

        ProfessionSystemDto result = professionSystemService.getProfessionSystemById(id);

        assertSame(professionSystemDto, result);
        verify(professionSystemRepository).findById(id);
        verify(professionSystemMapper).toDTO(professionSystem);
    }

    @Test
    void shouldThrowWhenGetProfessionSystemByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionSystemRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionSystemService.getProfessionSystemById(id));

        verify(professionSystemRepository).findById(id);
        verify(professionSystemMapper, never()).toDTO(any(ProfessionSystem.class));
    }

    @Test
    void shouldCreateProfessionSystemWhenCreateProfessionSystemIsCalled() {
        ProfessionSystemDto requestDto = createSampleProfessionSystemDto();
        ProfessionSystem mappedEntity = createSampleProfessionSystemEntity();
        ProfessionSystem savedEntity = createAnotherProfessionSystemEntity();
        ProfessionSystemDto responseDto = createAnotherProfessionSystemDto();

        given(professionSystemMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professionSystemRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professionSystemMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionSystemDto result = professionSystemService.createProfessionSystem(requestDto);

        assertSame(responseDto, result);
        verify(professionSystemMapper).toEntity(requestDto);
        verify(professionSystemRepository).save(mappedEntity);
        verify(professionSystemMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessionSystemWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionSystemDto requestDto = createPatchProfessionSystemDto();
        ProfessionSystem existingEntity = createSampleProfessionSystemEntity();
        ProfessionSystem savedEntity = createAnotherProfessionSystemEntity();
        ProfessionSystemDto responseDto = createAnotherProfessionSystemDto();

        given(professionSystemRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(professionSystemRepository.save(existingEntity)).willReturn(savedEntity);
        given(professionSystemMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionSystemDto result = professionSystemService.updateProfessionSystem(id, requestDto);

        assertSame(responseDto, result);
        verify(professionSystemRepository).findById(id);
        verify(professionSystemMapper).partialUpdate(existingEntity, requestDto);
        verify(professionSystemRepository).save(existingEntity);
        verify(professionSystemMapper).toDTO(savedEntity);
        verify(professionSystemMapper, never()).toEntity(any(ProfessionSystemDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessionSystemCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionSystemDto requestDto = createPatchProfessionSystemDto();

        given(professionSystemRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionSystemService.updateProfessionSystem(id, requestDto));

        verify(professionSystemRepository).findById(id);
        verify(professionSystemMapper, never()).partialUpdate(any(), any());
        verify(professionSystemRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessionSystemWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionSystem existingEntity = createSampleProfessionSystemEntity();

        given(professionSystemRepository.findById(id)).willReturn(Optional.of(existingEntity));

        professionSystemService.deleteProfessionSystem(id);

        verify(professionSystemRepository).findById(id);
        verify(professionSystemRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProfessionSystemCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionSystemRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionSystemService.deleteProfessionSystem(id));

        verify(professionSystemRepository).findById(id);
        verify(professionSystemRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ProfessionSystem fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionSystem createSampleProfessionSystemEntity() {
        ProfessionSystem entity = new ProfessionSystem();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberProfSystemId(1);
        entity.setCd("cd-value-1");
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ProfessionSystem fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionSystem createAnotherProfessionSystemEntity() {
        ProfessionSystem entity = new ProfessionSystem();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberProfSystemId(2);
        entity.setCd("cd-value-2");
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createSampleProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberProfSystemId(1);
        dto.setCd("cd-value-1");
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createAnotherProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberProfSystemId(2);
        dto.setCd("cd-value-2");
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProfessionSystemDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionSystemDto createPatchProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setChamberId(3);
        dto.setChamberProfSystemId(3);
        dto.setCd("cd-value-3");
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
