package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindi18nDto;
import gr.knowledge.pepTest.service.ProfessionKindi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProfessionKindDto;

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
@WebMvcTest(ProfessionKindi18nController.class)
class ProfessionKindi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfessionKindi18nService professionKindi18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professionKindi18nService.getAllProfessionKindi18ns()).willReturn(List.of(new ProfessionKindi18nDto()));

        mockMvc.perform(get("/api/profession-kindi18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindi18nService).getAllProfessionKindi18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(professionKindi18nService.getProfessionKindi18nById(professionKindId, languageId)).willReturn(new ProfessionKindi18nDto());

        mockMvc.perform(get("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindi18nService).getProfessionKindi18nById(professionKindId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(professionKindi18nService.getProfessionKindi18nById(professionKindId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionKindi18n")
                        .message("ProfessionKindi18n not found")
                        .build());

        mockMvc.perform(get("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        ProfessionKindi18nDto responseDto = new ProfessionKindi18nDto();
        given(professionKindi18nService.createProfessionKindi18n(any(ProfessionKindi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/profession-kindi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindi18nService).createProfessionKindi18n(any(ProfessionKindi18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        requestDto.setProfessionKind(null);

        mockMvc.perform(post("/api/profession-kindi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        given(professionKindi18nService.createProfessionKindi18n(any(ProfessionKindi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ProfessionKindi18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/profession-kindi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nDto requestDto = new ProfessionKindi18nDto();

        mockMvc.perform(patch("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        ProfessionKindi18nDto responseDto = new ProfessionKindi18nDto();
        given(professionKindi18nService.updateProfessionKindi18n(eq(professionKindId), eq(languageId), any(ProfessionKindi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindi18nService).updateProfessionKindi18n(eq(professionKindId), eq(languageId), any(ProfessionKindi18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        given(professionKindi18nService.updateProfessionKindi18n(eq(professionKindId), eq(languageId), any(ProfessionKindi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionKindi18n")
                        .message("ProfessionKindi18n not found")
                        .build());

        mockMvc.perform(patch("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        ProfessionKindi18nDto requestDto = createValidCreateProfessionKindi18nDto();
        given(professionKindi18nService.updateProfessionKindi18n(eq(professionKindId), eq(languageId), any(ProfessionKindi18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(professionKindi18nService).deleteProfessionKindi18n(professionKindId, languageId);

        mockMvc.perform(delete("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId))
                .andExpect(status().isNoContent());

        verify(professionKindi18nService).deleteProfessionKindi18n(professionKindId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID professionKindId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionKindi18n")
                .message("ProfessionKindi18n not found")
                .build())
                .given(professionKindi18nService).deleteProfessionKindi18n(professionKindId, languageId);

        mockMvc.perform(delete("/api/profession-kindi18n/{professionKindId}/{languageId}", professionKindId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ProfessionKindi18n.
     *
     * @return populated create request dto
     */
    private ProfessionKindi18nDto createValidCreateProfessionKindi18nDto() {
        ProfessionKindi18nDto dto = new ProfessionKindi18nDto();
        dto.setProfessionKind(new ProfessionKindDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);
        dto.setDescription("A");

        return dto;
    }

}
