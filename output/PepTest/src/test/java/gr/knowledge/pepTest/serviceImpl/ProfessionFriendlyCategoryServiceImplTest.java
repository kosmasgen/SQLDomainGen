package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ProfessionFriendlyCategory;
import gr.knowledge.pepTest.dto.ProfessionFriendlyCategoryDto;
import gr.knowledge.pepTest.repository.ProfessionFriendlyCategoryRepository;
import gr.knowledge.pepTest.mapper.ProfessionFriendlyCategoryMapper;

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
class ProfessionFriendlyCategoryServiceImplTest {

    @Mock
    private ProfessionFriendlyCategoryRepository professionFriendlyCategoryRepository;

    @Mock
    private ProfessionFriendlyCategoryMapper professionFriendlyCategoryMapper;

    @InjectMocks
    private ProfessionFriendlyCategoryServiceImpl professionFriendlyCategoryService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ProfessionFriendlyCategory.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ProfessionFriendlyCategory", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllProfessionFriendlyCategoriesIsCalled() {
        List<ProfessionFriendlyCategory> entityList = List.of(createSampleProfessionFriendlyCategoryEntity(), createAnotherProfessionFriendlyCategoryEntity());
        List<ProfessionFriendlyCategoryDto> dtoList = List.of(createSampleProfessionFriendlyCategoryDto(), createAnotherProfessionFriendlyCategoryDto());

        given(professionFriendlyCategoryRepository.findAll()).willReturn(entityList);
        given(professionFriendlyCategoryMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ProfessionFriendlyCategoryDto> result = professionFriendlyCategoryService.getAllProfessionFriendlyCategories();

        assertSame(dtoList, result);
        verify(professionFriendlyCategoryRepository).findAll();
        verify(professionFriendlyCategoryMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetProfessionFriendlyCategoryByIdIsCalled() {
        String id = "test-id";

        ProfessionFriendlyCategory professionFriendlyCategory = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategoryDto professionFriendlyCategoryDto = createSampleProfessionFriendlyCategoryDto();

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.of(professionFriendlyCategory));
        given(professionFriendlyCategoryMapper.toDTO(professionFriendlyCategory)).willReturn(professionFriendlyCategoryDto);

        ProfessionFriendlyCategoryDto result = professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id);

        assertSame(professionFriendlyCategoryDto, result);
        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryMapper).toDTO(professionFriendlyCategory);
    }

    @Test
    void shouldThrowWhenGetProfessionFriendlyCategoryByIdCannotFindEntity() {
        String id = "test-id";

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionFriendlyCategoryService.getProfessionFriendlyCategoryById(id));

        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryMapper, never()).toDTO(any(ProfessionFriendlyCategory.class));
    }

    @Test
    void shouldCreateProfessionFriendlyCategoryWhenCreateProfessionFriendlyCategoryIsCalled() {
        ProfessionFriendlyCategoryDto requestDto = createSampleProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategory mappedEntity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategory savedEntity = createAnotherProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategoryDto responseDto = createAnotherProfessionFriendlyCategoryDto();

        given(professionFriendlyCategoryMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(professionFriendlyCategoryRepository.save(mappedEntity)).willReturn(savedEntity);
        given(professionFriendlyCategoryMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionFriendlyCategoryDto result = professionFriendlyCategoryService.createProfessionFriendlyCategory(requestDto);

        assertSame(responseDto, result);
        verify(professionFriendlyCategoryMapper).toEntity(requestDto);
        verify(professionFriendlyCategoryRepository).save(mappedEntity);
        verify(professionFriendlyCategoryMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateProfessionFriendlyCategoryWhenEntityExists() {
        String id = "test-id";

        ProfessionFriendlyCategoryDto requestDto = createPatchProfessionFriendlyCategoryDto();
        ProfessionFriendlyCategory existingEntity = createSampleProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategory savedEntity = createAnotherProfessionFriendlyCategoryEntity();
        ProfessionFriendlyCategoryDto responseDto = createAnotherProfessionFriendlyCategoryDto();

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(professionFriendlyCategoryRepository.save(existingEntity)).willReturn(savedEntity);
        given(professionFriendlyCategoryMapper.toDTO(savedEntity)).willReturn(responseDto);

        ProfessionFriendlyCategoryDto result = professionFriendlyCategoryService.updateProfessionFriendlyCategory(id, requestDto);

        assertSame(responseDto, result);
        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryMapper).partialUpdate(existingEntity, requestDto);
        verify(professionFriendlyCategoryRepository).save(existingEntity);
        verify(professionFriendlyCategoryMapper).toDTO(savedEntity);
        verify(professionFriendlyCategoryMapper, never()).toEntity(any(ProfessionFriendlyCategoryDto.class));
    }

    @Test
    void shouldThrowWhenUpdateProfessionFriendlyCategoryCannotFindEntity() {
        String id = "test-id";

        ProfessionFriendlyCategoryDto requestDto = createPatchProfessionFriendlyCategoryDto();

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionFriendlyCategoryService.updateProfessionFriendlyCategory(id, requestDto));

        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryMapper, never()).partialUpdate(any(), any());
        verify(professionFriendlyCategoryRepository, never()).save(any());
    }

    @Test
    void shouldDeleteProfessionFriendlyCategoryWhenEntityExists() {
        String id = "test-id";

        ProfessionFriendlyCategory existingEntity = createSampleProfessionFriendlyCategoryEntity();

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.of(existingEntity));

        professionFriendlyCategoryService.deleteProfessionFriendlyCategory(id);

        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteProfessionFriendlyCategoryCannotFindEntity() {
        String id = "test-id";

        given(professionFriendlyCategoryRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> professionFriendlyCategoryService.deleteProfessionFriendlyCategory(id));

        verify(professionFriendlyCategoryRepository).findById(id);
        verify(professionFriendlyCategoryRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ProfessionFriendlyCategory fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionFriendlyCategory createSampleProfessionFriendlyCategoryEntity() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();
        entity.setId("id-value-1");
        entity.setDescr("descr-value-1");

        return entity;
    }

    /**
     * Creates a populated ProfessionFriendlyCategory fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ProfessionFriendlyCategory createAnotherProfessionFriendlyCategoryEntity() {
        ProfessionFriendlyCategory entity = new ProfessionFriendlyCategory();
        entity.setId("id-value-2");
        entity.setDescr("descr-value-2");

        return entity;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createSampleProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setId("id-value-1");
        dto.setDescr("descr-value-1");

        return dto;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createAnotherProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setId("id-value-2");
        dto.setDescr("descr-value-2");

        return dto;
    }

    /**
     * Creates a populated ProfessionFriendlyCategoryDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ProfessionFriendlyCategoryDto createPatchProfessionFriendlyCategoryDto() {
        ProfessionFriendlyCategoryDto dto = new ProfessionFriendlyCategoryDto();
        dto.setDescr("descr-value-3");

        return dto;
    }

}
