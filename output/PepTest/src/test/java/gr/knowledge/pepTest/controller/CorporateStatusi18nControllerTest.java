package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusi18nDto;
import gr.knowledge.pepTest.service.CorporateStatusi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
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
@WebMvcTest(CorporateStatusi18nController.class)
class CorporateStatusi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CorporateStatusi18nService corporateStatusi18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(corporateStatusi18nService.getAllCorporateStatusi18ns()).willReturn(List.of(new CorporateStatusi18nDto()));

        mockMvc.perform(get("/api/corporate-statusi18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusi18nService).getAllCorporateStatusi18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusi18nService.getCorporateStatusi18nById(corporateStatusId, languageId)).willReturn(new CorporateStatusi18nDto());

        mockMvc.perform(get("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusi18nService).getCorporateStatusi18nById(corporateStatusId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusi18nService.getCorporateStatusi18nById(corporateStatusId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatusi18n")
                        .message("CorporateStatusi18n not found")
                        .build());

        mockMvc.perform(get("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CorporateStatusi18nDto requestDto = createValidCreateCorporateStatusi18nDto();
        CorporateStatusi18nDto responseDto = new CorporateStatusi18nDto();
        given(corporateStatusi18nService.createCorporateStatusi18n(any(CorporateStatusi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/corporate-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusi18nService).createCorporateStatusi18n(any(CorporateStatusi18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CorporateStatusi18nDto requestDto = createValidCreateCorporateStatusi18nDto();
        requestDto.setDescription(null);

        mockMvc.perform(post("/api/corporate-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CorporateStatusi18nDto requestDto = createValidCreateCorporateStatusi18nDto();
        given(corporateStatusi18nService.createCorporateStatusi18n(any(CorporateStatusi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CorporateStatusi18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/corporate-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nDto requestDto = new CorporateStatusi18nDto();
        CorporateStatusi18nDto responseDto = new CorporateStatusi18nDto();
        given(corporateStatusi18nService.updateCorporateStatusi18n(eq(corporateStatusId), eq(languageId), any(CorporateStatusi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusi18nService).updateCorporateStatusi18n(eq(corporateStatusId), eq(languageId), any(CorporateStatusi18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusi18nDto requestDto = new CorporateStatusi18nDto();
        given(corporateStatusi18nService.updateCorporateStatusi18n(eq(corporateStatusId), eq(languageId), any(CorporateStatusi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatusi18n")
                        .message("CorporateStatusi18n not found")
                        .build());

        mockMvc.perform(patch("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(corporateStatusi18nService).deleteCorporateStatusi18n(corporateStatusId, languageId);

        mockMvc.perform(delete("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId))
                .andExpect(status().isNoContent());

        verify(corporateStatusi18nService).deleteCorporateStatusi18n(corporateStatusId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatusi18n")
                .message("CorporateStatusi18n not found")
                .build())
                .given(corporateStatusi18nService).deleteCorporateStatusi18n(corporateStatusId, languageId);

        mockMvc.perform(delete("/api/corporate-statusi18n/{corporateStatusId}/{languageId}", corporateStatusId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CorporateStatusi18n.
     *
     * @return populated create request dto
     */
    private CorporateStatusi18nDto createValidCreateCorporateStatusi18nDto() {
        CorporateStatusi18nDto dto = new CorporateStatusi18nDto();
        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
