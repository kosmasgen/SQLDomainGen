package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.StatsExpenseDto;
import gr.knowledge.pepTest.service.StatsExpenseService;
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
import java.util.UUID;
import java.math.BigInteger;

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
@WebMvcTest(StatsExpenseController.class)
class StatsExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StatsExpenseService statsExpenseService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(statsExpenseService.getAllStatsExpenses()).willReturn(List.of(new StatsExpenseDto()));

        mockMvc.perform(get("/api/stats-expense"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statsExpenseService).getAllStatsExpenses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(statsExpenseService.getStatsExpenseById(id)).willReturn(new StatsExpenseDto());

        mockMvc.perform(get("/api/stats-expense/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statsExpenseService).getStatsExpenseById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(statsExpenseService.getStatsExpenseById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("StatsExpense")
                        .message("StatsExpense not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/stats-expense/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        StatsExpenseDto requestDto = createValidCreateStatsExpenseDto();
        StatsExpenseDto responseDto = new StatsExpenseDto();
        given(statsExpenseService.createStatsExpense(any(StatsExpenseDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/stats-expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statsExpenseService).createStatsExpense(any(StatsExpenseDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        StatsExpenseDto requestDto = createValidCreateStatsExpenseDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/stats-expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        StatsExpenseDto requestDto = createValidCreateStatsExpenseDto();
        given(statsExpenseService.createStatsExpense(any(StatsExpenseDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("StatsExpense")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/stats-expense")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        StatsExpenseDto requestDto = new StatsExpenseDto();
        StatsExpenseDto responseDto = new StatsExpenseDto();
        given(statsExpenseService.updateStatsExpense(eq(id), any(StatsExpenseDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/stats-expense/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(statsExpenseService).updateStatsExpense(eq(id), any(StatsExpenseDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        StatsExpenseDto requestDto = new StatsExpenseDto();
        given(statsExpenseService.updateStatsExpense(eq(id), any(StatsExpenseDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("StatsExpense")
                        .message("StatsExpense not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/stats-expense/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(statsExpenseService).deleteStatsExpense(id);

        mockMvc.perform(delete("/api/stats-expense/{id}", id))
                .andExpect(status().isNoContent());

        verify(statsExpenseService).deleteStatsExpense(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("StatsExpense")
                .message("StatsExpense not found with id: " + id)
                .build())
                .given(statsExpenseService).deleteStatsExpense(id);

        mockMvc.perform(delete("/api/stats-expense/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for StatsExpense.
     *
     * @return populated create request dto
     */
    private StatsExpenseDto createValidCreateStatsExpenseDto() {
        StatsExpenseDto dto = new StatsExpenseDto();
        dto.setChamberId(1);
        dto.setAccountSumId(new BigInteger("1"));
        dto.setCdUse("aaaa");
        dto.setMm("aa");

        return dto;
    }

}
