package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyArticleFileDto;
import gr.knowledge.pepTest.service.CompanyArticleFileService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.dto.CompanyYpArticleDto;

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
@WebMvcTest(CompanyArticleFileController.class)
class CompanyArticleFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyArticleFileService companyArticleFileService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyArticleFileService.getAllCompanyArticleFiles()).willReturn(List.of(new CompanyArticleFileDto()));

        mockMvc.perform(get("/api/company-article-file"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyArticleFileService).getAllCompanyArticleFiles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyArticleFileService.getCompanyArticleFileById(id)).willReturn(new CompanyArticleFileDto());

        mockMvc.perform(get("/api/company-article-file/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyArticleFileService).getCompanyArticleFileById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyArticleFileService.getCompanyArticleFileById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyArticleFile")
                        .message("CompanyArticleFile not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-article-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        CompanyArticleFileDto responseDto = new CompanyArticleFileDto();
        given(companyArticleFileService.createCompanyArticleFile(any(CompanyArticleFileDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-article-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyArticleFileService).createCompanyArticleFile(any(CompanyArticleFileDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        requestDto.setArticle(null);

        mockMvc.perform(post("/api/company-article-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        given(companyArticleFileService.createCompanyArticleFile(any(CompanyArticleFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyArticleFile")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-article-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        requestDto.setArticle(null);

        mockMvc.perform(patch("/api/company-article-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        CompanyArticleFileDto responseDto = new CompanyArticleFileDto();
        given(companyArticleFileService.updateCompanyArticleFile(eq(id), any(CompanyArticleFileDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-article-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyArticleFileService).updateCompanyArticleFile(eq(id), any(CompanyArticleFileDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        given(companyArticleFileService.updateCompanyArticleFile(eq(id), any(CompanyArticleFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyArticleFile")
                        .message("CompanyArticleFile not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-article-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyArticleFileDto requestDto = createValidCreateCompanyArticleFileDto();
        given(companyArticleFileService.updateCompanyArticleFile(eq(id), any(CompanyArticleFileDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-article-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyArticleFileService).deleteCompanyArticleFile(id);

        mockMvc.perform(delete("/api/company-article-file/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyArticleFileService).deleteCompanyArticleFile(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyArticleFile")
                .message("CompanyArticleFile not found with id: " + id)
                .build())
                .given(companyArticleFileService).deleteCompanyArticleFile(id);

        mockMvc.perform(delete("/api/company-article-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyArticleFile.
     *
     * @return populated create request dto
     */
    private CompanyArticleFileDto createValidCreateCompanyArticleFileDto() {
        CompanyArticleFileDto dto = new CompanyArticleFileDto();
        dto.setArticle(new CompanyYpArticleDto());
        dto.setFile(new CompanyFileDto());
        dto.setOrderSeq(1);

        return dto;
    }

}
