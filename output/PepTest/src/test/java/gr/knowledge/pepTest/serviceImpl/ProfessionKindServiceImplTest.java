package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ProfessionKind;
import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.repository.ProfessionKindRepository;
import gr.knowledge.pepTest.mapper.ProfessionKindMapper;
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
class ProfessionKindServiceImplTest {

    @Mock
    private ProfessionKindRepository professionKindRepository;

    @Mock
    private ProfessionKindMapper professionKindMapper;

    @InjectMocks
    private ProfessionKindServiceImpl professionKindService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ProfessionKind.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ProfessionKind", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessionKindsIsCalled() {
        List<ProfessionKind> entityList = List.of(createSampleProfessionKindEntity(), createAnotherProfessionKindEntity());
        List<ProfessionKindDto> dtoList = List.of(createSampleProfessionKindDto(), createAnotherProfessionKindDto());

        given(professionKindRepository.findAll()).willReturn(entityList);
        given(professionKindMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProfessionKindDto> result = professionKindService.getAllProfessionKinds();

        assertSame(dtoList, result);
        verify(professionKindRepository).findAll();
        verify(professionKindMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessionKindByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKind professionKind = createSampleProfessionKindEntity();
        ProfessionKindDto professionKindDto = createSampleProfessionKindDto();

        given(professionKindRepository.findById(id)).willReturn(Optional.of(professionKind));
        given(professionKindMapper.toDTO(professionKind)).willReturn(professionKindDto);

        ProfessionKindDto result = professionKindService.getProfessionKindById(id);

        assertSame(professionKindDto, result);
        verify(professionKindRepository).findById(id);
        verify(professionKindMapper).toDTO(professionKind);
    }

    @Test
    void shouldThrowWhenGetProfessionKindByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionKindRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindService.getProfessionKindById(id));

        verify(professionKindRepository).findById(id);
        verify(professionKindMapper, never()).toDTO(any(ProfessionKind.class));
    }

    @Test
    void shouldCreateProfessionKindWhenCreateProfessionKindIsCalled() {
        ProfessionKindDto requestDto = createSampleProfessionKindDto();
        ProfessionKind mappedEntity = createSampleProfessionKindEntity();
        ProfessionKind savedEntity = createAnotherProfessionKindEntity();
        ProfessionKindDto responseDto = createAnotherProfessionKindDto();

        given(professionKindMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professionKindRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professionKindMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionKindDto result = professionKindService.createProfessionKind(requestDto);

        assertSame(responseDto, result);
        verify(professionKindMapper).toEntity(requestDto);
        verify(professionKindRepository).save(mappedEntity);
        verify(professionKindMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessionKindWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindDto requestDto = createPatchProfessionKindDto();
        ProfessionKind existingEntity = createSampleProfessionKindEntity();
        ProfessionKind savedEntity = createAnotherProfessionKindEntity();
        ProfessionKindDto responseDto = createAnotherProfessionKindDto();

        given(professionKindRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(professionKindRepository.save(existingEntity)).willReturn(savedEntity);
        given(professionKindMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionKindDto result = professionKindService.updateProfessionKind(id, requestDto);

        assertSame(responseDto, result);
        verify(professionKindRepository).findById(id);
        verify(professionKindMapper).partialUpdate(existingEntity, requestDto);
        verify(professionKindRepository).save(existingEntity);
        verify(professionKindMapper).toDTO(savedEntity);
        verify(professionKindMapper, never()).toEntity(any(ProfessionKindDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessionKindCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKindDto requestDto = createPatchProfessionKindDto();

        given(professionKindRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindService.updateProfessionKind(id, requestDto));

        verify(professionKindRepository).findById(id);
        verify(professionKindMapper, never()).partialUpdate(any(), any());
        verify(professionKindRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessionKindWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionKind existingEntity = createSampleProfessionKindEntity();

        given(professionKindRepository.findById(id)).willReturn(Optional.of(existingEntity));

        professionKindService.deleteProfessionKind(id);

        verify(professionKindRepository).findById(id);
        verify(professionKindRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProfessionKindCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionKindRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionKindService.deleteProfessionKind(id));

        verify(professionKindRepository).findById(id);
        verify(professionKindRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ProfessionKind fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKind createSampleProfessionKindEntity() {
        ProfessionKind entity = new ProfessionKind();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberProfKindId(1);
        entity.setCd("cd-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated ProfessionKind fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionKind createAnotherProfessionKindEntity() {
        ProfessionKind entity = new ProfessionKind();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberProfKindId(2);
        entity.setCd("cd-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createSampleProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberProfKindId(1);
        dto.setCd("cd-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createAnotherProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberProfKindId(2);
        dto.setCd("cd-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated ProfessionKindDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionKindDto createPatchProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setChamberId(3);
        dto.setChamberProfKindId(3);
        dto.setCd("cd-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
