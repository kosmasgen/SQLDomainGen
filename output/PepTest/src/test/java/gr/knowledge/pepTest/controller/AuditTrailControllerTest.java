package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.AuditTrailDto;
import gr.knowledge.pepTest.service.AuditTrailService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.CountryDto;

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
@WebMvcTest(AuditTrailController.class)
class AuditTrailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AuditTrailService auditTrailService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(auditTrailService.getAllAuditTrails()).willReturn(List.of(new AuditTrailDto()));

        mockMvc.perform(get("/api/audit-trail"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(auditTrailService).getAllAuditTrails();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(auditTrailService.getAuditTrailById(id)).willReturn(new AuditTrailDto());

        mockMvc.perform(get("/api/audit-trail/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(auditTrailService).getAuditTrailById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(auditTrailService.getAuditTrailById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("AuditTrail")
                        .message("AuditTrail not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/audit-trail/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        AuditTrailDto responseDto = new AuditTrailDto();
        given(auditTrailService.createAuditTrail(any(AuditTrailDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/audit-trail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(auditTrailService).createAuditTrail(any(AuditTrailDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        requestDto.setIp(null);

        mockMvc.perform(post("/api/audit-trail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        given(auditTrailService.createAuditTrail(any(AuditTrailDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("AuditTrail")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/audit-trail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        requestDto.setIp(null);

        mockMvc.perform(patch("/api/audit-trail/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        AuditTrailDto responseDto = new AuditTrailDto();
        given(auditTrailService.updateAuditTrail(eq(id), any(AuditTrailDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/audit-trail/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(auditTrailService).updateAuditTrail(eq(id), any(AuditTrailDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        given(auditTrailService.updateAuditTrail(eq(id), any(AuditTrailDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("AuditTrail")
                        .message("AuditTrail not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/audit-trail/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        AuditTrailDto requestDto = createValidCreateAuditTrailDto();
        given(auditTrailService.updateAuditTrail(eq(id), any(AuditTrailDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/audit-trail/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(auditTrailService).deleteAuditTrail(id);

        mockMvc.perform(delete("/api/audit-trail/{id}", id))
                .andExpect(status().isNoContent());

        verify(auditTrailService).deleteAuditTrail(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("AuditTrail")
                .message("AuditTrail not found with id: " + id)
                .build())
                .given(auditTrailService).deleteAuditTrail(id);

        mockMvc.perform(delete("/api/audit-trail/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for AuditTrail.
     *
     * @return populated create request dto
     */
    private AuditTrailDto createValidCreateAuditTrailDto() {
        AuditTrailDto dto = new AuditTrailDto();
        dto.setIp("A");

        return dto;
    }

}
