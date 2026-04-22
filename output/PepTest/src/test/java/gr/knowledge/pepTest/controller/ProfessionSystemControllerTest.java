package gr.knowledge.pepTest.controller;

import gr.knowledge.pepTest.dto.ProfessionSystemDto;
import gr.knowledge.pepTest.service.ProfessionSystemService;
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
@WebMvcTest(ProfessionSystemController.class)
class ProfessionSystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProfessionSystemService professionSystemService;

    @Test
    void shouldReturnOkForGetAll() throws Exception {
        given(professionSystemService.getAllProfessionSystems()).willReturn(List.of(new ProfessionSystemDto()));

        mockMvc.perform(get("/api/profession-system"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionSystemService).getAllProfessionSystems();
    }

    @Test
    void shouldReturnOkForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionSystemService.getProfessionSystemById(id)).willReturn(new ProfessionSystemDto());

        mockMvc.perform(get("/api/profession-system/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionSystemService).getProfessionSystemById(id);
    }

    @Test
    void shouldReturnNotFoundForGetById() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        given(professionSystemService.getProfessionSystemById(id))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionSystem")
                        .message("ProfessionSystem not found with id: " + id)
                        .build());

        mockMvc.perform(get("/api/profession-system/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnCreatedForCreate() throws Exception {
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        ProfessionSystemDto responseDto = new ProfessionSystemDto();
        given(professionSystemService.createProfessionSystem(any(ProfessionSystemDto.class))).willReturn(responseDto);

        mockMvc.perform(post("/api/profession-system")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionSystemService).createProfessionSystem(any(ProfessionSystemDto.class));
    }

    @Test
    void shouldReturnUnprocessableEntityForCreateValidationFailure() throws Exception {
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        requestDto.setChamberId(null);

        mockMvc.perform(post("/api/profession-system")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnBadRequestForCreateWhenServiceThrows() throws Exception {
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        given(professionSystemService.createProfessionSystem(any(ProfessionSystemDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.BAD_REQUEST)
                        .entity("ProfessionSystem")
                        .message("Invalid payload")
                        .build());

        mockMvc.perform(post("/api/profession-system")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnprocessableEntityForPatchWhenValidationFails() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        requestDto.setChamberId(null);

        mockMvc.perform(patch("/api/profession-system/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void shouldReturnOkForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        ProfessionSystemDto responseDto = new ProfessionSystemDto();
        given(professionSystemService.updateProfessionSystem(eq(id), any(ProfessionSystemDto.class))).willReturn(responseDto);

        mockMvc.perform(patch("/api/profession-system/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(professionSystemService).updateProfessionSystem(eq(id), any(ProfessionSystemDto.class));
    }

    @Test
    void shouldReturnNotFoundForPatch() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        given(professionSystemService.updateProfessionSystem(eq(id), any(ProfessionSystemDto.class)))
                .willThrow(GeneratedRuntimeException.builder()
                        .code(ErrorCodes.NOT_FOUND)
                        .entity("ProfessionSystem")
                        .message("ProfessionSystem not found with id: " + id)
                        .build());

        mockMvc.perform(patch("/api/profession-system/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnInternalServerErrorForPatchWhenServiceThrowsUnexpectedException() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        ProfessionSystemDto requestDto = createValidCreateProfessionSystemDto();
        given(professionSystemService.updateProfessionSystem(eq(id), any(ProfessionSystemDto.class)))
                .willThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(patch("/api/profession-system/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnNoContentForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willDoNothing().given(professionSystemService).deleteProfessionSystem(id);

        mockMvc.perform(delete("/api/profession-system/{id}", id))
                .andExpect(status().isNoContent());

        verify(professionSystemService).deleteProfessionSystem(id);
    }

    @Test
    void shouldReturnNotFoundForDelete() throws Exception {
        UUID id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        willThrow(GeneratedRuntimeException.builder()
                .code(ErrorCodes.NOT_FOUND)
                .entity("ProfessionSystem")
                .message("ProfessionSystem not found with id: " + id)
                .build())
                .given(professionSystemService).deleteProfessionSystem(id);

        mockMvc.perform(delete("/api/profession-system/{id}", id))
                .andExpect(status().isNotFound());
    }

    /**
     * Creates a valid create request DTO for ProfessionSystem.
     *
     * @return populated create request dto
     */
    private ProfessionSystemDto createValidCreateProfessionSystemDto() {
        ProfessionSystemDto dto = new ProfessionSystemDto();
        dto.setChamberId(1);
        dto.setCd("A");
        dto.setDescription("A");
        dto.setRecdeleted(true);

        return dto;
    }

}
