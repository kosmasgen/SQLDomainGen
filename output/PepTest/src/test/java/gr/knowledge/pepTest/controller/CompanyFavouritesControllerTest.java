package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyFavouritesDto;
import gr.knowledge.pepTest.service.CompanyFavouritesService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.CompanyDto;
import gr.knowledge.pepTest.dto.CompanyProfileDto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
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
@WebMvcTest(CompanyFavouritesController.class)
class CompanyFavouritesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyFavouritesService companyFavouritesService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyFavouritesService.getAllCompanyFavouriteses()).willReturn(List.of(new CompanyFavouritesDto()));

        mockMvc.perform(get("/api/company-favourites"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFavouritesService).getAllCompanyFavouriteses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyFavouritesService.getCompanyFavouritesById(id)).willReturn(new CompanyFavouritesDto());

        mockMvc.perform(get("/api/company-favourites/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFavouritesService).getCompanyFavouritesById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyFavouritesService.getCompanyFavouritesById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyFavourites")
                        .message("CompanyFavourites not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-favourites/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        CompanyFavouritesDto responseDto = new CompanyFavouritesDto();
        given(companyFavouritesService.createCompanyFavourites(any(CompanyFavouritesDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFavouritesService).createCompanyFavourites(any(CompanyFavouritesDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        requestDto.setCompany(null);

        mockMvc.perform(post("/api/company-favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        given(companyFavouritesService.createCompanyFavourites(any(CompanyFavouritesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyFavourites")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-favourites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        requestDto.setCompany(null);

        mockMvc.perform(patch("/api/company-favourites/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        CompanyFavouritesDto responseDto = new CompanyFavouritesDto();
        given(companyFavouritesService.updateCompanyFavourites(eq(id), any(CompanyFavouritesDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-favourites/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyFavouritesService).updateCompanyFavourites(eq(id), any(CompanyFavouritesDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        given(companyFavouritesService.updateCompanyFavourites(eq(id), any(CompanyFavouritesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyFavourites")
                        .message("CompanyFavourites not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-favourites/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyFavouritesDto requestDto = createValidCreateCompanyFavouritesDto();
        given(companyFavouritesService.updateCompanyFavourites(eq(id), any(CompanyFavouritesDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-favourites/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyFavouritesService).deleteCompanyFavourites(id);

        mockMvc.perform(delete("/api/company-favourites/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyFavouritesService).deleteCompanyFavourites(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyFavourites")
                .message("CompanyFavourites not found with id: " + id)
                .build())
                .given(companyFavouritesService).deleteCompanyFavourites(id);

        mockMvc.perform(delete("/api/company-favourites/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyFavourites.
     *
     * @return populated create request dto
     */
    private CompanyFavouritesDto createValidCreateCompanyFavouritesDto() {
        CompanyFavouritesDto dto = new CompanyFavouritesDto();
        dto.setCompany(new CompanyDto());
        dto.setFavouriteCompany(new CompanyDto());

        return dto;
    }

}
