package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ExportCompany;
import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.repository.ExportCompanyRepository;
import gr.knowledge.pepTest.mapper.ExportCompanyMapper;
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
class ExportCompanyServiceImplTest {

    @Mock
    private ExportCompanyRepository exportCompanyRepository;

    @Mock
    private ExportCompanyMapper exportCompanyMapper;

    @InjectMocks
    private ExportCompanyServiceImpl exportCompanyService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ExportCompany.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ExportCompany", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllExportCompaniesIsCalled() {
        List<ExportCompany> entityList = List.of(createSampleExportCompanyEntity(), createAnotherExportCompanyEntity());
        List<ExportCompanyDto> dtoList = List.of(createSampleExportCompanyDto(), createAnotherExportCompanyDto());

        given(exportCompanyRepository.findAll()).willReturn(entityList);
        given(exportCompanyMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ExportCompanyDto> result = exportCompanyService.getAllExportCompanies();

        assertSame(dtoList, result);
        verify(exportCompanyRepository).findAll();
        verify(exportCompanyMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetExportCompanyByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompany exportCompany = createSampleExportCompanyEntity();
        ExportCompanyDto exportCompanyDto = createSampleExportCompanyDto();

        given(exportCompanyRepository.findById(id)).willReturn(Optional.of(exportCompany));
        given(exportCompanyMapper.toDTO(exportCompany)).willReturn(exportCompanyDto);

        ExportCompanyDto result = exportCompanyService.getExportCompanyById(id);

        assertSame(exportCompanyDto, result);
        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyMapper).toDTO(exportCompany);
    }

    @Test
    void shouldThrowWhenGetExportCompanyByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(exportCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompanyService.getExportCompanyById(id));

        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyMapper, never()).toDTO(any(ExportCompany.class));
    }

    @Test
    void shouldCreateExportCompanyWhenCreateExportCompanyIsCalled() {
        ExportCompanyDto requestDto = createSampleExportCompanyDto();
        ExportCompany mappedEntity = createSampleExportCompanyEntity();
        ExportCompany savedEntity = createAnotherExportCompanyEntity();
        ExportCompanyDto responseDto = createAnotherExportCompanyDto();

        given(exportCompanyMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(exportCompanyRepository.save(mappedEntity)).willReturn(savedEntity);
        given(exportCompanyMapper.toDTO(savedEntity)).willReturn(responseDto);

        ExportCompanyDto result = exportCompanyService.createExportCompany(requestDto);

        assertSame(responseDto, result);
        verify(exportCompanyMapper).toEntity(requestDto);
        verify(exportCompanyRepository).save(mappedEntity);
        verify(exportCompanyMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateExportCompanyWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompanyDto requestDto = createPatchExportCompanyDto();
        ExportCompany existingEntity = createSampleExportCompanyEntity();
        ExportCompany savedEntity = createAnotherExportCompanyEntity();
        ExportCompanyDto responseDto = createAnotherExportCompanyDto();

        given(exportCompanyRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(exportCompanyRepository.save(existingEntity)).willReturn(savedEntity);
        given(exportCompanyMapper.toDTO(savedEntity)).willReturn(responseDto);

        ExportCompanyDto result = exportCompanyService.updateExportCompany(id, requestDto);

        assertSame(responseDto, result);
        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyMapper).partialUpdate(existingEntity, requestDto);
        verify(exportCompanyRepository).save(existingEntity);
        verify(exportCompanyMapper).toDTO(savedEntity);
        verify(exportCompanyMapper, never()).toEntity(any(ExportCompanyDto.class));
    }

    @Test
    void shouldThrowWhenUpdateExportCompanyCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompanyDto requestDto = createPatchExportCompanyDto();

        given(exportCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompanyService.updateExportCompany(id, requestDto));

        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyMapper, never()).partialUpdate(any(), any());
        verify(exportCompanyRepository, never()).save(any());
    }

    @Test
    void shouldDeleteExportCompanyWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ExportCompany existingEntity = createSampleExportCompanyEntity();

        given(exportCompanyRepository.findById(id)).willReturn(Optional.of(existingEntity));

        exportCompanyService.deleteExportCompany(id);

        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteExportCompanyCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(exportCompanyRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> exportCompanyService.deleteExportCompany(id));

        verify(exportCompanyRepository).findById(id);
        verify(exportCompanyRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ExportCompany fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ExportCompany createSampleExportCompanyEntity() {
        ExportCompany entity = new ExportCompany();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setActive(true);
        entity.setEmeCode(1L);

        return entity;
    }

    /**
     * Creates a populated ExportCompany fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ExportCompany createAnotherExportCompanyEntity() {
        ExportCompany entity = new ExportCompany();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setActive(false);
        entity.setEmeCode(2L);

        return entity;
    }

    /**
     * Creates a populated ExportCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompanyDto createSampleExportCompanyDto() {
        ExportCompanyDto dto = new ExportCompanyDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setActive(true);
        dto.setEmeCode(1L);

        return dto;
    }

    /**
     * Creates a populated ExportCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompanyDto createAnotherExportCompanyDto() {
        ExportCompanyDto dto = new ExportCompanyDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setActive(false);
        dto.setEmeCode(2L);

        return dto;
    }

    /**
     * Creates a populated ExportCompanyDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ExportCompanyDto createPatchExportCompanyDto() {
        ExportCompanyDto dto = new ExportCompanyDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCompany(new CompanyDto());
        dto.setActive(true);
        dto.setEmeCode(3L);

        return dto;
    }

}
