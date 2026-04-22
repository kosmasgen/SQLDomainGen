package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyi18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyi18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyDto;

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
@WebMvcTest(TemporaryCompanyi18nController.class)
class TemporaryCompanyi18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TemporaryCompanyi18nService temporaryCompanyi18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(temporaryCompanyi18nService.getAllTemporaryCompanyi18ns()).willReturn(List.of(new TemporaryCompanyi18nDto()));

        mockMvc.perform(get("/api/temporary-companyi18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyi18nService).getAllTemporaryCompanyi18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyi18nService.getTemporaryCompanyi18nById(id)).willReturn(new TemporaryCompanyi18nDto());

        mockMvc.perform(get("/api/temporary-companyi18n/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyi18nService).getTemporaryCompanyi18nById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyi18nService.getTemporaryCompanyi18nById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyi18n")
                        .message("TemporaryCompanyi18n not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/temporary-companyi18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        TemporaryCompanyi18nDto responseDto = new TemporaryCompanyi18nDto();
        given(temporaryCompanyi18nService.createTemporaryCompanyi18n(any(TemporaryCompanyi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/temporary-companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyi18nService).createTemporaryCompanyi18n(any(TemporaryCompanyi18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/temporary-companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        given(temporaryCompanyi18nService.createTemporaryCompanyi18n(any(TemporaryCompanyi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("TemporaryCompanyi18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/temporary-companyi18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        requestDto.setVersion(null);

        mockMvc.perform(patch("/api/temporary-companyi18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        TemporaryCompanyi18nDto responseDto = new TemporaryCompanyi18nDto();
        given(temporaryCompanyi18nService.updateTemporaryCompanyi18n(eq(id), any(TemporaryCompanyi18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/temporary-companyi18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyi18nService).updateTemporaryCompanyi18n(eq(id), any(TemporaryCompanyi18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        given(temporaryCompanyi18nService.updateTemporaryCompanyi18n(eq(id), any(TemporaryCompanyi18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyi18n")
                        .message("TemporaryCompanyi18n not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/temporary-companyi18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyi18nDto requestDto = createValidCreateTemporaryCompanyi18nDto();
        given(temporaryCompanyi18nService.updateTemporaryCompanyi18n(eq(id), any(TemporaryCompanyi18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/temporary-companyi18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willDoNothing().given(temporaryCompanyi18nService).deleteTemporaryCompanyi18n(id);

        mockMvc.perform(delete("/api/temporary-companyi18n/{id}", id))
                .andExpect(status().isNoContent());

        verify(temporaryCompanyi18nService).deleteTemporaryCompanyi18n(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyi18n")
                .message("TemporaryCompanyi18n not found with id: " + id)
                .build())
                .given(temporaryCompanyi18nService).deleteTemporaryCompanyi18n(id);

        mockMvc.perform(delete("/api/temporary-companyi18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for TemporaryCompanyi18n.
     *
     * @return populated create request dto
     */
    private TemporaryCompanyi18nDto createValidCreateTemporaryCompanyi18nDto() {
        TemporaryCompanyi18nDto dto = new TemporaryCompanyi18nDto();
        dto.setVersion(new BigInteger("1"));
        dto.setCompany(new TemporaryCompanyDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

}
