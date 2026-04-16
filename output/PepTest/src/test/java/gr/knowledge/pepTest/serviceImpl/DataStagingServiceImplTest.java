package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.DataStaging;
import gr.knowledge.pepTest.dto.DataStagingDto;
import gr.knowledge.pepTest.repository.DataStagingRepository;
import gr.knowledge.pepTest.mapper.DataStagingMapper;
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
class DataStagingServiceImplTest {

    @Mock
    private DataStagingRepository dataStagingRepository;

    @Mock
    private DataStagingMapper dataStagingMapper;

    @InjectMocks
    private DataStagingServiceImpl dataStagingService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for DataStaging.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("DataStaging", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllDataStagingsIsCalled() {
        List<DataStaging> entityList = List.of(createSampleDataStagingEntity(), createAnotherDataStagingEntity());
        List<DataStagingDto> dtoList = List.of(createSampleDataStagingDto(), createAnotherDataStagingDto());

        given(dataStagingRepository.findAll()).willReturn(entityList);
        given(dataStagingMapper.toDTOList(entityList)).willReturn(dtoList);

        List<DataStagingDto> result = dataStagingService.getAllDataStagings();

        assertSame(dtoList, result);
        verify(dataStagingRepository).findAll();
        verify(dataStagingMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetDataStagingByIdIsCalled() {
        Long id = 1L;

        DataStaging dataStaging = createSampleDataStagingEntity();
        DataStagingDto dataStagingDto = createSampleDataStagingDto();

        given(dataStagingRepository.findById(id)).willReturn(Optional.of(dataStaging));
        given(dataStagingMapper.toDTO(dataStaging)).willReturn(dataStagingDto);

        DataStagingDto result = dataStagingService.getDataStagingById(id);

        assertSame(dataStagingDto, result);
        verify(dataStagingRepository).findById(id);
        verify(dataStagingMapper).toDTO(dataStaging);
    }

    @Test
    void shouldThrowWhenGetDataStagingByIdCannotFindEntity() {
        Long id = 1L;

        given(dataStagingRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> dataStagingService.getDataStagingById(id));

        verify(dataStagingRepository).findById(id);
        verify(dataStagingMapper, never()).toDTO(any(DataStaging.class));
    }

    @Test
    void shouldCreateDataStagingWhenCreateDataStagingIsCalled() {
        DataStagingDto requestDto = createSampleDataStagingDto();
        DataStaging mappedEntity = createSampleDataStagingEntity();
        DataStaging savedEntity = createAnotherDataStagingEntity();
        DataStagingDto responseDto = createAnotherDataStagingDto();

        given(dataStagingMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(dataStagingRepository.save(mappedEntity)).willReturn(savedEntity);
        given(dataStagingMapper.toDTO(savedEntity)).willReturn(responseDto);

        DataStagingDto result = dataStagingService.createDataStaging(requestDto);

        assertSame(responseDto, result);
        verify(dataStagingMapper).toEntity(requestDto);
        verify(dataStagingRepository).save(mappedEntity);
        verify(dataStagingMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateDataStagingWhenEntityExists() {
        Long id = 1L;

        DataStagingDto requestDto = createPatchDataStagingDto();
        DataStaging existingEntity = createSampleDataStagingEntity();
        DataStaging savedEntity = createAnotherDataStagingEntity();
        DataStagingDto responseDto = createAnotherDataStagingDto();

        given(dataStagingRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(dataStagingRepository.save(existingEntity)).willReturn(savedEntity);
        given(dataStagingMapper.toDTO(savedEntity)).willReturn(responseDto);

        DataStagingDto result = dataStagingService.updateDataStaging(id, requestDto);

        assertSame(responseDto, result);
        verify(dataStagingRepository).findById(id);
        verify(dataStagingMapper).partialUpdate(existingEntity, requestDto);
        verify(dataStagingRepository).save(existingEntity);
        verify(dataStagingMapper).toDTO(savedEntity);
        verify(dataStagingMapper, never()).toEntity(any(DataStagingDto.class));
    }

    @Test
    void shouldThrowWhenUpdateDataStagingCannotFindEntity() {
        Long id = 1L;

        DataStagingDto requestDto = createPatchDataStagingDto();

        given(dataStagingRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> dataStagingService.updateDataStaging(id, requestDto));

        verify(dataStagingRepository).findById(id);
        verify(dataStagingMapper, never()).partialUpdate(any(), any());
        verify(dataStagingRepository, never()).save(any());
    }

    @Test
    void shouldDeleteDataStagingWhenEntityExists() {
        Long id = 1L;

        DataStaging existingEntity = createSampleDataStagingEntity();

        given(dataStagingRepository.findById(id)).willReturn(Optional.of(existingEntity));

        dataStagingService.deleteDataStaging(id);

        verify(dataStagingRepository).findById(id);
        verify(dataStagingRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteDataStagingCannotFindEntity() {
        Long id = 1L;

        given(dataStagingRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> dataStagingService.deleteDataStaging(id));

        verify(dataStagingRepository).findById(id);
        verify(dataStagingRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated DataStaging fixture for service tests.
     *
     * @return populated entity fixture
     */
    private DataStaging createSampleDataStagingEntity() {
        DataStaging entity = new DataStaging();
        entity.setId(1L);
        entity.setLegacyTableName("legacyTableName-value-1");
        entity.setLegacyRecordId("legacyRecordId-value-1");
        entity.setRawData("rawData-value-1");
        entity.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setPulledAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setStatus("status-value-1");

        return entity;
    }

    /**
     * Creates a populated DataStaging fixture for service tests.
     *
     * @return populated entity fixture
     */
    private DataStaging createAnotherDataStagingEntity() {
        DataStaging entity = new DataStaging();
        entity.setId(2L);
        entity.setLegacyTableName("legacyTableName-value-2");
        entity.setLegacyRecordId("legacyRecordId-value-2");
        entity.setRawData("rawData-value-2");
        entity.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setPulledAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setStatus("status-value-2");

        return entity;
    }

    /**
     * Creates a populated DataStagingDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createSampleDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setId(1L);
        dto.setLegacyTableName("legacyTableName-value-1");
        dto.setLegacyRecordId("legacyRecordId-value-1");
        dto.setRawData("rawData-value-1");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setPulledAt(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setStatus("status-value-1");

        return dto;
    }

    /**
     * Creates a populated DataStagingDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createAnotherDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setId(2L);
        dto.setLegacyTableName("legacyTableName-value-2");
        dto.setLegacyRecordId("legacyRecordId-value-2");
        dto.setRawData("rawData-value-2");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setPulledAt(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setStatus("status-value-2");

        return dto;
    }

    /**
     * Creates a populated DataStagingDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private DataStagingDto createPatchDataStagingDto() {
        DataStagingDto dto = new DataStagingDto();
        dto.setLegacyTableName("legacyTableName-value-3");
        dto.setLegacyRecordId("legacyRecordId-value-3");
        dto.setRawData("rawData-value-3");
        dto.setLegacyUpdatedAt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setPulledAt(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setStatus("status-value-3");

        return dto;
    }

}
