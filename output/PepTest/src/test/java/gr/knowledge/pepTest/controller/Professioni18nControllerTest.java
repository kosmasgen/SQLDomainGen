package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Professioni18nDto;
import gr.knowledge.pepTest.service.Professioni18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.ProfessionDto;

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
@WebMvcTest(Professioni18nController.class)
class Professioni18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private Professioni18nService professioni18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professioni18nService.getAllProfessioni18ns()).willReturn(List.of(new Professioni18nDto()));

        mockMvc.perform(get("/api/professioni18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professioni18nService).getAllProfessioni18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(professioni18nService.getProfessioni18nById(professionId, languageId)).willReturn(new Professioni18nDto());

        mockMvc.perform(get("/api/professioni18n/{professionId}/{languageId}", professionId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professioni18nService).getProfessioni18nById(professionId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(professioni18nService.getProfessioni18nById(professionId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Professioni18n")
                        .message("Professioni18n not found")
                        .build());

        mockMvc.perform(get("/api/professioni18n/{professionId}/{languageId}", professionId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        Professioni18nDto requestDto = createValidCreateProfessioni18nDto();
        Professioni18nDto responseDto = new Professioni18nDto();
        given(professioni18nService.createProfessioni18n(any(Professioni18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/professioni18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professioni18nService).createProfessioni18n(any(Professioni18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        Professioni18nDto requestDto = createValidCreateProfessioni18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/professioni18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        Professioni18nDto requestDto = createValidCreateProfessioni18nDto();
        given(professioni18nService.createProfessioni18n(any(Professioni18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Professioni18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/professioni18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        Professioni18nDto requestDto = new Professioni18nDto();
        Professioni18nDto responseDto = new Professioni18nDto();
        given(professioni18nService.updateProfessioni18n(eq(professionId), eq(languageId), any(Professioni18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/professioni18n/{professionId}/{languageId}", professionId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professioni18nService).updateProfessioni18n(eq(professionId), eq(languageId), any(Professioni18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        Professioni18nDto requestDto = new Professioni18nDto();
        given(professioni18nService.updateProfessioni18n(eq(professionId), eq(languageId), any(Professioni18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Professioni18n")
                        .message("Professioni18n not found")
                        .build());

        mockMvc.perform(patch("/api/professioni18n/{professionId}/{languageId}", professionId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(professioni18nService).deleteProfessioni18n(professionId, languageId);

        mockMvc.perform(delete("/api/professioni18n/{professionId}/{languageId}", professionId, languageId))
                .andExpect(status().isNoContent());

        verify(professioni18nService).deleteProfessioni18n(professionId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID professionId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Professioni18n")
                .message("Professioni18n not found")
                .build())
                .given(professioni18nService).deleteProfessioni18n(professionId, languageId);

        mockMvc.perform(delete("/api/professioni18n/{professionId}/{languageId}", professionId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Professioni18n.
     *
     * @return populated create request dto
     */
    private Professioni18nDto createValidCreateProfessioni18nDto() {
        Professioni18nDto dto = new Professioni18nDto();
        dto.setProfession(new ProfessionDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);
        dto.setDescription("aaaaa");

        return dto;
    }

}
