package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitlei18nDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitlei18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;

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
@WebMvcTest(TemporaryCompanyTitlei18nController.class)
class TemporaryCompanyTitlei18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TemporaryCompanyTitlei18nService temporaryCompanyTitlei18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(temporaryCompanyTitlei18nService.getAllTemporaryCompanyTitlei18ns()).willReturn(List.of(new TemporaryCompanyTitlei18nDto()));

        mockMvc.perform(get("/api/temporary-company-titlei18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitlei18nService).getAllTemporaryCompanyTitlei18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id)).willReturn(new TemporaryCompanyTitlei18nDto());

        mockMvc.perform(get("/api/temporary-company-titlei18n/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitlei18nService).getTemporaryCompanyTitlei18nById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyTitlei18nService.getTemporaryCompanyTitlei18nById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyTitlei18n")
                        .message("TemporaryCompanyTitlei18n not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/temporary-company-titlei18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18nDto responseDto = new TemporaryCompanyTitlei18nDto();
        given(temporaryCompanyTitlei18nService.createTemporaryCompanyTitlei18n(any(TemporaryCompanyTitlei18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/temporary-company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitlei18nService).createTemporaryCompanyTitlei18n(any(TemporaryCompanyTitlei18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/temporary-company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        given(temporaryCompanyTitlei18nService.createTemporaryCompanyTitlei18n(any(TemporaryCompanyTitlei18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("TemporaryCompanyTitlei18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/temporary-company-titlei18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        requestDto.setVersion(null);

        mockMvc.perform(patch("/api/temporary-company-titlei18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        TemporaryCompanyTitlei18nDto responseDto = new TemporaryCompanyTitlei18nDto();
        given(temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(eq(id), any(TemporaryCompanyTitlei18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/temporary-company-titlei18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitlei18nService).updateTemporaryCompanyTitlei18n(eq(id), any(TemporaryCompanyTitlei18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        given(temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(eq(id), any(TemporaryCompanyTitlei18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyTitlei18n")
                        .message("TemporaryCompanyTitlei18n not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/temporary-company-titlei18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitlei18nDto requestDto = createValidCreateTemporaryCompanyTitlei18nDto();
        given(temporaryCompanyTitlei18nService.updateTemporaryCompanyTitlei18n(eq(id), any(TemporaryCompanyTitlei18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/temporary-company-titlei18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willDoNothing().given(temporaryCompanyTitlei18nService).deleteTemporaryCompanyTitlei18n(id);

        mockMvc.perform(delete("/api/temporary-company-titlei18n/{id}", id))
                .andExpect(status().isNoContent());

        verify(temporaryCompanyTitlei18nService).deleteTemporaryCompanyTitlei18n(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyTitlei18n")
                .message("TemporaryCompanyTitlei18n not found with id: " + id)
                .build())
                .given(temporaryCompanyTitlei18nService).deleteTemporaryCompanyTitlei18n(id);

        mockMvc.perform(delete("/api/temporary-company-titlei18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for TemporaryCompanyTitlei18n.
     *
     * @return populated create request dto
     */
    private TemporaryCompanyTitlei18nDto createValidCreateTemporaryCompanyTitlei18nDto() {
        TemporaryCompanyTitlei18nDto dto = new TemporaryCompanyTitlei18nDto();
        dto.setVersion(new BigInteger("1"));
        dto.setCompanyTitle(new TemporaryCompanyTitleDto());
        dto.setLanguage(new LanguagesDto());
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

}
