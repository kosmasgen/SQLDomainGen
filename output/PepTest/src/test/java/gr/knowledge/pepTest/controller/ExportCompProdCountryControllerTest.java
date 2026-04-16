package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ExportCompProdCountryDto;
import gr.knowledge.pepTest.service.ExportCompProdCountryService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CountryDto;
import gr.knowledge.pepTest.dto.ExportCompanyDto;
import gr.knowledge.pepTest.dto.ProductDto;

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
@WebMvcTest(ExportCompProdCountryController.class)
class ExportCompProdCountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExportCompProdCountryService exportCompProdCountryService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(exportCompProdCountryService.getAllExportCompProdCountries()).willReturn(List.of(new ExportCompProdCountryDto()));

        mockMvc.perform(get("/api/export-comp-prod-country"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompProdCountryService).getAllExportCompProdCountries();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(exportCompProdCountryService.getExportCompProdCountryById(id)).willReturn(new ExportCompProdCountryDto());

        mockMvc.perform(get("/api/export-comp-prod-country/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompProdCountryService).getExportCompProdCountryById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(exportCompProdCountryService.getExportCompProdCountryById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ExportCompProdCountry")
                        .message("ExportCompProdCountry not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/export-comp-prod-country/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ExportCompProdCountryDto requestDto = createValidCreateExportCompProdCountryDto();
        ExportCompProdCountryDto responseDto = new ExportCompProdCountryDto();
        given(exportCompProdCountryService.createExportCompProdCountry(any(ExportCompProdCountryDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/export-comp-prod-country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompProdCountryService).createExportCompProdCountry(any(ExportCompProdCountryDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        ExportCompProdCountryDto requestDto = createValidCreateExportCompProdCountryDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/export-comp-prod-country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ExportCompProdCountryDto requestDto = createValidCreateExportCompProdCountryDto();
        given(exportCompProdCountryService.createExportCompProdCountry(any(ExportCompProdCountryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ExportCompProdCountry")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/export-comp-prod-country")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompProdCountryDto requestDto = new ExportCompProdCountryDto();
        ExportCompProdCountryDto responseDto = new ExportCompProdCountryDto();
        given(exportCompProdCountryService.updateExportCompProdCountry(eq(id), any(ExportCompProdCountryDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/export-comp-prod-country/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(exportCompProdCountryService).updateExportCompProdCountry(eq(id), any(ExportCompProdCountryDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ExportCompProdCountryDto requestDto = new ExportCompProdCountryDto();
        given(exportCompProdCountryService.updateExportCompProdCountry(eq(id), any(ExportCompProdCountryDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ExportCompProdCountry")
                        .message("ExportCompProdCountry not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/export-comp-prod-country/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(exportCompProdCountryService).deleteExportCompProdCountry(id);

        mockMvc.perform(delete("/api/export-comp-prod-country/{id}", id))
                .andExpect(status().isNoContent());

        verify(exportCompProdCountryService).deleteExportCompProdCountry(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ExportCompProdCountry")
                .message("ExportCompProdCountry not found with id: " + id)
                .build())
                .given(exportCompProdCountryService).deleteExportCompProdCountry(id);

        mockMvc.perform(delete("/api/export-comp-prod-country/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ExportCompProdCountry.
     *
     * @return populated create request dto
     */
    private ExportCompProdCountryDto createValidCreateExportCompProdCountryDto() {
        ExportCompProdCountryDto dto = new ExportCompProdCountryDto();
        dto.setExportCompany(new ExportCompanyDto());
        dto.setCountry(new CountryDto());
        dto.setRecdeleted(true);
        dto.setExpYear(1);
        dto.setProduct(new ProductDto());

        return dto;
    }

}
