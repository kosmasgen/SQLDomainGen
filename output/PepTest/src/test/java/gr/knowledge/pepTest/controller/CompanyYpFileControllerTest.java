package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpFileDto;
import gr.knowledge.pepTest.service.CompanyYpFileService;
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
@WebMvcTest(CompanyYpFileController.class)
class CompanyYpFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyYpFileService companyYpFileService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyYpFileService.getAllCompanyYpFiles()).willReturn(List.of(new CompanyYpFileDto()));

        mockMvc.perform(get("/api/company-yp-file"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpFileService).getAllCompanyYpFiles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpFileService.getCompanyYpFileById(id)).willReturn(new CompanyYpFileDto());

        mockMvc.perform(get("/api/company-yp-file/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpFileService).getCompanyYpFileById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpFileService.getCompanyYpFileById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpFile")
                        .message("CompanyYpFile not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-yp-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        CompanyYpFileDto responseDto = new CompanyYpFileDto();
        given(companyYpFileService.createCompanyYpFile(any(CompanyYpFileDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-yp-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpFileService).createCompanyYpFile(any(CompanyYpFileDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/company-yp-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        given(companyYpFileService.createCompanyYpFile(any(CompanyYpFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyYpFile")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-yp-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/company-yp-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        CompanyYpFileDto responseDto = new CompanyYpFileDto();
        given(companyYpFileService.updateCompanyYpFile(eq(id), any(CompanyYpFileDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-yp-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpFileService).updateCompanyYpFile(eq(id), any(CompanyYpFileDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        given(companyYpFileService.updateCompanyYpFile(eq(id), any(CompanyYpFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpFile")
                        .message("CompanyYpFile not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-yp-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpFileDto requestDto = createValidCreateCompanyYpFileDto();
        given(companyYpFileService.updateCompanyYpFile(eq(id), any(CompanyYpFileDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-yp-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyYpFileService).deleteCompanyYpFile(id);

        mockMvc.perform(delete("/api/company-yp-file/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyYpFileService).deleteCompanyYpFile(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpFile")
                .message("CompanyYpFile not found with id: " + id)
                .build())
                .given(companyYpFileService).deleteCompanyYpFile(id);

        mockMvc.perform(delete("/api/company-yp-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyYpFile.
     *
     * @return populated create request dto
     */
    private CompanyYpFileDto createValidCreateCompanyYpFileDto() {
        CompanyYpFileDto dto = new CompanyYpFileDto();
        dto.setChamberId(1);
        dto.setFileName("A");
        dto.setMimeType("A");
        dto.setFileSize(1);
        dto.setOrderSeq(1);
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

}
