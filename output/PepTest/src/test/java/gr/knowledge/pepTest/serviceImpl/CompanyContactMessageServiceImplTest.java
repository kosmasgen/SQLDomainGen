package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.CompanyContactMessage;
import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.repository.CompanyContactMessageRepository;
import gr.knowledge.pepTest.mapper.CompanyContactMessageMapper;
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
class CompanyContactMessageServiceImplTest {

    @Mock
    private CompanyContactMessageRepository companyContactMessageRepository;

    @Mock
    private CompanyContactMessageMapper companyContactMessageMapper;

    @InjectMocks
    private CompanyContactMessageServiceImpl companyContactMessageService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for CompanyContactMessage.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("CompanyContactMessage", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllCompanyContactMessagesIsCalled() {
        List<CompanyContactMessage> entityList = List.of(createSampleCompanyContactMessageEntity(), createAnotherCompanyContactMessageEntity());
        List<CompanyContactMessageDto> dtoList = List.of(createSampleCompanyContactMessageDto(), createAnotherCompanyContactMessageDto());

        given(companyContactMessageRepository.findAll()).willReturn(entityList);
        given(companyContactMessageMapper.toDTOList(entityList)).willReturn(dtoList);

        List<CompanyContactMessageDto> result = companyContactMessageService.getAllCompanyContactMessages();

        assertSame(dtoList, result);
        verify(companyContactMessageRepository).findAll();
        verify(companyContactMessageMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetCompanyContactMessageByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessage companyContactMessage = createSampleCompanyContactMessageEntity();
        CompanyContactMessageDto companyContactMessageDto = createSampleCompanyContactMessageDto();

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.of(companyContactMessage));
        given(companyContactMessageMapper.toDTO(companyContactMessage)).willReturn(companyContactMessageDto);

        CompanyContactMessageDto result = companyContactMessageService.getCompanyContactMessageById(id);

        assertSame(companyContactMessageDto, result);
        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageMapper).toDTO(companyContactMessage);
    }

    @Test
    void shouldThrowWhenGetCompanyContactMessageByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyContactMessageService.getCompanyContactMessageById(id));

        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageMapper, never()).toDTO(any(CompanyContactMessage.class));
    }

    @Test
    void shouldCreateCompanyContactMessageWhenCreateCompanyContactMessageIsCalled() {
        CompanyContactMessageDto requestDto = createSampleCompanyContactMessageDto();
        CompanyContactMessage mappedEntity = createSampleCompanyContactMessageEntity();
        CompanyContactMessage savedEntity = createAnotherCompanyContactMessageEntity();
        CompanyContactMessageDto responseDto = createAnotherCompanyContactMessageDto();

        given(companyContactMessageMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(companyContactMessageRepository.save(mappedEntity)).willReturn(savedEntity);
        given(companyContactMessageMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyContactMessageDto result = companyContactMessageService.createCompanyContactMessage(requestDto);

        assertSame(responseDto, result);
        verify(companyContactMessageMapper).toEntity(requestDto);
        verify(companyContactMessageRepository).save(mappedEntity);
        verify(companyContactMessageMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateCompanyContactMessageWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessageDto requestDto = createPatchCompanyContactMessageDto();
        CompanyContactMessage existingEntity = createSampleCompanyContactMessageEntity();
        CompanyContactMessage savedEntity = createAnotherCompanyContactMessageEntity();
        CompanyContactMessageDto responseDto = createAnotherCompanyContactMessageDto();

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(companyContactMessageRepository.save(existingEntity)).willReturn(savedEntity);
        given(companyContactMessageMapper.toDTO(savedEntity)).willReturn(responseDto);

        CompanyContactMessageDto result = companyContactMessageService.updateCompanyContactMessage(id, requestDto);

        assertSame(responseDto, result);
        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageMapper).partialUpdate(existingEntity, requestDto);
        verify(companyContactMessageRepository).save(existingEntity);
        verify(companyContactMessageMapper).toDTO(savedEntity);
        verify(companyContactMessageMapper, never()).toEntity(any(CompanyContactMessageDto.class));
    }

    @Test
    void shouldThrowWhenUpdateCompanyContactMessageCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessageDto requestDto = createPatchCompanyContactMessageDto();

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyContactMessageService.updateCompanyContactMessage(id, requestDto));

        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageMapper, never()).partialUpdate(any(), any());
        verify(companyContactMessageRepository, never()).save(any());
    }

    @Test
    void shouldDeleteCompanyContactMessageWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        CompanyContactMessage existingEntity = createSampleCompanyContactMessageEntity();

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.of(existingEntity));

        companyContactMessageService.deleteCompanyContactMessage(id);

        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteCompanyContactMessageCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(companyContactMessageRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> companyContactMessageService.deleteCompanyContactMessage(id));

        verify(companyContactMessageRepository).findById(id);
        verify(companyContactMessageRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated CompanyContactMessage fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyContactMessage createSampleCompanyContactMessageEntity() {
        CompanyContactMessage entity = new CompanyContactMessage();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setFullName("fullName-value-1");
        entity.setSenderEmail("senderEmail-value-1");
        entity.setSubject("subject-value-1");
        entity.setMessage("message-value-1");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setCompanyId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

        return entity;
    }

    /**
     * Creates a populated CompanyContactMessage fixture for service tests.
     *
     * @return populated entity fixture
     */
    private CompanyContactMessage createAnotherCompanyContactMessageEntity() {
        CompanyContactMessage entity = new CompanyContactMessage();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setFullName("fullName-value-2");
        entity.setSenderEmail("senderEmail-value-2");
        entity.setSubject("subject-value-2");
        entity.setMessage("message-value-2");
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setCompanyId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));

        return entity;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createSampleCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setFullName("fullName-value-1");
        dto.setSenderEmail("senderEmail-value-1");
        dto.setSubject("subject-value-1");
        dto.setMessage("message-value-1");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setCompanyId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createAnotherCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setFullName("fullName-value-2");
        dto.setSenderEmail("senderEmail-value-2");
        dto.setSubject("subject-value-2");
        dto.setMessage("message-value-2");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setCompanyId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

    /**
     * Creates a populated CompanyContactMessageDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private CompanyContactMessageDto createPatchCompanyContactMessageDto() {
        CompanyContactMessageDto dto = new CompanyContactMessageDto();
        dto.setFullName("fullName-value-3");
        dto.setSenderEmail("senderEmail-value-3");
        dto.setSubject("subject-value-3");
        dto.setMessage("message-value-3");
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setCompanyId(UUID.fromString("323e4567-e89b-12d3-a456-426614174000"));

        return dto;
    }

}
