package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.CompanyContactMessageDto;
import gr.knowledge.pepTest.service.CompanyContactMessageService;
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
@WebMvcTest(CompanyContactMessageController.class)
class CompanyContactMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CompanyContactMessageService companyContactMessageService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(companyContactMessageService.getAllCompanyContactMessages()).willReturn(List.of(new CompanyContactMessageDto()));

        mockMvc.perform(get("/api/company-contact-message"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyContactMessageService).getAllCompanyContactMessages();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyContactMessageService.getCompanyContactMessageById(id)).willReturn(new CompanyContactMessageDto());

        mockMvc.perform(get("/api/company-contact-message/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyContactMessageService).getCompanyContactMessageById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(companyContactMessageService.getCompanyContactMessageById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyContactMessage")
                        .message("CompanyContactMessage not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/company-contact-message/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        CompanyContactMessageDto requestDto = createValidCreateCompanyContactMessageDto();
        CompanyContactMessageDto responseDto = new CompanyContactMessageDto();
        given(companyContactMessageService.createCompanyContactMessage(any(CompanyContactMessageDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/company-contact-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyContactMessageService).createCompanyContactMessage(any(CompanyContactMessageDto.class));
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        CompanyContactMessageDto requestDto = createValidCreateCompanyContactMessageDto();
        given(companyContactMessageService.createCompanyContactMessage(any(CompanyContactMessageDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("CompanyContactMessage")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/company-contact-message")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyContactMessageDto requestDto = createValidCreateCompanyContactMessageDto();
        CompanyContactMessageDto responseDto = new CompanyContactMessageDto();
        given(companyContactMessageService.updateCompanyContactMessage(eq(id), any(CompanyContactMessageDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/company-contact-message/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(companyContactMessageService).updateCompanyContactMessage(eq(id), any(CompanyContactMessageDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyContactMessageDto requestDto = createValidCreateCompanyContactMessageDto();
        given(companyContactMessageService.updateCompanyContactMessage(eq(id), any(CompanyContactMessageDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("CompanyContactMessage")
                        .message("CompanyContactMessage not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/company-contact-message/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        CompanyContactMessageDto requestDto = createValidCreateCompanyContactMessageDto();
        given(companyContactMessageService.updateCompanyContactMessage(eq(id), any(CompanyContactMessageDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/company-contact-message/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(companyContactMessageService).deleteCompanyContactMessage(id);

        mockMvc.perform(delete("/api/company-contact-message/{id}", id))
                .andExpect(status().isNoContent());

        verify(companyContactMessageService).deleteCompanyContactMessage(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("CompanyContactMessage")
                .message("CompanyContactMessage not found with id: " + id)
                .build())
                .given(companyContactMessageService).deleteCompanyContactMessage(id);

        mockMvc.perform(delete("/api/company-contact-message/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for CompanyContactMessage.
     *
     * @return populated create request dto
     */
    private CompanyContactMessageDto createValidCreateCompanyContactMessageDto() {
        return new CompanyContactMessageDto();
    }

}
