package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.service.CompanyService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.BusinessLocationDto;
import gr.knowledge.pepTest.dto.ChamberDepartmentDto;
import gr.knowledge.pepTest.dto.CompanyStatusDto;
import gr.knowledge.pepTest.dto.CorporateStatusDto;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.dto.MunicipalityDto;

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
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyService companyService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyService.getAllCompanies()).willReturn(List.of(new CompanyDto()));

        mockMvc.perform(get("/api/company"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyService).getAllCompanies();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyService.getCompanyById(id)).willReturn(new CompanyDto());

        mockMvc.perform(get("/api/company/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyService).getCompanyById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyService.getCompanyById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Company")
                        .message("Company not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyDto requestDto = createValidCreateCompanyDto();
        CompanyDto responseDto = new CompanyDto();
        given(companyService.createCompany(any(CompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyService).createCompany(any(CompanyDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyDto requestDto = createValidCreateCompanyDto();
        requestDto.setCoName(null);

        mockMvc.perform(post("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyDto requestDto = createValidCreateCompanyDto();
        given(companyService.createCompany(any(CompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Company")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto requestDto = new CompanyDto();
        CompanyDto responseDto = new CompanyDto();
        given(companyService.updateCompany(eq(id), any(CompanyDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyService).updateCompany(eq(id), any(CompanyDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyDto requestDto = new CompanyDto();
        given(companyService.updateCompany(eq(id), any(CompanyDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Company")
                        .message("Company not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyService).deleteCompany(id);

        mockMvc.perform(delete("/api/company/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyService).deleteCompany(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Company")
                .message("Company not found with id: " + id)
                .build())
                .given(companyService).deleteCompany(id);

        mockMvc.perform(delete("/api/company/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Company.
     *
     * @return populated create request dto
     */
    private CompanyDto createValidCreateCompanyDto() {
        CompanyDto dto = new CompanyDto();
        dto.setCoName("aaaaa");
        dto.setChamberId(1);
        dto.setAddressMunicipalityPri(new MunicipalityDto());
        dto.setChamberDepartment(new ChamberDepartmentDto());
        dto.setCompanyStatus(new CompanyStatusDto());
        dto.setCorporateStatus(new CorporateStatusDto());
        dto.setIsChamberCompany(true);
        dto.setIsTradesCompany(true);
        dto.setShowBusinessGuide(true);
        dto.setBusinessLocation(new BusinessLocationDto());
        dto.setAddressCountry(new CountryDto());

        return dto;
    }

}
