package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyProfessionDto;
import gr.knowledge.pepTest.service.TemporaryCompanyProfessionService;
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
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@WebMvcTest(TemporaryCompanyProfessionController.class)
class TemporaryCompanyProfessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TemporaryCompanyProfessionService temporaryCompanyProfessionService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(temporaryCompanyProfessionService.getAllTemporaryCompanyProfessions()).willReturn(List.of(new TemporaryCompanyProfessionDto()));

        mockMvc.perform(get("/api/temporary-company-profession"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyProfessionService).getAllTemporaryCompanyProfessions();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id)).willReturn(new TemporaryCompanyProfessionDto());

        mockMvc.perform(get("/api/temporary-company-profession/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyProfessionService).getTemporaryCompanyProfessionById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyProfessionService.getTemporaryCompanyProfessionById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyProfession")
                        .message("TemporaryCompanyProfession not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/temporary-company-profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        TemporaryCompanyProfessionDto responseDto = new TemporaryCompanyProfessionDto();
        given(temporaryCompanyProfessionService.createTemporaryCompanyProfession(any(TemporaryCompanyProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/temporary-company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyProfessionService).createTemporaryCompanyProfession(any(TemporaryCompanyProfessionDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/temporary-company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        given(temporaryCompanyProfessionService.createTemporaryCompanyProfession(any(TemporaryCompanyProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("TemporaryCompanyProfession")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/temporary-company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        requestDto.setVersion(null);

        mockMvc.perform(patch("/api/temporary-company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        TemporaryCompanyProfessionDto responseDto = new TemporaryCompanyProfessionDto();
        given(temporaryCompanyProfessionService.updateTemporaryCompanyProfession(eq(id), any(TemporaryCompanyProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/temporary-company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyProfessionService).updateTemporaryCompanyProfession(eq(id), any(TemporaryCompanyProfessionDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        given(temporaryCompanyProfessionService.updateTemporaryCompanyProfession(eq(id), any(TemporaryCompanyProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyProfession")
                        .message("TemporaryCompanyProfession not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/temporary-company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyProfessionDto requestDto = createValidCreateTemporaryCompanyProfessionDto();
        given(temporaryCompanyProfessionService.updateTemporaryCompanyProfession(eq(id), any(TemporaryCompanyProfessionDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/temporary-company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willDoNothing().given(temporaryCompanyProfessionService).deleteTemporaryCompanyProfession(id);

        mockMvc.perform(delete("/api/temporary-company-profession/{id}", id))
                .andExpect(status().isNoContent());

        verify(temporaryCompanyProfessionService).deleteTemporaryCompanyProfession(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyProfession")
                .message("TemporaryCompanyProfession not found with id: " + id)
                .build())
                .given(temporaryCompanyProfessionService).deleteTemporaryCompanyProfession(id);

        mockMvc.perform(delete("/api/temporary-company-profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for TemporaryCompanyProfession.
     *
     * @return populated create request dto
     */
    private TemporaryCompanyProfessionDto createValidCreateTemporaryCompanyProfessionDto() {
        TemporaryCompanyProfessionDto dto = new TemporaryCompanyProfessionDto();
        dto.setVersion(new BigInteger("1"));
        dto.setCompanyId(new BigInteger("1"));
        dto.setProfessionId(new BigInteger("1"));
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

}
