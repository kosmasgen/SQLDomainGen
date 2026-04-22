package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyTitleDto;
import gr.knowledge.pepTest.service.CompanyTitleService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;

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
@WebMvcTest(CompanyTitleController.class)
class CompanyTitleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyTitleService companyTitleService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyTitleService.getAllCompanyTitles()).willReturn(List.of(new CompanyTitleDto()));

        mockMvc.perform(get("/api/company-title"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitleService).getAllCompanyTitles();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyTitleService.getCompanyTitleById(id)).willReturn(new CompanyTitleDto());

        mockMvc.perform(get("/api/company-title/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitleService).getCompanyTitleById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyTitleService.getCompanyTitleById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyTitle")
                        .message("CompanyTitle not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-title/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        CompanyTitleDto responseDto = new CompanyTitleDto();
        given(companyTitleService.createCompanyTitle(any(CompanyTitleDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitleService).createCompanyTitle(any(CompanyTitleDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        given(companyTitleService.createCompanyTitle(any(CompanyTitleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyTitle")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-title")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        CompanyTitleDto responseDto = new CompanyTitleDto();
        given(companyTitleService.updateCompanyTitle(eq(id), any(CompanyTitleDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyTitleService).updateCompanyTitle(eq(id), any(CompanyTitleDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        given(companyTitleService.updateCompanyTitle(eq(id), any(CompanyTitleDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyTitle")
                        .message("CompanyTitle not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyTitleDto requestDto = createValidCreateCompanyTitleDto();
        given(companyTitleService.updateCompanyTitle(eq(id), any(CompanyTitleDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-title/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyTitleService).deleteCompanyTitle(id);

        mockMvc.perform(delete("/api/company-title/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyTitleService).deleteCompanyTitle(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyTitle")
                .message("CompanyTitle not found with id: " + id)
                .build())
                .given(companyTitleService).deleteCompanyTitle(id);

        mockMvc.perform(delete("/api/company-title/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyTitle.
     *
     * @return populated create request dto
     */
    private CompanyTitleDto createValidCreateCompanyTitleDto() {
        CompanyTitleDto dto = new CompanyTitleDto();
        dto.setChamberId(1);
        dto.setCompany(new CompanyDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
