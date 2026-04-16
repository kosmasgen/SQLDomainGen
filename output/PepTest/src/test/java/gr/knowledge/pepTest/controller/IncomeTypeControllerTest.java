package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.IncomeTypeDto;
import gr.knowledge.pepTest.service.IncomeTypeService;
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
@WebMvcTest(IncomeTypeController.class)
class IncomeTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IncomeTypeService incomeTypeService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(incomeTypeService.getAllIncomeTypes()).willReturn(List.of(new IncomeTypeDto()));

        mockMvc.perform(get("/api/income-type"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTypeService).getAllIncomeTypes();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeTypeService.getIncomeTypeById(id)).willReturn(new IncomeTypeDto());

        mockMvc.perform(get("/api/income-type/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTypeService).getIncomeTypeById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(incomeTypeService.getIncomeTypeById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeType")
                        .message("IncomeType not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/income-type/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        IncomeTypeDto requestDto = createValidCreateIncomeTypeDto();
        IncomeTypeDto responseDto = new IncomeTypeDto();
        given(incomeTypeService.createIncomeType(any(IncomeTypeDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/income-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTypeService).createIncomeType(any(IncomeTypeDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        IncomeTypeDto requestDto = createValidCreateIncomeTypeDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/income-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        IncomeTypeDto requestDto = createValidCreateIncomeTypeDto();
        given(incomeTypeService.createIncomeType(any(IncomeTypeDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("IncomeType")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/income-type")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTypeDto requestDto = new IncomeTypeDto();
        IncomeTypeDto responseDto = new IncomeTypeDto();
        given(incomeTypeService.updateIncomeType(eq(id), any(IncomeTypeDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/income-type/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(incomeTypeService).updateIncomeType(eq(id), any(IncomeTypeDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        IncomeTypeDto requestDto = new IncomeTypeDto();
        given(incomeTypeService.updateIncomeType(eq(id), any(IncomeTypeDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("IncomeType")
                        .message("IncomeType not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/income-type/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(incomeTypeService).deleteIncomeType(id);

        mockMvc.perform(delete("/api/income-type/{id}", id))
                .andExpect(status().isNoContent());

        verify(incomeTypeService).deleteIncomeType(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("IncomeType")
                .message("IncomeType not found with id: " + id)
                .build())
                .given(incomeTypeService).deleteIncomeType(id);

        mockMvc.perform(delete("/api/income-type/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for IncomeType.
     *
     * @return populated create request dto
     */
    private IncomeTypeDto createValidCreateIncomeTypeDto() {
        IncomeTypeDto dto = new IncomeTypeDto();
        dto.setChamberId(1);
        dto.setChamberTypeId(1);
        dto.setDescription("aaaaa");

        return dto;
    }

}
