package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.Companyi18nDto;
import gr.knowledge.pepTest.service.Companyi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
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
@WebMvcTest(Companyi18nController.class)
class Companyi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private Companyi18nService companyi18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyi18nService.getAllCompanyi18ns()).willReturn(List.of(new Companyi18nDto()));

        mockMvc.perform(get("/api/companyi18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyi18nService).getAllCompanyi18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        given(companyi18nService.getCompanyi18nById(companyId, languageId, chamberI18nId)).willReturn(new Companyi18nDto());

        mockMvc.perform(get("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyi18nService).getCompanyi18nById(companyId, languageId, chamberI18nId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        given(companyi18nService.getCompanyi18nById(companyId, languageId, chamberI18nId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Companyi18n")
                        .message("Companyi18n not found")
                        .build());

        mockMvc.perform(get("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        Companyi18nDto requestDto = createValidCreateCompanyi18nDto();
        Companyi18nDto responseDto = new Companyi18nDto();
        given(companyi18nService.createCompanyi18n(any(Companyi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyi18nService).createCompanyi18n(any(Companyi18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        Companyi18nDto requestDto = createValidCreateCompanyi18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        Companyi18nDto requestDto = createValidCreateCompanyi18nDto();
        given(companyi18nService.createCompanyi18n(any(Companyi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Companyi18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        Companyi18nDto requestDto = new Companyi18nDto();
        Companyi18nDto responseDto = new Companyi18nDto();
        given(companyi18nService.updateCompanyi18n(eq(companyId), eq(languageId), eq(chamberI18nId), any(Companyi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyi18nService).updateCompanyi18n(eq(companyId), eq(languageId), eq(chamberI18nId), any(Companyi18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        Companyi18nDto requestDto = new Companyi18nDto();
        given(companyi18nService.updateCompanyi18n(eq(companyId), eq(languageId), eq(chamberI18nId), any(Companyi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Companyi18n")
                        .message("Companyi18n not found")
                        .build());

        mockMvc.perform(patch("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        willDoNothing().given(companyi18nService).deleteCompanyi18n(companyId, languageId, chamberI18nId);

        mockMvc.perform(delete("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId))
                .andExpect(status().isNoContent());

        verify(companyi18nService).deleteCompanyi18n(companyId, languageId, chamberI18nId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Companyi18n")
                .message("Companyi18n not found")
                .build())
                .given(companyi18nService).deleteCompanyi18n(companyId, languageId, chamberI18nId);

        mockMvc.perform(delete("/api/companyi18n/{companyId}/{languageId}/{chamberI18nId}", companyId, languageId, chamberI18nId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Companyi18n.
     *
     * @return populated create request dto
     */
    private Companyi18nDto createValidCreateCompanyi18nDto() {
        Companyi18nDto dto = new Companyi18nDto();
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
