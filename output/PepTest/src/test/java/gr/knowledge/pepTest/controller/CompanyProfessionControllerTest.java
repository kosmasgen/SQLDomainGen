package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyProfessionDto;
import gr.knowledge.pepTest.service.CompanyProfessionService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;
import gr.knowledge.pepTest.dto.ProfessionDto;
import gr.knowledge.pepTest.dto.ProfessionKindDto;

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
@WebMvcTest(CompanyProfessionController.class)
class CompanyProfessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyProfessionService companyProfessionService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyProfessionService.getAllCompanyProfessions()).willReturn(List.of(new CompanyProfessionDto()));

        mockMvc.perform(get("/api/company-profession"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfessionService).getAllCompanyProfessions();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyProfessionService.getCompanyProfessionById(id)).willReturn(new CompanyProfessionDto());

        mockMvc.perform(get("/api/company-profession/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfessionService).getCompanyProfessionById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyProfessionService.getCompanyProfessionById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyProfession")
                        .message("CompanyProfession not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyProfessionDto requestDto = createValidCreateCompanyProfessionDto();
        CompanyProfessionDto responseDto = new CompanyProfessionDto();
        given(companyProfessionService.createCompanyProfession(any(CompanyProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfessionService).createCompanyProfession(any(CompanyProfessionDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CompanyProfessionDto requestDto = createValidCreateCompanyProfessionDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyProfessionDto requestDto = createValidCreateCompanyProfessionDto();
        given(companyProfessionService.createCompanyProfession(any(CompanyProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyProfession")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-profession")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyProfessionDto requestDto = new CompanyProfessionDto();
        CompanyProfessionDto responseDto = new CompanyProfessionDto();
        given(companyProfessionService.updateCompanyProfession(eq(id), any(CompanyProfessionDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyProfessionService).updateCompanyProfession(eq(id), any(CompanyProfessionDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyProfessionDto requestDto = new CompanyProfessionDto();
        given(companyProfessionService.updateCompanyProfession(eq(id), any(CompanyProfessionDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyProfession")
                        .message("CompanyProfession not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-profession/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyProfessionService).deleteCompanyProfession(id);

        mockMvc.perform(delete("/api/company-profession/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyProfessionService).deleteCompanyProfession(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyProfession")
                .message("CompanyProfession not found with id: " + id)
                .build())
                .given(companyProfessionService).deleteCompanyProfession(id);

        mockMvc.perform(delete("/api/company-profession/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyProfession.
     *
     * @return populated create request dto
     */
    private CompanyProfessionDto createValidCreateCompanyProfessionDto() {
        CompanyProfessionDto dto = new CompanyProfessionDto();
        dto.setChamberId(1);
        dto.setCompany(new CompanyDto());
        dto.setProfession(new ProfessionDto());
        dto.setProfessionKind(new ProfessionKindDto());
        dto.setRecdeleted(true);
        dto.setProfile(new CompanyProfileDto());

        return dto;
    }

}
