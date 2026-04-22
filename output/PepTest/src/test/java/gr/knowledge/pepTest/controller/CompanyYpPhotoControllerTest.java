package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyYpPhotoDto;
import gr.knowledge.pepTest.service.CompanyYpPhotoService;
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
@WebMvcTest(CompanyYpPhotoController.class)
class CompanyYpPhotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyYpPhotoService companyYpPhotoService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyYpPhotoService.getAllCompanyYpPhotos()).willReturn(List.of(new CompanyYpPhotoDto()));

        mockMvc.perform(get("/api/company-yp-photo"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpPhotoService).getAllCompanyYpPhotos();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpPhotoService.getCompanyYpPhotoById(id)).willReturn(new CompanyYpPhotoDto());

        mockMvc.perform(get("/api/company-yp-photo/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpPhotoService).getCompanyYpPhotoById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyYpPhotoService.getCompanyYpPhotoById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpPhoto")
                        .message("CompanyYpPhoto not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-yp-photo/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        CompanyYpPhotoDto responseDto = new CompanyYpPhotoDto();
        given(companyYpPhotoService.createCompanyYpPhoto(any(CompanyYpPhotoDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-yp-photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpPhotoService).createCompanyYpPhoto(any(CompanyYpPhotoDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/company-yp-photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        given(companyYpPhotoService.createCompanyYpPhoto(any(CompanyYpPhotoDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyYpPhoto")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-yp-photo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/company-yp-photo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        CompanyYpPhotoDto responseDto = new CompanyYpPhotoDto();
        given(companyYpPhotoService.updateCompanyYpPhoto(eq(id), any(CompanyYpPhotoDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-yp-photo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyYpPhotoService).updateCompanyYpPhoto(eq(id), any(CompanyYpPhotoDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        given(companyYpPhotoService.updateCompanyYpPhoto(eq(id), any(CompanyYpPhotoDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyYpPhoto")
                        .message("CompanyYpPhoto not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-yp-photo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyYpPhotoDto requestDto = createValidCreateCompanyYpPhotoDto();
        given(companyYpPhotoService.updateCompanyYpPhoto(eq(id), any(CompanyYpPhotoDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-yp-photo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyYpPhotoService).deleteCompanyYpPhoto(id);

        mockMvc.perform(delete("/api/company-yp-photo/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyYpPhotoService).deleteCompanyYpPhoto(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyYpPhoto")
                .message("CompanyYpPhoto not found with id: " + id)
                .build())
                .given(companyYpPhotoService).deleteCompanyYpPhoto(id);

        mockMvc.perform(delete("/api/company-yp-photo/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyYpPhoto.
     *
     * @return populated create request dto
     */
    private CompanyYpPhotoDto createValidCreateCompanyYpPhotoDto() {
        CompanyYpPhotoDto dto = new CompanyYpPhotoDto();
        dto.setChamberId(1);
        dto.setCompany(new CompanyDto());
        dto.setFileName("A");
        dto.setMimeType("A");
        dto.setFileSize(1);
        dto.setOrderSeq(1);
        dto.setRecdeleted(true);
        dto.setBlobUri("A");

        return dto;
    }

}
