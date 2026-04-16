package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.MunicipalityI18nDto;
import gr.knowledge.pepTest.service.MunicipalityI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.MunicipalityDto;

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
@WebMvcTest(MunicipalityI18nController.class)
class MunicipalityI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private MunicipalityI18nService municipalityI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(municipalityI18nService.getAllMunicipalityI18ns()).willReturn(List.of(new MunicipalityI18nDto()));

        mockMvc.perform(get("/api/municipality-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(municipalityI18nService).getAllMunicipalityI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(municipalityI18nService.getMunicipalityI18nById(municipalityId, languageId)).willReturn(new MunicipalityI18nDto());

        mockMvc.perform(get("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(municipalityI18nService).getMunicipalityI18nById(municipalityId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(municipalityI18nService.getMunicipalityI18nById(municipalityId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("MunicipalityI18n")
                        .message("MunicipalityI18n not found")
                        .build());

        mockMvc.perform(get("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        MunicipalityI18nDto requestDto = createValidCreateMunicipalityI18nDto();
        MunicipalityI18nDto responseDto = new MunicipalityI18nDto();
        given(municipalityI18nService.createMunicipalityI18n(any(MunicipalityI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/municipality-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(municipalityI18nService).createMunicipalityI18n(any(MunicipalityI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        MunicipalityI18nDto requestDto = createValidCreateMunicipalityI18nDto();
        requestDto.setDescription(null);

        mockMvc.perform(post("/api/municipality-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        MunicipalityI18nDto requestDto = createValidCreateMunicipalityI18nDto();
        given(municipalityI18nService.createMunicipalityI18n(any(MunicipalityI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("MunicipalityI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/municipality-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nDto requestDto = new MunicipalityI18nDto();
        MunicipalityI18nDto responseDto = new MunicipalityI18nDto();
        given(municipalityI18nService.updateMunicipalityI18n(eq(municipalityId), eq(languageId), any(MunicipalityI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(municipalityI18nService).updateMunicipalityI18n(eq(municipalityId), eq(languageId), any(MunicipalityI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        MunicipalityI18nDto requestDto = new MunicipalityI18nDto();
        given(municipalityI18nService.updateMunicipalityI18n(eq(municipalityId), eq(languageId), any(MunicipalityI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("MunicipalityI18n")
                        .message("MunicipalityI18n not found")
                        .build());

        mockMvc.perform(patch("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(municipalityI18nService).deleteMunicipalityI18n(municipalityId, languageId);

        mockMvc.perform(delete("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId))
                .andExpect(status().isNoContent());

        verify(municipalityI18nService).deleteMunicipalityI18n(municipalityId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID municipalityId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("MunicipalityI18n")
                .message("MunicipalityI18n not found")
                .build())
                .given(municipalityI18nService).deleteMunicipalityI18n(municipalityId, languageId);

        mockMvc.perform(delete("/api/municipality-i18n/{municipalityId}/{languageId}", municipalityId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for MunicipalityI18n.
     *
     * @return populated create request dto
     */
    private MunicipalityI18nDto createValidCreateMunicipalityI18nDto() {
        MunicipalityI18nDto dto = new MunicipalityI18nDto();
        dto.setMunicipality(new MunicipalityDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
