package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomePaymentMethodDto;
import gr.knowledge.pepTest.service.IncomePaymentMethodService;
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
@WebMvcTest(IncomePaymentMethodController.class)
class IncomePaymentMethodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncomePaymentMethodService incomePaymentMethodService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(incomePaymentMethodService.getAllIncomePaymentMethods()).willReturn(List.of(new IncomePaymentMethodDto()));

        mockMvc.perform(get("/api/income-payment-method"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomePaymentMethodService).getAllIncomePaymentMethods();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomePaymentMethodService.getIncomePaymentMethodById(id)).willReturn(new IncomePaymentMethodDto());

        mockMvc.perform(get("/api/income-payment-method/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomePaymentMethodService).getIncomePaymentMethodById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomePaymentMethodService.getIncomePaymentMethodById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomePaymentMethod")
                        .message("IncomePaymentMethod not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/income-payment-method/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        IncomePaymentMethodDto responseDto = new IncomePaymentMethodDto();
        given(incomePaymentMethodService.createIncomePaymentMethod(any(IncomePaymentMethodDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/income-payment-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomePaymentMethodService).createIncomePaymentMethod(any(IncomePaymentMethodDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/income-payment-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        given(incomePaymentMethodService.createIncomePaymentMethod(any(IncomePaymentMethodDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("IncomePaymentMethod")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/income-payment-method")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/income-payment-method/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        IncomePaymentMethodDto responseDto = new IncomePaymentMethodDto();
        given(incomePaymentMethodService.updateIncomePaymentMethod(eq(id), any(IncomePaymentMethodDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/income-payment-method/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomePaymentMethodService).updateIncomePaymentMethod(eq(id), any(IncomePaymentMethodDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        given(incomePaymentMethodService.updateIncomePaymentMethod(eq(id), any(IncomePaymentMethodDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomePaymentMethod")
                        .message("IncomePaymentMethod not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/income-payment-method/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomePaymentMethodDto requestDto = createValidCreateIncomePaymentMethodDto();
        given(incomePaymentMethodService.updateIncomePaymentMethod(eq(id), any(IncomePaymentMethodDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/income-payment-method/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(incomePaymentMethodService).deleteIncomePaymentMethod(id);

        mockMvc.perform(delete("/api/income-payment-method/{id}", id))
                .andExpect(status().isNoContent());

        verify(incomePaymentMethodService).deleteIncomePaymentMethod(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomePaymentMethod")
                .message("IncomePaymentMethod not found with id: " + id)
                .build())
                .given(incomePaymentMethodService).deleteIncomePaymentMethod(id);

        mockMvc.perform(delete("/api/income-payment-method/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for IncomePaymentMethod.
     *
     * @return populated create request dto
     */
    private IncomePaymentMethodDto createValidCreateIncomePaymentMethodDto() {
        IncomePaymentMethodDto dto = new IncomePaymentMethodDto();
        dto.setChamberId(1);
        dto.setChamberPayMethodId(1);
        dto.setDescription("A");

        return dto;
    }

}
