package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.service.ExportCompanyService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;

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
@WebMvcTest(ExportCompanyController.class)
class ExportCompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExportCompanyService exportCompanyService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(exportCompanyService.getAllExportCompanies()).willReturn(List.of(new ExportCompanyDto()));

        mockMvc.perform(get("/api/export-company"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompanyService).getAllExportCompanies();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(exportCompanyService.getExportCompanyById(id)).willReturn(new ExportCompanyDto());

        mockMvc.perform(get("/api/export-company/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompanyService).getExportCompanyById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(exportCompanyService.getExportCompanyById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ExportCompany")
                        .message("ExportCompany not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/export-company/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        ExportCompanyDto responseDto = new ExportCompanyDto();
        given(exportCompanyService.createExportCompany(any(ExportCompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/export-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompanyService).createExportCompany(any(ExportCompanyDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        requestDto.setCompany(null);

        mockMvc.perform(post("/api/export-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        given(exportCompanyService.createExportCompany(any(ExportCompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ExportCompany")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/export-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        requestDto.setCompany(null);

        mockMvc.perform(patch("/api/export-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        ExportCompanyDto responseDto = new ExportCompanyDto();
        given(exportCompanyService.updateExportCompany(eq(id), any(ExportCompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/export-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompanyService).updateExportCompany(eq(id), any(ExportCompanyDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        given(exportCompanyService.updateExportCompany(eq(id), any(ExportCompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ExportCompany")
                        .message("ExportCompany not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/export-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompanyDto requestDto = createValidCreateExportCompanyDto();
        given(exportCompanyService.updateExportCompany(eq(id), any(ExportCompanyDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/export-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(exportCompanyService).deleteExportCompany(id);

        mockMvc.perform(delete("/api/export-company/{id}", id))
                .andExpect(status().isNoContent());

        verify(exportCompanyService).deleteExportCompany(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ExportCompany")
                .message("ExportCompany not found with id: " + id)
                .build())
                .given(exportCompanyService).deleteExportCompany(id);

        mockMvc.perform(delete("/api/export-company/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ExportCompany.
     *
     * @return populated create request dto
     */
    private ExportCompanyDto createValidCreateExportCompanyDto() {
        ExportCompanyDto dto = new ExportCompanyDto();
        dto.setCompany(new CompanyDto());
        dto.setEmeCode(1L);

        return dto;
    }

}
