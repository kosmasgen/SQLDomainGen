package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.service.CompanyStatusService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;

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
@WebMvcTest(CompanyStatusController.class)
class CompanyStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyStatusService companyStatusService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyStatusService.getAllCompanyStatuses()).willReturn(List.of(new CompanyStatusDto()));

        mockMvc.perform(get("/api/company-status"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusService).getAllCompanyStatuses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyStatusService.getCompanyStatusById(id)).willReturn(new CompanyStatusDto());

        mockMvc.perform(get("/api/company-status/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusService).getCompanyStatusById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyStatusService.getCompanyStatusById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatus")
                        .message("CompanyStatus not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-status/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        CompanyStatusDto responseDto = new CompanyStatusDto();
        given(companyStatusService.createCompanyStatus(any(CompanyStatusDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusService).createCompanyStatus(any(CompanyStatusDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/company-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        given(companyStatusService.createCompanyStatus(any(CompanyStatusDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyStatus")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/company-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        CompanyStatusDto responseDto = new CompanyStatusDto();
        given(companyStatusService.updateCompanyStatus(eq(id), any(CompanyStatusDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusService).updateCompanyStatus(eq(id), any(CompanyStatusDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        given(companyStatusService.updateCompanyStatus(eq(id), any(CompanyStatusDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatus")
                        .message("CompanyStatus not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyStatusDto requestDto = createValidCreateCompanyStatusDto();
        given(companyStatusService.updateCompanyStatus(eq(id), any(CompanyStatusDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-status/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyStatusService).deleteCompanyStatus(id);

        mockMvc.perform(delete("/api/company-status/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyStatusService).deleteCompanyStatus(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatus")
                .message("CompanyStatus not found with id: " + id)
                .build())
                .given(companyStatusService).deleteCompanyStatus(id);

        mockMvc.perform(delete("/api/company-status/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyStatus.
     *
     * @return populated create request dto
     */
    private CompanyStatusDto createValidCreateCompanyStatusDto() {
        CompanyStatusDto dto = new CompanyStatusDto();
        dto.setChamberId(1);

        return dto;
    }

}
