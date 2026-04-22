package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.TemporaryCompanyDto;
import gr.knowledge.pepTest.service.TemporaryCompanyService;
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
@WebMvcTest(TemporaryCompanyController.class)
class TemporaryCompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TemporaryCompanyService temporaryCompanyService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(temporaryCompanyService.getAllTemporaryCompanies()).willReturn(List.of(new TemporaryCompanyDto()));

        mockMvc.perform(get("/api/temporary-company"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyService).getAllTemporaryCompanies();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyService.getTemporaryCompanyById(id)).willReturn(new TemporaryCompanyDto());

        mockMvc.perform(get("/api/temporary-company/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyService).getTemporaryCompanyById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        BigInteger id = new BigInteger("1");
        given(temporaryCompanyService.getTemporaryCompanyById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompany")
                        .message("TemporaryCompany not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/temporary-company/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        TemporaryCompanyDto responseDto = new TemporaryCompanyDto();
        given(temporaryCompanyService.createTemporaryCompany(any(TemporaryCompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/temporary-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyService).createTemporaryCompany(any(TemporaryCompanyDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        requestDto.setVersion(null);

        mockMvc.perform(post("/api/temporary-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        given(temporaryCompanyService.createTemporaryCompany(any(TemporaryCompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("TemporaryCompany")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/temporary-company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        requestDto.setVersion(null);

        mockMvc.perform(patch("/api/temporary-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        TemporaryCompanyDto responseDto = new TemporaryCompanyDto();
        given(temporaryCompanyService.updateTemporaryCompany(eq(id), any(TemporaryCompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/temporary-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(temporaryCompanyService).updateTemporaryCompany(eq(id), any(TemporaryCompanyDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        given(temporaryCompanyService.updateTemporaryCompany(eq(id), any(TemporaryCompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("TemporaryCompany")
                        .message("TemporaryCompany not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/temporary-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        BigInteger id = new BigInteger("1");
        TemporaryCompanyDto requestDto = createValidCreateTemporaryCompanyDto();
        given(temporaryCompanyService.updateTemporaryCompany(eq(id), any(TemporaryCompanyDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/temporary-company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willDoNothing().given(temporaryCompanyService).deleteTemporaryCompany(id);

        mockMvc.perform(delete("/api/temporary-company/{id}", id))
                .andExpect(status().isNoContent());

        verify(temporaryCompanyService).deleteTemporaryCompany(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        BigInteger id = new BigInteger("1");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("TemporaryCompany")
                .message("TemporaryCompany not found with id: " + id)
                .build())
                .given(temporaryCompanyService).deleteTemporaryCompany(id);

        mockMvc.perform(delete("/api/temporary-company/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for TemporaryCompany.
     *
     * @return populated create request dto
     */
    private TemporaryCompanyDto createValidCreateTemporaryCompanyDto() {
        TemporaryCompanyDto dto = new TemporaryCompanyDto();
        dto.setVersion(new BigInteger("1"));
        dto.setCd(new BigInteger("1"));
        dto.setChamberRegisteredId(new BigInteger("1"));
        dto.setCoName("A");
        dto.setCorporateStatusId(new BigInteger("1"));
        dto.setMailAddress(new BigInteger("1"));
        dto.setRecdeleted(new BigInteger("1"));
        dto.setGemiCreated(new BigInteger("1"));
        dto.setPrintKatastFlag(new BigInteger("1"));
        dto.setShowBusinessGuide(new BigInteger("1"));

        return dto;
    }

}
