package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Profession;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.repository.ProfessionRepository;
import gr.knowledge.pepTest.mapper.ProfessionMapper;
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
class ProfessionServiceImplTest {

    @Mock
    private ProfessionRepository professionRepository;

    @Mock
    private ProfessionMapper professionMapper;

    @InjectMocks
    private ProfessionServiceImpl professionService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Profession.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Profession", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessionsIsCalled() {
        List<Profession> entityList = List.of(createSampleProfessionEntity(), createAnotherProfessionEntity());
        List<ProfessionDto> dtoList = List.of(createSampleProfessionDto(), createAnotherProfessionDto());

        given(professionRepository.findAll()).willReturn(entityList);
        given(professionMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProfessionDto> result = professionService.getAllProfessions();

        assertSame(dtoList, result);
        verify(professionRepository).findAll();
        verify(professionMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessionByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Profession profession = createSampleProfessionEntity();
        ProfessionDto professionDto = createSampleProfessionDto();

        given(professionRepository.findById(id)).willReturn(Optional.of(profession));
        given(professionMapper.toDTO(profession)).willReturn(professionDto);

        ProfessionDto result = professionService.getProfessionById(id);

        assertSame(professionDto, result);
        verify(professionRepository).findById(id);
        verify(professionMapper).toDTO(profession);
    }

    @Test
    void shouldThrowWhenGetProfessionByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionService.getProfessionById(id));

        verify(professionRepository).findById(id);
        verify(professionMapper, never()).toDTO(any(Profession.class));
    }

    @Test
    void shouldCreateProfessionWhenCreateProfessionIsCalled() {
        ProfessionDto requestDto = createSampleProfessionDto();
        Profession mappedEntity = createSampleProfessionEntity();
        Profession savedEntity = createAnotherProfessionEntity();
        ProfessionDto responseDto = createAnotherProfessionDto();

        given(professionMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professionRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professionMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionDto result = professionService.createProfession(requestDto);

        assertSame(responseDto, result);
        verify(professionMapper).toEntity(requestDto);
        verify(professionRepository).save(mappedEntity);
        verify(professionMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionDto requestDto = createPatchProfessionDto();
        Profession existingEntity = createSampleProfessionEntity();
        Profession savedEntity = createAnotherProfessionEntity();
        ProfessionDto responseDto = createAnotherProfessionDto();

        given(professionRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(professionRepository.save(existingEntity)).willReturn(savedEntity);
        given(professionMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionDto result = professionService.updateProfession(id, requestDto);

        assertSame(responseDto, result);
        verify(professionRepository).findById(id);
        verify(professionMapper).partialUpdate(existingEntity, requestDto);
        verify(professionRepository).save(existingEntity);
        verify(professionMapper).toDTO(savedEntity);
        verify(professionMapper, never()).toEntity(any(ProfessionDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ProfessionDto requestDto = createPatchProfessionDto();

        given(professionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionService.updateProfession(id, requestDto));

        verify(professionRepository).findById(id);
        verify(professionMapper, never()).partialUpdate(any(), any());
        verify(professionRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Profession existingEntity = createSampleProfessionEntity();

        given(professionRepository.findById(id)).willReturn(Optional.of(existingEntity));

        professionService.deleteProfession(id);

        verify(professionRepository).findById(id);
        verify(professionRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProfessionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(professionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionService.deleteProfession(id));

        verify(professionRepository).findById(id);
        verify(professionRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Profession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Profession createSampleProfessionEntity() {
        Profession entity = new Profession();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberProfessionId(1);
        entity.setCode("code-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setProteasId(new BigInteger("1"));

        return entity;
    }

    /**
     * Creates a populated Profession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Profession createAnotherProfessionEntity() {
        Profession entity = new Profession();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberProfessionId(2);
        entity.setCode("code-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setProteasId(new BigInteger("2"));

        return entity;
    }

    /**
     * Creates a populated ProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createSampleProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberProfessionId(1);
        dto.setParentProfession(new ProfessionDto());
        dto.setProfessionSystem(new ProfessionSystemDto());
        dto.setCode("code-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setProteasId(new BigInteger("1"));
        dto.setFriendlyCat(new ProfessionFriendlyCategoryDto());

        return dto;
    }

    /**
     * Creates a populated ProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createAnotherProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberProfessionId(2);
        dto.setParentProfession(new ProfessionDto());
        dto.setProfessionSystem(new ProfessionSystemDto());
        dto.setCode("code-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setProteasId(new BigInteger("2"));
        dto.setFriendlyCat(new ProfessionFriendlyCategoryDto());

        return dto;
    }

    /**
     * Creates a populated ProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionDto createPatchProfessionDto() {
        ProfessionDto dto = new ProfessionDto();
        dto.setChamberId(3);
        dto.setChamberProfessionId(3);
        dto.setParentProfession(new ProfessionDto());
        dto.setProfessionSystem(new ProfessionSystemDto());
        dto.setCode("code-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setProteasId(new BigInteger("3"));
        dto.setFriendlyCat(new ProfessionFriendlyCategoryDto());

        return dto;
    }

}
