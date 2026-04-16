package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFileDto;
import gr.knowledge.pepTest.service.CompanyFileService;
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
@WebMvcTest(CompanyFileController.class)
class CompanyFileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyFileService companyFileService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyFileService.getAllCompanyFiles()).willReturn(List.of(new CompanyFileDto()));

        mockMvc.perform(get("/api/company-file"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFileService).getAllCompanyFiles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyFileService.getCompanyFileById(id)).willReturn(new CompanyFileDto());

        mockMvc.perform(get("/api/company-file/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFileService).getCompanyFileById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyFileService.getCompanyFileById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyFile")
                        .message("CompanyFile not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyFileDto requestDto = createValidCreateCompanyFileDto();
        CompanyFileDto responseDto = new CompanyFileDto();
        given(companyFileService.createCompanyFile(any(CompanyFileDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFileService).createCompanyFile(any(CompanyFileDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyFileDto requestDto = createValidCreateCompanyFileDto();
        requestDto.setFileName(null);

        mockMvc.perform(post("/api/company-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyFileDto requestDto = createValidCreateCompanyFileDto();
        given(companyFileService.createCompanyFile(any(CompanyFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyFile")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFileDto requestDto = new CompanyFileDto();
        CompanyFileDto responseDto = new CompanyFileDto();
        given(companyFileService.updateCompanyFile(eq(id), any(CompanyFileDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFileService).updateCompanyFile(eq(id), any(CompanyFileDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFileDto requestDto = new CompanyFileDto();
        given(companyFileService.updateCompanyFile(eq(id), any(CompanyFileDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyFile")
                        .message("CompanyFile not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-file/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyFileService).deleteCompanyFile(id);

        mockMvc.perform(delete("/api/company-file/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyFileService).deleteCompanyFile(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyFile")
                .message("CompanyFile not found with id: " + id)
                .build())
                .given(companyFileService).deleteCompanyFile(id);

        mockMvc.perform(delete("/api/company-file/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyFile.
     *
     * @return populated create request dto
     */
    private CompanyFileDto createValidCreateCompanyFileDto() {
        CompanyFileDto dto = new CompanyFileDto();
        dto.setFileName("aaaaa");
        dto.setFileSize(1);
        dto.setBlobUri("aaaaa");
        dto.setOrderSeq(1);
        dto.setCompany(new CompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setCompanyProfile(new CompanyProfileDto());
        dto.setIsEmbedded(true);

        return dto;
    }

}
