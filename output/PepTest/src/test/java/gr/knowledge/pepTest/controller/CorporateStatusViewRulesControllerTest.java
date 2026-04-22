package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CorporateStatusViewRulesDto;
import gr.knowledge.pepTest.service.CorporateStatusViewRulesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyViewRulesDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;

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
@WebMvcTest(CorporateStatusViewRulesController.class)
class CorporateStatusViewRulesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CorporateStatusViewRulesService corporateStatusViewRulesService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(corporateStatusViewRulesService.getAllCorporateStatusViewRuleses()).willReturn(List.of(new CorporateStatusViewRulesDto()));

        mockMvc.perform(get("/api/corporate-status-view-rules"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusViewRulesService).getAllCorporateStatusViewRuleses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusViewRulesService.getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId)).willReturn(new CorporateStatusViewRulesDto());

        mockMvc.perform(get("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusViewRulesService).getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(corporateStatusViewRulesService.getCorporateStatusViewRulesById(corporateStatusId, companyViewRulesId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatusViewRules")
                        .message("CorporateStatusViewRules not found")
                        .build());

        mockMvc.perform(get("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        CorporateStatusViewRulesDto responseDto = new CorporateStatusViewRulesDto();
        given(corporateStatusViewRulesService.createCorporateStatusViewRules(any(CorporateStatusViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/corporate-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusViewRulesService).createCorporateStatusViewRules(any(CorporateStatusViewRulesDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        requestDto.setCorporateStatus(null);

        mockMvc.perform(post("/api/corporate-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        given(corporateStatusViewRulesService.createCorporateStatusViewRules(any(CorporateStatusViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CorporateStatusViewRules")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/corporate-status-view-rules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchValidationFailure() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesDto requestDto = new CorporateStatusViewRulesDto();

        mockMvc.perform(patch("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        CorporateStatusViewRulesDto responseDto = new CorporateStatusViewRulesDto();
        given(corporateStatusViewRulesService.updateCorporateStatusViewRules(eq(corporateStatusId), eq(companyViewRulesId), any(CorporateStatusViewRulesDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(corporateStatusViewRulesService).updateCorporateStatusViewRules(eq(corporateStatusId), eq(companyViewRulesId), any(CorporateStatusViewRulesDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        given(corporateStatusViewRulesService.updateCorporateStatusViewRules(eq(corporateStatusId), eq(companyViewRulesId), any(CorporateStatusViewRulesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CorporateStatusViewRules")
                        .message("CorporateStatusViewRules not found")
                        .build());

        mockMvc.perform(patch("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CorporateStatusViewRulesDto requestDto = createValidCreateCorporateStatusViewRulesDto();
        given(corporateStatusViewRulesService.updateCorporateStatusViewRules(eq(corporateStatusId), eq(companyViewRulesId), any(CorporateStatusViewRulesDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(corporateStatusViewRulesService).deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId);

        mockMvc.perform(delete("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId))
                .andExpect(status().isNoContent());

        verify(corporateStatusViewRulesService).deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID corporateStatusId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID companyViewRulesId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CorporateStatusViewRules")
                .message("CorporateStatusViewRules not found")
                .build())
                .given(corporateStatusViewRulesService).deleteCorporateStatusViewRules(corporateStatusId, companyViewRulesId);

        mockMvc.perform(delete("/api/corporate-status-view-rules/{corporateStatusId}/{companyViewRulesId}", corporateStatusId, companyViewRulesId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CorporateStatusViewRules.
     *
     * @return populated create request dto
     */
    private CorporateStatusViewRulesDto createValidCreateCorporateStatusViewRulesDto() {
        CorporateStatusViewRulesDto dto = new CorporateStatusViewRulesDto();
        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setCompanyViewRules(new CompanyViewRulesDto());

        return dto;
    }

}
