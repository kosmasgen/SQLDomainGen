package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleI18nDto;
import gr.knowledge.pepTest.service.CompanyYpArticleI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
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
@WebMvcTest(CompanyYpArticleI18nController.class)
class CompanyYpArticleI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyYpArticleI18nService companyYpArticleI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyYpArticleI18nService.getAllCompanyYpArticleI18ns()).willReturn(List.of(new CompanyYpArticleI18nDto()));

        mockMvc.perform(get("/api/company-yp-article-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleI18nService).getAllCompanyYpArticleI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyYpArticleI18nService.getCompanyYpArticleI18nById(companyArticleId, languageId)).willReturn(new CompanyYpArticleI18nDto());

        mockMvc.perform(get("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleI18nService).getCompanyYpArticleI18nById(companyArticleId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyYpArticleI18nService.getCompanyYpArticleI18nById(companyArticleId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpArticleI18n")
                        .message("CompanyYpArticleI18n not found")
                        .build());

        mockMvc.perform(get("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyYpArticleI18nDto requestDto = createValidCreateCompanyYpArticleI18nDto();
        CompanyYpArticleI18nDto responseDto = new CompanyYpArticleI18nDto();
        given(companyYpArticleI18nService.createCompanyYpArticleI18n(any(CompanyYpArticleI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-yp-article-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleI18nService).createCompanyYpArticleI18n(any(CompanyYpArticleI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyYpArticleI18nDto requestDto = createValidCreateCompanyYpArticleI18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/company-yp-article-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyYpArticleI18nDto requestDto = createValidCreateCompanyYpArticleI18nDto();
        given(companyYpArticleI18nService.createCompanyYpArticleI18n(any(CompanyYpArticleI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyYpArticleI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-yp-article-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nDto requestDto = new CompanyYpArticleI18nDto();
        CompanyYpArticleI18nDto responseDto = new CompanyYpArticleI18nDto();
        given(companyYpArticleI18nService.updateCompanyYpArticleI18n(eq(companyArticleId), eq(languageId), any(CompanyYpArticleI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleI18nService).updateCompanyYpArticleI18n(eq(companyArticleId), eq(languageId), any(CompanyYpArticleI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyYpArticleI18nDto requestDto = new CompanyYpArticleI18nDto();
        given(companyYpArticleI18nService.updateCompanyYpArticleI18n(eq(companyArticleId), eq(languageId), any(CompanyYpArticleI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpArticleI18n")
                        .message("CompanyYpArticleI18n not found")
                        .build());

        mockMvc.perform(patch("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(companyYpArticleI18nService).deleteCompanyYpArticleI18n(companyArticleId, languageId);

        mockMvc.perform(delete("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId))
                .andExpect(status().isNoContent());

        verify(companyYpArticleI18nService).deleteCompanyYpArticleI18n(companyArticleId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyArticleId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpArticleI18n")
                .message("CompanyYpArticleI18n not found")
                .build())
                .given(companyYpArticleI18nService).deleteCompanyYpArticleI18n(companyArticleId, languageId);

        mockMvc.perform(delete("/api/company-yp-article-i18n/{companyArticleId}/{languageId}", companyArticleId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyYpArticleI18n.
     *
     * @return populated create request dto
     */
    private CompanyYpArticleI18nDto createValidCreateCompanyYpArticleI18nDto() {
        CompanyYpArticleI18nDto dto = new CompanyYpArticleI18nDto();
        dto.setCompanyArticle(new CompanyYpArticleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
