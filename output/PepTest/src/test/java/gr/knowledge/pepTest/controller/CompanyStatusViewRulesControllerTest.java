package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyStatusViewRulesDto;
import gr.knowledge.pepTest.service.CompanyStatusViewRulesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;

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
@WebMvcTest(CompanyStatusViewRulesController.class)
class CompanyStatusViewRulesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyStatusViewRulesService companyStatusViewRulesService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyStatusViewRulesService.getAllCompanyStatusViewRuleses()).willReturn(List.of(new CompanyStatusViewRulesDto()));

        mockMvc.perform(get("/api/company-status-view-rules"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusViewRulesService).getAllCompanyStatusViewRuleses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyStatusViewRulesService.getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId)).willReturn(new CompanyStatusViewRulesDto());

        mockMvc.perform(get("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusViewRulesService).getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(companyStatusViewRulesService.getCompanyStatusViewRulesById(companyStatusId, companyViewRulesId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatusViewRules")
                        .message("CompanyStatusViewRules not found")
                        .build());

        mockMvc.perform(get("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        CompanyStatusViewRulesDto responseDto = new CompanyStatusViewRulesDto();
        given(companyStatusViewRulesService.createCompanyStatusViewRules(any(CompanyStatusViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusViewRulesService).createCompanyStatusViewRules(any(CompanyStatusViewRulesDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        requestDto.setCompanyStatus(null);

        mockMvc.perform(post("/api/company-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        given(companyStatusViewRulesService.createCompanyStatusViewRules(any(CompanyStatusViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyStatusViewRules")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesDto requestDto = new CompanyStatusViewRulesDto();

        mockMvc.perform(patch("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        CompanyStatusViewRulesDto responseDto = new CompanyStatusViewRulesDto();
        given(companyStatusViewRulesService.updateCompanyStatusViewRules(eq(companyStatusId), eq(companyViewRulesId), any(CompanyStatusViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyStatusViewRulesService).updateCompanyStatusViewRules(eq(companyStatusId), eq(companyViewRulesId), any(CompanyStatusViewRulesDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        given(companyStatusViewRulesService.updateCompanyStatusViewRules(eq(companyStatusId), eq(companyViewRulesId), any(CompanyStatusViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyStatusViewRules")
                        .message("CompanyStatusViewRules not found")
                        .build());

        mockMvc.perform(patch("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CompanyStatusViewRulesDto requestDto = createValidCreateCompanyStatusViewRulesDto();
        given(companyStatusViewRulesService.updateCompanyStatusViewRules(eq(companyStatusId), eq(companyViewRulesId), any(CompanyStatusViewRulesDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(companyStatusViewRulesService).deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId);

        mockMvc.perform(delete("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId))
                .andExpect(status().isNoContent());

        verify(companyStatusViewRulesService).deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID companyStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyStatusViewRules")
                .message("CompanyStatusViewRules not found")
                .build())
                .given(companyStatusViewRulesService).deleteCompanyStatusViewRules(companyStatusId, companyViewRulesId);

        mockMvc.perform(delete("/api/company-status-view-rules/{companyStatusId}/{companyViewRulesId}", companyStatusId, companyViewRulesId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyStatusViewRules.
     *
     * @return populated create request dto
     */
    private CompanyStatusViewRulesDto createValidCreateCompanyStatusViewRulesDto() {
        CompanyStatusViewRulesDto dto = new CompanyStatusViewRulesDto();
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());

        return dto;
    }

}
