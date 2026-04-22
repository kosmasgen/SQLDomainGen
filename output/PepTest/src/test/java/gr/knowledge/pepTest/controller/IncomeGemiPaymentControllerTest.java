package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeGemiPaymentDto;
import gr.knowledge.pepTest.service.IncomeGemiPaymentService;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

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
@WebMvcTest(IncomeGemiPaymentController.class)
class IncomeGemiPaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncomeGemiPaymentService incomeGemiPaymentService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(incomeGemiPaymentService.getAllIncomeGemiPayments()).willReturn(List.of(new IncomeGemiPaymentDto()));

        mockMvc.perform(get("/api/income-gemi-payment"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeGemiPaymentService).getAllIncomeGemiPayments();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeGemiPaymentService.getIncomeGemiPaymentById(id)).willReturn(new IncomeGemiPaymentDto());

        mockMvc.perform(get("/api/income-gemi-payment/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeGemiPaymentService).getIncomeGemiPaymentById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeGemiPaymentService.getIncomeGemiPaymentById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeGemiPayment")
                        .message("IncomeGemiPayment not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/income-gemi-payment/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        IncomeGemiPaymentDto responseDto = new IncomeGemiPaymentDto();
        given(incomeGemiPaymentService.createIncomeGemiPayment(any(IncomeGemiPaymentDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/income-gemi-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeGemiPaymentService).createIncomeGemiPayment(any(IncomeGemiPaymentDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/income-gemi-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        given(incomeGemiPaymentService.createIncomeGemiPayment(any(IncomeGemiPaymentDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("IncomeGemiPayment")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/income-gemi-payment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/income-gemi-payment/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        IncomeGemiPaymentDto responseDto = new IncomeGemiPaymentDto();
        given(incomeGemiPaymentService.updateIncomeGemiPayment(eq(id), any(IncomeGemiPaymentDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/income-gemi-payment/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeGemiPaymentService).updateIncomeGemiPayment(eq(id), any(IncomeGemiPaymentDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        given(incomeGemiPaymentService.updateIncomeGemiPayment(eq(id), any(IncomeGemiPaymentDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeGemiPayment")
                        .message("IncomeGemiPayment not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/income-gemi-payment/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeGemiPaymentDto requestDto = createValidCreateIncomeGemiPaymentDto();
        given(incomeGemiPaymentService.updateIncomeGemiPayment(eq(id), any(IncomeGemiPaymentDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/income-gemi-payment/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(incomeGemiPaymentService).deleteIncomeGemiPayment(id);

        mockMvc.perform(delete("/api/income-gemi-payment/{id}", id))
                .andExpect(status().isNoContent());

        verify(incomeGemiPaymentService).deleteIncomeGemiPayment(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeGemiPayment")
                .message("IncomeGemiPayment not found with id: " + id)
                .build())
                .given(incomeGemiPaymentService).deleteIncomeGemiPayment(id);

        mockMvc.perform(delete("/api/income-gemi-payment/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for IncomeGemiPayment.
     *
     * @return populated create request dto
     */
    private IncomeGemiPaymentDto createValidCreateIncomeGemiPaymentDto() {
        IncomeGemiPaymentDto dto = new IncomeGemiPaymentDto();
        dto.setChamberId(1);
        dto.setGemiPaymentId(new BigInteger("1"));
        dto.setPaymentMethod("A");

        return dto;
    }

}
