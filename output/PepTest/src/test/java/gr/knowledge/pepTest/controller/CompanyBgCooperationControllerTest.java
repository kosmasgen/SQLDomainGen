package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyBgCooperationDto;
import gr.knowledge.pepTest.service.CompanyBgCooperationService;
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
@WebMvcTest(CompanyBgCooperationController.class)
class CompanyBgCooperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyBgCooperationService companyBgCooperationService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyBgCooperationService.getAllCompanyBgCooperations()).willReturn(List.of(new CompanyBgCooperationDto()));

        mockMvc.perform(get("/api/company-bg-cooperation"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationService).getAllCompanyBgCooperations();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyBgCooperationService.getCompanyBgCooperationById(id)).willReturn(new CompanyBgCooperationDto());

        mockMvc.perform(get("/api/company-bg-cooperation/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationService).getCompanyBgCooperationById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyBgCooperationService.getCompanyBgCooperationById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyBgCooperation")
                        .message("CompanyBgCooperation not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-bg-cooperation/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyBgCooperationDto requestDto = createValidCreateCompanyBgCooperationDto();
        CompanyBgCooperationDto responseDto = new CompanyBgCooperationDto();
        given(companyBgCooperationService.createCompanyBgCooperation(any(CompanyBgCooperationDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-bg-cooperation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationService).createCompanyBgCooperation(any(CompanyBgCooperationDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyBgCooperationDto requestDto = createValidCreateCompanyBgCooperationDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/company-bg-cooperation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyBgCooperationDto requestDto = createValidCreateCompanyBgCooperationDto();
        given(companyBgCooperationService.createCompanyBgCooperation(any(CompanyBgCooperationDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyBgCooperation")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-bg-cooperation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyBgCooperationDto requestDto = new CompanyBgCooperationDto();
        CompanyBgCooperationDto responseDto = new CompanyBgCooperationDto();
        given(companyBgCooperationService.updateCompanyBgCooperation(eq(id), any(CompanyBgCooperationDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-bg-cooperation/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyBgCooperationService).updateCompanyBgCooperation(eq(id), any(CompanyBgCooperationDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyBgCooperationDto requestDto = new CompanyBgCooperationDto();
        given(companyBgCooperationService.updateCompanyBgCooperation(eq(id), any(CompanyBgCooperationDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyBgCooperation")
                        .message("CompanyBgCooperation not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-bg-cooperation/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyBgCooperationService).deleteCompanyBgCooperation(id);

        mockMvc.perform(delete("/api/company-bg-cooperation/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyBgCooperationService).deleteCompanyBgCooperation(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyBgCooperation")
                .message("CompanyBgCooperation not found with id: " + id)
                .build())
                .given(companyBgCooperationService).deleteCompanyBgCooperation(id);

        mockMvc.perform(delete("/api/company-bg-cooperation/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyBgCooperation.
     *
     * @return populated create request dto
     */
    private CompanyBgCooperationDto createValidCreateCompanyBgCooperationDto() {
        CompanyBgCooperationDto dto = new CompanyBgCooperationDto();
        dto.setCompany(new CompanyDto());
        dto.setCoopCompany(new CompanyDto());
        dto.setRecdeleted(true);

        return dto;
    }

}
