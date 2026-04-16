package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CountryI18nDto;
import gr.knowledge.pepTest.service.CountryI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CountryDto;
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
@WebMvcTest(CountryI18nController.class)
class CountryI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CountryI18nService countryI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(countryI18nService.getAllCountryI18ns()).willReturn(List.of(new CountryI18nDto()));

        mockMvc.perform(get("/api/country-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(countryI18nService).getAllCountryI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(countryI18nService.getCountryI18nById(countryId, languageId)).willReturn(new CountryI18nDto());

        mockMvc.perform(get("/api/country-i18n/{countryId}/{languageId}", countryId, languageId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(countryI18nService).getCountryI18nById(countryId, languageId);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        given(countryI18nService.getCountryI18nById(countryId, languageId))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CountryI18n")
                        .message("CountryI18n not found")
                        .build());

        mockMvc.perform(get("/api/country-i18n/{countryId}/{languageId}", countryId, languageId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CountryI18nDto requestDto = createValidCreateCountryI18nDto();
        CountryI18nDto responseDto = new CountryI18nDto();
        given(countryI18nService.createCountryI18n(any(CountryI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/country-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(countryI18nService).createCountryI18n(any(CountryI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateWhenValidationFails() throws Exception {
        CountryI18nDto requestDto = createValidCreateCountryI18nDto();
        requestDto.setDescription(null);

        mockMvc.perform(post("/api/country-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CountryI18nDto requestDto = createValidCreateCountryI18nDto();
        given(countryI18nService.createCountryI18n(any(CountryI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CountryI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/country-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CountryI18nDto requestDto = new CountryI18nDto();
        CountryI18nDto responseDto = new CountryI18nDto();
        given(countryI18nService.updateCountryI18n(eq(countryId), eq(languageId), any(CountryI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/country-i18n/{countryId}/{languageId}", countryId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(countryI18nService).updateCountryI18n(eq(countryId), eq(languageId), any(CountryI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        CountryI18nDto requestDto = new CountryI18nDto();
        given(countryI18nService.updateCountryI18n(eq(countryId), eq(languageId), any(CountryI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CountryI18n")
                        .message("CountryI18n not found")
                        .build());

        mockMvc.perform(patch("/api/country-i18n/{countryId}/{languageId}", countryId, languageId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willDoNothing().given(countryI18nService).deleteCountryI18n(countryId, languageId);

        mockMvc.perform(delete("/api/country-i18n/{countryId}/{languageId}", countryId, languageId))
                .andExpect(status().isNoContent());

        verify(countryI18nService).deleteCountryI18n(countryId, languageId);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID countryId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID languageId = UUID.fromString("223e4567-e89b-12d3-a456-426614174000");

        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CountryI18n")
                .message("CountryI18n not found")
                .build())
                .given(countryI18nService).deleteCountryI18n(countryId, languageId);

        mockMvc.perform(delete("/api/country-i18n/{countryId}/{languageId}", countryId, languageId))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CountryI18n.
     *
     * @return populated create request dto
     */
    private CountryI18nDto createValidCreateCountryI18nDto() {
        CountryI18nDto dto = new CountryI18nDto();
        dto.setCountry(new CountryDto());
        dto.setLanguage(new LanguagesDto());
        dto.setDescription("aaaaa");
        dto.setRecdeleted(true);

        return dto;
    }

}
