package gr.knowledge.pepTest.serviceImpl;

import gr.knowledge.pepTest.entity.ChAppUserContact;
import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;
import gr.knowledge.pepTest.repository.ChAppUserContactRepository;
import gr.knowledge.pepTest.mapper.ChAppUserContactMapper;
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
class ChAppUserContactServiceImplTest {

    @Mock
    private ChAppUserContactRepository chAppUserContactRepository;

    @Mock
    private ChAppUserContactMapper chAppUserContactMapper;

    @InjectMocks
    private ChAppUserContactServiceImpl chAppUserContactService;

    /**
     * Asserts that a NOT FOUND GeneratedRuntimeException is thrown for ChAppUserContact.
     *
     * @param executable executable under test
     */
    private void assertNotFound(Runnable executable) {
        GeneratedRuntimeException exception =
                assertThrows(GeneratedRuntimeException.class, executable::run);
        assertEquals(ErrorCodes.NOT_FOUND, exception.getCode());
        assertEquals("ChAppUserContact", exception.getEntity());
    }

    @Test
    void shouldReturnMappedDtoListWhenGetAllChAppUserContactsIsCalled() {
        List<ChAppUserContact> entityList = List.of(createSampleChAppUserContactEntity(), createAnotherChAppUserContactEntity());
        List<ChAppUserContactDto> dtoList = List.of(createSampleChAppUserContactDto(), createAnotherChAppUserContactDto());

        given(chAppUserContactRepository.findAll()).willReturn(entityList);
        given(chAppUserContactMapper.toDTOList(entityList)).willReturn(dtoList);

        List<ChAppUserContactDto> result = chAppUserContactService.getAllChAppUserContacts();

        assertSame(dtoList, result);
        verify(chAppUserContactRepository).findAll();
        verify(chAppUserContactMapper).toDTOList(entityList);
    }

