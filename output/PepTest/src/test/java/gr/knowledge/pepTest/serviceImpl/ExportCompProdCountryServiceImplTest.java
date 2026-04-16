package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ExportCompProdCountry;
import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.repository.ExportCompProdCountryRepository;
import gr.knowledge.pepTest.mapper.ExportCompProdCountryMapper;
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
class ExportCompProdCountryServiceImplTest {

    @Mock
    private ExportCompProdCountryRepository exportCompProdCountryRepository;

    @Mock
    private ExportCompProdCountryMapper exportCompProdCountryMapper;

    @InjectMocks
    private ExportCompProdCountryServiceImpl exportCompProdCountryService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ExportCompProdCountry.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ExportCompProdCountry", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllExportCompProdCountriesIsCalled() {
        List<ExportCompProdCountry> entityList = List.of(createSampleExportCompProdCountryEntity(), createAnotherExportCompProdCountryEntity());
        List<ExportCompProdCountryDto> dtoList = List.of(createSampleExportCompProdCountryDto(), createAnotherExportCompProdCountryDto());

        given(exportCompProdCountryRepository.findAll()).willReturn(entityList);
        given(exportCompProdCountryMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ExportCompProdCountryDto> result = exportCompProdCountryService.getAllExportCompProdCountries();

        assertSame(dtoList, result);
        verify(exportCompProdCountryRepository).findAll();
        verify(exportCompProdCountryMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetExportCompProdCountryByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompProdCountry exportCompProdCountry = createSampleExportCompProdCountryEntity();
        ExportCompProdCountryDto exportCompProdCountryDto = createSampleExportCompProdCountryDto();

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.of(exportCompProdCountry));
        given(exportCompProdCountryMapper.toDTO(exportCompProdCountry)).willReturn(exportCompProdCountryDto);

        ExportCompProdCountryDto result = exportCompProdCountryService.getExportCompProdCountryById(id);

        assertSame(exportCompProdCountryDto, result);
        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryMapper).toDTO(exportCompProdCountry);
    }

    @Test
    void shouldThrowWhenGetExportCompProdCountryByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompProdCountryService.getExportCompProdCountryById(id));

        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryMapper, never()).toDTO(any(ExportCompProdCountry.class));
    }

    @Test
    void shouldCreateExportCompProdCountryWhenCreateExportCompProdCountryIsCalled() {
        ExportCompProdCountryDto requestDto = createSampleExportCompProdCountryDto();
        ExportCompProdCountry mappedEntity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountry savedEntity = createAnotherExportCompProdCountryEntity();
        ExportCompProdCountryDto responseDto = createAnotherExportCompProdCountryDto();

        given(exportCompProdCountryMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(exportCompProdCountryRepository.save(mappedEntity)).willReturn(savedEntity);
        given(exportCompProdCountryMapper.toDTO(savedEntity)).willReturn(responseDto);

        ExportCompProdCountryDto result = exportCompProdCountryService.createExportCompProdCountry(requestDto);

        assertSame(responseDto, result);
        verify(exportCompProdCountryMapper).toEntity(requestDto);
        verify(exportCompProdCountryRepository).save(mappedEntity);
        verify(exportCompProdCountryMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateExportCompProdCountryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompProdCountryDto requestDto = createPatchExportCompProdCountryDto();
        ExportCompProdCountry existingEntity = createSampleExportCompProdCountryEntity();
        ExportCompProdCountry savedEntity = createAnotherExportCompProdCountryEntity();
        ExportCompProdCountryDto responseDto = createAnotherExportCompProdCountryDto();

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(exportCompProdCountryRepository.save(existingEntity)).willReturn(savedEntity);
        given(exportCompProdCountryMapper.toDTO(savedEntity)).willReturn(responseDto);

        ExportCompProdCountryDto result = exportCompProdCountryService.updateExportCompProdCountry(id, requestDto);

        assertSame(responseDto, result);
        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryMapper).partialUpdate(existingEntity, requestDto);
        verify(exportCompProdCountryRepository).save(existingEntity);
        verify(exportCompProdCountryMapper).toDTO(savedEntity);
        verify(exportCompProdCountryMapper, never()).toEntity(any(ExportCompProdCountryDto.class));
    }

    @Test
    void shouldThrowWhenUpdateExportCompProdCountryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompProdCountryDto requestDto = createPatchExportCompProdCountryDto();

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompProdCountryService.updateExportCompProdCountry(id, requestDto));

        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryMapper, never()).partialUpdate(any(), any());
        verify(exportCompProdCountryRepository, never()).save(any());
    }

    @Test
    void shouldDeleteExportCompProdCountryWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompProdCountry existingEntity = createSampleExportCompProdCountryEntity();

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.of(existingEntity));

        exportCompProdCountryService.deleteExportCompProdCountry(id);

        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteExportCompProdCountryCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(exportCompProdCountryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompProdCountryService.deleteExportCompProdCountry(id));

        verify(exportCompProdCountryRepository).findById(id);
        verify(exportCompProdCountryRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ExportCompProdCountry fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ExportCompProdCountry createSampleExportCompProdCountryEntity() {
        ExportCompProdCountry entity = new ExportCompProdCountry();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setExpYear(1);

        return entity;
    }

    /**
     * Creates a populated ExportCompProdCountry fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ExportCompProdCountry createAnotherExportCompProdCountryEntity() {
        ExportCompProdCountry entity = new ExportCompProdCountry();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setExpYear(2);

        return entity;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createSampleExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setExpYear(1);

        return dto;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createAnotherExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setExpYear(2);

        return dto;
    }

    /**
     * Creates a populated ExportCompProdCountryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompProdCountryDto createPatchExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setExpYear(3);

        return dto;
    }

}
