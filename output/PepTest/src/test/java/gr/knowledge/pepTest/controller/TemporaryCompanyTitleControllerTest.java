package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyTitleDto;
import gr.knowledge.pepTest.service.TemporaryCompanyTitleService;
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
@WebMvcTest(TemporaryCompanyTitleController.class)
class TemporaryCompanyTitleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TemporaryCompanyTitleService temporaryCompanyTitleService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(temporaryCompanyTitleService.getAllTemporaryCompanyTitles()).willReturn(List.of(new TemporaryCompanyTitleDto()));

        mockMvc.perform(get("/api/temporary-company-title"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitleService).getAllTemporaryCompanyTitles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyTitleService.getTemporaryCompanyTitleById(id)).willReturn(new TemporaryCompanyTitleDto());

        mockMvc.perform(get("/api/temporary-company-title/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitleService).getTemporaryCompanyTitleById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyTitleService.getTemporaryCompanyTitleById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyTitle")
                        .message("TemporaryCompanyTitle not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/temporary-company-title/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        TemporaryCompanyTitleDto requestDto = createValidCreateTemporaryCompanyTitleDto();
        TemporaryCompanyTitleDto responseDto = new TemporaryCompanyTitleDto();
        given(temporaryCompanyTitleService.createTemporaryCompanyTitle(any(TemporaryCompanyTitleDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/temporary-company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitleService).createTemporaryCompanyTitle(any(TemporaryCompanyTitleDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        TemporaryCompanyTitleDto requestDto = createValidCreateTemporaryCompanyTitleDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/temporary-company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        TemporaryCompanyTitleDto requestDto = createValidCreateTemporaryCompanyTitleDto();
        given(temporaryCompanyTitleService.createTemporaryCompanyTitle(any(TemporaryCompanyTitleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("TemporaryCompanyTitle")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/temporary-company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitleDto requestDto = new TemporaryCompanyTitleDto();
        TemporaryCompanyTitleDto responseDto = new TemporaryCompanyTitleDto();
        given(temporaryCompanyTitleService.updateTemporaryCompanyTitle(eq(id), any(TemporaryCompanyTitleDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/temporary-company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyTitleService).updateTemporaryCompanyTitle(eq(id), any(TemporaryCompanyTitleDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyTitleDto requestDto = new TemporaryCompanyTitleDto();
        given(temporaryCompanyTitleService.updateTemporaryCompanyTitle(eq(id), any(TemporaryCompanyTitleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompanyTitle")
                        .message("TemporaryCompanyTitle not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/temporary-company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willDoNothing().given(temporaryCompanyTitleService).deleteTemporaryCompanyTitle(id);

        mockMvc.perform(delete("/api/temporary-company-title/{id}", id))
                .andExpect(status().isNoContent());

        verify(temporaryCompanyTitleService).deleteTemporaryCompanyTitle(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompanyTitle")
                .message("TemporaryCompanyTitle not found with id: " + id)
                .build())
                .given(temporaryCompanyTitleService).deleteTemporaryCompanyTitle(id);

        mockMvc.perform(delete("/api/temporary-company-title/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for TemporaryCompanyTitle.
     *
     * @return populated create request dto
     */
    private TemporaryCompanyTitleDto createValidCreateTemporaryCompanyTitleDto() {
        TemporaryCompanyTitleDto dto = new TemporaryCompanyTitleDto();
        dto.setVersion(new BigInteger("1"));
        dto.setOrderSeq(new BigInteger("1"));
        dto.setRecdeleted(new BigInteger("1"));

        return dto;
    }

}
