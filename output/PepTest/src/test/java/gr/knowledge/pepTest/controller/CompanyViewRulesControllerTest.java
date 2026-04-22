package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.service.CompanyViewRulesService;
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
@WebMvcTest(CompanyViewRulesController.class)
class CompanyViewRulesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyViewRulesService companyViewRulesService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyViewRulesService.getAllCompanyViewRuleses()).willReturn(List.of(new CompanyViewRulesDto()));

        mockMvc.perform(get("/api/company-view-rules"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyViewRulesService).getAllCompanyViewRuleses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyViewRulesService.getCompanyViewRulesById(id)).willReturn(new CompanyViewRulesDto());

        mockMvc.perform(get("/api/company-view-rules/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyViewRulesService).getCompanyViewRulesById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyViewRulesService.getCompanyViewRulesById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyViewRules")
                        .message("CompanyViewRules not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-view-rules/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyViewRulesDto requestDto = createValidCreateCompanyViewRulesDto();
        CompanyViewRulesDto responseDto = new CompanyViewRulesDto();
        given(companyViewRulesService.createCompanyViewRules(any(CompanyViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyViewRulesService).createCompanyViewRules(any(CompanyViewRulesDto.class));
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyViewRulesDto requestDto = createValidCreateCompanyViewRulesDto();
        given(companyViewRulesService.createCompanyViewRules(any(CompanyViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyViewRules")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyViewRulesDto requestDto = createValidCreateCompanyViewRulesDto();
        CompanyViewRulesDto responseDto = new CompanyViewRulesDto();
        given(companyViewRulesService.updateCompanyViewRules(eq(id), any(CompanyViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-view-rules/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyViewRulesService).updateCompanyViewRules(eq(id), any(CompanyViewRulesDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyViewRulesDto requestDto = createValidCreateCompanyViewRulesDto();
        given(companyViewRulesService.updateCompanyViewRules(eq(id), any(CompanyViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyViewRules")
                        .message("CompanyViewRules not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-view-rules/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyViewRulesDto requestDto = createValidCreateCompanyViewRulesDto();
        given(companyViewRulesService.updateCompanyViewRules(eq(id), any(CompanyViewRulesDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-view-rules/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyViewRulesService).deleteCompanyViewRules(id);

        mockMvc.perform(delete("/api/company-view-rules/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyViewRulesService).deleteCompanyViewRules(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyViewRules")
                .message("CompanyViewRules not found with id: " + id)
                .build())
                .given(companyViewRulesService).deleteCompanyViewRules(id);

        mockMvc.perform(delete("/api/company-view-rules/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyViewRules.
     *
     * @return populated create request dto
     */
    private CompanyViewRulesDto createValidCreateCompanyViewRulesDto() {
        return new CompanyViewRulesDto();
    }

}