    @Test
    void shouldReturnMappedDtoWhenGetChAppUserContactByIdIsCalled() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContact chAppUserContact = createSampleChAppUserContactEntity();
        ChAppUserContactDto chAppUserContactDto = createSampleChAppUserContactDto();

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.of(chAppUserContact));
        given(chAppUserContactMapper.toDTO(chAppUserContact)).willReturn(chAppUserContactDto);

        ChAppUserContactDto result = chAppUserContactService.getChAppUserContactById(id);

        assertSame(chAppUserContactDto, result);
        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactMapper).toDTO(chAppUserContact);
    }

    @Test
    void shouldThrowWhenGetChAppUserContactByIdCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactService.getChAppUserContactById(id));

        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactMapper, never()).toDTO(any(ChAppUserContact.class));
    }

    @Test
    void shouldCreateChAppUserContactWhenCreateChAppUserContactIsCalled() {
        ChAppUserContactDto requestDto = createSampleChAppUserContactDto();
        ChAppUserContact mappedEntity = createSampleChAppUserContactEntity();
        ChAppUserContact savedEntity = createAnotherChAppUserContactEntity();
        ChAppUserContactDto responseDto = createAnotherChAppUserContactDto();

        given(chAppUserContactMapper.toEntity(requestDto)).willReturn(mappedEntity);
        given(chAppUserContactRepository.save(mappedEntity)).willReturn(savedEntity);
        given(chAppUserContactMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChAppUserContactDto result = chAppUserContactService.createChAppUserContact(requestDto);

        assertSame(responseDto, result);
        verify(chAppUserContactMapper).toEntity(requestDto);
        verify(chAppUserContactRepository).save(mappedEntity);
        verify(chAppUserContactMapper).toDTO(savedEntity);
    }

    @Test
    void shouldUpdateChAppUserContactWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactDto requestDto = createPatchChAppUserContactDto();
        ChAppUserContact existingEntity = createSampleChAppUserContactEntity();
        ChAppUserContact savedEntity = createAnotherChAppUserContactEntity();
        ChAppUserContactDto responseDto = createAnotherChAppUserContactDto();

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.of(existingEntity));
        given(chAppUserContactRepository.save(existingEntity)).willReturn(savedEntity);
        given(chAppUserContactMapper.toDTO(savedEntity)).willReturn(responseDto);

        ChAppUserContactDto result = chAppUserContactService.updateChAppUserContact(id, requestDto);

        assertSame(responseDto, result);
        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactMapper).partialUpdate(existingEntity, requestDto);
        verify(chAppUserContactRepository).save(existingEntity);
        verify(chAppUserContactMapper).toDTO(savedEntity);
        verify(chAppUserContactMapper, never()).toEntity(any(ChAppUserContactDto.class));
    }

    @Test
    void shouldThrowWhenUpdateChAppUserContactCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactDto requestDto = createPatchChAppUserContactDto();

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactService.updateChAppUserContact(id, requestDto));

        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactMapper, never()).partialUpdate(any(), any());
        verify(chAppUserContactRepository, never()).save(any());
    }

    @Test
    void shouldDeleteChAppUserContactWhenEntityExists() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        ChAppUserContact existingEntity = createSampleChAppUserContactEntity();

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.of(existingEntity));

        chAppUserContactService.deleteChAppUserContact(id);

        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactRepository).deleteById(id);
    }

    @Test
    void shouldThrowWhenDeleteChAppUserContactCannotFindEntity() {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        given(chAppUserContactRepository.findById(id)).willReturn(Optional.empty());

        assertNotFound(() -> chAppUserContactService.deleteChAppUserContact(id));

        verify(chAppUserContactRepository).findById(id);
        verify(chAppUserContactRepository, never()).deleteById(id);
    }

    /**
     * Creates a populated ChAppUserContact fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContact createSampleChAppUserContactEntity() {
        ChAppUserContact entity = new ChAppUserContact();
        entity.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        entity.setPhone1("phone1-value-1");
        entity.setPhone2("phone2-value-1");
        entity.setMobile1("mobile1-value-1");
        entity.setMobile2("mobile2-value-1");
        entity.setEmail1("email1-value-1");
        entity.setEmail2("email2-value-1");
        entity.setUrl("url-value-1");
        entity.setZipCode("zipCode-value-1");
        entity.setLatitude("latitude-value-1");
        entity.setLongitude("longitude-value-1");
        entity.setStreetNumber("streetNumber-value-1");
        entity.setRecdeleted(true);
        entity.setListingUrl("listingUrl-value-1");
        entity.setEmail("email-value-1");
        entity.setMobile("mobile-value-1");

        return entity;
    }

    /**
     * Creates a populated ChAppUserContact fixture for service tests.
     *
     * @return populated entity fixture
     */
    private ChAppUserContact createAnotherChAppUserContactEntity() {
        ChAppUserContact entity = new ChAppUserContact();
        entity.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        entity.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        entity.setPhone1("phone1-value-2");
        entity.setPhone2("phone2-value-2");
        entity.setMobile1("mobile1-value-2");
        entity.setMobile2("mobile2-value-2");
        entity.setEmail1("email1-value-2");
        entity.setEmail2("email2-value-2");
        entity.setUrl("url-value-2");
        entity.setZipCode("zipCode-value-2");
        entity.setLatitude("latitude-value-2");
        entity.setLongitude("longitude-value-2");
        entity.setStreetNumber("streetNumber-value-2");
        entity.setRecdeleted(false);
        entity.setListingUrl("listingUrl-value-2");
        entity.setEmail("email-value-2");
        entity.setMobile("mobile-value-2");

        return entity;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createSampleChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 1, 10, 0, 0));
        dto.setChamberAppUser(new ChamberAppUserDto());
        dto.setPhone1("phone1-value-1");
        dto.setPhone2("phone2-value-1");
        dto.setMobile1("mobile1-value-1");
        dto.setMobile2("mobile2-value-1");
        dto.setEmail1("email1-value-1");
        dto.setEmail2("email2-value-1");
        dto.setUrl("url-value-1");
        dto.setZipCode("zipCode-value-1");
        dto.setLatitude("latitude-value-1");
        dto.setLongitude("longitude-value-1");
        dto.setStreetNumber("streetNumber-value-1");
        dto.setRecdeleted(true);
        dto.setListingUrl("listingUrl-value-1");
        dto.setEmail("email-value-1");
        dto.setMobile("mobile-value-1");

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createAnotherChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174000"));
        dto.setDateCreated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 2, 10, 0, 0));
        dto.setChamberAppUser(new ChamberAppUserDto());
        dto.setPhone1("phone1-value-2");
        dto.setPhone2("phone2-value-2");
        dto.setMobile1("mobile1-value-2");
        dto.setMobile2("mobile2-value-2");
        dto.setEmail1("email1-value-2");
        dto.setEmail2("email2-value-2");
        dto.setUrl("url-value-2");
        dto.setZipCode("zipCode-value-2");
        dto.setLatitude("latitude-value-2");
        dto.setLongitude("longitude-value-2");
        dto.setStreetNumber("streetNumber-value-2");
        dto.setRecdeleted(false);
        dto.setListingUrl("listingUrl-value-2");
        dto.setEmail("email-value-2");
        dto.setMobile("mobile-value-2");

        return dto;
    }

    /**
     * Creates a populated ChAppUserContactDto fixture for service tests.
     *
     * @return populated dto fixture
     */
    private ChAppUserContactDto createPatchChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setDateCreated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setLastUpdated(LocalDateTime.of(2025, 1, 3, 10, 0, 0));
        dto.setChamberAppUser(new ChamberAppUserDto());
        dto.setPhone1("phone1-value-3");
        dto.setPhone2("phone2-value-3");
        dto.setMobile1("mobile1-value-3");
        dto.setMobile2("mobile2-value-3");
        dto.setEmail1("email1-value-3");
        dto.setEmail2("email2-value-3");
        dto.setUrl("url-value-3");
        dto.setZipCode("zipCode-value-3");
        dto.setLatitude("latitude-value-3");
        dto.setLongitude("longitude-value-3");
        dto.setStreetNumber("streetNumber-value-3");
        dto.setRecdeleted(true);
        dto.setListingUrl("listingUrl-value-3");
        dto.setEmail("email-value-3");
        dto.setMobile("mobile-value-3");

        return dto;
    }

}
