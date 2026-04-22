package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.Languages;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.LanguagesRepository;
import gr.knowledge.pepTest.mapper.LanguagesMapper;
import java.util.UUID;

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
class LanguagesServiceImplTest {

    @Mock
    private LanguagesRepository languagesRepository;

    @Mock
    private LanguagesMapper languagesMapper;

    @InjectMocks
    private LanguagesServiceImpl languagesService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for Languages.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("Languages", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllLanguagesesIsCalled() {
        List<Languages> entityList = List.of(createSampleLanguagesEntity(), createAnotherLanguagesEntity());
        List<LanguagesDto> dtoList = List.of(createSampleLanguagesDto(), createAnotherLanguagesDto());

        given(languagesRepository.findAll()).willReturn(entityList);
        given(languagesMapper.toDTOList(entityList)).willReturn(dtoList);

        List<LanguagesDto> result = languagesService.getAllLanguageses();

        assertSame(dtoList, result);
        verify(languagesRepository).findAll();
        verify(languagesMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetLanguagesByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Languages languages = createSampleLanguagesEntity();
        LanguagesDto languagesDto = createSampleLanguagesDto();

        given(languagesRepository.findById(id)).willReturn(Optional.of(languages));
        given(languagesMapper.toDTO(languages)).willReturn(languagesDto);

        LanguagesDto result = languagesService.getLanguagesById(id);

        assertSame(languagesDto, result);
        verify(languagesRepository).findById(id);
        verify(languagesMapper).toDTO(languages);
    }

    @Test
    void shouldThrowWhenGetLanguagesByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(languagesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> languagesService.getLanguagesById(id));

        verify(languagesRepository).findById(id);
        verify(languagesMapper, never()).toDTO(any(Languages.class));
    }

    @Test
    void shouldCreateLanguagesWhenCreateLanguagesIsCalled() {
        LanguagesDto requestDto = createSampleLanguagesDto();
        Languages mappedEntity = createSampleLanguagesEntity();
        Languages savedEntity = createAnotherLanguagesEntity();
        LanguagesDto responseDto = createAnotherLanguagesDto();

        given(languagesMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(languagesRepository.save(mappedEntity)).willReturn(savedEntity);
        given(languagesMapper.toDTO(savedEntity)).willReturn(responseDto);

        LanguagesDto result = languagesService.createLanguages(requestDto);

        assertSame(responseDto, result);
        verify(languagesMapper).toEntity(requestDto);
        verify(languagesRepository).save(mappedEntity);
        verify(languagesMapper).toDTO(savedEntity);
    }

    @Test
    void shouldThrowBadRequestWhenCreateLanguagesWithExistingCd() {
        LanguagesDto requestDto = createSampleLanguagesDto();

        given(languagesRepository.existsByCd(requestDto.getCd())).willReturn(true);

        GeneratedRuntimeException exception = assertThrows(GeneratedRuntimeException.class, () -> languagesService.createLanguages(requestDto));

        assertEquals(ErrorCodes.BAD_REQUEST, exception.getCode());
        assertEquals("Languages", exception.getEntity());

        verify(languagesRepository, never()).save(any());
    }

    @Test
    void shouldThrowBadRequestWhenCreateLanguagesWithExistingChamberLanguageId() {
        LanguagesDto requestDto = createSampleLanguagesDto();

        given(languagesRepository.existsByChamberLanguageId(requestDto.getChamberLanguageId())).willReturn(true);

        GeneratedRuntimeException exception = assertThrows(GeneratedRuntimeException.class, () -> languagesService.createLanguages(requestDto));

        assertEquals(ErrorCodes.BAD_REQUEST, exception.getCode());
        assertEquals("Languages", exception.getEntity());

        verify(languagesRepository, never()).save(any());
    }

    @Test
    void shouldUpdateLanguagesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        LanguagesDto requestDto = createPatchLanguagesDto();
        Languages existingEntity = createSampleLanguagesEntity();
        Languages savedEntity = createAnotherLanguagesEntity();
        LanguagesDto responseDto = createAnotherLanguagesDto();

        given(languagesRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(languagesRepository.save(existingEntity)).willReturn(savedEntity);
        given(languagesMapper.toDTO(savedEntity)).willReturn(responseDto);

        LanguagesDto result = languagesService.updateLanguages(id, requestDto);

        assertSame(responseDto, result);
        verify(languagesRepository).findById(id);
        verify(languagesMapper).partialUpdate(existingEntity, requestDto);
        verify(languagesRepository).save(existingEntity);
        verify(languagesMapper).toDTO(savedEntity);
        verify(languagesMapper, never()).toEntity(any(LanguagesDto.class));
    }

    @Test
    void shouldThrowWhenUpdateLanguagesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        LanguagesDto requestDto = createPatchLanguagesDto();

        given(languagesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> languagesService.updateLanguages(id, requestDto));

        verify(languagesRepository).findById(id);
        verify(languagesMapper, never()).partialUpdate(any(), any());
        verify(languagesRepository, never()).save(any());
    }

    @Test
    void shouldDeleteLanguagesWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Languages existingEntity = createSampleLanguagesEntity();

        given(languagesRepository.findById(id)).willReturn(Optional.of(existingEntity));

        languagesService.deleteLanguages(id);

        verify(languagesRepository).findById(id);
        verify(languagesRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteLanguagesCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(languagesRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> languagesService.deleteLanguages(id));

        verify(languagesRepository).findById(id);
        verify(languagesRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated Languages fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Languages createSampleLanguagesEntity() {
        Languages entity = new Languages();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setCd("cd-value-1");
        entity.setDescr("descr-value-1");
        entity.setChamberLanguageId(1);

        return entity;
    }

    /**
     * Creates a populated Languages fixture for service tests.
     *
     * @return populated entity fixture
     */
    private Languages createAnotherLanguagesEntity() {
        Languages entity = new Languages();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setCd("cd-value-2");
        entity.setDescr("descr-value-2");
        entity.setChamberLanguageId(2);

        return entity;
    }

    /**
     * Creates a populated LanguagesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createSampleLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setCd("cd-value-1");
        dto.setDescr("descr-value-1");
        dto.setChamberLanguageId(1);

        return dto;
    }

    /**
     * Creates a populated LanguagesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createAnotherLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setCd("cd-value-2");
        dto.setDescr("descr-value-2");
        dto.setChamberLanguageId(2);

        return dto;
    }

    /**
     * Creates a populated LanguagesDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private LanguagesDto createPatchLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setChamberId(3);
        dto.setCd("cd-value-3");
        dto.setDescr("descr-value-3");
        dto.setChamberLanguageId(3);

        return dto;
    }

}
