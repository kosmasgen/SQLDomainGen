package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.BgPoiI18nDto;
import gr.knowledge.pepTest.service.BgPoiI18nService;
import gr.knowledge.pepTest.exception.ErrorCodes;
import gr.knowledge.pepTest.exception.GeneratedRuntimeException;
import gr.knowledge.pepTest.dto.BgPoiDto;
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
@WebMvcTest(BgPoiI18nController.class)
class BgPoiI18nControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BgPoiI18nService bgPoiI18nService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(bgPoiI18nService.getAllBgPoiI18ns()).willReturn(List.of(new BgPoiI18nDto()));

        mockMvc.perform(get("/api/bg-poi-i18n"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(bgPoiI18nService).getAllBgPoiI18ns();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(bgPoiI18nService.getBgPoiI18nById(id)).willReturn(new BgPoiI18nDto());

        mockMvc.perform(get("/api/bg-poi-i18n/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(bgPoiI18nService).getBgPoiI18nById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(bgPoiI18nService.getBgPoiI18nById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("BgPoiI18n")
                        .message("BgPoiI18n not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/bg-poi-i18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        BgPoiI18nDto responseDto = new BgPoiI18nDto();
        given(bgPoiI18nService.createBgPoiI18n(any(BgPoiI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/bg-poi-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(bgPoiI18nService).createBgPoiI18n(any(BgPoiI18nDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(post("/api/bg-poi-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        given(bgPoiI18nService.createBgPoiI18n(any(BgPoiI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("BgPoiI18n")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/bg-poi-i18n")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        requestDto.setRecdeleted(null);

        mockMvc.perform(patch("/api/bg-poi-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        BgPoiI18nDto responseDto = new BgPoiI18nDto();
        given(bgPoiI18nService.updateBgPoiI18n(eq(id), any(BgPoiI18nDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/bg-poi-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(bgPoiI18nService).updateBgPoiI18n(eq(id), any(BgPoiI18nDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        given(bgPoiI18nService.updateBgPoiI18n(eq(id), any(BgPoiI18nDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("BgPoiI18n")
                        .message("BgPoiI18n not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/bg-poi-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        BgPoiI18nDto requestDto = createValidCreateBgPoiI18nDto();
        given(bgPoiI18nService.updateBgPoiI18n(eq(id), any(BgPoiI18nDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/bg-poi-i18n/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(bgPoiI18nService).deleteBgPoiI18n(id);

        mockMvc.perform(delete("/api/bg-poi-i18n/{id}", id))
                .andExpect(status().isNoContent());

        verify(bgPoiI18nService).deleteBgPoiI18n(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("BgPoiI18n")
                .message("BgPoiI18n not found with id: " + id)
                .build())
                .given(bgPoiI18nService).deleteBgPoiI18n(id);

        mockMvc.perform(delete("/api/bg-poi-i18n/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for BgPoiI18n.
     *
     * @return populated create request dto
     */
    private BgPoiI18nDto createValidCreateBgPoiI18nDto() {
        BgPoiI18nDto dto = new BgPoiI18nDto();
        dto.setRecdeleted(true);
        dto.setTitle("A");
        dto.setPoi(new BgPoiDto());
        dto.setLanguage(new LanguagesDto());

        return dto;
    }

}
