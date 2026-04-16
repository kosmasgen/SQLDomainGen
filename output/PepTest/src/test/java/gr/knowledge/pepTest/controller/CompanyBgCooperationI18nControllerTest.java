package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationI18nDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.dto.LanguagesDto;

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
@WebMvcTest(CompanyBgCooperationI18nController.class)
class CompanyBgCooperationI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyBgCooperationI18nService companyBgCooperationI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyBgCooperationI18nService.getAllCompanyBgCooperationI18ns()).willReturn(List.of(new CompanyBgCooperationI18nDto()));

        mockMvc.perform(get("/api/company-bg-cooperation-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationI18nService).getAllCompanyBgCooperationI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id)).willReturn(new CompanyBgCooperationI18nDto());

        mockMvc.perform(get("/api/company-bg-cooperation-i18n/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationI18nService).getCompanyBgCooperationI18nById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyBgCooperationI18nService.getCompanyBgCooperationI18nById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyBgCooperationI18n")
                        .message("CompanyBgCooperationI18n not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-bg-cooperation-i18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyBgCooperationI18nDto requestDto = createValidCreateCompanyBgCooperationI18nDto();
        CompanyBgCooperationI18nDto responseDto = new CompanyBgCooperationI18nDto();
        given(companyBgCooperationI18nService.createCompanyBgCooperationI18n(any(CompanyBgCooperationI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-bg-cooperation-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationI18nService).createCompanyBgCooperationI18n(any(CompanyBgCooperationI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyBgCooperationI18nDto requestDto = createValidCreateCompanyBgCooperationI18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/company-bg-cooperation-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyBgCooperationI18nDto requestDto = createValidCreateCompanyBgCooperationI18nDto();
        given(companyBgCooperationI18nService.createCompanyBgCooperationI18n(any(CompanyBgCooperationI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyBgCooperationI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-bg-cooperation-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyBgCooperationI18nDto requestDto = new CompanyBgCooperationI18nDto();
        CompanyBgCooperationI18nDto responseDto = new CompanyBgCooperationI18nDto();
        given(companyBgCooperationI18nService.updateCompanyBgCooperationI18n(eq(id), any(CompanyBgCooperationI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-bg-cooperation-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationI18nService).updateCompanyBgCooperationI18n(eq(id), any(CompanyBgCooperationI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyBgCooperationI18nDto requestDto = new CompanyBgCooperationI18nDto();
        given(companyBgCooperationI18nService.updateCompanyBgCooperationI18n(eq(id), any(CompanyBgCooperationI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyBgCooperationI18n")
                        .message("CompanyBgCooperationI18n not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-bg-cooperation-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyBgCooperationI18nService).deleteCompanyBgCooperationI18n(id);

        mockMvc.perform(delete("/api/company-bg-cooperation-i18n/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyBgCooperationI18nService).deleteCompanyBgCooperationI18n(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyBgCooperationI18n")
                .message("CompanyBgCooperationI18n not found with id: " + id)
                .build())
                .given(companyBgCooperationI18nService).deleteCompanyBgCooperationI18n(id);

        mockMvc.perform(delete("/api/company-bg-cooperation-i18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyBgCooperationI18n.
     *
     * @return populated create request dto
     */
    private CompanyBgCooperationI18nDto createValidCreateCompanyBgCooperationI18nDto() {
        CompanyBgCooperationI18nDto dto = new CompanyBgCooperationI18nDto();
        dto.setRecdeleted(true);
        dto.setCooperation(new CompanyBgCooperationDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

}
