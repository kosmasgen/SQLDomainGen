package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTransactionDto;
import gr.knowledge.pepTest.service.IncomeTransactionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.dto.IncomeTypeDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

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
@WebMvcTest(IncomeTransactionController.class)
class IncomeTransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncomeTransactionService incomeTransactionService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(incomeTransactionService.getAllIncomeTransactions()).willReturn(List.of(new IncomeTransactionDto()));

        mockMvc.perform(get("/api/income-transaction"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTransactionService).getAllIncomeTransactions();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeTransactionService.getIncomeTransactionById(id)).willReturn(new IncomeTransactionDto());

        mockMvc.perform(get("/api/income-transaction/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTransactionService).getIncomeTransactionById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeTransactionService.getIncomeTransactionById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeTransaction")
                        .message("IncomeTransaction not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/income-transaction/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        IncomeTransactionDto responseDto = new IncomeTransactionDto();
        given(incomeTransactionService.createIncomeTransaction(any(IncomeTransactionDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/income-transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTransactionService).createIncomeTransaction(any(IncomeTransactionDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/income-transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        given(incomeTransactionService.createIncomeTransaction(any(IncomeTransactionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("IncomeTransaction")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/income-transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/income-transaction/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        IncomeTransactionDto responseDto = new IncomeTransactionDto();
        given(incomeTransactionService.updateIncomeTransaction(eq(id), any(IncomeTransactionDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/income-transaction/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTransactionService).updateIncomeTransaction(eq(id), any(IncomeTransactionDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        given(incomeTransactionService.updateIncomeTransaction(eq(id), any(IncomeTransactionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeTransaction")
                        .message("IncomeTransaction not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/income-transaction/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTransactionDto requestDto = createValidCreateIncomeTransactionDto();
        given(incomeTransactionService.updateIncomeTransaction(eq(id), any(IncomeTransactionDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/income-transaction/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(incomeTransactionService).deleteIncomeTransaction(id);

        mockMvc.perform(delete("/api/income-transaction/{id}", id))
                .andExpect(status().isNoContent());

        verify(incomeTransactionService).deleteIncomeTransaction(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeTransaction")
                .message("IncomeTransaction not found with id: " + id)
                .build())
                .given(incomeTransactionService).deleteIncomeTransaction(id);

        mockMvc.perform(delete("/api/income-transaction/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for IncomeTransaction.
     *
     * @return populated create request dto
     */
    private IncomeTransactionDto createValidCreateIncomeTransactionDto() {
        IncomeTransactionDto dto = new IncomeTransactionDto();
        dto.setChamberId(1);
        dto.setChamberInTransdId(new BigInteger("1"));
        dto.setCdUse("A");
        dto.setDt(LocalDateTime.of(2025, 1, 11, 10, 0));
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

}
