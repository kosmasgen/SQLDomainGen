package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ChAppUserContactI18nDto;
import gr.knowledge.pepTest.service.ChAppUserContactI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.ChAppUserContactDto;
import gr.knowledge.pepTest.dto.LanguagesDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

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
@WebMvcTest(ChAppUserContactI18nController.class)
class ChAppUserContactI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ChAppUserContactI18nService chAppUserContactI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(chAppUserContactI18nService.getAllChAppUserContactI18ns()).willReturn(List.of(new ChAppUserContactI18nDto()));

        mockMvc.perform(get("/api/ch-app-user-contact-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactI18nService).getAllChAppUserContactI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(chAppUserContactI18nService.getChAppUserContactI18nById(chAppUserContactId, languageId)).willReturn(new ChAppUserContactI18nDto());

        mockMvc.perform(get("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactI18nService).getChAppUserContactI18nById(chAppUserContactId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(chAppUserContactI18nService.getChAppUserContactI18nById(chAppUserContactId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChAppUserContactI18n")
                        .message("ChAppUserContactI18n not found")
                        .build());

        mockMvc.perform(get("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        ChAppUserContactI18nDto responseDto = new ChAppUserContactI18nDto();
        given(chAppUserContactI18nService.createChAppUserContactI18n(any(ChAppUserContactI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/ch-app-user-contact-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactI18nService).createChAppUserContactI18n(any(ChAppUserContactI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        requestDto.setChAppUserContact(null);

        mockMvc.perform(post("/api/ch-app-user-contact-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        given(chAppUserContactI18nService.createChAppUserContactI18n(any(ChAppUserContactI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ChAppUserContactI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/ch-app-user-contact-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nDto requestDto = new ChAppUserContactI18nDto();

        mockMvc.perform(patch("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        ChAppUserContactI18nDto responseDto = new ChAppUserContactI18nDto();
        given(chAppUserContactI18nService.updateChAppUserContactI18n(eq(chAppUserContactId), eq(languageId), any(ChAppUserContactI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(chAppUserContactI18nService).updateChAppUserContactI18n(eq(chAppUserContactId), eq(languageId), any(ChAppUserContactI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        given(chAppUserContactI18nService.updateChAppUserContactI18n(eq(chAppUserContactId), eq(languageId), any(ChAppUserContactI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ChAppUserContactI18n")
                        .message("ChAppUserContactI18n not found")
                        .build());

        mockMvc.perform(patch("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ChAppUserContactI18nDto requestDto = createValidCreateChAppUserContactI18nDto();
        given(chAppUserContactI18nService.updateChAppUserContactI18n(eq(chAppUserContactId), eq(languageId), any(ChAppUserContactI18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(chAppUserContactI18nService).deleteChAppUserContactI18n(chAppUserContactId, languageId);

        mockMvc.perform(delete("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId))
                .andExpect(status().isNoContent());

        verify(chAppUserContactI18nService).deleteChAppUserContactI18n(chAppUserContactId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID chAppUserContactId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ChAppUserContactI18n")
                .message("ChAppUserContactI18n not found")
                .build())
                .given(chAppUserContactI18nService).deleteChAppUserContactI18n(chAppUserContactId, languageId);

        mockMvc.perform(delete("/api/ch-app-user-contact-i18n/{chAppUserContactId}/{languageId}", chAppUserContactId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ChAppUserContactI18n.
     *
     * @return populated create request dto
     */
    private ChAppUserContactI18nDto createValidCreateChAppUserContactI18nDto() {
        ChAppUserContactI18nDto dto = new ChAppUserContactI18nDto();
        dto.setChAppUserContact(new ChAppUserContactDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
