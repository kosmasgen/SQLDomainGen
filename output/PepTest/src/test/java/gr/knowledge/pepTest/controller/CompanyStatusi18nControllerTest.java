package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusi18nDto;
import gr.knowledge.pepTest.service.CompanyStatusi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
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
@WebMvcTest(CompanyStatusi18nController.class)
class CompanyStatusi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyStatusi18nService companyStatusi18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyStatusi18nService.getAllCompanyStatusi18ns()).willReturn(List.of(new CompanyStatusi18nDto()));

        mockMvc.perform(get("/api/company-statusi18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusi18nService).getAllCompanyStatusi18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyStatusi18nService.getCompanyStatusi18nById(companyStatusId, languageId)).willReturn(new CompanyStatusi18nDto());

        mockMvc.perform(get("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusi18nService).getCompanyStatusi18nById(companyStatusId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyStatusi18nService.getCompanyStatusi18nById(companyStatusId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatusi18n")
                        .message("CompanyStatusi18n not found")
                        .build());

        mockMvc.perform(get("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        CompanyStatusi18nDto responseDto = new CompanyStatusi18nDto();
        given(companyStatusi18nService.createCompanyStatusi18n(any(CompanyStatusi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusi18nService).createCompanyStatusi18n(any(CompanyStatusi18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        requestDto.setCompanyStatus(null);

        mockMvc.perform(post("/api/company-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        given(companyStatusi18nService.createCompanyStatusi18n(any(CompanyStatusi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyStatusi18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-statusi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nDto requestDto = new CompanyStatusi18nDto();

        mockMvc.perform(patch("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        CompanyStatusi18nDto responseDto = new CompanyStatusi18nDto();
        given(companyStatusi18nService.updateCompanyStatusi18n(eq(companyStatusId), eq(languageId), any(CompanyStatusi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusi18nService).updateCompanyStatusi18n(eq(companyStatusId), eq(languageId), any(CompanyStatusi18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        given(companyStatusi18nService.updateCompanyStatusi18n(eq(companyStatusId), eq(languageId), any(CompanyStatusi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatusi18n")
                        .message("CompanyStatusi18n not found")
                        .build());

        mockMvc.perform(patch("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusi18nDto requestDto = createValidCreateCompanyStatusi18nDto();
        given(companyStatusi18nService.updateCompanyStatusi18n(eq(companyStatusId), eq(languageId), any(CompanyStatusi18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(companyStatusi18nService).deleteCompanyStatusi18n(companyStatusId, languageId);

        mockMvc.perform(delete("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId))
                .andExpect(status().isNoContent());

        verify(companyStatusi18nService).deleteCompanyStatusi18n(companyStatusId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatusi18n")
                .message("CompanyStatusi18n not found")
                .build())
                .given(companyStatusi18nService).deleteCompanyStatusi18n(companyStatusId, languageId);

        mockMvc.perform(delete("/api/company-statusi18n/{companyStatusId}/{languageId}", companyStatusId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyStatusi18n.
     *
     * @return populated create request dto
     */
    private CompanyStatusi18nDto createValidCreateCompanyStatusi18nDto() {
        CompanyStatusi18nDto dto = new CompanyStatusi18nDto();
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("A");
        dto.setRecdeleted(true);

        return dto;
    }

}
