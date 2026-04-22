package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfileI18nDto;
import gr.knowledge.pepTest.service.CompanyProfileI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
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
@WebMvcTest(CompanyProfileI18nController.class)
class CompanyProfileI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyProfileI18nService companyProfileI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyProfileI18nService.getAllCompanyProfileI18ns()).willReturn(List.of(new CompanyProfileI18nDto()));

        mockMvc.perform(get("/api/company-profile-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfileI18nService).getAllCompanyProfileI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyProfileI18nService.getCompanyProfileI18nById(companyProfileId, languageId)).willReturn(new CompanyProfileI18nDto());

        mockMvc.perform(get("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfileI18nService).getCompanyProfileI18nById(companyProfileId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyProfileI18nService.getCompanyProfileI18nById(companyProfileId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyProfileI18n")
                        .message("CompanyProfileI18n not found")
                        .build());

        mockMvc.perform(get("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        CompanyProfileI18nDto responseDto = new CompanyProfileI18nDto();
        given(companyProfileI18nService.createCompanyProfileI18n(any(CompanyProfileI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-profile-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfileI18nService).createCompanyProfileI18n(any(CompanyProfileI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        requestDto.setCompanyProfile(null);

        mockMvc.perform(post("/api/company-profile-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        given(companyProfileI18nService.createCompanyProfileI18n(any(CompanyProfileI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyProfileI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-profile-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nDto requestDto = new CompanyProfileI18nDto();

        mockMvc.perform(patch("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        CompanyProfileI18nDto responseDto = new CompanyProfileI18nDto();
        given(companyProfileI18nService.updateCompanyProfileI18n(eq(companyProfileId), eq(languageId), any(CompanyProfileI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfileI18nService).updateCompanyProfileI18n(eq(companyProfileId), eq(languageId), any(CompanyProfileI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        given(companyProfileI18nService.updateCompanyProfileI18n(eq(companyProfileId), eq(languageId), any(CompanyProfileI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyProfileI18n")
                        .message("CompanyProfileI18n not found")
                        .build());

        mockMvc.perform(patch("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyProfileI18nDto requestDto = createValidCreateCompanyProfileI18nDto();
        given(companyProfileI18nService.updateCompanyProfileI18n(eq(companyProfileId), eq(languageId), any(CompanyProfileI18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(companyProfileI18nService).deleteCompanyProfileI18n(companyProfileId, languageId);

        mockMvc.perform(delete("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId))
                .andExpect(status().isNoContent());

        verify(companyProfileI18nService).deleteCompanyProfileI18n(companyProfileId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyProfileId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyProfileI18n")
                .message("CompanyProfileI18n not found")
                .build())
                .given(companyProfileI18nService).deleteCompanyProfileI18n(companyProfileId, languageId);

        mockMvc.perform(delete("/api/company-profile-i18n/{companyProfileId}/{languageId}", companyProfileId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyProfileI18n.
     *
     * @return populated create request dto
     */
    private CompanyProfileI18nDto createValidCreateCompanyProfileI18nDto() {
        CompanyProfileI18nDto dto = new CompanyProfileI18nDto();
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecDeleted(true);

        return dto;
    }

}
