package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.TemporaryCompanyProfession;
import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.repository.TemporaryCompanyProfessionRepository;
import gr.knowledge.pepTest.mapper.TemporaryCompanyProfessionMapper;
import java.math.BigInteger;
import java.time.LocalDate;
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
class TemporaryCompanyProfessionServiceImplTest {

    @Mock
    private TemporaryCompanyProfessionRepository temporaryCompanyProfessionRepository;

    @Mock
    private TemporaryCompanyProfessionMapper temporaryCompanyProfessionMapper;

    @InjectMocks
    private TemporaryCompanyProfessionServiceImpl temporaryCompanyProfessionService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for TemporaryCompanyProfession.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("TemporaryCompanyProfession", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllTemporaryCompanyProfessionsIsCalled() {
        List<TemporaryCompanyProfession> entityList = List.of(createSampleTemporaryCompanyProfessionEntity(), createAnotherTemporaryCompanyProfessionEntity());
        List<TemporaryCompanyProfessionDto> dtoList = List.of(createSampleTemporaryCompanyProfessionDto(), createAnotherTemporaryCompanyProfessionDto());

        given(temporaryCompanyProfessionRepository.findAll()).willReturn(entityList);
        given(temporaryCompanyProfessionMapper.toDTOList(entityList)).willReturn(dtoList);

        List<TemporaryCompanyProfessionDto> result = temporaryCompanyProfessionService.getAllTemporaryCompanyProfessions();

        assertSame(dtoList, result);
        verify(temporaryCompanyProfessionRepository).findAll();
        verify(temporaryCompanyProfessionMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetTemporaryCompanyProfessionByIdIsCalled() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyProfession temporaryCompanyProfession = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfessionDto temporaryCompanyProfessionDto = createSampleTemporaryCompanyProfessionDto();

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.of(temporaryCompanyProfession));
        given(temporaryCompanyProfessionMapper.toDTO(temporaryCompanyProfession)).willReturn(temporaryCompanyProfessionDto);

        TemporaryCompanyProfessionDto result = temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id);

        assertSame(temporaryCompanyProfessionDto, result);
        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionMapper).toDTO(temporaryCompanyProfession);
    }

    @Test
    void shouldThrowWhenGetTemporaryCompanyProfessionByIdCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id));

        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionMapper, never()).toDTO(any(TemporaryCompanyProfession.class));
    }

    @Test
    void shouldCreateTemporaryCompanyProfessionWhenCreateTemporaryCompanyProfessionIsCalled() {
        TemporaryCompanyProfessionDto requestDto = createSampleTemporaryCompanyProfessionDto();
        TemporaryCompanyProfession mappedEntity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfession savedEntity = createAnotherTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfessionDto responseDto = createAnotherTemporaryCompanyProfessionDto();

        given(temporaryCompanyProfessionMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(temporaryCompanyProfessionRepository.save(mappedEntity)).willReturn(savedEntity);
        given(temporaryCompanyProfessionMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyProfessionDto result = temporaryCompanyProfessionService.createTemporaryCompanyProfession(requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyProfessionMapper).toEntity(requestDto);
        verify(temporaryCompanyProfessionRepository).save(mappedEntity);
        verify(temporaryCompanyProfessionMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateTemporaryCompanyProfessionWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyProfessionDto requestDto = createPatchTemporaryCompanyProfessionDto();
        TemporaryCompanyProfession existingEntity = createSampleTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfession savedEntity = createAnotherTemporaryCompanyProfessionEntity();
        TemporaryCompanyProfessionDto responseDto = createAnotherTemporaryCompanyProfessionDto();

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(temporaryCompanyProfessionRepository.save(existingEntity)).willReturn(savedEntity);
        given(temporaryCompanyProfessionMapper.toDTO(savedEntity)).willReturn(responseDto);

        TemporaryCompanyProfessionDto result = temporaryCompanyProfessionService.updateTemporaryCompanyProfession(id, requestDto);

        assertSame(responseDto, result);
        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionMapper).partialUpdate(existingEntity, requestDto);
        verify(temporaryCompanyProfessionRepository).save(existingEntity);
        verify(temporaryCompanyProfessionMapper).toDTO(savedEntity);
        verify(temporaryCompanyProfessionMapper, never()).toEntity(any(TemporaryCompanyProfessionDto.class));
    }

    @Test
    void shouldThrowWhenUpdateTemporaryCompanyProfessionCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyProfessionDto requestDto = createPatchTemporaryCompanyProfessionDto();

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyProfessionService.updateTemporaryCompanyProfession(id, requestDto));

        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionMapper, never()).partialUpdate(any(), any());
        verify(temporaryCompanyProfessionRepository, never()).save(any());
    }

    @Test
    void shouldDeleteTemporaryCompanyProfessionWhenEntityExists() {
        BigInteger id = new BigInteger("1");

        TemporaryCompanyProfession existingEntity = createSampleTemporaryCompanyProfessionEntity();

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.of(existingEntity));

        temporaryCompanyProfessionService.deleteTemporaryCompanyProfession(id);

        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteTemporaryCompanyProfessionCannotFindEntity() {
        BigInteger id = new BigInteger("1");

        given(temporaryCompanyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> temporaryCompanyProfessionService.deleteTemporaryCompanyProfession(id));

        verify(temporaryCompanyProfessionRepository).findById(id);
        verify(temporaryCompanyProfessionRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated TemporaryCompanyProfession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyProfession createSampleTemporaryCompanyProfessionEntity() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();
        entity.setId(new BigInteger("1"));
        entity.setVersion(new BigInteger("1"));
        entity.setCompanyId(new BigInteger("1"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setProfessionId(new BigInteger("1"));
        entity.setProfessionKindId(new BigInteger("1"));
        entity.setRecdeleted(new BigInteger("1"));
        entity.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyProfession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private TemporaryCompanyProfession createAnotherTemporaryCompanyProfessionEntity() {
        TemporaryCompanyProfession entity = new TemporaryCompanyProfession();
        entity.setId(new BigInteger("2"));
        entity.setVersion(new BigInteger("2"));
        entity.setCompanyId(new BigInteger("2"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setProfessionId(new BigInteger("2"));
        entity.setProfessionKindId(new BigInteger("2"));
        entity.setRecdeleted(new BigInteger("2"));
        entity.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        entity.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return entity;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createSampleTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setId(new BigInteger("1"));
        dto.setVersion(new BigInteger("1"));
        dto.setCompanyId(new BigInteger("1"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setProfessionId(new BigInteger("1"));
        dto.setProfessionKindId(new BigInteger("1"));
        dto.setRecdeleted(new BigInteger("1"));
        dto.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 1));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 1));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createAnotherTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setId(new BigInteger("2"));
        dto.setVersion(new BigInteger("2"));
        dto.setCompanyId(new BigInteger("2"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setProfessionId(new BigInteger("2"));
        dto.setProfessionKindId(new BigInteger("2"));
        dto.setRecdeleted(new BigInteger("2"));
        dto.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 2));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 2));

        return dto;
    }

    /**
     * Creates a populated TemporaryCompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private TemporaryCompanyProfessionDto createPatchTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setVersion(new BigInteger("3"));
        dto.setCompanyId(new BigInteger("3"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setProfessionId(new BigInteger("3"));
        dto.setProfessionKindId(new BigInteger("3"));
        dto.setRecdeleted(new BigInteger("3"));
        dto.setToDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDate.of(2025, 1, 3));
        dto.setGemiLastUpdated(LocalDate.of(2025, 1, 3));

        return dto;
    }

}
