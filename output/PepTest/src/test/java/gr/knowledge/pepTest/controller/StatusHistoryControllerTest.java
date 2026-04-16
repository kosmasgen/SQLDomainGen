package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatusHistoryDto;
import gr.knowledge.pepTest.service.StatusHistoryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;

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
import java.math.BigInteger;
import java.time.LocalDateTime;

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
@WebMvcTest(StatusHistoryController.class)
class StatusHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StatusHistoryService statusHistoryService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(statusHistoryService.getAllStatusHistories()).willReturn(List.of(new StatusHistoryDto()));

        mockMvc.perform(get("/api/status-history"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statusHistoryService).getAllStatusHistories();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(statusHistoryService.getStatusHistoryById(id)).willReturn(new StatusHistoryDto());

        mockMvc.perform(get("/api/status-history/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statusHistoryService).getStatusHistoryById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(statusHistoryService.getStatusHistoryById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("StatusHistory")
                        .message("StatusHistory not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/status-history/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        StatusHistoryDto requestDto = createValidCreateStatusHistoryDto();
        StatusHistoryDto responseDto = new StatusHistoryDto();
        given(statusHistoryService.createStatusHistory(any(StatusHistoryDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/status-history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statusHistoryService).createStatusHistory(any(StatusHistoryDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        StatusHistoryDto requestDto = createValidCreateStatusHistoryDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/status-history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        StatusHistoryDto requestDto = createValidCreateStatusHistoryDto();
        given(statusHistoryService.createStatusHistory(any(StatusHistoryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("StatusHistory")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/status-history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        StatusHistoryDto requestDto = new StatusHistoryDto();
        StatusHistoryDto responseDto = new StatusHistoryDto();
        given(statusHistoryService.updateStatusHistory(eq(id), any(StatusHistoryDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/status-history/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statusHistoryService).updateStatusHistory(eq(id), any(StatusHistoryDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        StatusHistoryDto requestDto = new StatusHistoryDto();
        given(statusHistoryService.updateStatusHistory(eq(id), any(StatusHistoryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("StatusHistory")
                        .message("StatusHistory not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/status-history/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(statusHistoryService).deleteStatusHistory(id);

        mockMvc.perform(delete("/api/status-history/{id}", id))
                .andExpect(status().isNoContent());

        verify(statusHistoryService).deleteStatusHistory(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("StatusHistory")
                .message("StatusHistory not found with id: " + id)
                .build())
                .given(statusHistoryService).deleteStatusHistory(id);

        mockMvc.perform(delete("/api/status-history/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for StatusHistory.
     *
     * @return populated create request dto
     */
    private StatusHistoryDto createValidCreateStatusHistoryDto() {
        StatusHistoryDto dto = new StatusHistoryDto();
        dto.setChamberId(1);
        dto.setChamberStatusHistoryId(new BigInteger("1"));
        dto.setCompany(new CompanyDto());
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setRegDt(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setStartDt(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setRecdeleted(true);

        return dto;
    }

}
