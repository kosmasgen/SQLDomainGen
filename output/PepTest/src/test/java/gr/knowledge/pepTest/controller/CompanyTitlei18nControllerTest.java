package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitlei18nDto;
import gr.knowledge.pepTest.service.CompanyTitlei18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyTitleDto;
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
@WebMvcTest(CompanyTitlei18nController.class)
class CompanyTitlei18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyTitlei18nService companyTitlei18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyTitlei18nService.getAllCompanyTitlei18ns()).willReturn(List.of(new CompanyTitlei18nDto()));

        mockMvc.perform(get("/api/company-titlei18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitlei18nService).getAllCompanyTitlei18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        given(companyTitlei18nService.getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId)).willReturn(new CompanyTitlei18nDto());

        mockMvc.perform(get("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitlei18nService).getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        given(companyTitlei18nService.getCompanyTitlei18nById(companyTitleId, languageId, chamberI18nId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyTitlei18n")
                        .message("CompanyTitlei18n not found")
                        .build());

        mockMvc.perform(get("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        CompanyTitlei18nDto responseDto = new CompanyTitlei18nDto();
        given(companyTitlei18nService.createCompanyTitlei18n(any(CompanyTitlei18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitlei18nService).createCompanyTitlei18n(any(CompanyTitlei18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        requestDto.setCompanyTitle(null);

        mockMvc.perform(post("/api/company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        given(companyTitlei18nService.createCompanyTitlei18n(any(CompanyTitlei18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyTitlei18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        CompanyTitlei18nDto requestDto = new CompanyTitlei18nDto();

        mockMvc.perform(patch("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        CompanyTitlei18nDto responseDto = new CompanyTitlei18nDto();
        given(companyTitlei18nService.updateCompanyTitlei18n(eq(companyTitleId), eq(languageId), eq(chamberI18nId), any(CompanyTitlei18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitlei18nService).updateCompanyTitlei18n(eq(companyTitleId), eq(languageId), eq(chamberI18nId), any(CompanyTitlei18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        given(companyTitlei18nService.updateCompanyTitlei18n(eq(companyTitleId), eq(languageId), eq(chamberI18nId), any(CompanyTitlei18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyTitlei18n")
                        .message("CompanyTitlei18n not found")
                        .build());

        mockMvc.perform(patch("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        CompanyTitlei18nDto requestDto = createValidCreateCompanyTitlei18nDto();
        given(companyTitlei18nService.updateCompanyTitlei18n(eq(companyTitleId), eq(languageId), eq(chamberI18nId), any(CompanyTitlei18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        willDoNothing().given(companyTitlei18nService).deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId);

        mockMvc.perform(delete("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId))
                .andExpect(status().isNoContent());

        verify(companyTitlei18nService).deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyTitleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");
        Integer chamberI18nId = 3;

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyTitlei18n")
                .message("CompanyTitlei18n not found")
                .build())
                .given(companyTitlei18nService).deleteCompanyTitlei18n(companyTitleId, languageId, chamberI18nId);

        mockMvc.perform(delete("/api/company-titlei18n/{companyTitleId}/{languageId}/{chamberI18nId}", companyTitleId, languageId, chamberI18nId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyTitlei18n.
     *
     * @return populated create request dto
     */
    private CompanyTitlei18nDto createValidCreateCompanyTitlei18nDto() {
        CompanyTitlei18nDto dto = new CompanyTitlei18nDto();
        dto.setCompanyTitle(new CompanyTitleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
