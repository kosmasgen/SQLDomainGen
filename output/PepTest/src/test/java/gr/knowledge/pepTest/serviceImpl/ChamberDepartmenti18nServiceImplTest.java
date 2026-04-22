package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ChamberDepartmenti18n;
import gr.knowledge.pepTest.dto.ChamberDepartmenti18nDto;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.repository.ChamberDepartmenti18nRepository;
import gr.knowledge.pepTest.mapper.ChamberDepartmenti18nMapper;
import gr.knowledge.pepTest.entity.ChamberDepartmenti18nKey;
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
class ChamberDepartmenti18nServiceImplTest {

    @Mock
    private ChamberDepartmenti18nRepository chamberDepartmenti18nRepository;

    @Mock
    private ChamberDepartmenti18nMapper chamberDepartmenti18nMapper;

    @InjectMocks
    private ChamberDepartmenti18nServiceImpl chamberDepartmenti18nService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ChamberDepartmenti18n.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ChamberDepartmenti18n", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllChamberDepartmenti18nsIsCalled() {
        List<ChamberDepartmenti18n> entityList = List.of(createSampleChamberDepartmenti18nEntity(), createAnotherChamberDepartmenti18nEntity());
        List<ChamberDepartmenti18nDto> dtoList = List.of(createSampleChamberDepartmenti18nDto(), createAnotherChamberDepartmenti18nDto());

        given(chamberDepartmenti18nRepository.findAll()).willReturn(entityList);
        given(chamberDepartmenti18nMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ChamberDepartmenti18nDto> result = chamberDepartmenti18nService.getAllChamberDepartmenti18ns();

        assertSame(dtoList, result);
        verify(chamberDepartmenti18nRepository).findAll();
        verify(chamberDepartmenti18nMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetChamberDepartmenti18nByIdIsCalled() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        ChamberDepartmenti18n chamberDepartmenti18n = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18nDto chamberDepartmenti18nDto = createSampleChamberDepartmenti18nDto();

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.of(chamberDepartmenti18n));
        given(chamberDepartmenti18nMapper.toDTO(chamberDepartmenti18n)).willReturn(chamberDepartmenti18nDto);

        ChamberDepartmenti18nDto result = chamberDepartmenti18nService.getChamberDepartmenti18nById(departmentId, languageId);

        assertSame(chamberDepartmenti18nDto, result);
        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nMapper).toDTO(chamberDepartmenti18n);
    }

    @Test
    void shouldThrowWhenGetChamberDepartmenti18nByIdCannotFindEntity() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmenti18nService.getChamberDepartmenti18nById(departmentId, languageId));

        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nMapper, never()).toDTO(any(ChamberDepartmenti18n.class));
    }

    @Test
    void shouldCreateChamberDepartmenti18nWhenCreateChamberDepartmenti18nIsCalled() {
        ChamberDepartmenti18nDto requestDto = createSampleChamberDepartmenti18nDto();
        ChamberDepartmenti18n mappedEntity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18n savedEntity = createAnotherChamberDepartmenti18nEntity();
        ChamberDepartmenti18nDto responseDto = createAnotherChamberDepartmenti18nDto();

        given(chamberDepartmenti18nMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(chamberDepartmenti18nRepository.save(mappedEntity)).willReturn(savedEntity);
        given(chamberDepartmenti18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberDepartmenti18nDto result = chamberDepartmenti18nService.createChamberDepartmenti18n(requestDto);

        assertSame(responseDto, result);
        verify(chamberDepartmenti18nMapper).toEntity(requestDto);
        verify(chamberDepartmenti18nRepository).save(mappedEntity);
        verify(chamberDepartmenti18nMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateChamberDepartmenti18nWhenEntityExists() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        ChamberDepartmenti18nDto requestDto = createPatchChamberDepartmenti18nDto();
        ChamberDepartmenti18n existingEntity = createSampleChamberDepartmenti18nEntity();
        ChamberDepartmenti18n savedEntity = createAnotherChamberDepartmenti18nEntity();
        ChamberDepartmenti18nDto responseDto = createAnotherChamberDepartmenti18nDto();

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));
        given(chamberDepartmenti18nRepository.save(existingEntity)).willReturn(savedEntity);
        given(chamberDepartmenti18nMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChamberDepartmenti18nDto result = chamberDepartmenti18nService.updateChamberDepartmenti18n(departmentId, languageId, requestDto);

        assertSame(responseDto, result);
        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nMapper).partialUpdate(existingEntity, requestDto);
        verify(chamberDepartmenti18nRepository).save(existingEntity);
        verify(chamberDepartmenti18nMapper).toDTO(savedEntity);
        verify(chamberDepartmenti18nMapper, never()).toEntity(any(ChamberDepartmenti18nDto.class));
    }

    @Test
    void shouldThrowWhenUpdateChamberDepartmenti18nCannotFindEntity() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        ChamberDepartmenti18nDto requestDto = createPatchChamberDepartmenti18nDto();

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmenti18nService.updateChamberDepartmenti18n(departmentId, languageId, requestDto));

        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nMapper, never()).partialUpdate(any(), any());
        verify(chamberDepartmenti18nRepository, never()).save(any());
    }

    @Test
    void shouldDeleteChamberDepartmenti18nWhenEntityExists() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        ChamberDepartmenti18n existingEntity = createSampleChamberDepartmenti18nEntity();

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.of(existingEntity));

        chamberDepartmenti18nService.deleteChamberDepartmenti18n(departmentId, languageId);

        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nRepository).deleteById(expectedKey);
    }

    @Test
    void shouldThrowWhenDeleteChamberDepartmenti18nCannotFindEntity() {
        UUID departmentId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(departmentId);
        id.setLanguageId(languageId);

        ChamberDepartmenti18nKey expectedKey = new ChamberDepartmenti18nKey();
        expectedKey.setDepartmentId(departmentId);
        expectedKey.setLanguageId(languageId);

        given(chamberDepartmenti18nRepository.findById(expectedKey)).willReturn(Optional.empty());

        assertNotFound(() -> chamberDepartmenti18nService.deleteChamberDepartmenti18n(departmentId, languageId));

        verify(chamberDepartmenti18nRepository).findById(expectedKey);
        verify(chamberDepartmenti18nRepository, never()).deleteById(expectedKey);
    }

    /**
     * Creates a populated ChamberDepartmenti18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartmenti18n createSampleChamberDepartmenti18nEntity() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();
        entity.setDescription("description-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setChamberI18nId(1);

        return entity;
    }

    /**
     * Creates a populated ChamberDepartmenti18n fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChamberDepartmenti18n createAnotherChamberDepartmenti18nEntity() {
        ChamberDepartmenti18n entity = new ChamberDepartmenti18n();
        entity.setDescription("description-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setChamberI18nId(2);

        return entity;
    }

    /**
     * Creates a populated ChamberDepartmenti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createSampleChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setDepartment(new ChamberDepartmentDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(1);

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmenti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createAnotherChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        ChamberDepartmenti18nKey id = new ChamberDepartmenti18nKey();
        id.setDepartmentId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        id.setLanguageId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setId(id);

        dto.setDepartment(new ChamberDepartmentDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setChamberI18nId(2);

        return dto;
    }

    /**
     * Creates a populated ChamberDepartmenti18nDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChamberDepartmenti18nDto createPatchChamberDepartmenti18nDto() {
        ChamberDepartmenti18nDto dto = new ChamberDepartmenti18nDto();
        dto.setDepartment(new ChamberDepartmentDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("description-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setChamberI18nId(3);

        return dto;
    }

}
