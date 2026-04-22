package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionKindDto;
import gr.knowledge.pepTest.service.ProfessionKindService;
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
@WebMvcTest(ProfessionKindController.class)
class ProfessionKindControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfessionKindService professionKindService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professionKindService.getAllProfessionKinds()).willReturn(List.of(new ProfessionKindDto()));

        mockMvc.perform(get("/api/profession-kind"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindService).getAllProfessionKinds();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionKindService.getProfessionKindById(id)).willReturn(new ProfessionKindDto());

        mockMvc.perform(get("/api/profession-kind/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindService).getProfessionKindById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionKindService.getProfessionKindById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionKind")
                        .message("ProfessionKind not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/profession-kind/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        ProfessionKindDto responseDto = new ProfessionKindDto();
        given(professionKindService.createProfessionKind(any(ProfessionKindDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/profession-kind")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindService).createProfessionKind(any(ProfessionKindDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/profession-kind")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        given(professionKindService.createProfessionKind(any(ProfessionKindDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ProfessionKind")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/profession-kind")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/profession-kind/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        ProfessionKindDto responseDto = new ProfessionKindDto();
        given(professionKindService.updateProfessionKind(eq(id), any(ProfessionKindDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/profession-kind/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionKindService).updateProfessionKind(eq(id), any(ProfessionKindDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        given(professionKindService.updateProfessionKind(eq(id), any(ProfessionKindDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionKind")
                        .message("ProfessionKind not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/profession-kind/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionKindDto requestDto = createValidCreateProfessionKindDto();
        given(professionKindService.updateProfessionKind(eq(id), any(ProfessionKindDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/profession-kind/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(professionKindService).deleteProfessionKind(id);

        mockMvc.perform(delete("/api/profession-kind/{id}", id))
                .andExpect(status().isNoContent());

        verify(professionKindService).deleteProfessionKind(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionKind")
                .message("ProfessionKind not found with id: " + id)
                .build())
                .given(professionKindService).deleteProfessionKind(id);

        mockMvc.perform(delete("/api/profession-kind/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ProfessionKind.
     *
     * @return populated create request dto
     */
    private ProfessionKindDto createValidCreateProfessionKindDto() {
        ProfessionKindDto dto = new ProfessionKindDto();
        dto.setChamberId(1);
        dto.setCd("A");
        dto.setRecdeleted(true);

        return dto;
    }

}
