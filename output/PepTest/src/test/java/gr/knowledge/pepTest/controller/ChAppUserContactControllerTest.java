package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.service.ChAppUserContactService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.ChamberAppUserDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ChAppUserContactController.class)
class ChAppUserContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChAppUserContactService chAppUserContactService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(chAppUserContactService.getAllChAppUserContacts()).willReturn(List.of(new ChAppUserContactDto()));

        mockMvc.perform(get("/api/ch-app-user-contact"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactService).getAllChAppUserContacts();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chAppUserContactService.getChAppUserContactById(id)).willReturn(new ChAppUserContactDto());

        mockMvc.perform(get("/api/ch-app-user-contact/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactService).getChAppUserContactById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(chAppUserContactService.getChAppUserContactById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChAppUserContact")
                        .message("ChAppUserContact not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/ch-app-user-contact/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ChAppUserContactDto requestDto = createValidCreateChAppUserContactDto();
        ChAppUserContactDto responseDto = new ChAppUserContactDto();
        given(chAppUserContactService.createChAppUserContact(any(ChAppUserContactDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/ch-app-user-contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactService).createChAppUserContact(any(ChAppUserContactDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ChAppUserContactDto requestDto = createValidCreateChAppUserContactDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/ch-app-user-contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ChAppUserContactDto requestDto = createValidCreateChAppUserContactDto();
        given(chAppUserContactService.createChAppUserContact(any(ChAppUserContactDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ChAppUserContact")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/ch-app-user-contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChAppUserContactDto requestDto = new ChAppUserContactDto();
        ChAppUserContactDto responseDto = new ChAppUserContactDto();
        given(chAppUserContactService.updateChAppUserContact(eq(id), any(ChAppUserContactDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/ch-app-user-contact/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactService).updateChAppUserContact(eq(id), any(ChAppUserContactDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ChAppUserContactDto requestDto = new ChAppUserContactDto();
        given(chAppUserContactService.updateChAppUserContact(eq(id), any(ChAppUserContactDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChAppUserContact")
                        .message("ChAppUserContact not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/ch-app-user-contact/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(chAppUserContactService).deleteChAppUserContact(id);

        mockMvc.perform(delete("/api/ch-app-user-contact/{id}", id))
                .andExpect(status().isNoContent());

        verify(chAppUserContactService).deleteChAppUserContact(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChAppUserContact")
                .message("ChAppUserContact not found with id: " + id)
                .build())
                .given(chAppUserContactService).deleteChAppUserContact(id);

        mockMvc.perform(delete("/api/ch-app-user-contact/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ChAppUserContact.
     *
     * @return populated create request dto
     */
    private ChAppUserContactDto createValidCreateChAppUserContactDto() {
        ChAppUserContactDto dto = new ChAppUserContactDto();
        dto.setChamberAppUser(new ChamberAppUserDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
