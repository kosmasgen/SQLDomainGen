package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyProfession;
import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.repository.CompanyProfessionRepository;
import gr.knowledge.pepTest.mapper.CompanyProfessionMapper;
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
class CompanyProfessionServiceImplTest {

    @Mock
    private CompanyProfessionRepository companyProfessionRepository;

    @Mock
    private CompanyProfessionMapper companyProfessionMapper;

    @InjectMocks
    private CompanyProfessionServiceImpl companyProfessionService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyProfession.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyProfession", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyProfessionsIsCalled() {
        List<CompanyProfession> entityList = List.of(createSampleCompanyProfessionEntity(), createAnotherCompanyProfessionEntity());
        List<CompanyProfessionDto> dtoList = List.of(createSampleCompanyProfessionDto(), createAnotherCompanyProfessionDto());

        given(companyProfessionRepository.findAll()).willReturn(entityList);
        given(companyProfessionMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyProfessionDto> result = companyProfessionService.getAllCompanyProfessions();

        assertSame(dtoList, result);
        verify(companyProfessionRepository).findAll();
        verify(companyProfessionMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyProfessionByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfession companyProfession = createSampleCompanyProfessionEntity();
        CompanyProfessionDto companyProfessionDto = createSampleCompanyProfessionDto();

        given(companyProfessionRepository.findById(id)).willReturn(Optional.of(companyProfession));
        given(companyProfessionMapper.toDTO(companyProfession)).willReturn(companyProfessionDto);

        CompanyProfessionDto result = companyProfessionService.getCompanyProfessionById(id);

        assertSame(companyProfessionDto, result);
        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionMapper).toDTO(companyProfession);
    }

    @Test
    void shouldThrowWhenGetCompanyProfessionByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfessionService.getCompanyProfessionById(id));

        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionMapper, never()).toDTO(any(CompanyProfession.class));
    }

    @Test
    void shouldCreateCompanyProfessionWhenCreateCompanyProfessionIsCalled() {
        CompanyProfessionDto requestDto = createSampleCompanyProfessionDto();
        CompanyProfession mappedEntity = createSampleCompanyProfessionEntity();
        CompanyProfession savedEntity = createAnotherCompanyProfessionEntity();
        CompanyProfessionDto responseDto = createAnotherCompanyProfessionDto();

        given(companyProfessionMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyProfessionRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyProfessionMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfessionDto result = companyProfessionService.createCompanyProfession(requestDto);

        assertSame(responseDto, result);
        verify(companyProfessionMapper).toEntity(requestDto);
        verify(companyProfessionRepository).save(mappedEntity);
        verify(companyProfessionMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyProfessionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfessionDto requestDto = createPatchCompanyProfessionDto();
        CompanyProfession existingEntity = createSampleCompanyProfessionEntity();
        CompanyProfession savedEntity = createAnotherCompanyProfessionEntity();
        CompanyProfessionDto responseDto = createAnotherCompanyProfessionDto();

        given(companyProfessionRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyProfessionRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyProfessionMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyProfessionDto result = companyProfessionService.updateCompanyProfession(id, requestDto);

        assertSame(responseDto, result);
        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionMapper).partialUpdate(existingEntity, requestDto);
        verify(companyProfessionRepository).save(existingEntity);
        verify(companyProfessionMapper).toDTO(savedEntity);
        verify(companyProfessionMapper, never()).toEntity(any(CompanyProfessionDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyProfessionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfessionDto requestDto = createPatchCompanyProfessionDto();

        given(companyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfessionService.updateCompanyProfession(id, requestDto));

        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionMapper, never()).partialUpdate(any(), any());
        verify(companyProfessionRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyProfessionWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyProfession existingEntity = createSampleCompanyProfessionEntity();

        given(companyProfessionRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyProfessionService.deleteCompanyProfession(id);

        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyProfessionCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyProfessionRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyProfessionService.deleteCompanyProfession(id));

        verify(companyProfessionRepository).findById(id);
        verify(companyProfessionRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyProfession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfession createSampleCompanyProfessionEntity() {
        CompanyProfession entity = new CompanyProfession();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(1);
        entity.setChamberCompanyProfessionId(1);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setRecdeleted(true);
        entity.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiId(new BigInteger("1"));
        entity.setGemiDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setGemiLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyProfession fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyProfession createAnotherCompanyProfessionEntity() {
        CompanyProfession entity = new CompanyProfession();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setChamberId(2);
        entity.setChamberCompanyProfessionId(2);
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setRecdeleted(false);
        entity.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiId(new BigInteger("2"));
        entity.setGemiDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setGemiLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return entity;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createSampleCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(1);
        dto.setChamberCompanyProfessionId(1);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setToDate(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiId(new BigInteger("1"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createAnotherCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setChamberId(2);
        dto.setChamberCompanyProfessionId(2);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setRecdeleted(false);
        dto.setToDate(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiId(new BigInteger("2"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));

        return dto;
    }

    /**
     * Creates a populated CompanyProfessionDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyProfessionDto createPatchCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setChamberId(3);
        dto.setChamberCompanyProfessionId(3);
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setFromDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setRecdeleted(true);
        dto.setToDate(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiId(new BigInteger("3"));
        dto.setGemiDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setGemiLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));

        return dto;
    }

}
