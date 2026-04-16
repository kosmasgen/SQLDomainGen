package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpArticleDto;
import gr.knowledge.pepTest.service.CompanyYpArticleService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
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
@WebMvcTest(CompanyYpArticleController.class)
class CompanyYpArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyYpArticleService companyYpArticleService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyYpArticleService.getAllCompanyYpArticles()).willReturn(List.of(new CompanyYpArticleDto()));

        mockMvc.perform(get("/api/company-yp-article"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleService).getAllCompanyYpArticles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpArticleService.getCompanyYpArticleById(id)).willReturn(new CompanyYpArticleDto());

        mockMvc.perform(get("/api/company-yp-article/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleService).getCompanyYpArticleById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpArticleService.getCompanyYpArticleById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpArticle")
                        .message("CompanyYpArticle not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-yp-article/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyYpArticleDto requestDto = createValidCreateCompanyYpArticleDto();
        CompanyYpArticleDto responseDto = new CompanyYpArticleDto();
        given(companyYpArticleService.createCompanyYpArticle(any(CompanyYpArticleDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-yp-article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleService).createCompanyYpArticle(any(CompanyYpArticleDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyYpArticleDto requestDto = createValidCreateCompanyYpArticleDto();
        requestDto.setOrderSeq(null);

        mockMvc.perform(post("/api/company-yp-article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyYpArticleDto requestDto = createValidCreateCompanyYpArticleDto();
        given(companyYpArticleService.createCompanyYpArticle(any(CompanyYpArticleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyYpArticle")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-yp-article")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticleDto requestDto = new CompanyYpArticleDto();
        CompanyYpArticleDto responseDto = new CompanyYpArticleDto();
        given(companyYpArticleService.updateCompanyYpArticle(eq(id), any(CompanyYpArticleDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-yp-article/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpArticleService).updateCompanyYpArticle(eq(id), any(CompanyYpArticleDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpArticleDto requestDto = new CompanyYpArticleDto();
        given(companyYpArticleService.updateCompanyYpArticle(eq(id), any(CompanyYpArticleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpArticle")
                        .message("CompanyYpArticle not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-yp-article/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyYpArticleService).deleteCompanyYpArticle(id);

        mockMvc.perform(delete("/api/company-yp-article/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyYpArticleService).deleteCompanyYpArticle(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpArticle")
                .message("CompanyYpArticle not found with id: " + id)
                .build())
                .given(companyYpArticleService).deleteCompanyYpArticle(id);

        mockMvc.perform(delete("/api/company-yp-article/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyYpArticle.
     *
     * @return populated create request dto
     */
    private CompanyYpArticleDto createValidCreateCompanyYpArticleDto() {
        CompanyYpArticleDto dto = new CompanyYpArticleDto();
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setOrderSeq(1);
        dto.setRecdeleted(true);
        dto.setIsPublished(true);
        dto.setCompanyProfile(new CompanyProfileDto());

        return dto;
    }

}
