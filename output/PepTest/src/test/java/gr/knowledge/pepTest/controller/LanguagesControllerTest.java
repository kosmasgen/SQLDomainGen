package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.LanguagesDto;
import gr.knowledge.pepTest.service.LanguagesService;
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
@WebMvcTest(LanguagesController.class)
class LanguagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private LanguagesService languagesService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(languagesService.getAllLanguageses()).willReturn(List.of(new LanguagesDto()));

        mockMvc.perform(get("/api/languages"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(languagesService).getAllLanguageses();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(languagesService.getLanguagesById(id)).willReturn(new LanguagesDto());

        mockMvc.perform(get("/api/languages/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(languagesService).getLanguagesById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(languagesService.getLanguagesById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Languages")
                        .message("Languages not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/languages/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        LanguagesDto responseDto = new LanguagesDto();
        given(languagesService.createLanguages(any(LanguagesDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/languages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(languagesService).createLanguages(any(LanguagesDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/languages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        given(languagesService.createLanguages(any(LanguagesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("Languages")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/languages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/languages/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        LanguagesDto responseDto = new LanguagesDto();
        given(languagesService.updateLanguages(eq(id), any(LanguagesDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/languages/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(languagesService).updateLanguages(eq(id), any(LanguagesDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        given(languagesService.updateLanguages(eq(id), any(LanguagesDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("Languages")
                        .message("Languages not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/languages/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        LanguagesDto requestDto = createValidCreateLanguagesDto();
        given(languagesService.updateLanguages(eq(id), any(LanguagesDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/languages/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(languagesService).deleteLanguages(id);

        mockMvc.perform(delete("/api/languages/{id}", id))
                .andExpect(status().isNoContent());

        verify(languagesService).deleteLanguages(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("Languages")
                .message("Languages not found with id: " + id)
                .build())
                .given(languagesService).deleteLanguages(id);

        mockMvc.perform(delete("/api/languages/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for Languages.
     *
     * @return populated create request dto
     */
    private LanguagesDto createValidCreateLanguagesDto() {
        LanguagesDto dto = new LanguagesDto();
        dto.setChamberId(1);
        dto.setCd("A");
        dto.setDescr("A");

        return dto;
    }

}
