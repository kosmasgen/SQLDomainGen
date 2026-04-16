package gr.knowledge.pepTest.mapper;

import gr.knowledge.pepTest.dto.FolderDto;
import gr.knowledge.pepTest.entity.Folder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;

class FolderMapperTest {

    private FolderMapper folderMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        folderMapper = new FolderMapper(modelMapper);
    }

    /**
     * Verifies full entity to DTO mapping, including nested project types.
     */
    @Test
    void shouldMapFolderToFolderDto() {
        Folder entity = createSampleFolderEntity();
        FolderDto expectedDto = createSampleFolderDto();

        FolderDto actualDto = folderMapper.toDTO(entity);

        assertThat(actualDto)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    /**
     * Verifies full DTO to entity mapping, including nested project types.
     */
    @Test
    void shouldMapFolderDtoToFolder() {
        FolderDto dto = createSampleFolderDto();
        Folder expectedEntity = createSampleFolderEntity();

        Folder actualEntity = folderMapper.toEntity(dto);

        assertThat(actualEntity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Verifies full entity list to DTO list mapping.
     */
    @Test
    void shouldMapFolderListToFolderDtoList() {
        List<Folder> entityList = List.of(
                createSampleFolderEntity(),
                createAnotherFolderEntity()
        );
        List<FolderDto> expectedDtoList = List.of(
                createSampleFolderDto(),
                createAnotherFolderDto()
        );

        List<FolderDto> actualDtoList = folderMapper.toDTOList(entityList);

        assertThat(actualDtoList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedDtoList);
    }

    /**
     * Verifies full DTO list to entity list mapping.
     */
    @Test
    void shouldMapFolderDtoListToFolderList() {
        List<FolderDto> dtoList = List.of(
                createSampleFolderDto(),
                createAnotherFolderDto()
        );
        List<Folder> expectedEntityList = List.of(
                createSampleFolderEntity(),
                createAnotherFolderEntity()
        );

        List<Folder> actualEntityList = folderMapper.toEntityList(dtoList);

        assertThat(actualEntityList)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(expectedEntityList);
    }

    /**
     * Verifies that partialUpdate replaces every non-null mapped field,
     * preserves null-patched fields from the original entity,
     * and never changes primary key fields.
     */
    @Test
    void shouldApplyFullPartialUpdateForFolder() {
        Folder originalEntity = createSampleFolderEntity();
        Folder actualEntity = createSampleFolderEntity();
        FolderDto patchDto = createPatchFolderDto();
        Folder patchEntity = folderMapper.toEntity(patchDto);

        folderMapper.partialUpdate(actualEntity, patchDto);

        assertThat(actualEntity.getId())
                .isEqualTo(originalEntity.getId());

        Object descrExpectedValue = patchEntity.getDescr() != null ? patchEntity.getDescr() : originalEntity.getDescr();
        assertThat(actualEntity.getDescr())
                .isEqualTo(descrExpectedValue);

        Object uriExpectedValue = patchEntity.getUri() != null ? patchEntity.getUri() : originalEntity.getUri();
        assertThat(actualEntity.getUri())
                .isEqualTo(uriExpectedValue);

        Object dateCreatedExpectedValue = patchEntity.getDateCreated() != null ? patchEntity.getDateCreated() : originalEntity.getDateCreated();
        assertThat(actualEntity.getDateCreated())
                .isEqualTo(dateCreatedExpectedValue);

        Object lastUpdatedExpectedValue = patchEntity.getLastUpdated() != null ? patchEntity.getLastUpdated() : originalEntity.getLastUpdated();
        assertThat(actualEntity.getLastUpdated())
                .isEqualTo(lastUpdatedExpectedValue);

        Object recdeletedExpectedValue = patchEntity.getRecdeleted() != null ? patchEntity.getRecdeleted() : originalEntity.getRecdeleted();
        assertThat(actualEntity.getRecdeleted())
                .isEqualTo(recdeletedExpectedValue);

    }

    /**
     * Verifies that entity list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyFolderDtoListForNullOrEmptyFolderList() {
        assertThat(folderMapper.toDTOList(null)).isEmpty();
        assertThat(folderMapper.toDTOList(List.of())).isEmpty();
    }

    /**
     * Verifies that DTO list mapping returns an empty list for null and empty input.
     */
    @Test
    void shouldReturnEmptyFolderListForNullOrEmptyFolderDtoList() {
        assertThat(folderMapper.toEntityList(null)).isEmpty();
        assertThat(folderMapper.toEntityList(List.of())).isEmpty();
    }

    /**
     * Verifies that partialUpdate safely ignores null inputs.
     */
    @Test
    void shouldIgnoreNullArgumentsInPartialUpdate() {
        Folder entity = createSampleFolderEntity();
        Folder expectedEntity = createSampleFolderEntity();

        folderMapper.partialUpdate(entity, null);
        folderMapper.partialUpdate(null, createPatchFolderDto());

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(expectedEntity);
    }

    /**
     * Creates a populated Folder fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Folder createSampleFolderEntity() {
        Folder entity = new Folder();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        entity.setDescr("descrValue1");
        entity.setUri("uriValue1");
        entity.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        entity.setRecdeleted(true);

        return entity;
    }

    /**
     * Creates a populated Folder fixture for mapper tests.
     *
     * @return populated entity fixture
     */
    private Folder createAnotherFolderEntity() {
        Folder entity = new Folder();
        entity.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        entity.setDescr("descrValue2");
        entity.setUri("uriValue2");
        entity.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        entity.setRecdeleted(false);

        return entity;
    }

    /**
     * Creates a populated FolderDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createSampleFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        dto.setDescr("descrValue1");
        dto.setUri("uriValue1");
        dto.setDateCreated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setLastUpdated(LocalDateTime.of(2024, 1, 11, 9, 6));
        dto.setRecdeleted(true);

        return dto;
    }

    /**
     * Creates a populated FolderDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createAnotherFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
        dto.setDescr("descrValue2");
        dto.setUri("uriValue2");
        dto.setDateCreated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setLastUpdated(LocalDateTime.of(2024, 2, 12, 10, 7));
        dto.setRecdeleted(false);

        return dto;
    }

    /**
     * Creates a populated FolderDto fixture for mapper tests.
     *
     * @return populated dto fixture
     */
    private FolderDto createPatchFolderDto() {
        FolderDto dto = new FolderDto();
        dto.setDescr("descrValue3");
        dto.setDateCreated(LocalDateTime.of(2024, 3, 13, 11, 8));
        dto.setRecdeleted(true);

        return dto;
    }

}
